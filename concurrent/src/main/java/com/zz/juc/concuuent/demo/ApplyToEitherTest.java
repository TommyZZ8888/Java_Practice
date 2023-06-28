package com.zz.juc.concuuent.demo;

import com.zz.juc.utils.MyThreadFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description ApplyToEitherTest
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class ApplyToEitherTest {

    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(5),
            MyThreadFactory.create("applyToEitherTest"),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    /**
     * 定时器线程，专门给fastfail使用
     */
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newScheduledThreadPool(
            1,
            MyThreadFactory.create("applyToEitherTest"));

    /**
     * 构造一个指定时间后抛出异常的future
     *
     * @param timeOut
     * @param timeUnit
     * @param <T>
     * @return
     */
    private static <T> CompletableFuture<T> fastFail(long timeOut, TimeUnit timeUnit) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        SCHEDULED_EXECUTOR.schedule(() -> {
            final TimeoutException ex = new TimeoutException("超时啦" + timeOut);
            return future.completeExceptionally(ex);
        }, timeOut, timeUnit);
        return future;
    }


    private static CompletableFuture<String> query3rdService(String serviceName) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        int t = ThreadLocalRandom.current().nextInt(5);
                        System.out.println(Thread.currentThread().getName() + "请求超时 " + serviceName + " 超时时间 " + t);
                        TimeUnit.SECONDS.sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return serviceName;
                }, POOL_EXECUTOR)
                //applyToEither: 最快返回输出的线程结果作为下一次任务的输入
                // 这里理解：模拟三方服务为futureA，fastfail为futureB，则这个方法返回a和b更快的哪一个执行结果去执行下步任务
                // futureB 是必然的一个结果：timeout时间之后抛出一个超时异常
                // 则futureA如果在timeout之前返回，则applyToEither会返回futureA的结果
                // 否则： 返回futureB的结果
                .applyToEitherAsync(fastFail(2000, TimeUnit.MILLISECONDS), Function.identity()).exceptionally(e -> "error");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //模拟6个请求三方服务的future
        List<CompletableFuture<String>> collect = IntStream.range(0, 6).mapToObj(item -> query3rdService("service" + item)).collect(Collectors.toList());
        //并发执行
        collect.forEach(CompletableFuture::join);

        for (CompletableFuture<String> completableFuture : collect) {
            System.out.println(completableFuture.get());
        }
    }
}

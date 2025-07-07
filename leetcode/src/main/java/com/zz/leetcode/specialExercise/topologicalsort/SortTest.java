package com.zz.leetcode.specialExercise.topologicalsort;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Describtion: ListTest
 * @Author: 张卫刚
 * @Date: 2024/3/30 16:25
 */
@Slf4j
public class SortTest {
    public static void main(String[] args) throws InterruptedException {

        testLeetcode();
    }

    public static void test(String param){
      String s = "hello";
      String b = "he"+new String("llo");

      System.out.println(s.equals(b));
    }

    private static  void testLeetcode() {
        int numCourses = 4;
        int[][] prerequisites = {{0,1},{0,2},{1,3},{2,3}};
        int[] inDegree = new int[numCourses];

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[0]).add(prerequisite[1]);
            inDegree[prerequisite[1]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            result.add(poll);

            List<Integer> integers = adjList.get(poll);
            for (Integer integer : integers) {
                inDegree[integer]--;
                if (inDegree[integer] == 0) {
                    queue.offer(integer);
                }
            }
        }
        if (result.size() == numCourses){
            System.out.println(Arrays.toString(result.toArray()));
        }

    }
}

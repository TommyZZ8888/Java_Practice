package com.zz.leetcode.specialExercise.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Describtion: TopologicalSort Kahn 算法实现
 * @Author: 张卫刚
 * @Date: 2025/6/26 8:59
 */
public class TopologicalSort {

	private int vertices;
	private List<List<Integer>> adjList;
	private int[] inDegree;

	public TopologicalSort(int vertices) {
		this.vertices = vertices;
		adjList = new ArrayList<>(vertices);
		inDegree = new int[vertices];

		for (int i = 0; i < vertices; i++) {
			adjList.add(new ArrayList<>());
		}
	}

	// 添加边 u -> v
	public void addEdge(int u, int v) {
		adjList.get(u).add(v);
		inDegree[v]++;
	}

	// Kahn 算法实现拓扑排序
	public List<Integer> topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		// 将入度为 0 的节点加入队列
		for (int i = 0; i < vertices; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int u = queue.poll();
			result.add(u);

			// 遍历当前节点的所有邻接点
			for (int v : adjList.get(u)) {
				inDegree[v]--; // 减少邻接点的入度
				if (inDegree[v] == 0) {
					queue.offer(v); // 入度为 0 则入队
				}
			}
		}

		// 检查是否所有顶点都被访问过（是否存在环）
		if (result.size() != vertices) {
			System.out.println("图中存在环，无法进行拓扑排序");
			return new ArrayList<>();
		}

		return result;
	}

	public static void main(String[] args) {
		// 创建有向无环图
		TopologicalSort graph = new TopologicalSort(6);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);

		System.out.println("使用 Kahn 算法进行拓扑排序的结果：");
		List<Integer> order = graph.topologicalSort();
		if (!order.isEmpty()) {
			System.out.println(order); // 输出类似 [5, 4, 2, 3, 1, 0]
		}
	}



	/**
	 * 拓扑排序实现
	 * @param numCourses 节点总数(课程数)
	 * @param prerequisites 边集合，每个int[]表示[后继节点, 前驱节点]
	 * @return 拓扑排序结果，如果存在环则返回空列表
	 */
//	public static List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
//		// 1. 构建邻接表(图的表示)
//		List<Integer>[] graph = new ArrayList[numCourses];
//		for (int i = 0; i < numCourses; i++) {
//			graph[i] = new ArrayList<>();
//		}
//
//		// 2. 构建入度数组
//		int[] inDegree = new int[numCourses];
//
//		// 3. 填充邻接表和入度数组
//		for (int[] edge : prerequisites) {
//			int from = edge[1];  // 前驱节点
//			int to = edge[0];    // 后继节点
//			graph[from].add(to); // 添加边
//			inDegree[to]++;      // 后继节点入度+1
//		}
//
//		// 4. 初始化队列(入度为0的节点)
//		Queue<Integer> queue = new LinkedList<>();
//		for (int i = 0; i < numCourses; i++) {
//			if (inDegree[i] == 0) {
//				queue.offer(i);
//			}
//		}
//
//		// 5. 执行拓扑排序
//		List<Integer> result = new ArrayList<>();
//		while (!queue.isEmpty()) {
//			int node = queue.poll();
//			result.add(node);
//
//			// 遍历当前节点的所有邻居
//			for (int neighbor : graph[node]) {
//				// 邻居节点入度-1
//				if (--inDegree[neighbor] == 0) {
//					queue.offer(neighbor);
//				}
//			}
//		}
//
//		// 6. 检查是否有环(结果是否包含所有节点)
//		return result.size() == numCourses ? result : Collections.emptyList();
//	}
//
//	public static void main(String[] args) {
//		// 示例：课程安排
//		int numCourses = 4;
//		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}}; // 0→1, 0→2, 1→3, 2→3
//
//		List<Integer> sortedOrder = topologicalSort(numCourses, prerequisites);
//
//		if (sortedOrder.isEmpty()) {
//			System.out.println("图中存在循环依赖，无法拓扑排序");
//		} else {
//			System.out.println("拓扑排序结果: " + sortedOrder);
//			// 输出: [0, 1, 2, 3] 或 [0, 2, 1, 3]
//		}
//	}
}

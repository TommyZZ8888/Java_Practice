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
}

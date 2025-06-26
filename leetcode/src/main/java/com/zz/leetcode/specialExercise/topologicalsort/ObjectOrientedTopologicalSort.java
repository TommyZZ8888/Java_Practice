package com.zz.leetcode.specialExercise.topologicalsort;

import java.util.*;

/**
 * @Describtion: TopologicalSort Kahn 算法实现 面向对象实现
 * @Author: 张卫刚
 * @Date: 2025/6/26 8:59
 */
class Node {
	String id;
	List<Node> neighbors;

	public Node(String id) {
		this.id = id;
		this.neighbors = new ArrayList<>();
	}

	public void addNeighbor(Node node) {
		neighbors.add(node);
	}
}

public class ObjectOrientedTopologicalSort {

	public static List<Node> topologicalSort(List<Node> nodes) {
		// 1. 计算入度
		Map<Node, Integer> inDegree = new HashMap<>();
		for (Node node : nodes) {
			inDegree.putIfAbsent(node, 0);
			for (Node neighbor : node.neighbors) {
				inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
			}
		}

		// 2. 初始化队列(入度为0的节点)
		Queue<Node> queue = new LinkedList<>();
		for (Node node : nodes) {
			if (inDegree.get(node) == 0) {
				queue.add(node);
			}
		}

		// 3. 执行拓扑排序
		List<Node> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			result.add(current);

			for (Node neighbor : current.neighbors) {
				inDegree.put(neighbor, inDegree.get(neighbor) - 1);
				if (inDegree.get(neighbor) == 0) {
					queue.add(neighbor);
				}
			}
		}

		// 4. 检查是否有环
		return result.size() == nodes.size() ? result : Collections.emptyList();
	}

	public static void main(String[] args) {
		// 创建节点
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");

		// 建立依赖关系
		a.addNeighbor(b); // A → B
		a.addNeighbor(c); // A → C
		b.addNeighbor(d); // B → D
		c.addNeighbor(d); // C → D

		List<Node> graph = Arrays.asList(a, b, c, d);
		List<Node> sorted = topologicalSort(graph);

		if (sorted.isEmpty()) {
			System.out.println("存在循环依赖");
		} else {
			System.out.println("拓扑排序结果:");
			sorted.forEach(node -> System.out.print(node.id + " "));
			// 输出: A B C D 或 A C B D
		}
	}
}

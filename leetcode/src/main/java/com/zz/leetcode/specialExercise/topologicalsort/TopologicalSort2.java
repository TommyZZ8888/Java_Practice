package com.zz.leetcode.specialExercise.topologicalsort;

import java.util.*;

/**
 * @Describtion: TopologicalSort Kahn 算法实现
 * @Author: 张卫刚
 * @Date: 2025/6/26 8:59
 */
public class TopologicalSort2 {

	/**
	 * 拓扑排序实现
	 * @param numCourses 节点总数(课程数)
	 * @param prerequisites 边集合，每个int[]表示[后继节点, 前驱节点]
	 * @return 拓扑排序结果，如果存在环则返回空列表
	 *       0
	 *     /   \
	 *    1     2
	 *     \   /
	 *       3
	 */
	public static List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
		// 1. 构建邻接表(图的表示)
//		graph = [
//          [], // 0
//          [], // 1
//          [], // 2
//          []  // 3
//          ]
		List<Integer>[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<>();
		}

		// 2. 构建入度数组
		int[] inDegree = new int[numCourses];

		// 3. 填充邻接表和入度数组
//		[
//		    [1, 2], // 0 → 1, 0 → 2
//		    [3],    // 1 → 3
//		    [3],    // 2 → 3
//		    []      // 3 无后继
//		]
		for (int[] edge : prerequisites) {
			int from = edge[1];  // 前驱节点
			int to = edge[0];    // 后继节点
			graph[from].add(to); // 添加边
			inDegree[to]++;      // 后继节点入度+1
		}

		// 4. 初始化队列(入度为0的节点)
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		// 5. 执行拓扑排序
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int node = queue.poll();
			result.add(node);

			// 遍历当前节点的所有邻居
			for (int neighbor : graph[node]) {
				// 邻居节点入度-1
				if (--inDegree[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
		}

		// 6. 检查是否有环(结果是否包含所有节点)
		return result.size() == numCourses ? result : Collections.emptyList();
	}

	public static void main(String[] args) {
		// 示例：课程安排
//		先修关系：[[1,0],[2,0],[3,1],[3,2]]
//              [1,0] 表示课程1需要先修课程0
//				[2,0] 表示课程2需要先修课程0
//				[3,1] 表示课程3需要先修课程1
//				[3,2] 表示课程3需要先修课程2
		int numCourses = 4;
		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}}; // 0→1, 0→2, 1→3, 2→3

		List<Integer> sortedOrder = topologicalSort(numCourses, prerequisites);

		if (sortedOrder.isEmpty()) {
			System.out.println("图中存在循环依赖，无法拓扑排序");
		} else {
			System.out.println("拓扑排序结果: " + sortedOrder);
			// 输出: [0, 1, 2, 3] 或 [0, 2, 1, 3]
		}
	}
}

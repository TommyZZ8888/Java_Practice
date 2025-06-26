package com.zz.leetcode.specialExercise.topologicalsort.projectTopo;

import java.util.*;

/**
 * @Describtion: ProjectTopologicalSort
 * @Author: 张卫刚
 * @Date: 2025/6/26 9:24
 */
public class ProjectTopologicalSort {

	public static void main(String[] args) {
		// 1. 创建测试节点
		DataModelNode nodeA = new DataModelNode();
		nodeA.setId("A");
		nodeA.setNodeKey("DATA_TABLE");

		DataModelNode nodeB = new DataModelNode();
		nodeB.setId("B");
		nodeB.setNodeKey("DATA_TABLE");

		DataModelNode nodeC = new DataModelNode();
		nodeC.setId("C");
		nodeC.setNodeKey("TABLE_JOIN");

		DataModelNode nodeD = new DataModelNode();
		nodeD.setId("D");
		nodeD.setNodeKey("DATA_SAVE");

		// 2. 创建边关系
		List<DataModelEdge> edges = new ArrayList<>();
		edges.add(createEdge("1", "A", "C"));  // A → C
		edges.add(createEdge("2", "B", "C"));  // B → C
		edges.add(createEdge("3", "C", "D"));  // C → D

		// 3. 创建节点列表
		List<DataModelNode> nodes = new ArrayList<>();
		nodes.add(nodeA);
		nodes.add(nodeB);
		nodes.add(nodeC);
		nodes.add(nodeD);

		// 4. 调用拓扑排序
		try {
			List<DataModelNode> sortedNodes = topologicalSort(nodes, edges);

			// 5. 打印排序结果
			System.out.println("拓扑排序结果:");
			for (DataModelNode node : sortedNodes) {
				System.out.println(node.getId() + " (" + node.getNodeKey() + ")");
			}
		} catch (RuntimeException e) {
			System.err.println("排序失败: " + e.getMessage());
		}
	}

	private static DataModelEdge createEdge(String id, String source, String target) {
		DataModelEdge edge = new DataModelEdge();
		edge.setId(id);
		edge.setSource(source);
		edge.setTarget(target);
		return edge;
	}

	public static List<DataModelNode> topologicalSort(List<DataModelNode> dataModelNodeList, List<DataModelEdge> dataModelEdgeList) {
		for (DataModelNode dataModelNode : dataModelNodeList) {
			List<DataModelEdge> edgeList = new ArrayList<>();
			String nodeId = dataModelNode.getId();
			for (DataModelEdge dataModelEdge : dataModelEdgeList) {
				if (nodeId.equals(dataModelEdge.getSource())) {
					edgeList.add(dataModelEdge);
				}
			}
			dataModelNode.setEdgeList(edgeList);
		}

		// 记录节点的入度
		Map<DataModelNode, Integer> inDegree = new HashMap<>();
		for (DataModelNode node : dataModelNodeList) {
			inDegree.put(node, 0); // 初始化所有节点的入度为0
		}

		// 更新节点的入度
		for (DataModelNode node : dataModelNodeList) {
			for (DataModelEdge edge : node.getEdgeList()) {
				String target = edge.getTarget();
				DataModelNode dataModelNode = dataModelNodeList.stream().filter(dataModelNode1 -> target.equals(dataModelNode1.getId())).findFirst().get();
				inDegree.put(dataModelNode, inDegree.get(dataModelNode) + 1);
			}
		}

		// 使用队列存储入度为0的节点
		Queue<DataModelNode> queue = new LinkedList<>();
		for (DataModelNode node : dataModelNodeList) {
			if (inDegree.get(node) == 0) {
				queue.add(node);
			}
		}

		List<DataModelNode> sortedNodes = new ArrayList<>();
		while (!queue.isEmpty()) {
			DataModelNode node = queue.poll();
			sortedNodes.add(node);

			// 处理与该节点相邻的边，减少相邻节点的入度
			for (DataModelEdge edge : node.getEdgeList()) {
				String target = edge.getTarget();
				DataModelNode dataModelNode = dataModelNodeList.stream().filter(dataModelNode1 -> target.equals(dataModelNode1.getId())).findFirst().get();
				inDegree.put(dataModelNode, inDegree.get(dataModelNode) - 1);
				if (inDegree.get(dataModelNode) == 0) {
					queue.add(dataModelNode);
				}
			}
		}

		// 如果所有节点都已排序，返回排序结果
		if (sortedNodes.size() != dataModelNodeList.size()) {
			throw new RuntimeException("模型节点排序异常");
		}

		return sortedNodes;
	}
}

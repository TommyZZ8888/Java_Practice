package com.zz.leetcode.specialExercise.arraytotree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Describtion: ArrayToTree
 * @Author: 张卫刚
 * @Date: 2025/6/24 15:45
 */
public class ArrayToTree {
	public static void main(String[] args) {
		Category root = new Category(1L, "root", null,null);
		Category c1 = new Category(2L, "c1", 1L,null);
		Category c2 = new Category(3L, "c2", 1L,null);
		Category c3 = new Category(4L, "c3", 2L,null);
		Category c4 = new Category(5L, "c4", 2L,null);
		List<Category> list = Arrays.asList(root, c1, c2, c3, c4);
		List<Category> tree = buildTree(list);
		tree.forEach(System.out::println);
		List<Category> tree2 = buildTreeSolution2(list);
		tree2.forEach(System.out::println);
	}




	private static List<Category> buildTree(List<Category> all) {
		System.out.println(System.currentTimeMillis());
		for (Category curr : all) {
			Long pid = curr.getParentId();

			Optional<Category> parentOptional = all.stream().filter(ca -> ca.getId().equals(pid)).findFirst();
			if (parentOptional.isPresent()) { // 相当于 !parentOptional.isEmpty()
				Category p = parentOptional.get();
				if (null == p.getChildren()) {
					p.setChildren(new ArrayList<>());
				}
				p.getChildren().add(curr);
			}
		}
		System.out.println(System.currentTimeMillis());

		return all.stream().filter(ca -> ca.getParentId() == null).collect(Collectors.toList());
	}

	private static List<Category> buildTreeSolution2(List<Category> all) {
		System.out.println(System.currentTimeMillis());
		List<Category> categoryList = new ArrayList<>();
		List<Category> collect = all.stream().filter(ca -> ca.getParentId() == null).collect(Collectors.toList());
		List<Category> list = all.stream().filter(ca -> ca.getParentId() != null).collect(Collectors.toList());
		for (Category category : collect) {
			List<Category> children = list.stream().filter(ca -> ca.getParentId().equals(category.getId())).collect(Collectors.toList());
			category.setChildren(children);
			categoryList.add(category);
		}
		System.out.println(System.currentTimeMillis());
		return categoryList.stream().filter(ca -> ca.getParentId() == null).collect(Collectors.toList());
	}

}

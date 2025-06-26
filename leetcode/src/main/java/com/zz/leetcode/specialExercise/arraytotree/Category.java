package com.zz.leetcode.specialExercise.arraytotree;

import lombok.Data;

import java.util.List;

/**
 * @Describtion: Category
 * @Author: 张卫刚
 * @Date: 2025/6/24 15:45
 */
@Data
public class Category {
	private Long id;

	private String title;

	private Long parentId;

	private List<Category> children;

	public Category(Long id, String title, Long parentId, List<Category> children) {
		this.id = id;
		this.title = title;
		this.parentId = parentId;
		this.children = children;
	}

	public Category() {
	}
}


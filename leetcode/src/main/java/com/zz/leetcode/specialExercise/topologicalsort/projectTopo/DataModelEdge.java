package com.zz.leetcode.specialExercise.topologicalsort.projectTopo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据模型 - 边（连接线）
 *
 * @author 系统管理员
 * @date 2025-02-10
 */
@Data
public class DataModelEdge {


    /**
     * 主键
     */
    private String id;
    /**
     * 数据模型id
     */
    private String dataModelId;
    /**
     * 模型节点id（起点或起始节点、连接桩信息）
     */
    private String source;
    /**
     * 模型节点id（起点或起始节点、连接桩信息）
     */
    private String target;

}

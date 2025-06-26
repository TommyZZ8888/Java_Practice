package com.zz.leetcode.specialExercise.topologicalsort.projectTopo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 数据模型 - 节点
 *
 * @author 系统管理员
 * @date 2025-02-10
 */
@Data
public class DataModelNode {


    /**
     * 主键
     */
    private String id;
    /**
     * 数据模型id
     */
    private String dataModelId;
    /**
     * 节点类型
     */
    private String nodeType;
    /**
     * 节点Key
     */
    private String nodeKey;
    /**
     * 节点数据类型（暂仅支持数据表）
     */
    private String nodeDataType;
    /**
     * 节点数据（数据表id）
     */
    private String nodeDataSourceId;
    /**
     * 节点数据（x6）
     */
    private Object nodeData;
    /**
     * 节点设置（业务）
     */
    private Object nodeConfig;


    private List<DataModelEdge> edgeList;
}

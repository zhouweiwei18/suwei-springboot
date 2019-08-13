package com.mall.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用zTree展示菜单
 * @author weiwei
 * @create 2019-08-13 21:41
 */
public class ItemCatTreeNode implements Serializable {

    private long  id;//主键
    private String name;//名字
    private boolean open;//true:展开 false:折叠
    private Integer parentTId;//父节点
    private List<ItemCatTreeNode> children = new ArrayList<>();//子节点

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getParentTId() {
        return parentTId;
    }

    public void setParentTId(Integer parentTId) {
        this.parentTId = parentTId;
    }

    public List<ItemCatTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ItemCatTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ItemCatTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", parentTId=" + parentTId +
                ", children=" + children +
                '}';
    }
}

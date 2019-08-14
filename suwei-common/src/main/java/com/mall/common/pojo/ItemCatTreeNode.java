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

    private Integer  id;//主键
    private String name;//名字
    //private String url;//访问地址
    private boolean open;//true:展开  false:折叠
    private boolean isParent;
    //private Integer parentTId;//父节点
    //private List<ItemCatTreeNode> children = new ArrayList<>();//子节点
    //private boolean checked;//true:被选中   false:未选中
    //private String icon;//图标


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    @Override
    public String toString() {
        return "ItemCatTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", isParent=" + isParent +
                '}';
    }
}

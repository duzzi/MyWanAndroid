package com.duzzi.mywanandroid.core.bean.data;

/**
 * 文件名: CommonWebsiteBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class CommonWebsiteBean {

    /**
     * icon :
     * id : 17
     * link : http://www.wanandroid.com/article/list/0?cid=176
     * name : 国内大牛博客集合
     * order : 1
     * visible : 1
     */

    private String icon;
    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}

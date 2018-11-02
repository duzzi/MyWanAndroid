package com.duzzi.mywanandroid.core.bean.data;

/**
 * 文件名: BannerBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/24
 */
public class BannerBean {

    /**
     * desc :
     * id : 5
     * imagePath : http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png
     * isVisible : 1
     * order : 3
     * title : 微信文章合集
     * type : 1
     * url : http://www.wanandroid.com/blog/show/6
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

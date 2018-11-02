package com.duzzi.mywanandroid.core.bean.data;

import java.util.List;

/**
 * 文件名: SearchResultsBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class SearchResultsBean {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"技术小黑屋","chapterId":89,"chapterName":"app缓存相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2871,"link":"https://droidyue.com/blog/2014/07/12/scan-media-files-in-android-chinese-edition/","niceDate":"2018-04-25","origin":"","projectLink":"","publishTime":1524627148000,"superChapterId":90,"superChapterName":"数据存储","tags":[],"title":"Android扫描<em class='highlight'>多媒体<\/em>文件剖析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":5484,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247486623&idx=1&sn=8077efd65bfff257c4423525413e1432&chksm=97f6b22ba0813b3dcb19d123270a9d1810fc67ec8262f480d17c731122daee65e7f353c8b90f&scene=38#wechat_redirect","niceDate":"2018-02-12","origin":"","projectLink":"","publishTime":1518364800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Charles - Android <em class='highlight'>多媒体<\/em>选择器","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 2
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<ArticleBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }

}

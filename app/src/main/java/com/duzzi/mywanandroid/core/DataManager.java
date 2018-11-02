package com.duzzi.mywanandroid.core;

import com.duzzi.mywanandroid.core.bean.base.BaseResponse;
import com.duzzi.mywanandroid.core.bean.data.CommonWebsiteBean;
import com.duzzi.mywanandroid.core.bean.data.HierarchyArticleListBean;
import com.duzzi.mywanandroid.core.bean.data.HotKeyBean;
import com.duzzi.mywanandroid.core.bean.data.KnowledgeHierarchyBean;
import com.duzzi.mywanandroid.core.bean.data.NavigationBean;
import com.duzzi.mywanandroid.core.bean.data.ProjectCategoryBean;
import com.duzzi.mywanandroid.core.bean.data.ProjectsBean;
import com.duzzi.mywanandroid.core.bean.data.SearchResultsBean;
import com.duzzi.mywanandroid.core.bean.data.UserInfoBean;
import com.duzzi.mywanandroid.core.bean.response.ArticleListResponse;
import com.duzzi.mywanandroid.core.bean.response.BannerResponse;
import com.duzzi.mywanandroid.core.http.HttpHelper;
import com.duzzi.mywanandroid.core.http.WanAndroidService;
import com.duzzi.mywanandroid.core.sp.IAcacheHelper;
import com.duzzi.mywanandroid.core.sp.IAcacheHelperImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * 文件名: DataManager
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
public class DataManager implements WanAndroidService ,IAcacheHelper {
    private static DataManager sDataManager;

    public static DataManager getInstance() {
        if (sDataManager == null) {
            synchronized (DataManager.class) {
                if (sDataManager == null) {
                    sDataManager = new DataManager();
                }
            }
        }
        return sDataManager;
    }

    @Override
    public Observable<BannerResponse> getBanner() {
        return HttpHelper.getInstance().getWanAndroidService().getBanner();
    }

    @Override
    public Observable<ArticleListResponse> getHomeArticle(int page) {
        return HttpHelper.getInstance().getWanAndroidService().getHomeArticle(page);
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyBean>>> getKnowledgeHierarchy() {
        return HttpHelper.getInstance().getWanAndroidService().getKnowledgeHierarchy();
    }

    @Override
    public Observable<BaseResponse<HierarchyArticleListBean>> getHierarchyArticle(int page, int cid) {
        return HttpHelper.getInstance().getWanAndroidService().getHierarchyArticle(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<CommonWebsiteBean>>> getCommonWebSite() {
        return HttpHelper.getInstance().getWanAndroidService().getCommonWebSite();
    }

    @Override
    public Observable<BaseResponse<List<HotKeyBean>>> getHotKey() {
        return HttpHelper.getInstance().getWanAndroidService().getHotKey();
    }

    @Override
    public Observable<BaseResponse<SearchResultsBean>> search(int page, String k) {
        return HttpHelper.getInstance().getWanAndroidService().search(page, k);
    }

    @Override
    public Observable<BaseResponse<List<ProjectCategoryBean>>> getProjectCategory() {
        return HttpHelper.getInstance().getWanAndroidService().getProjectCategory();
    }

    @Override
    public Observable<BaseResponse<ProjectsBean>> getProjects(int page, int cid) {
        return HttpHelper.getInstance().getWanAndroidService().getProjects(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationBean>>> getNavigation() {
        return HttpHelper.getInstance().getWanAndroidService().getNavigation();
    }

    @Override
    public Observable<BaseResponse<UserInfoBean>> login(String username, String password) {
        return HttpHelper.getInstance().getWanAndroidService().login(username, password);
    }

    @Override
    public Observable<BaseResponse<UserInfoBean>> register(String username, String password, String repassword) {
        return HttpHelper.getInstance().getWanAndroidService().register(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<UserInfoBean>> logout() {
        return HttpHelper.getInstance().getWanAndroidService().logout();
    }

    @Override
    public void setLoginAccount(String account) {
        IAcacheHelperImpl.getInstance().setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return IAcacheHelperImpl.getInstance().getLoginAccount();
    }

    @Override
    public String getLoginPassword() {
        return IAcacheHelperImpl.getInstance().getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        IAcacheHelperImpl.getInstance().setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return IAcacheHelperImpl.getInstance().getLoginStatus();
    }

    @Override
    public void setCookie(String domain, String cookie) {

    }

    @Override
    public String getCookie(String domain) {
        return null;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean b) {

    }
}

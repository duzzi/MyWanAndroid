package com.duzzi.mywanandroid.core.http;

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

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 文件名: WanAndroidService
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/24
 */
public interface WanAndroidService {


    @GET("banner/json")
    Observable<BannerResponse> getBanner();

    @GET("article/list/{page}/json")
    Observable<ArticleListResponse> getHomeArticle(@Path("page") int page);


    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeHierarchyBean>>> getKnowledgeHierarchy();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<HierarchyArticleListBean>> getHierarchyArticle(@Path("page") int page,
                                                                           @Query("cid") int cid);

    @GET("friend/json")
    Observable<BaseResponse<List<CommonWebsiteBean>>> getCommonWebSite();

    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyBean>>> getHotKey();

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<BaseResponse<SearchResultsBean>> search(@Path("page") int page, @Field("k") String k);

    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectCategoryBean>>> getProjectCategory();

    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectsBean>> getProjects(@Path("page") int page, @Query("cid") int cid);

    @GET("navi/json")
    Observable<BaseResponse<List<NavigationBean>>> getNavigation();

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<UserInfoBean>> login(@Field("username") String username,
                                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponse<UserInfoBean>> register(@Field("username") String username,
                                                    @Field("password") String password,
                                                    @Field("repassword") String repassword);

    @POST("user/logout/json")
    Observable<BaseResponse<UserInfoBean>> logout();
}

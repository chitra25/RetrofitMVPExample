package com.hfad.retrofitmvpexample.retrofit;

import com.hfad.retrofitmvpexample.model.Notice;
import com.hfad.retrofitmvpexample.model.NoticeList;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;



public interface APIInterface {
    @GET("bins/1bsqcn/")
    Call<NoticeList> getNoticeData();

    @GET("bins/path/")
    Call<NoticeList> getNoticeDataData(@Query("company_no") int companyNo);

    @GET("group/{id}/users")
    Call<List<Notice>> groupList(@Path("id") int groupId);

    @GET("group/{id}/users")
    Call<List<Notice>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @GET("group/{id}/noticelist")
    Call<List<Notice>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);

    @POST("notice/new")
    Call<Notice> createNotice(@Body Notice notice);

    /**
     * FORM ENCODED AND MULTIPART
     * Indicates that the request will have its MIME type (a header field that identifies the format of the body of an HTTP request or response) set to application/x-www-form-urlencoded and also that its field names and values will be UTF-8 encoded before being URI-encoded.
     * Form-encoded data is sent when @FormUrlEncoded is present on the method.
     * Each key-value pair is annotated with @Field containing the name and the object providing the value
     * */
    @FormUrlEncoded
    @POST("notice/edit")
    Call<Notice> updateNotice(@Field("id") String id, @Field("title") String title);

    @Multipart
    @PUT("notice/photo")
    Call<Notice> updateNotice(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @Headers("Cache-Control: max-age=640000")
    @GET("notice/list")
    Call<List<Notice>> NoticeList();

}

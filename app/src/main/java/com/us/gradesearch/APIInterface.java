package com.us.gradesearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("?")
    Call<List<ApiResponse>> getSearchResultFromCourse(@Query("search") String text);

}

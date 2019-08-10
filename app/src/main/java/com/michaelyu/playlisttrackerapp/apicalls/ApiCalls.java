package com.michaelyu.playlisttrackerapp.apicalls;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCalls {
    @POST("/api/write_track")
    Call<ResponseBody> writeTrack(@Body RequestBody song);
}

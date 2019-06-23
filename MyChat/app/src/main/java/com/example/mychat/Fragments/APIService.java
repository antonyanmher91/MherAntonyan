package com.example.mychat.Fragments;

import com.example.mychat.Notifications.MyResponse;
import com.example.mychat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAXZvFYU8:APA91bGR63DUW1s86r2rDDrMY5f-FaLOcc49V_XlV_PXI8VQ7nVK-tOd-hGmZ_DSxDy1xtsN2C9sRE8ZhV7n2ydYEyVgglaMATOXIvE-NSx8uD_dkd4YFhOT6vuLu8yZ-Kk6eT4vdc5h"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

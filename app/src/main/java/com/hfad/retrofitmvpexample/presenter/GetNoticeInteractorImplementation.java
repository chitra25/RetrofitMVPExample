package com.hfad.retrofitmvpexample.presenter;

import com.hfad.retrofitmvpexample.model.NoticeList;
import com.hfad.retrofitmvpexample.retrofit.APIInterface;
import com.hfad.retrofitmvpexample.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetNoticeInteractorImplementation implements MainInterface.GetNoticeInteractor {
    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {

        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<NoticeList> call = service.getNoticeData();
        call.enqueue(new Callback<NoticeList>() {
            @Override
            public void onResponse(Call<NoticeList> call, Response<NoticeList> response) {
                onFinishedListener.onFinished(response.body().getNoticeArrayList());
            }

            @Override
            public void onFailure(Call<NoticeList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}

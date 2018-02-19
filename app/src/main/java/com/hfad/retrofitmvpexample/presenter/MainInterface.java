package com.hfad.retrofitmvpexample.presenter;


import com.hfad.retrofitmvpexample.model.Notice;

import java.util.ArrayList;

public interface MainInterface {

    interface clickInterface{

        void onDestroy();
        void onRefreshButtonClick();
        void requestDataFromServer();

    }


    interface DisplayInterface {

        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(ArrayList<Notice> noticeArrayList);
        void onResponseFailure(Throwable throwable);

    }

    interface GetNoticeInteractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Notice> noticeArrayList);
            void onFailure(Throwable t);
        }
        void getNoticeArrayList(OnFinishedListener onFinishedListener);

    }

}


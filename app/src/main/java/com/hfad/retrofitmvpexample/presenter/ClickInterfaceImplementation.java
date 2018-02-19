package com.hfad.retrofitmvpexample.presenter;


import com.hfad.retrofitmvpexample.model.Notice;

import java.util.ArrayList;

public class ClickInterfaceImplementation implements MainInterface.clickInterface, MainInterface.GetNoticeInteractor.OnFinishedListener {
    private MainInterface.DisplayInterface displayView;
    private MainInterface.GetNoticeInteractor getNoticeInteractor;

    public ClickInterfaceImplementation(MainInterface.DisplayInterface displayView, MainInterface.GetNoticeInteractor getNoticeInteractor){
        this.displayView=displayView;
        this.getNoticeInteractor=getNoticeInteractor;
    }

    @Override
    public void onDestroy() {
        displayView=null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(displayView != null){
            displayView.showProgress();
        }
        getNoticeInteractor.getNoticeArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getNoticeInteractor.getNoticeArrayList(this);
    }

    @Override
    public void onFinished(ArrayList<Notice> noticeArrayList) {
        if(displayView != null){
            displayView.setDataToRecyclerView(noticeArrayList);
            displayView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(displayView!= null) {
            displayView.onResponseFailure(t);
            displayView.hideProgress();
        }
    }
}

package com.hfad.retrofitmvpexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hfad.retrofitmvpexample.R;
import com.hfad.retrofitmvpexample.presenter.ClickInterfaceImplementation;
import com.hfad.retrofitmvpexample.presenter.GetNoticeInteractorImplementation;
import com.hfad.retrofitmvpexample.presenter.MainInterface;
import com.hfad.retrofitmvpexample.presenter.RecyclerViewItemClickListener;
import com.hfad.retrofitmvpexample.ui.adapter.RecyclerViewAdapter;
import com.hfad.retrofitmvpexample.model.Notice;
import com.hfad.retrofitmvpexample.presenter.MainInterface.clickInterface;
;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements MainInterface.DisplayInterface {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    clickInterface clickpreint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();

        clickpreint = new ClickInterfaceImplementation(this, new GetNoticeInteractorImplementation());
        clickpreint.requestDataFromServer();

    }

    private void initializeToolbarAndRecyclerView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initProgressBar(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);
        this.addContentView(relativeLayout, params);
    }

    private RecyclerViewItemClickListener recyclerViewItemClickListener = new RecyclerViewItemClickListener() {
        @Override
        public void onItemClick(Notice notice) {
            Toast.makeText(MainActivity.this,
                    "List title:  " + notice.getTitle(),
                    Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Notice> noticeArrayList) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(noticeArrayList , recyclerViewItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clickpreint.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            clickpreint.onRefreshButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }


}



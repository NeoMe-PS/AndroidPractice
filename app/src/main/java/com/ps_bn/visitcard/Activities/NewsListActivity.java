package com.ps_bn.visitcard.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ps_bn.visitcard.Data.DataUtils;
import com.ps_bn.visitcard.Data.NewsItem;
import com.ps_bn.visitcard.Data.RecyclerViewAdapter;
import com.ps_bn.visitcard.R;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {


    private RecyclerView mRecycler;
    private RecyclerViewAdapter mAdapter;
    private List<NewsItem> dataArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        mRecycler = findViewById(R.id.recyclerview);
        dataArray=new ArrayList<>();
        dataArray.addAll(DataUtils.generateNews());
        mAdapter = new RecyclerViewAdapter(dataArray,getApplicationContext());
        setDivider();
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setHasFixedSize(true);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        }
        mAdapter.setMyOnClickListener(new RecyclerViewAdapter.MyOnClickListener() {

            @Override
            public void onClick(int position) {
                NewsItem item = dataArray.get(position);
                String title = item.getTitle();
                String fullText = item.getFullText();
                String date = String.valueOf(item.getPublishDate());
                Intent toDetailsActivity = new Intent(getApplicationContext(), NewsDetailsActivity.class);
                toDetailsActivity.putExtra("title",title);
                toDetailsActivity.putExtra("fulltext",fullText);
                toDetailsActivity.putExtra("date",date);
                toDetailsActivity.putExtra("category",item.getCategory().getName());
                toDetailsActivity.putExtra("image_url",item.getImageUrl());
                startActivity(toDetailsActivity);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.about_menu_item) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void setDivider() {
        DividerItemDecoration dividerItemDecorationVertical=new DividerItemDecoration(mRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecorationVertical.setDrawable(getResources().getDrawable(R.drawable.decorate));
        mRecycler.addItemDecoration(dividerItemDecorationVertical);
        DividerItemDecoration dividerItemDecorationHorizontal=new DividerItemDecoration(mRecycler.getContext(),
                DividerItemDecoration.HORIZONTAL);
        dividerItemDecorationHorizontal.setDrawable(getResources().getDrawable(R.drawable.decorate));
        mRecycler.addItemDecoration(dividerItemDecorationHorizontal);
    }
}
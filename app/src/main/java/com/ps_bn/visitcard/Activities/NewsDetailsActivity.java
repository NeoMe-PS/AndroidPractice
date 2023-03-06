package com.ps_bn.visitcard.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ps_bn.visitcard.Data.DownloadTask;
import com.ps_bn.visitcard.R;

public class NewsDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView fullText;
    private TextView date;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initLayout();
    }


    private void initLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        image = findViewById(R.id.details_full_image);
        title = findViewById(R.id.title_detail_text);
        fullText = findViewById(R.id.full_detail_text);
        date = findViewById(R.id.date_detail_text);


        Intent intent = getIntent();
        if (intent != null) {
           title.setText(intent.getStringExtra("title"));
            fullText.setText(intent.getStringExtra("fulltext"));
            date.setText(intent.getStringExtra("date"));
            collapsingToolbar.setTitle(intent.getStringExtra("category"));
            DownloadTask task = new DownloadTask(image, image);
            task.getDownloadImage().execute(intent.getStringExtra("image_url"));
        }
    }
}
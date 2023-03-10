package com.ps_bn.visitcard.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        image = findViewById(R.id.details_full_image);
        title = findViewById(R.id.title_detail_text);
        fullText = findViewById(R.id.full_detail_text);
        date = findViewById(R.id.date_detail_text);
        ActionBar actionBar=getSupportActionBar();
        Intent intent = getIntent();
        if (intent != null) {
            title.setText(intent.getStringExtra("title"));
            fullText.setText(intent.getStringExtra("fulltext"));
            date.setText(intent.getStringExtra("date"));
           actionBar.setTitle(intent.getStringExtra("category"));

            DownloadTask task = new DownloadTask(image,image);
            task.getDownloadImage().execute(intent.getStringExtra("image_url"));
        }
    }
}
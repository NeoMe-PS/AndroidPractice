package com.ps_bn.visitcard.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ps_bn.visitcard.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderer> {
    private List<NewsItem> newsArray;
    private Context context;
    private MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onClick(int position);
    }


    public RecyclerViewAdapter(List<NewsItem> newsArray, Context context) {
        this.newsArray = newsArray;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderer(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderer holder, int position) {
        holder.bind(newsArray.get(position));
    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }

    public class ViewHolderer extends RecyclerView.ViewHolder {

        private TextView category;
        private TextView title;
        private TextView text;
        private TextView date;
        private ImageView newsImage;
        private String fullTime;

        public ViewHolderer(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category_text);
            title = itemView.findViewById(R.id.title_text);
            text = itemView.findViewById(R.id.short_info_text);
            date = itemView.findViewById(R.id.date_text);
            newsImage = itemView.findViewById(R.id.main_news_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myOnClickListener != null) {
                        myOnClickListener.onClick(getAdapterPosition());
                    }
                }
            });
        }

        public ViewHolderer getViewHolder(ViewGroup parent) {
            return new ViewHolderer(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
        }

        public void bind(NewsItem newsItem) {

            category.setText(newsItem.getCategory().getName());
            title.setText(newsItem.getTitle());
            text.setText(newsItem.getPreviewText());
            /*DownloadImage downloadImage = new DownloadImage(newsImage);
            downloadImage.execute(newsItem.getImageUrl());*/
            DownloadTask task = new DownloadTask(newsImage,itemView.findViewById(R.id.progress_layout));
            task.getDownloadImage().execute(newsItem.getImageUrl());
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String dateStr = formatter.format(newsItem.getPublishDate());
            date.setText(dateStr);
            fullTime=newsItem.getFullText();
        }
    }
}

     /*class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView view;
        Bitmap bitmap;
        ConstraintLayout d;
     //   ProgressDialog progressDialog;
        public DownloadImage(ImageView view) {
            this.view = view;
            d = view.getRootView().findViewById(R.id.progress_layout);
        }
        @Override
        protected void onPreExecute() {
            d.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
//           progressDialog= ProgressDialog.show(view.getContext(), "",
//                    "Loading. Please wait...", true);
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageURL = strings[0];

            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            d.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            view.setImageBitmap(bitmap);
            // progressDialog.dismiss();
        }
    }
}*/

package com.ps_bn.visitcard.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.InputStream;

public class DownloadTask {
    private  ImageView view;
 //   private  ConstraintLayout d;
    private View d;
    DownloadImage downloadImage;

    public DownloadImage getDownloadImage() {
        downloadImage=new DownloadImage();
        return downloadImage;
    }

    public DownloadTask(ImageView view, View d) {
        this.view = view;
        this.d = d;
    }


    public  class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            d.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageURL = strings[0];

            try {
                InputStream input = new java.net.URL(imageURL).openStream();
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
        }
    }
}


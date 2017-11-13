package com.example.imageloader.ImageLoaderUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 张金瑞 on 2017/11/13.
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    ImageCache mImageCache = new MemoryCache();

    ExecutorService mExcutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());



    public void setImageLoader(ImageCache imageCache){

        mImageCache = imageCache;

    }

    public void displayImage(String url, ImageView imageView){
        Log.e(TAG, "displayImage: "+url );
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null){

            imageView.setImageBitmap(bitmap);

            return;

        }
        submitLoadRequest(url,imageView);


    }

    private void submitLoadRequest(final String imageUrl, final ImageView imageView){

        Log.e(TAG, "submitLoadRequest: 走没走" );
        imageView.setTag(imageUrl);
        mExcutorService.submit(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap =downloadImage(imageUrl);

                if (bitmap == null){
                    return;
                }

                if (imageView.getTag().equals(imageUrl)){

                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imageUrl,bitmap);
            }
        });

    }

    public Bitmap downloadImage(String imageUrl){

        Log.e(TAG, "downloadImage: 应该走这的" );
        Bitmap bitmap = null;

        try {
            URL url = new URL(imageUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap ;
    }

}

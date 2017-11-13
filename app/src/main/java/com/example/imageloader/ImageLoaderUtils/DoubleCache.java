package com.example.imageloader.ImageLoaderUtils;

import android.graphics.Bitmap;

/**
 * Created by 张金瑞 on 2017/11/13.
 */

public class DoubleCache implements ImageCache {

    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();



    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);

        if (bitmap == null){
            bitmap = mDiskCache.get(url);

        }

        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}

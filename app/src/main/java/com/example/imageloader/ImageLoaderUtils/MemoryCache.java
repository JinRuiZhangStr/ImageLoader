package com.example.imageloader.ImageLoaderUtils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by 张金瑞 on 2017/11/13.
 *
 * 内存缓存类
 */

public class MemoryCache implements ImageCache {

    private LruCache<String,Bitmap> mMemoryCache;


    public MemoryCache(){

        initImageCacheSezi();

    }

    private void initImageCacheSezi(){

        //计算可使用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        //取四分之一的内存作为缓存
        int cacheSize = maxMemory/4;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
    }
}

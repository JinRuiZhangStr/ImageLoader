package com.example.imageloader.ImageLoaderUtils;

import android.graphics.Bitmap;

/**
 * Created by 张金瑞 on 2017/11/13.
 */

public interface ImageCache {

    /**
     * 从缓存中获取图片
     * @param url  图片路径
     * @return
     */
    public Bitmap get(String url);

    /**
     * 缓存图片到缓存中
     * @param url
     * @param bitmap
     */
    public void put(String url,Bitmap bitmap);

}

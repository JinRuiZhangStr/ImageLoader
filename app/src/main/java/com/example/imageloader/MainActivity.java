package com.example.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.imageloader.ImageLoaderUtils.DiskCache;
import com.example.imageloader.ImageLoaderUtils.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;
    private ImageLoader imageLoader = new ImageLoader();
    private String imageUrl = "https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/72f082025aafa40f12647690a064034f79f019d8.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mImg = findViewById(R.id.img);

    }

    public void imageLoader(View view){
        //设置缓存模式
        imageLoader.setImageLoader(new DiskCache());
        //设置图片
        imageLoader.displayImage(imageUrl,mImg);

    }
}

package com.xinlan.peach;

import com.xinlan.peachloader.PeachImageLoader;

import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;

public class MainActivity extends Activity
{
    private ImageView img1, img2;

    public static final String url1 = "http://image4.suning.cn/images/shop/cms/4225/1410952421118_1200.jpg";
    public static final String url2 = "http://image4.suning.cn/images/shop/cms/4225/1410950456932_1200.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        
        PeachImageLoader.getInstance().showImage(img1,url1);
        PeachImageLoader.getInstance().showImage(img2,url2);
    }

}// end class

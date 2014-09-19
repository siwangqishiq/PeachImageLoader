package com.xinlan.peachloader;

import android.widget.ImageView;

public class PeachImageLoader
{
    private volatile static PeachImageLoader instance;
    
    /**
     * 私有构造方法 保证单例模式
     */
    private PeachImageLoader()
    {
        
    }

    /**
     * 获取单例实例
     * @return
     */
    public static PeachImageLoader getInstance()
    {
        if (instance == null)
        {
            synchronized (PeachImageLoader.class)
            {
                if (instance == null)
                {
                    instance = new PeachImageLoader();
                }
            }
        }
        return instance;
    }
    
    public void showImage(ImageView imgView,String picUrl)
    {
        
    }
}// end class

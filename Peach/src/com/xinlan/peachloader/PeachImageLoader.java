package com.xinlan.peachloader;

import android.widget.ImageView;

public class PeachImageLoader
{
    private volatile static PeachImageLoader instance;
    
    /**
     * ˽�й��췽�� ��֤����ģʽ
     */
    private PeachImageLoader()
    {
        
    }

    /**
     * ��ȡ����ʵ��
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

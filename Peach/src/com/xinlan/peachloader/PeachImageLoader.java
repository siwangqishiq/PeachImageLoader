package com.xinlan.peachloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.xinlan.peachloader.DownloadImageTask.MsgPkg;

public class PeachImageLoader
{
    private static final int MAX_THREAD_NUM = 10;
    private volatile static PeachImageLoader instance;

    protected ExecutorService excutor;// �̳߳�

    private static ImageShowHandler handler = new ImageShowHandler();

    /**
     * ˽�й��췽�� ��֤����ģʽ
     */
    private PeachImageLoader()
    {
        int cpuNums = Runtime.getRuntime().availableProcessors();// ��ȡCPU ��Ŀ
        // excutor = Executors.newCachedThreadPool();//�������̳߳�
        excutor = Executors.newFixedThreadPool(MAX_THREAD_NUM * cpuNums); // �̶������̳߳�
    }

    /**
     * ��ȡ����ʵ��
     * 
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

    public void showImage(ImageView imgView, String picUrl)
    {
        excutor.submit(new DownloadImageTask(imgView,
                picUrl, handler));
    }

    private static final class ImageShowHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            MsgPkg pkg = (MsgPkg)msg.obj;
            ImageView view = pkg.imgView;
            view.setImageBitmap(pkg.bitmap);
        }
    }// end inner class

}// end class

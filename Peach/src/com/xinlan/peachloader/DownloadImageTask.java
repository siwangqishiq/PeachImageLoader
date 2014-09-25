package com.xinlan.peachloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class DownloadImageTask implements Callable<Bitmap>
{
    private static final int EACH_READ_TEMP_LEN=1024;
    private String downloadUrl;
    private String downloadFileName;
    private ImageView mImageView;
    private Handler mHandler;

    public DownloadImageTask(ImageView imgView,String url,Handler handler)
    {
        this.downloadUrl = url;
        downloadFileName = downloadUrl;
        this.mImageView = imgView;
        this.mHandler = handler;
    }

    public byte[] getImage(String path)
    {
        URL url;
        try
        {
            url = new URL(path);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            int size = conn.getContentLength();
            System.out.println("size = " + size + "     " + path);
            InputStream inStream = conn.getInputStream();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                return readStream(inStream);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[EACH_READ_TEMP_LEN];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    @Override
    public Bitmap call() throws Exception
    {
        byte[] data = getImage(downloadUrl);
        Bitmap retBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        Message msg = new Message();
        MsgPkg pkg = new MsgPkg();
        pkg.bitmap = retBitmap;
        pkg.imgView = mImageView;
        msg.what = 1;
        msg.obj = pkg;
        mHandler.sendMessage(msg);
        return retBitmap;
    }
    
    public static class MsgPkg
    {
        ImageView imgView;
        Bitmap bitmap;
    }//end inner class
}// end class

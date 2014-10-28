package stu.love.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

/**
 * @author aaa
 *
 */
public class ImageCacheSDUtils {

	private static String Tag = "ImageCacheSDUtils";
	
	private static ImageCacheSDUtils iamgeCacheSDUtils;
	
	private ImageCacheSDUtils() {
		// TODO Auto-generated constructor stub
		super();
	}

//	单利设计模式
	public static ImageCacheSDUtils getInstance()
	{
		if(iamgeCacheSDUtils == null)
		{
			iamgeCacheSDUtils = new ImageCacheSDUtils();
		}
		return iamgeCacheSDUtils;
	}
	
	
	 public void putBitmapData(String url, byte[] data) {
	        // TODO 将从网络获取的图片数据，保存到SDCard文件系统中

		 	Log.i(Tag, "--- putBitmapData  url="+url);
	        File directory = Environment.getExternalStorageDirectory();
	        File cacheFolder = new File(directory, "MyCache/images");

	        Log.i(Tag, "---cacheFolder="+cacheFolder);
	        boolean bok = true;
	        if (!cacheFolder.exists()) {
	            bok = cacheFolder.mkdirs();
	        }

	        if (bok) {
	            // url 要进行编码处理，需要将url中 特定的字符去掉
//				md5 解码就是为了 命名 图片名称 唯一性！
	            try {
	                MessageDigest digest = MessageDigest.getInstance("MD5");
	                byte[] bytes = digest.digest(url.getBytes());
	                StringBuilder sb = new StringBuilder();

	                for (byte aByte : bytes) {
	                    int iv = (int) (aByte & 0x0ff);
	                    if (iv <= 0x0f) {
	                        sb.append('0');
	                    }
	                    sb.append(Integer.toHexString(iv));
	                }

	                String s = sb.toString();
	                File targetFile = new File(cacheFolder, s);
	                FileOutputStream fout = null;
	                try {
	                    if (!targetFile.exists()) {
	                        targetFile.createNewFile();
	                    }
	                    fout = new FileOutputStream(targetFile);
//	                    	写入数据
	                    fout.write(data);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } finally {
	                    if (fout != null) {
	                        try {
	                            fout.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }

	            } catch (NoSuchAlgorithmException e) {
	                e.printStackTrace();
	            }

	        }

	 }
	 
	 
	 

	 
	 /**
	  * 
	  * 获取 Bitmap
	 * @param url
	 * @return
	 */
	public byte[] getBitmapData(String url) {
	        // TODO 根据图片地址，加载SDCard文件系统中的图片数据s
			Log.i(Tag, "--- getBitmapData  url="+url);
	        byte[] data = null;

	        File directory = Environment.getExternalStorageDirectory();
	        File cacheFolder = new File(directory, "MyCache/images");

	        Log.i(Tag, "--- cacheFolder ="+cacheFolder);
	        boolean bok = true;
	        if (!cacheFolder.exists()) {
	            bok = cacheFolder.mkdirs();
	        }

	        if (bok) {
	            // url 要进行编码处理，需要将url中 特定的字符去掉

	            try {

	                // MD5 计算结果，生成文件名称
	                MessageDigest digest = MessageDigest.getInstance("MD5");
	                byte[] bytes = digest.digest(url.getBytes());
	                StringBuilder sb = new StringBuilder();


	                for (byte aByte : bytes) {
	                    int iv = (int) (aByte & 0x0ff);
	                    if (iv <= 0x0f) {
	                        sb.append('0');
	                    }
	                    sb.append(Integer.toHexString(iv));
	                }

	                String s = sb.toString();

	                File targetFile = new File(cacheFolder, s);

	                if (targetFile.exists()) {
	                    FileInputStream fin = null;
	                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	                    try {
	                        fin = new FileInputStream(targetFile);

	                        // TODO Read fin to data
	                        byte[] buf = new byte[1028];
	                        int len;

	                        while (true) {
	                            len = fin.read(buf);
	                            if (len == -1) {
	                                break;
	                            }
	                            bout.write(buf, 0, len);
	                        }
	                        buf = null;
	                        data = bout.toByteArray();

	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    } finally {
	                        if (fin != null) {
	                            try {
	                                fin.close();
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            }
	                        }
	                        try {
	                            bout.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            } catch (NoSuchAlgorithmException e) {
	                e.printStackTrace();
	            }

	        }
	        return data;
	 }
}
package com.pkrss.common.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.pkrss.common.base.AppVar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

public class FileHelper{
	
	public static String getFromRaw(int rid){
		return getFromRaw(AppVar.getAppContext().getResources(), rid);
	}
	
	public static String getFromRaw(Resources resources,int rid){
	    String result = "";
	    try {
	    	InputStream in = resources.openRawResource(rid);

	    	if(in == null)
	        	return null;
	    	
	    	int lenght = in.available();

	    	byte[]  buffer = new byte[lenght];

	    	in.read(buffer);

	    	result = new String(buffer, "UTF-8");

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    return result;
	}
	
	public static Boolean existAssets(String fileName){
		Boolean ret = false;
		try {
			InputStream in = AppVar.getAppContext().getResources().getAssets().open(fileName);
			if(in != null)
				ret = true;
		} catch (Exception e) {
		}
		
		return ret;
	}
	
	public static String getFromAssets(String fileName){
		return getFromAssets(AppVar.getAppContext().getResources(),fileName);
	}
	
	public static String getFromAssets(Resources resources,String fileName){
		String result = null;
	    try {
	        InputStream in = resources.getAssets().open(fileName);
	        
	        if(in == null)
	        	return null;
	
	        int length = in.available();
	        
	        if(length == 0)
	        	return "";
	          
	        byte[] buffer = new byte[length];
	        in.read(buffer);
	          
	        result = new String(buffer, "UTF-8");
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
//	public static void mkdirs(String filename){
//		if(filename != null && filename.length()>0 ){
//			int pos;
//			String path;
//			pos = filename.lastIndexOf('/');
//			if(pos > 0){
//				path = filename.substring(0,pos);
//			}else{
//				path = filename;
//			}
//			
//			if(path.indexOf('/')!=0 && !path.startsWith(_sd_data_dir))
//				path = _sd_data_dir +  path;
//			
//			File file = new File(path);
//			if(!file.isDirectory())
//				file.mkdirs();
//		}
//	}

	public static String getCacheRoot(){
		String path = null;
		do{
			Context c = AppVar.getAppContext();
			if(c == null)
				break;

			File f = c.getExternalCacheDir();
			if(f != null)
				path = f.getAbsolutePath();
			
			if(path == null){
				f = c.getCacheDir();
				if(f != null)
					path = f.getAbsolutePath();
			}
			
		}while(false);
		
		return path;
	}
	
	private static String _img_root = null;
	public static String getImgRoot(){
		if(_img_root != null)
			return _img_root;
		
		do{
			Context c = AppVar.getAppContext();
			if(c == null)
				break;

			File f = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
			if(f != null)
				_img_root = f.getAbsolutePath();
			
			if(_img_root == null){
				f = c.getFilesDir();
				if(f != null)
					_img_root = f.getAbsolutePath();
			}
			
		}while(false);
		
		return _img_root;
	}
	
	public static final String UNKNOWN_IMG = "empty.png";
	
	public static String getImgFilePath(String filename){
		
		String ret = getImgRoot();
		if(filename!=null && filename.length()>0){
			int pos = filename.indexOf('/');
			if(pos == 0)
				return filename;

			if(pos>0){
				if(pos < filename.length()-1)
					filename = filename.substring(pos+1);
				else
					filename = UNKNOWN_IMG;
			}
			
//			mkdirs(filename);
			
			ret += filename;
		}
		return ret;
	}
	public static Boolean moveAssertToImgPath(String asserFilePath,String imgFileName){
		imgFileName = getImgFilePath(imgFileName);
		return moveAssertToImgPath(asserFilePath,new File(imgFileName));
	}
	public static Boolean moveAssertToImgPath(String asserFilePath,File imgFile){
		InputStream is=null;
		FileOutputStream out=null;
		Boolean f = false;

		try {
			is=AppVar.getAppContext().getResources().getAssets().open(asserFilePath);
			
			imgFile.mkdirs();
			
			out=new FileOutputStream(imgFile);
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				out.write(buffer, 0, len);
				out.flush();
			}
			is.close();
			out.close();
			f = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public static void copyFile(String oldPath, String newPath) throws Exception {   

		int bytesum = 0;   
		int byteread = 0;   
		File oldfile = new File(oldPath);   
		if (oldfile.exists()) { //文件存在时   
			InputStream inStream = new FileInputStream(oldPath); //读入原文件   
			FileOutputStream fs = new FileOutputStream(newPath);   
			byte[] buffer = new byte[1024];
			while ( (byteread = inStream.read(buffer)) != -1) {   
				bytesum += byteread; //字节数 文件大小   
				System.out.println(bytesum);   
				fs.write(buffer, 0, byteread);   
			}   
			inStream.close(); 
			fs.close();
		}

	}  
}
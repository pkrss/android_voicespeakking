package com.pkrss.common.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.pkrss.common.base.AppVar;
import com.pkrss.module.StatAnalytics;

public class WebHelper{

	public static void sendShare(Context c,String subject, String body) {
		if(c == null)
			return;
		
		sendShare(c, subject, body, null);
	}
	public static void sendShare(Context c,String subject, String body, String type) {

		if(c == null)
			return;
		
		final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        try {
            if (type == null || type.length() == 0) {
                type = "text";
            }
            shareIntent.setType(type + "/*");
            if (subject != null && subject.length() > 0) {
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            }
            if (body != null && body.length() > 0) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, body);
            }
//            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(Intent.createChooser(shareIntent, ""));
        } catch (Exception e) {
        	Toast.makeText(c, e.getMessage(), Toast.LENGTH_LONG);
        }
    }
	
	public static Boolean IsNullOrEmpty(String s){
		return ((s == null) || (s.length()==0));
	}
	
	public static Map<String,List<Locale> > getAvaliableLocales(List<String> locales){
		Map<String,List<Locale> > ret = new HashMap<String,List<Locale> >();
		
		Locale[] locs = Locale.getAvailableLocales();

		String language;
		List<Locale> v;
		
		List<String> containers = new ArrayList<String>();
		
		//String country;
		for(String locale : locales){
			int pos = locale.replace('-', '_').indexOf('_');
			if(pos > 0){
				language = locale.substring(0,pos);
				//country = locale.substring(pos);
			}else{
				language = locale;
				//country = "";				
			}
			
			if(containers.contains(language))
				continue;
			containers.add(language);
				
			for(Locale loc : locs){
				if(loc.getLanguage().equals(language) && loc.getCountry().equals(""))
				{
					v = new ArrayList<Locale>();
					ret.put(language, v);
					v.add(loc);
				}
			}
		}
		
		return ret;
	}
	
	public static String GetPageContent(String pageURL)
	{
		return _GetRemotePageContent("GET",pageURL,null);
	}

	public static void GetPageContentAsync(final String pageURL)
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				_GetRemotePageContent("GET",pageURL,null);
			}
		}).start();
	}
	
	public static String GetPostPageContent(String pageURL,Map<String,String> parameters)
	{
		return _GetRemotePageContent("POST",pageURL,parameters);
	}
	
	// private static String s_ErrorMsg = "";
	private static String _GetRemotePageContent(String method,String pageURL,Map<String,String> parameters)
	{
		String pageContent="";
		BufferedReader in = null;
		InputStreamReader isr = null;
		InputStream is = null;
		HttpURLConnection huc = null;
		
		try
		{

			String strparam = "";
			
			if(parameters != null){
				for(Map.Entry<String,String> item : parameters.entrySet()){
					if(strparam != "")
						strparam += "&";
					strparam += URLEncoder.encode(item.getKey(), "UTF-8") + "=" + URLEncoder.encode(item.getValue(), "UTF-8");
				}
			}
			
			if(!IsNullOrEmpty(strparam) && method.equals("GET")){
				if(pageURL.contains("?"))
					pageURL += "&";
				else
					pageURL += "?";

				pageURL += strparam;
			}
			
			URL url = new URL(pageURL);
			huc = (HttpURLConnection)url.openConnection();

			huc.setRequestMethod(method);
			
			if(method.equals("POST")){
				huc.setDoOutput(true);
				huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				
				OutputStreamWriter request = new OutputStreamWriter(huc.getOutputStream());
				
	            request.write(strparam);
	            request.flush();
	            request.close();
			}
			
			is = huc.getInputStream();
			
			isr = new InputStreamReader(is,"UTF-8");
			in = new BufferedReader(isr);
			String line = null;
			while(((line = in.readLine()) != null))
			{
				if(line.length()==0)
	                continue;
				pageContent+=line; // + "\n";
			}   
       }
       catch (Exception e)
       {
    	   // s_ErrorMsg = e.getMessage();
    	   // BaseApplication.getHandler().post(new Runnable(){
       	 //    	public void run() {
       	    		// Toast.makeText(BaseApplication.getTopActivity(), s_ErrorMsg, Toast.LENGTH_LONG).show();
       	 //    	}   
       	 // 	});
    	  // e.printStackTrace();
		   StatAnalytics.exception(e.getMessage());
       }
		finally
        {
              try
              {
            	  is.close(); isr.close();in.close();huc.disconnect();
              }
              catch (Exception e) {}
       }
      return pageContent;
	}
	
	public static String saveHttpImgDataToFile(String serveruri,File file){
		String ret = null;
		try {
			URL url = new URL(serveruri);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        if (conn.getResponseCode() == 200) {

	            InputStream is = conn.getInputStream();
	            // if(!file.isFile())
	            //	file.createNewFile();
	            FileOutputStream fos = new FileOutputStream(file);
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	                fos.write(buffer, 0, len);
	            }
	            is.close();
	            fos.close();
	            
	        }else{
	        	FileHelper.moveAssertToImgPath("ico/empty.png",file);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

        ret = file.getPath();
        return ret;
	}
	
	public static String getHtml(String url) {
        return GetPageContent(url);
    }
	
	public static String filterTag(String html){
		if(html == null || html.equals(""))
			return "";
		return html;
	}
	
	private static void _hideIM(View edt){

		// try to hide input_method:
		try {
			InputMethodManager im = (InputMethodManager) AppVar.getAppContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
	
			IBinder windowToken = edt.getWindowToken();
			if(windowToken != null) {
	
				// always de-activate IM
				im.hideSoftInputFromWindow(windowToken, 0);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static OnFocusChangeListener focus_listener_noIM = new OnFocusChangeListener(){
	
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus==true) {
				_hideIM(v);
			}
		}
	};

	private static OnTouchListener touch_listener_noIM = new OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN) {
				_hideIM(v);
				return false;
			}
			return v.performClick();
		}
	};
	
	public static void hideIM(EditText edt){
		edt.setOnFocusChangeListener(focus_listener_noIM);
		edt.setOnTouchListener(touch_listener_noIM);
	}
	
//	public static String getPlainTextFromUrl( String url ){
//		String ret = null;
//
//		do{
//			RemoteContentInfo info = getContentFromUrl( url );
//			if(info == null)
//				break;
//
//			if(info.content == null || info.content.length == 0)
//				break;
//
//			String encoding = null;
//
//			int bodyindx = 0;
//			if(IsNullOrEmpty(encoding)){
//				String tmp = (new String(info.content)).toLowerCase(Locale.ENGLISH);
//				bodyindx = tmp.indexOf("<body");
//				if(bodyindx > 0){
//					int charsetindx = tmp.indexOf("charset=");
//					if(charsetindx>0){
//						char c;
//						int charsetidx = charsetindx+"charset=".length();
//						for(int i=charsetidx;i<bodyindx;++i){
//							c = tmp.charAt(i);
//
//							if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-')
//								continue;
//
//							encoding = tmp.substring(charsetidx,i);
//							break;
//						}
//					}
//				}
//			}
//
//			if(IsNullOrEmpty(encoding))
//				encoding = info.contentEncoding;
//
//			// if(IsNullOrEmpty(encoding))
//			//	encoding = "UTF-8";
//
//			try {
//				if(bodyindx < 0)
//					bodyindx = 0;
//
//				if(IsNullOrEmpty(encoding))
//					ret = (new String(info.content));
//				else
//					ret = (new String(info.content,encoding));
//
//				String tmp = ret.toLowerCase(Locale.ENGLISH);
//				bodyindx = tmp.indexOf("<body");
//				if(bodyindx > 0)
//					ret = ret.substring(bodyindx);
//
//				 String regEx_html = "<[^>]+>";
//				 Pattern p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE + Pattern.MULTILINE);
//		         Matcher m_html = p_html.matcher(ret);
//		         ret = m_html.replaceAll("");
//			} catch (Exception e) {
//				e.printStackTrace();
//				ret = new String(info.content);
//			}
//		}while(false);
//
//		return ret;
//	}
	
//	public static RemoteContentInfo getContentFromUrl( String url )
//    {
//		RemoteContentInfo ret  = null;
//
//        int recvcode = 0;
//        int recvidx = 0;
//        int length = 0;
//
//        URL urlx = null;
//        URLConnection con = null;
//        InputStream stream = null;
//        try
//        {
//            urlx = new URL( url );
//
//            con = urlx.openConnection();
//
//            con.setConnectTimeout( 20000 );
//
//            con.setReadTimeout( 20000 );
//
//            stream = con.getInputStream();
//
//            length = con.getContentLength();
//            if(length == 0)
//            	return ret;
//
//
//            ret = new RemoteContentInfo();
//            ret.contentEncoding = con.getContentEncoding();
//            ret.httpContentType = con.getContentType();
//            ret.content = new byte[length];
//
//            while ( ( recvcode = stream.read(ret.content, recvidx, length) ) != -1 )
//            {
//            	recvidx += recvcode;
//            	if(recvidx >= length)
//            		break;
//            }
//        }
//        catch ( Exception e )
//        {
//            Log.e( "exception : ", e.getMessage()+"" );
//
//        }
//        finally
//        {
//            try
//            {
//                if( stream != null )
//                {
//                    stream.close();
//                }
//            }
//            catch ( Exception e )
//            {
//				StatAnalytics.exception(e.getMessage());
//				e.printStackTrace();
//            }
//
//        }
//
//        return ret;
//    }
}

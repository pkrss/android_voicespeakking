package com.pkrss.module.tts.pkrss.dao;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.pkrss.data.bean.RemoteCacheData;
import com.pkrss.core.WebHelper;
import com.pkrss.i.IRemoteCacheDataListener;
import com.pkrss.tts.pkrss.PkrssTTS;
import com.pkrss.data.BaseUserData;

public final class RemoteCacheDao {
	public interface IRemoteCacheDataListener{
		void onResult(String content);
	}


	public static void getTTSList(String url,IRemoteCacheDataListener listener){
		getRemoteOrCacheDataAsync(listener, url,3); // PkrssTTS.getBaseurl() +  "?task=getTTSList"
	}
	
	private static void getRemoteOrCacheDataAsync(IRemoteCacheDataListener listener,String url,int days){
		RemoteCacheData remoteCacheData = BaseUserData.BaseInstance().getRemoteCacheData(url);

		if(remoteCacheData != null){
			if(listener != null){
				listener.onResult(remoteCacheData.content);
				listener = null;
			}
		}
		
		final IRemoteCacheDataListener m_ttslist_listener = listener;
		final String m_ttslist_url = url;
		final int m_ttslist_days = days;
		
		Thread t = new Thread(){
			@Override
			public void run(){
				getRemoteOrCacheData(m_ttslist_listener,m_ttslist_url,m_ttslist_days);
			}
		};
		t.start();
	}
	
	private static void getRemoteOrCacheData(IRemoteCacheDataListener listener,String url,int days){

		String ret = null;
		RemoteCacheData remoteCacheData = BaseUserData.BaseInstance().getRemoteCacheData(url);
		String curTime = BaseUserData.getCurTime();
		if(remoteCacheData != null){
			ret = remoteCacheData.content;
			
			try{
				Date curDate = DateFormat.getDateTimeInstance().parse(curTime);
				Date oldDate = DateFormat.getDateTimeInstance().parse(remoteCacheData.createTime);
				
				Calendar curCalendar=Calendar.getInstance();
				Calendar oldCalendar=Calendar.getInstance();
				curCalendar.setTime(curDate);
				oldCalendar.setTime(oldDate);
				
				oldCalendar.add(Calendar.DAY_OF_MONTH,days);
				
				if(oldCalendar.compareTo(curCalendar)<0){
					remoteCacheData.content = null;
				}
			}catch(Exception e){
				remoteCacheData.content = null;
			}
		}
		if((remoteCacheData == null) || (remoteCacheData.content == null)){
			ret = WebHelper.GetPageContent(url);
			
			if(!WebHelper.IsNullOrEmpty(ret)){
				if(remoteCacheData == null){
					remoteCacheData = new RemoteCacheData();
					remoteCacheData.url = url;
				}
				remoteCacheData.content = ret;
				remoteCacheData.createTime = curTime;
				BaseUserData.BaseInstance().setRemoteCacheData(remoteCacheData);				
			}
		}
		
		if(listener != null)
			listener.onResult(ret);
	}
}

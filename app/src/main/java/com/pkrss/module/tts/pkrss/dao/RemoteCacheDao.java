package com.pkrss.module.tts.pkrss.dao;

import com.pkrss.common.helper.WebHelper;
import com.pkrss.voicespeakking.db.dao.RemoteCacheDataDao;
import com.pkrss.voicespeakking.db.model.RemoteCacheData;
import com.pkrss.voicespeakking.db.util.DbCore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class RemoteCacheDao {

	public interface IRemoteCacheDataListener{
		void onResult(String content);
	}

	public static void getTTSList(String url,IRemoteCacheDataListener listener){
		getRemoteOrCacheDataAsync(listener, url,3); // PkrssTTS.getBaseurl() +  "?task=getTTSList"
	}
	
	private static void getRemoteOrCacheDataAsync(IRemoteCacheDataListener listener,String url,int days){

		RemoteCacheData remoteCacheData = null;

		RemoteCacheDataDao remoteCacheDataDao = DbCore.getDaoSession().getRemoteCacheDataDao();
		List<RemoteCacheData> remoteCacheDataList = remoteCacheDataDao.queryBuilder().where(RemoteCacheDataDao.Properties.Url.eq(url)).list();
		if(remoteCacheDataList!=null && remoteCacheDataList.size()>0)
			remoteCacheData = remoteCacheDataList.get(0);

		if(remoteCacheData != null){
			if(listener != null){
				listener.onResult(remoteCacheData.getContent());
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

		RemoteCacheData remoteCacheData = null;

		RemoteCacheDataDao remoteCacheDataDao = DbCore.getDaoSession().getRemoteCacheDataDao();
		List<RemoteCacheData> remoteCacheDataList = remoteCacheDataDao.queryBuilder().where(RemoteCacheDataDao.Properties.Url.eq(url)).list();
		if(remoteCacheDataList!=null && remoteCacheDataList.size()>0)
			remoteCacheData = remoteCacheDataList.get(0);

		String ret = null;
		if(remoteCacheData != null){
			ret = remoteCacheData.getContent();
			
			try{
				Date curDate = new Date();
				Date oldDate = remoteCacheData.getCreateTime();
				
				Calendar curCalendar=Calendar.getInstance();
				Calendar oldCalendar=Calendar.getInstance();
				curCalendar.setTime(curDate);
				oldCalendar.setTime(oldDate);
				
				oldCalendar.add(Calendar.DATE, days);
				
				if(oldCalendar.compareTo(curCalendar)<0){
					remoteCacheData.setContent(null);
				}
			}catch(Exception e){
				remoteCacheData.setContent(null);
			}
		}
		if((remoteCacheData == null) || (remoteCacheData.getContent() == null)){
			ret = WebHelper.GetPageContent(url);
			
			if(ret!=null && ret.length()>0){
				if(remoteCacheData == null){
					remoteCacheData = new RemoteCacheData();
					remoteCacheData.setUrl(url);
				}
				remoteCacheData.setContent(ret);
				remoteCacheData.setCreateTime(new Date());

				remoteCacheDataDao.insertOrReplace(remoteCacheData);
			}
		}

		if (listener != null)
			listener.onResult(ret);
	}
}

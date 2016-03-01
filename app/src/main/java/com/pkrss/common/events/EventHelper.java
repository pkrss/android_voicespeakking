package com.pkrss.common.events;

import android.os.Handler;

import com.pkrss.module.StatAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class EventHelper {

    private final boolean debugMode = true;
    private final Handler handler = new Handler();
	
	public interface ICallback{
		void onCallback(Object cbParam);
	}
	
	private static final class P{
		String eventName;
		ICallback cb;
	}
	
	private Map<Object,List<P> > _data = new HashMap<Object,List<P> >();
	private static EventHelper _instance;

	private EventHelper(){
	}

	public static EventHelper Instance(){
		if(_instance == null)
			_instance = new EventHelper();
		return _instance;
	}

	public void add(String eventName,ICallback cb){
		add(null, eventName, cb);
	}

	public void add(Object key,String eventName,ICallback cb){
		if(key == null)
			key = "_unspecify";

		P e = new P();
		e.cb = cb;
		e.eventName = eventName;
		List<P> l = _data.get(key);
		if(l == null){
			l = new ArrayList<P>();
			l.add(e);
			_data.put(key,l);
		}else{
			l.add(e);
		}
	}
	
	public void remove(Object key){
		_data.remove(key);
	}
	
	private static final class MyRunnable implements Runnable{
		private String eventName;
		private Object p1;
		
		@Override
		public void run() {
			EventHelper.Instance().notify(eventName,p1);
		}
	}
	
	public void notifyOnMainThread(String eventName,Object p1){
		MyRunnable t = new MyRunnable();
		t.eventName = eventName;
		t.p1 = p1;
        handler.post(t);
	}
	
	public void notify(String eventName,Object p1){
		try {
			for (Entry<Object, List<P>> a : _data.entrySet()) {
				for (P b : a.getValue()) {
					if (b.eventName.equals(eventName))
						b.cb.onCallback(p1);
				}
			}
		}catch (Exception e){
			StatAnalytics.exception(e.getMessage());

			if(debugMode)
				throw e;
		}
	}
}

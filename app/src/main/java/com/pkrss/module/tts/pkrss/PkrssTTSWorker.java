package com.pkrss.module.tts.pkrss;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by liand on 2016/3/24.
 */
public class PkrssTTSWorker {

//	static ProgressDialog progressDialog;

//	private Boolean mSubscribedPkrssTTS = false;
//	static IBillingSubscribeListener mSubscribePkrssTTSListener;
//	static TTSPurchaseCallback mBuyCallback;

    private PkrssTTS self;

    private JSONArray mPkrssEngineList;

//	public static void setTTSPurchaseCallback(TTSPurchaseCallback buyCallback){
//		mBuyCallback = buyCallback;
//	}

    @Override
    public boolean init(Context context,String engineName){

        super.init(context, engineName);

        self = this;

        mcurEngineName = engineName;

        if(mMediaPlayer==null) {
            mMediaPlayer = new MediaPlayer();

            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if(mMediaPlayer!=null && mMediaPlayer.equals(mp))
                        mp.start();
                }
            });

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer arg0) {
                    _speakNext();
                }
            });

            //		mMediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener(){
            //
            //			@Override
            //			public void onBufferingUpdate(MediaPlayer mp, int percent) {
            //				if(progressDialog != null){
            //					progressDialog.setProgress(percent);
            //					if(percent > 95){
            //						progressDialog.dismiss();
            //						progressDialog = null;
            //					}
            //				}
            //			}
            //
            //		});
        }

        if(mPkrssEngineList==null)
            initTTS();
        else{
            BaseApplication.getHandler().postDelayed(new Runnable() {
                public void run() {
                    _init_locale_and_engine_cb();
                }
            }, 2000);
        }

        return true;
    }

    private void initTTS(){
        RemoteCacheDataHelper.getTTSList(new IRemoteCacheDataListener() {

            @Override
            public void onResult(String content) {

                do {
                    if (WebHelper.IsNullOrEmpty(content))
                        break;

                    try {
                        mPkrssEngineList = new JSONArray(content);

                        engineInfos = new ArrayList<TTSEngineInfo>();

                        String country2,language;
                        int pos;

                        JSONObject obj,obj2;
                        JSONArray ary;

                        for(int i=0,c=mPkrssEngineList.length();i<c;++i) {

                            obj = mPkrssEngineList.optJSONObject(i);
                            if (obj == null)
                                continue;

                            language = obj.optString("locale");
                            if (language == null)
                                continue;

                            country2 = "";
                            pos = language.replace('-', '_').indexOf('_');
                            if(pos > 0){
                                country2 = language.substring(pos+1);
                                language = language.substring(0,pos);
                            }

                            ary = obj.optJSONArray("tts");
                            if( ary != null){
                                for(int i2=0,c2=ary.length();i2<c2;++i2){
                                    obj2 = ary.optJSONObject(i2);
                                    if(obj2 == null)
                                        continue;

                                    String id = obj2.optString("id");
                                    if(id == null)
                                        continue;
                                    String name = obj2.optString("name");
                                    if(name == null)
                                        continue;

                                    TTSEngineInfo ttsEngineInfo = new TTSEngineInfo(id,name,language,new Locale(language, country2));
                                    engineInfos.add(ttsEngineInfo);
                                }
                            }
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } while (false);

                BaseApplication.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        _init_locale_and_engine_cb();
                    }
                },2000);
            }

        });
    }

    private void _init_locale_and_engine_cb(){
        // wait for local engine to first loaded.

        ArrayAdapter<Object> enginesLocalAdapterList =  TTSModule.getInstance().getEnginesLocalesAdapterList();
        if(enginesLocalAdapterList == null)
            return;

        do{
            if(mPkrssEngineList == null)
                break;

            String country2,language;
            int pos;

            JSONObject obj;

            for(int i=0,c=mPkrssEngineList.length();i<c;++i){

                obj = mPkrssEngineList.optJSONObject(i);
                if(obj == null)
                    continue;

                country2 = obj.optString("locale");
                if(country2 == null)
                    continue;

                language = "";
                pos = country2.replace('-', '_').indexOf('_');
                if(pos > 0){
                    language = country2.substring(pos+1);
                    country2 = country2.substring(0,pos);
                }

                TTSModule.getInstance().addEnginesLocalAdapterList(new Locale(country2, language));
            }

        }while(false);

        TTSModule.getInstance().sortEnginesLocaleAndRefreshEngines();
    }

    @Override
    public boolean destroy() {

//		if(progressDialog != null){
//			progressDialog.dismiss();
//			progressDialog = null;
//		}

        super.destroy();

        self = null;

        if(mMediaPlayer == null)
            return false;
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;

        return true;
    }

    public static final String getBaseurl(){
        return "http://wincdn.pkrss.com:8080/ttsservice/ajax/tts.ashx";
    }

    protected void _onStop() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying())
            mMediaPlayer.pause();
        _text = null;
    }

    private static String _text = null;
    @Override
    protected void _onDoSub(String text) {

        if(text == null)
            return;

        _text = text;

        Thread t = new Thread(){
            @Override
            public void run(){
                if(_text!=null && _text.length() > 0)
                    _sayTextThread(_text);
            }
        };
        t.start();
    }


    private void _sayTextThread(String text){
        if(text == null || text.length()==0)
            return;

        Map<String,String> param = new HashMap<String,String>();
        param.put("task", "getfileurl");
        param.put("tts", mcurEngineName);
        param.put("text", text);

        BaseApplication.getHandler().post(new Runnable() {
            public void run() {
                Toast.makeText(BaseApplication.AppContext, BaseApplication.AppContext.getResources().getString(R.string.text_getdownloadlink), Toast.LENGTH_SHORT).show();
            }
        });

        String content = WebHelper.GetPostPageContent(getBaseurl(), param);

        if(text != _text)
            return;

        try {
            JSONObject obj = new JSONObject(content);
            if(obj != null){
                String url = obj.optString("url");
                if(url != null && url.length()>0){

                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(url);

//					BaseApplication.getHandler().post(new Runnable(){
//
//						@Override
//						public void run() {
//							if(progressDialog == null){
//								try{
//									progressDialog = new ProgressDialog(BaseApplication.AppContext);
//
//									progressDialog.setMax(100);
//
//									progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//									progressDialog.setTitle(BaseApplication.AppContext.getResources().getString(R.string.text_downloading));
//								}catch(Exception e){
//									progressDialog = null;
//								}
//							}
//							if(progressDialog != null)
//								progressDialog.show();
//						}
//
//					});

                    BaseApplication.getHandler().post(new Runnable() {
                        public void run() {
                            Toast.makeText(BaseApplication.AppContext, BaseApplication.AppContext.getResources().getString(R.string.text_downloading), Toast.LENGTH_SHORT).show();
                        }
                    });

                    mMediaPlayer.prepareAsync();
//					mMediaPlayer.start();
                }
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public boolean isSpeaking() {
        return (mMediaPlayer != null) && mMediaPlayer.isPlaying();
    }

    static String mcurEngineName;

    private static MediaPlayer mMediaPlayer;

    @Override
    public Boolean changeEngineByName(String engineName){

        if(!WebHelper.IsNullOrEmpty(mcurEngineName) && engineName.equals(mcurEngineName))
            return true;

        mcurEngineName = engineName;
        return false;
    }


    @Override
    protected int getSubPos(){
        if(isSpeaking())
            return mMediaPlayer.getCurrentPosition();
        return 0;
    }

//	private void _showUIBuyNoticeDialog()
//	{
//		AlertDialog.Builder builder = new Builder(BaseApplication.AppContext);
//
//		builder.setTitle(R.string.text_buy_title);
//
//		String msg = BaseApplication.AppContext.getString(R.string.text_buy_content);
//
//		builder.setMessage(msg);
//
//		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//			 public void onCancel(DialogInterface dialog) {
//			 }
//			});
//
//		builder.setPositiveButton(android.R.string.ok, new OnClickListener()
//		{
//			public void onClick(DialogInterface dialog, int which)
//			{
//				dialog.dismiss();
//
//				 if(mBuyCallback != null)
//					 mBuyCallback.onBuy();
//			}
//		});
//
//		builder.setNegativeButton(android.R.string.cancel, new OnClickListener()
//		{
//			public void onClick(DialogInterface dialog, int which)
//			{
//				dialog.dismiss();
//			}
//		});
//		try{
//			Dialog noticeDialog = builder.create();
//			noticeDialog.show();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

    //	public static IBillingSubscribeListener getBillingSubscribeListener(){
//
//		if(mSubscribePkrssTTSListener == null){
//			mSubscribePkrssTTSListener = new IBillingSubscribeListener(){
//
//				@Override
//				public void onSubscribeResult(Boolean result) {
//					mSubscribedPkrssTTS = result;
//					if(result){
//						if(self != null)
//							self._sayText();
//					}else{
//						BaseApplication.getHandler().post(new Runnable(){
//				    	    public void run() {
//				    	    	Toast.makeText(BaseApplication.AppContext, BaseApplication.AppContext.getResources().getString(R.string.text_error_unknown), Toast.LENGTH_LONG).show();
//				    	    }
//				    	 });
//					}
//				}
//
//			};
//		}
//
//		return mSubscribePkrssTTSListener;
//	}
    @Override
    public String getShowName() {
        return "_pkrss";
    }
}

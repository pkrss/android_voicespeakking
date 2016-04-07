package com.pkrss.module.tts.pkrss;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.pkrss.common.base.AppVar;
import com.pkrss.common.helper.WebHelper;
import com.pkrss.module.tts.common.BaseTTS;
import com.pkrss.module.tts.pkrss.dao.RemoteCacheDao;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.adapter.PkrssTTSAdapter;
import com.pkrss.voicespeakking.common.ETTSEngineIdenty;
import com.pkrss.voicespeakking.data.SpData;
import com.pkrss.voicespeakking.fragment.TTSTabPkrssFragment;
import com.pkrss.voicespeakking.model.TTSTabPkrssModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by liand on 2016/3/24.
 */
public final class PkrssTTSWorker extends BaseTTS {

//	static ProgressDialog progressDialog;

//	private Boolean mSubscribedPkrssTTS = false;
//	static IBillingSubscribeListener mSubscribePkrssTTSListener;
//	static TTSPurchaseCallback mBuyCallback;


//	public static void setTTSPurchaseCallback(TTSPurchaseCallback buyCallback){
//		mBuyCallback = buyCallback;
//	}

    @Override
    public boolean init(Context context){

        super.init(context);

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

        return true;
    }

    public interface ITTSListener{
        void onCallback(Collection<PkrssTTSAdapter.PkrssTTSModel> data);
    }

    public static void initTTSListLocale(final ITTSListener ttsListener){ // final PkrssTTSAdapter spinnerLocalAdapter
        RemoteCacheDao.getTTSList(getBaseurl() +  "?task=getTTSList", new RemoteCacheDao.IRemoteCacheDataListener() {

            @Override
            public void onResult(String content) {

                final List<PkrssTTSAdapter.PkrssTTSModel> data = new ArrayList<PkrssTTSAdapter.PkrssTTSModel>();
                data.clear();
                do {
                    if (WebHelper.IsNullOrEmpty(content))
                        break;

                    try {
                        JSONArray pkrssEngineList = new JSONArray(content);

                        if(pkrssEngineList == null)
                            break;

                        Map<String,String> curLocaleEngineList = new HashMap<String,String>();
                        String curBestEngine = null;

                        Locale curLocale = Locale.getDefault();
                        String curCountry = curLocale.getCountry();
                        String curLanguage = curLocale.getLanguage();

                        String curEngineId = SpData.getTTSPkrssVoicer();

                        String country, language;
                        int pos;

                        JSONObject obj, obj2;
                        JSONArray ary;

                        boolean curLocalePutFront = false;

                        for (int i = 0, c = pkrssEngineList.length(); i < c; ++i) {

                            obj = pkrssEngineList.optJSONObject(i);
                            if (obj == null)
                                continue;

                            language = obj.optString("locale");
                            if (language == null)
                                continue;

                            country = "";
                            pos = language.replace('-', '_').indexOf('_');
                            if (pos > 0) {
                                country = language.substring(pos + 1);
                                language = language.substring(0, pos);
                            }

                            boolean isCurrentEngineLocale = false;

                            if(!curLocalePutFront) {
                                if (curEngineId == null || curEngineId.length() == 0) {
                                    if (curLanguage.equals(language)) {
                                        if (country.equals(curCountry)) {
//                                            ary = obj.optJSONArray("tts");
//                                            if (ary != null && ary.length() > 0) {
//                                                obj2 = ary.optJSONObject(0);
//                                                curEngineId = obj2.optString("id");
//                                                SpData.setTTSPkrssVoicer(curEngineId);
                                                isCurrentEngineLocale = true;
//                                            }
                                        }
                                    }
                                } else {
                                    ary = obj.optJSONArray("tts");
                                    if (ary != null) {
                                        for (int i2 = 0, c2 = ary.length(); i2 < c2; ++i2) {
                                            obj2 = ary.optJSONObject(i2);
                                            if (obj2 == null)
                                                continue;

                                            String id = obj2.optString("id");
                                            if (id == null)
                                                continue;

                                            if (curEngineId.equals(id)) {
                                                isCurrentEngineLocale = true;
                                            }
                                        }
                                    }
                                }
                            }

                            Locale loc = new Locale(language, country);
                            PkrssTTSAdapter.PkrssTTSModel pkrssTTSModel = new PkrssTTSAdapter.PkrssTTSModel();

                            String id = language;
                            if(country!=null && country.length()>0)
                                id += "_" + country;
                            pkrssTTSModel.setId(id);
                            pkrssTTSModel.setTitle(loc.getDisplayName());

                            if(isCurrentEngineLocale) {
                                data.add(0, pkrssTTSModel);
                                curLocalePutFront = true;
                            }else {
                                data.add(pkrssTTSModel);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (false);

                AppVar.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if(ttsListener!=null){
                            ttsListener.onCallback(data);
                        }
                    }
                });
            }

        });
    }

    public static void initTTSListEngine(final String localeId, final ITTSListener ttsListener){ // final PkrssTTSAdapter spinnerLocalAdapter
        RemoteCacheDao.getTTSList(getBaseurl() +  "?task=getTTSList", new RemoteCacheDao.IRemoteCacheDataListener() {

            @Override
            public void onResult(String content) {

                final List<PkrssTTSAdapter.PkrssTTSModel> data = new ArrayList<PkrssTTSAdapter.PkrssTTSModel>();
                data.clear();
                do {
                    if (WebHelper.IsNullOrEmpty(content))
                        break;

                    try {
                        JSONArray pkrssEngineList = new JSONArray(content);

                        if(pkrssEngineList == null)
                            break;

                        PkrssTTSAdapter.PkrssTTSModel bestPkrssTTSModel = null;

                        String curCountry = "";
                        String curLanguage = "";
                        int pos;

                        pos = localeId.replace('-', '_').indexOf('_');
                        if (pos > 0) {
                            curCountry = localeId.substring(pos + 1);
                            curLanguage = localeId.substring(0, pos);
                        }

                        String curEngineId = SpData.getTTSPkrssVoicer();

                        String country, language;

                        JSONObject obj, obj2;
                        JSONArray ary;

                        for (int i = 0, c = pkrssEngineList.length(); i < c; ++i) {

                            obj = pkrssEngineList.optJSONObject(i);
                            if (obj == null)
                                continue;

                            language = obj.optString("locale");
                            if (language == null)
                                continue;

                            country = "";
                            pos = language.replace('-', '_').indexOf('_');
                            if (pos > 0) {
                                country = language.substring(pos + 1);
                                language = language.substring(0, pos);
                            }

                            if(!language.equals(curLanguage))
                                continue;

                            ary = obj.optJSONArray("tts");
                            if (ary != null) {
                                for (int i2 = 0, c2 = ary.length(); i2 < c2; ++i2) {
                                    obj2 = ary.optJSONObject(i2);
                                    if (obj2 == null)
                                        continue;

                                    String id = obj2.optString("id");
                                    if (id == null)
                                        continue;
                                    String name = obj2.optString("name");
                                    if (name == null)
                                        continue;

                                    PkrssTTSAdapter.PkrssTTSModel pkrssTTSModel = new PkrssTTSAdapter.PkrssTTSModel();
                                    pkrssTTSModel.setId(id);
                                    pkrssTTSModel.setTitle(name);

                                    if(id.equals(curEngineId)){
                                        bestPkrssTTSModel = pkrssTTSModel;
                                        continue;
                                    }

                                    if (curCountry.equals(country)) {
                                        data.add(0, pkrssTTSModel);
                                    } else {
                                        data.add(pkrssTTSModel);
                                    }
                                }
                            }
                        }

                        if(bestPkrssTTSModel!=null){
                            data.add(0, bestPkrssTTSModel);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (false);

                AppVar.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if(ttsListener!=null){
                            ttsListener.onCallback(data);
                        }
                    }
                });
            }

        });
    }

//    private void _init_locale_and_engine_cb(){
//        // wait for local engine to first loaded.
//
//        ArrayAdapter<Object> enginesLocalAdapterList =  TTSModule.getInstance().getEnginesLocalesAdapterList();
//        if(enginesLocalAdapterList == null)
//            return;
//
//        do{
//            if(mPkrssEngineList == null)
//                break;
//
//            String country2,language;
//            int pos;
//
//            JSONObject obj;
//
//            for(int i=0,c=mPkrssEngineList.length();i<c;++i){
//
//                obj = mPkrssEngineList.optJSONObject(i);
//                if(obj == null)
//                    continue;
//
//                country2 = obj.optString("locale");
//                if(country2 == null)
//                    continue;
//
//                language = "";
//                pos = country2.replace('-', '_').indexOf('_');
//                if(pos > 0){
//                    language = country2.substring(pos+1);
//                    country2 = country2.substring(0,pos);
//                }
//
//                TTSModule.getInstance().addEnginesLocalAdapterList(new Locale(country2, language));
//            }
//
//        }while(false);
//
//        TTSModule.getInstance().sortEnginesLocaleAndRefreshEngines();
//    }

    @Override
    public boolean destroy() {

//		if(progressDialog != null){
//			progressDialog.dismiss();
//			progressDialog = null;
//		}

        super.destroy();

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
    protected void _child_onDoSub(String text) {

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

    @Override
    protected void _child_onDoStop() {
        if(mMediaPlayer == null)
            return;
        mMediaPlayer.stop();
    }


    private void _sayTextThread(String text){
        if(text == null || text.length()==0)
            return;

        String engineName = SpData.getTTSPkrssVoicer();

        if(engineName == null || engineName.length()==0)
            return;

        Map<String,String> param = new HashMap<String,String>();
        param.put("task", "getfileurl");
        param.put("tts", engineName);
        param.put("text", text);

        AppVar.getHandler().post(new Runnable() {
            public void run() {
                Toast.makeText(AppVar.getAppContext(), AppVar.getAppContext().getResources().getString(R.string.text_getdownloadlink), Toast.LENGTH_SHORT).show();
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

                    AppVar.getHandler().post(new Runnable() {
                        public void run() {
                            Toast.makeText(AppVar.getAppContext(), AppVar.getAppContext().getResources().getString(R.string.text_downloading), Toast.LENGTH_SHORT).show();
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

    @Override
    public void pause() {
        if(mMediaPlayer==null)
            return;
        mMediaPlayer.pause();
    }

    @Override
    public void resume() {
        if(mMediaPlayer==null)
            return;
        mMediaPlayer.start();
    }

    @Override
    public Fragment createOptionFragment() {
        return new TTSTabPkrssFragment();
    }

    private static MediaPlayer mMediaPlayer;

//    @Override
//    public Boolean changeEngineByName(String engineName){
//
//        if(!WebHelper.IsNullOrEmpty(mcurEngineName) && engineName.equals(mcurEngineName))
//            return true;
//
//        mcurEngineName = engineName;
//        return false;
//    }

    @Override
    public int getId() {
        return ETTSEngineIdenty.pkrss;
    }

    @Override
    public String getShowName() {
        return "_pkrss";
    }
}

package com.pkrss.common.helper;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.pkrss.common.base.AppVar;
import com.pkrss.voicespeakking.R;

import java.io.DataOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public final class UtilsHelper{

    public static String getSDCardDir() {
        String ret = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return ret;
    }

    public static String getDownloadDir() {
        String ret = getSDCardDir();
        if (ret.length() > 0)
            ret += "/download";
        return ret;
    }

    public static void openUrl(Context context, String url){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void sendSMS(String phoneNumber, String message) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));//Intent.ACTION_MAIN
            //intent.setData(Uri.parse("content://mms-sms/"));
            intent.putExtra("sms_body", message);
            AppVar.getAppContext().startActivity(intent);
            return;

			/*
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_STREAM, uri);// image uri
			intent.putExtra("subject", subString);
			//intent.putExtra("sms_body", "sdfsdf");
			intent.putExtra(Intent.EXTRA_TEXT, "sdfsdf");
			intent.setType("image/*");// MMS attachment type
			intent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
			startActivity(intent);
			*/

			/*
		    SmsManager manager = SmsManager.getDefault();
		    
		    Intent localIntent = new Intent("SENT_SMS_ACTION");
		    ArrayList<String> messages = manager.divideMessage(message);
	    	int messageCount = messages.size();
	    	ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(messageCount);
	    	for (int i = 0; i < messageCount; i++) {
	    		int requestCode = 0;
	            if (i == messageCount -1) {
	                requestCode = 1;
	            }
	    		sentIntents.add(PendingIntent.getBroadcast(BaseApplication.AppContext, requestCode, localIntent, 0));
	    	}
	    	manager.sendMultipartTextMessage(phoneNumber, null, messages, sentIntents, null);
	    	*/

            //PendingIntent sentIntent = PendingIntent.getActivity(BaseApplication.AppContext, 0, new Intent(), 0);
            //manager.sendTextMessage(phoneNumber, null, message, sentIntent, null); // 70 char limit.

            //ContentValues values = new ContentValues();
            //values.put("address", phoneNumber);
            //values.put("body", message);
            //BaseApplication.AppContext.getContentResolver().insert(Uri.parse("content://sms/sent"), values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SendSMSCallback(Context mContext) {
    	BroadcastReceiver sendReceive = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        //send success
                        break;
                    default:
                        //send fail
                        break;
                }
            }
        };
        IntentFilter sendFilter = new IntentFilter();
        sendFilter.addAction("SENT_SMS_ACTION");
        mContext.registerReceiver(sendReceive, sendFilter);
    }

    public static void sendEmail(String address, String body, String subject, boolean isHTML, String cc, String bcc, String attachment) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        try {
            if (isHTML) {
                emailIntent.setType("text/html");
            } else {
                emailIntent.setType("text/plain");
            }

            if (subject != null && subject.length() > 0) {
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            }
            if (body != null && body.length() > 0) {
                if (isHTML) {
                    emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
                } else {
                    emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                }
            }
            if (address != null && address.length() > 0) {
                emailIntent.putExtra(Intent.EXTRA_EMAIL, address.split(";"));
            } else{
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "pkrss@gmail.com");
            }

            if (cc != null && cc.length() > 0 && cc!="null") {
                emailIntent.putExtra(Intent.EXTRA_CC, address.split(";"));
            }
            if (bcc != null && bcc.length() > 0 && bcc!="null") {
                emailIntent.putExtra(Intent.EXTRA_BCC, address.split(";"));
            }
            if (attachment != null && attachment.length() > 0 && attachment!="null") {
                String[] attachments = attachment.split(";");

                ArrayList<Uri> uris = new ArrayList<Uri>();
                //convert from paths to Android friendly Parcelable Uri's
                for (int i = 0; i < attachments.length; i++) {
                    File file = new File(attachments[i]);
                    if (file.exists()) {
                        uris.add(Uri.fromFile(file));
                    }
                }
                if (uris.size() > 0) {
                    emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                }
            }

            AppVar.getAppContext().startActivity(Intent.createChooser(emailIntent, ""));

        } catch (Exception e) {

        }
    }

    public static void sendShare(String subject, String body, String type) {
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
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AppVar.getAppContext().startActivity(Intent.createChooser(shareIntent, ""));

        } catch (Exception e) {

        }
    }
    
    public static boolean getRootPermission() {
    	return getRootPermission(null);
    }
    
    public static boolean getRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            if(pkgCodePath!=null && pkgCodePath.length()>0)
            	os.writeBytes("chmod 777 " + pkgCodePath + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
            
            Toast.makeText(AppVar.getAppContext(), "root success!" + pkgCodePath, Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(AppVar.getAppContext(), "root error!" + pkgCodePath, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();


            } catch (Exception e) {
            }
        }
        
        return false;
    }
    
    public static String getCurrentDateTimeFileName(){
    	return (new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",Locale.ENGLISH)).format(new Date(System.currentTimeMillis()));
    }
    
    public static void closeSoftKeyboard(View v){
		try{
			InputMethodManager inputmanger = (InputMethodManager) AppVar.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
	        inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}catch(Exception e){
			
		}
	}
}
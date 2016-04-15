package com.pkrss.voicespeakking.handler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.model.MainModel;

/**
 * Created by liand on 2016/2/11.
 */
public final class MainHandler {
    private MainModel mainModel;
    public MainHandler(MainModel mainModel){
        this.mainModel = mainModel;
    }

    private PlayerBarHandler playerBarHandler;

    public PlayerBarHandler getPlayerBarHandler() {
        if(playerBarHandler==null)
            playerBarHandler = new PlayerBarHandler(mainModel);
        return playerBarHandler;
    }

    public void clickEditText(Context c){

//        Context c = v.getContext();

        final EditText et = new EditText(c);
        et.setText(mainModel.getContentModel().getContent());
        new AlertDialog.Builder(c).setTitle(R.string.edit).setIcon(
                android.R.drawable.ic_dialog_info).setView(et).setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String content = et.getText().toString();

                            mainModel.getContentModel().setContent(content);

                            dialog.dismiss();
                        }
                    })
                .setNegativeButton(android.R.string.cancel, null).show();
    }
}

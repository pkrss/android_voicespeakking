package com.pkrss.voicespeakking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by liand on 2016/2/13.
 */
public final class PkrssTTSAdapter extends ArrayAdapter<PkrssTTSAdapter.PkrssTTSModel> {
    public PkrssTTSAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(getItem(position).title);
        return textView;
    }

    public static final class PkrssTTSModel{
        private Locale locale;
        private String id;
        private String title;

        public Locale getLocale() {
            return locale;
        }

        public void setLocale(Locale locale) {
            this.locale = locale;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

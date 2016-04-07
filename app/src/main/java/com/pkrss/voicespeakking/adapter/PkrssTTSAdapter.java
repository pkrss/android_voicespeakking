package com.pkrss.voicespeakking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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
    private final int mFieldId = 0;

    private int mResource;

    private final LayoutInflater mInflater;

    public PkrssTTSAdapter(Context context) {
        this(context, android.R.layout.simple_spinner_item);
    }

    public PkrssTTSAdapter(Context context, int resource) {
        super(context, resource);
        mResource = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(mInflater, position, convertView, parent, mResource);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        final LayoutInflater inflater = mDropDownInflater == null ? mInflater : mDropDownInflater;
        return createViewFromResource(mInflater, position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
    }

    private View createViewFromResource(LayoutInflater inflater, int position, View convertView,
                                        ViewGroup parent, int resource) {
        View view;
        TextView text;

        if (convertView == null) {
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = (TextView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                text = (TextView) view.findViewById(mFieldId);
            }
        } catch (Exception e) {
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e);
        }

        PkrssTTSModel obj = getItem(position);

        text.setText(obj.getTitle());

        return view;
    }

    public static final class PkrssTTSModel{
//        private Locale locale;
        private String id;
        private String title;

//        public Locale getLocale() {
//            return locale;
//        }
//
//        public void setLocale(Locale locale) {
//            this.locale = locale;
//        }
//

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

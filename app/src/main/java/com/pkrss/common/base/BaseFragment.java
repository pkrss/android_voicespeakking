package com.pkrss.common.base;

import android.support.v4.app.Fragment;

import com.pkrss.common.events.EventConstants;
import com.pkrss.common.events.EventHelper;

public abstract class BaseFragment extends Fragment {


    private boolean injected = false;

	@Override
	public void onPause() {
		EventHelper.Instance().notify(EventConstants.Fragment.Pause, this);
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		EventHelper.Instance().notify(EventConstants.Fragment.Resume, this);
	}

}

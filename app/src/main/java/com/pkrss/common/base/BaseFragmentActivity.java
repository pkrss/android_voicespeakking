package com.pkrss.common.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.pkrss.common.events.EventConstants;
import com.pkrss.common.events.EventHelper;
import com.pkrss.common.helper.ActivityHelper;

public abstract class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityHelper.getInstance().addActivity(this);
		EventHelper.Instance().notify(EventConstants.Activity.Create, this);
	}

	@Override
	protected void onDestroy() {
		ActivityHelper.getInstance().removeActivity(this);
		EventHelper.Instance().notify(EventConstants.Activity.Destroy, this);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		EventHelper.Instance().notify(EventConstants.Activity.Resume, this);
	}
	@Override
	protected void onPause() {
		EventHelper.Instance().notify(EventConstants.Activity.Pause, this);
		super.onPause();
	}

}

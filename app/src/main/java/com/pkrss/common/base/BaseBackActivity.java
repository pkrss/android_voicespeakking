package com.pkrss.common.base;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.pkrss.common.events.EventConstants;
import com.pkrss.common.events.EventHelper;
import com.pkrss.common.helper.ActivityHelper;

public abstract class BaseBackActivity extends BaseActivity {

	private boolean _inited_actionbar = false;
	@Override
	protected void onResume() {

		if(!_inited_actionbar) {
			_inited_actionbar = true;

			ActionBar actionBar = getActionBar();
			if (actionBar == null)
				return;
//        android.support.v7.app.ActionBar actionBar2 = this.getSupportActionBar();
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(false);
		}

		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id==android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

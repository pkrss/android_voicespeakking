package com.pkrss.voicespeakking.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.pkrss.common.base.AppVar;
import com.pkrss.common.base.BaseActivity;
import com.pkrss.module.TTSModule;
import com.pkrss.voicespeakking.R;
import com.pkrss.voicespeakking.data.SpData;
import com.pkrss.voicespeakking.databinding.ActivityMainBinding;
import com.pkrss.voicespeakking.handler.MainHandler;
import com.pkrss.voicespeakking.model.MainModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MainModel mainModel;
    private MainHandler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppVar.setAppContext(this.getApplicationContext());
        SpData.init(this);
        TTSModule.getInstance().init(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainModel = new MainModel();
        mainModel.setActivity(this);
        mainHandler = new MainHandler(mainModel);
        binding.setMainModel(mainModel);
        binding.setMainHandler(mainHandler);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onDestroy() {

        TTSModule.getInstance().uninit();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case 0:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit) {
            mainHandler.clickEditText(this);
        } else if (id == R.id.nav_tts) {
            this.startActivity(new Intent(this, TTSTabActivity.class));
        }else if (id == R.id.nav_aboutus) {
            this.startActivity(new Intent(this, AboutUsActivity.class));
        }

//        else if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

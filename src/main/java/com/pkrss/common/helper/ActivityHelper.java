package com.pkrss.common.helper;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liand on 2015/11/30.
 */
public final class ActivityHelper {
    private static ActivityHelper instance;

    private List<Activity> activityList;

    private ActivityHelper(){
        activityList = new ArrayList<Activity>();
    }

    private Class<?> mainActivityClass;

    public static ActivityHelper getInstance() {
        if (instance == null)
            instance = new ActivityHelper();
        return instance;
    }

    public void addActivity(Activity act){
        if(mainActivityClass == null)
            mainActivityClass = act.getClass();
        activityList.add(act);
    }

    public void removeActivity(Activity act){
        activityList.remove(act);
    }

    public int activityCount(){
        return activityList.size();
    }

    public Class<?> getMainActivityClass() {
        return mainActivityClass;
    }

    public void setMainActivityClass(Class<?> mainActivityClass) {
        this.mainActivityClass = mainActivityClass;
    }
}

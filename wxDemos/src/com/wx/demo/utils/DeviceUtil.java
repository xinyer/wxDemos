package com.wx.demo.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Description:
 * Created by browserwang on 2015/11/10.
 */
public class DeviceUtil {

    private static float dmDensityDpi = 0f;
    private static DisplayMetrics dm;
    public static float scale = 0f;

    public DeviceUtil(Context context) {
        dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();

        setDmDensityDpi(dm.densityDpi);
        scale = getDmDensityDpi()/160;
    }

    public static void setDmDensityDpi(float dmDensityDpi) {
        DeviceUtil.dmDensityDpi = dmDensityDpi;
    }

    public static float getDmDensityDpi() {
        return dmDensityDpi;
    }
}

package jester.bgweather.com;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //debug purpose
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();

        //todo: convert image to bitmap
        Bitmap srcBitmap = ;

        WallpaperManager wm = WallpaperManager.getInstance(context);
        int height = wm.getDesiredMinimumHeight();
        int width = wm.getDesiredMinimumWidth();
        wm.setBitmap(Bitmap.createScaledBitmap(srcBitmap, width , height , true));
    }


}
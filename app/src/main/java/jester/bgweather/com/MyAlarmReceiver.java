package jester.bgweather.com;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.Random;

import util.BitmapUtil;

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        WallpaperManager wm = WallpaperManager.getInstance(context);

        String[] images = {"img0000000.jpg", "img0001000.png", "img0002000.jpg", "img0100000.jpg", };

        try {
            wm.setBitmap(BitmapUtil.getBitmapFromAsset(context, images[new Random().nextInt(images.length-1)]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
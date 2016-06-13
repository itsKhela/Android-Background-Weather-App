package jester.bgweather.com;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //debug purpose
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();

        //todo: convert image to bitmap
        /*
        File root = Environment.getExternalStorageDirectory();
        ImageView IV = (ImageView) findViewById(R.id."image view");
        Bitmap bMap = BitmapFactory.decodeFile(root+"/images/01.jpg");
        IV.setImageBitmap(bMap);
         */

        /*
        Any of the BitmapFactory.decode* methods should be able to handle standard JPG files.
        If you post some code it could be easier to see why it won't work
         */

        String bgFileName = "";

        File root = Environment.getExternalStorageDirectory();
        Bitmap srcBitmap = BitmapFactory.decodeFile(root+bgFileName);

        WallpaperManager wm = WallpaperManager.getInstance(context);
        int height = wm.getDesiredMinimumHeight();
        int width = wm.getDesiredMinimumWidth();

        try {
            wm.setBitmap(Bitmap.createScaledBitmap(srcBitmap, width , height , true));
        }
        catch(IOException ex) {
            return;
        }


    }


}
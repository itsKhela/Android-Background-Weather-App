package util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by the_b on 16/05/2016.
 */

public class BitmapUtil {
  /*
    Use it as following:
    Bitmap bitmap = BitmapUtil.getBitmapFromAsset(this, "rain.jpg");
    Where this refers to the context of an Activity for example.

    Then you can set this bitmap to an ImageView:
    imageView.setImageBitmap(bitmap);

    You must save the image assets in assets folder, placed under src/main/ folder
    like:

    src/main/assets/rain.jpg

     */

    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {}

        return bitmap;
    }
}

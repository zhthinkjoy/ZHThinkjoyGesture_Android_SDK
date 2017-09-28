package com.thinkjoy.zhthtinkjoygesturedetect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by thinkjoy on 17-8-2.
 */

public class PhotoUtils {


    public static Bitmap resizeBitmapAndRotate90(Bitmap bitmap, int width, int height) {
        Matrix matrix = new Matrix();
        matrix.postScale((float)width / bitmap.getHeight(), (float)height / bitmap.getWidth());
        matrix.postRotate(270);
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap2;
    }

}

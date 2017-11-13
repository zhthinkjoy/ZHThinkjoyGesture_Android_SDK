package com.thinkjoy.zhthtinkjoygesturedetect;

import android.graphics.Bitmap;

/**
 * Created by thinkjoy on 17-9-16.
 */

public class GlobalFlag {
    public boolean isFaceDetectFinished;
    public boolean isGestureDetectFinished;
    public Bitmap currentDetectBitmap;
    public byte [] currentImage;
    private static GlobalFlag globalFlag;
    private GlobalFlag() {
        isFaceDetectFinished = true;
        isGestureDetectFinished = true;
    }
    public static GlobalFlag getInstance() {
        if (globalFlag == null) {
            globalFlag = new GlobalFlag();
        }
        return globalFlag;
    }
}

package com.thinkjoy.zhthtinkjoygesturedetect;

/**
 * Created by thinkjoy on 17-9-12.
 */

public class GestureDetectResult {
    public int shape;
    public float bottom;
    public float top;
    public float right;
    public float left;
    public GestureDetectResult() {
        shape = -1;
        bottom = 0;
        top = 0;
        right = 0;
        left = 0;
    }
}

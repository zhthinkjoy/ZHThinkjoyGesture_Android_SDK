package com.thinkjoy.zhthtinkjoygesturedetect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.thinkjoy.zhthinkjoygesturedetectlib.GestureInfo;
import com.thinkjoy.zhthinkjoygesturedetectlib.ZHThinkjoyGesture;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Handler handler;
    private GlobalFlag globalFlag;
    private ZHThinkjoyGesture zhThinkjoyGesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zhThinkjoyGesture = ZHThinkjoyGesture.getInstance(this);
        zhThinkjoyGesture.init();
        globalFlag = GlobalFlag.getInstance();

        final CameraPreview cameraPreview = (CameraPreview)findViewById(R.id.cv_camera_preview);
        final FaceOverlayView fv_draw_rect = (FaceOverlayView)findViewById(R.id.fv_draw_rect);
        fv_draw_rect.setWindowSize();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GlobalInfo.MSG_GESTURE_DETECT:
                        Log.i("sendmessage", Long.toString(System.currentTimeMillis()));
                        List<GestureInfo> gestureInfoList = new ArrayList<>();
                        long time1 = System.currentTimeMillis();
                        Bitmap bitmap = globalFlag.currentDetectBitmap;
                        zhThinkjoyGesture.gestureDetect(bitmap, gestureInfoList);
                        long time2 = System.currentTimeMillis();
                            GestureDetectResult gestureDetectResult = new GestureDetectResult();
                        if (gestureInfoList.size() > 0) {
                            gestureDetectResult.shape = gestureInfoList.get(0).type;
                            gestureDetectResult.left = gestureInfoList.get(0).gestureRectangle[0].x;
                            gestureDetectResult.top = gestureInfoList.get(0).gestureRectangle[0].y;
                            gestureDetectResult.right = gestureInfoList.get(0).gestureRectangle[1].x;
                            gestureDetectResult.bottom = gestureInfoList.get(0).gestureRectangle[1].y;
                        }
                            fv_draw_rect.setGestureResult(gestureDetectResult, time2 - time1);
                            fv_draw_rect.postInvalidate();
                        globalFlag.isGestureDetectFinished = true;
                        break;
                }
            }
        };
        cameraPreview.setHandler(handler);
    }
}

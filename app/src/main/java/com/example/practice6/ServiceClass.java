package com.example.practice6;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ServiceClass extends Service {

    private WindowManager windowManager;
    private View bannerView;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Create the banner view from the layout file
        bannerView = LayoutInflater.from(this).inflate(R.layout.service_layout, null);

        // Add a click listener to the button to navigate to the running application
        Button bannerButton = bannerView.findViewById(R.id.button5);
        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the running application
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                startActivity(launchIntent);
            }
        });

        /* Add the banner view to the window manager as a hardware overlay
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(bannerView, params);*/

        final WindowManager.LayoutParams params = new
                WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(bannerView, params);
        params.gravity = Gravity.CENTER;
        windowManager.updateViewLayout(bannerView, params);
        ImageButton closeButton = bannerView.findViewById(R.id.imageButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf(); // stop the service and close the overlay
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null && bannerView != null) {
            windowManager.removeView(bannerView);
        }
    }

}

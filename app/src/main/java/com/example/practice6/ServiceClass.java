package com.example.practice6;

import android.app.PendingIntent;
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
        bannerView = LayoutInflater.from(this).inflate(R.layout.service_layout, null);
        Button bannerButton = bannerView.findViewById(R.id.button5);
        // Создание PendingIntent'a для перехода в приложение
        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {//если попытка отправки отмененного PendingIntent'a
                    e.printStackTrace();
                }
            }
        });
        // Создание и настройка всплывающего окна, которое будет отображаться поверх других приложений
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        // Получение сервиса WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // Добавление всплывающего окна на экран
        windowManager.addView(bannerView, params);
        // Задание гравитации окна (центрирование)
        params.gravity = Gravity.CENTER;
        // Обновление параметров окна
        windowManager.updateViewLayout(bannerView, params);
        // Настройка кнопки закрытия всплывающего окна
        ImageButton closeButton = bannerView.findViewById(R.id.imageButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Закрытие сервиса при нажатии на кнопку
                stopSelf();
            }
        });
    }
    // Остановка сервиса и удаление всплывающего окна при уничтожении сервиса
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null && bannerView != null) {
            windowManager.removeView(bannerView);
        }
    }

}

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

import androidx.annotation.Nullable;

import com.example.practice6.R;
import com.example.practice6.MainActivity;

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
        // Создание PendingIntent'a для перехода в приложение
        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //  перехода в запущенное приложение при клике на баннер запущенного сервиса
        bannerView.setOnClickListener(v -> {
            try {
                pendingIntent.send();//запуск приложения
                stopSelf();//остановка сервиса
            } catch (PendingIntent.CanceledException e) {//Если PendingIntent не удалось выполнить,
                //то выбрасываем исключение
                e.printStackTrace();
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

    }
    // Остановка сервиса и удаление всплывающего окна при уничтожении сервиса
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null) {
            windowManager.removeView(bannerView);
        }
    }

}

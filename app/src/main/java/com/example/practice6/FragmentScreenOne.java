package com.example.practice6;


import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.practice6.databinding.Screen1Binding;

public class FragmentScreenOne extends Fragment {
    Screen1Binding binding;

    private static final String CHANNEL_ID = "my_channel";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;

    public FragmentScreenOne() {
        super(R.layout.screen1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Screen1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Создаем канал уведомлений (для Android 8.0 и выше)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Мой канал";
            String description = "Канал для уведомлений";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = requireContext().
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        //Реакция на уведомления
        Intent notificationIntent = new Intent(getActivity(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Есть ли разрешения на отправку уведомления
                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.
                        POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // Если разрешение не получено, запрашиваем его у пользователя
                    requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                }
                // Создаем уведомление
                NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(),
                        CHANNEL_ID)
                        .setSmallIcon(R.drawable.bell)
                        .setContentTitle("Библиотека")
                        .setContentText("Вам пришло уведомление из библиотеки!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                // Отправляем уведомление
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }


        });
/*
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Проверяем, есть ли разрешение на отправку уведомлений
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.
                        POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // Если разрешение не получено, запрашиваем его у пользователя
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            PERMISSION_REQUEST_CODE);
                    return;
                } else {
                    sendNotification();
                }
            }
        });*/
        /*binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext())) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getActivity().getPackageName()));
                    startActivity(intent);
                } else {
                    // Permission granted, start the service
                    Intent intent = new Intent(getActivity(), ServiceClass.class);
                    getActivity().startService(intent);
                }
            }
        });*/
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults
        );
    }
}

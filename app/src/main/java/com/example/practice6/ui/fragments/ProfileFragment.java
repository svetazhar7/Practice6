package com.example.practice6.ui.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.practice6.ui.activities.MainActivity;
import com.example.practice6.R;
import com.example.practice6.ServiceClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileFragment extends Fragment {
    Screen1Binding binding;
    private static final String CHANNEL_ID = "my_channel";
    SharedPreferences sharedPreferences;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;


    public ProfileFragment() {
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

        sharedPreferences = getActivity().getSharedPreferences("name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.editText.setText(sharedPreferences.getString("name",null));

        String text  = binding.editText.getText().toString();
        String fileName = "userName.txt";

        //Запись файла в app-specific storage
        createFileAppScecificStorage(fileName,text);

        //Запись файла в общем хранилище
        createFileExternalStorage(fileName,text);

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_author_list_fragment);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_fragment_to_book_list_fragment);
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("name", binding.editText.getText().toString());
                editor.apply();
            }
        });
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

        // Создаем intent, который будет запускать наше приложение при нажатии на уведомление
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0);

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
                        .setSmallIcon(R.drawable.bell) //маленькая иконка
                        .setContentTitle("Библиотека") //заголовок
                        .setContentText("Вам пришло уведомление из библиотеки!") //текст уведомления
                        .setAutoCancel(true) // автоматически закрывает уведомление после нажатия
                        .setContentIntent(pendingIntent)// добавляем PendingIntent
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                // Отправляем уведомление
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }


        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
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
        });
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
    void createFileAppScecificStorage(String fileName, String text)
    {
        Context context = getContext();
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(text.getBytes());
            //  Toast.makeText(context, "Был создан текстовый файл в app-specific storage  "
            //       + context.getDataDir().getAbsolutePath()+"/"
            //       + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    void createFileExternalStorage(String fileName, String text) {
        Context context = getContext();
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(filePath, fileName);
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(text.getBytes());
                Toast.makeText(context, "Был создан текстовый файл в общем хранилище " + filePath +"/"+ fileName, Toast.LENGTH_SHORT).show();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1 );
        }
    }
}




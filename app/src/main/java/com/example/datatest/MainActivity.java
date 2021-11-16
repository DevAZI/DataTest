package com.example.datatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnWrite = findViewById(R.id.button);
        Button btnRead = findViewById(R.id.button2);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outputStream = openFileOutput("file.txt", Context.MODE_PRIVATE);//file.txt를 만듬 있으면 그거 사용  /  Context.MODE_PRIVATE= 나만 접근 가능한 것
                    String str = "파일 테스트";
                    outputStream.write(str.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //FileInputStream inputStream = openFileInput("file.txt");
                    InputStream inputStream =getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[30];
                    inputStream.read(txt);
                    String str = new String(txt).trim();
                    inputStream.close();
                    Log.i(TAG, "input:" + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
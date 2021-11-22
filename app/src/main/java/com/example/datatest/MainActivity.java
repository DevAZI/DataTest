package com.example.datatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    String name, department;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnParse, btnAdd, btnDelete, btnSelect;
        EditText editName, editAge, editDepartment;

        editName = findViewById(R.id.editTextTextPersonName);
        editAge = findViewById(R.id.editTextTextPersonName2);
        editDepartment = findViewById(R.id.editTextTextPersonName3);

        btnParse = findViewById(R.id.button);
        btnAdd = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);
        btnSelect = findViewById(R.id.button4);



        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString();
                age = Integer.parseInt(editAge.getText().toString());
                department = editDepartment.getText().toString();
            }
        });
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(TableInfo.COLUMN_NAME_NAME, name);
                values.put(TableInfo.COLUMN_NAME_AGE, age);
                values.put(TableInfo.COLUMN_NAME_DEP,department);

                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                long newRowId = db.insert(TableInfo.TABLE_NAME, null, values);
                Log.i(TAG, "new row Id, "+newRowId);

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDBHelper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME, null);
                if (c.moveToFirst()) {
                    do {
                        int col1 = c.getInt(0);
                        String col2 = c.getString(1);
                        int col3 = c.getInt(2);
                        String col4 = c.getString(3);
                        Log.i(TAG, "READ, col1 " + col1 + "/2: " + col2 + "/3: " + col3 + "/4: " + col4);
                    }
                    while (c.moveToNext());
                }
                c.close();
                db.close();

            }
        });

    }

}

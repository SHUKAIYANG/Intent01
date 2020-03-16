package com.example.intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ImageButton buttonStart;
    private EditText editTextName, editTextAge, editTextHeight, editTextWeight;
    private String name, age;
    private static final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextAge = (EditText) findViewById(R.id.editText_age);
        editTextHeight = (EditText) findViewById(R.id.editText_height);
        editTextWeight = (EditText) findViewById(R.id.editText_weight);



        buttonStart = (ImageButton) findViewById(R.id.imageButton_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 跳過去之前要把資料取進來

                // 取 name
                name = editTextName.getText().toString();

                if(name.length() == 0) {
                    Toast.makeText(context, "Please input name.", Toast.LENGTH_SHORT).show();
                    name ="";  // 若不設初始值，log去讀時可能會當機。
                }

                Log.d(TAG, "name = " + name);

                //取 age
                age = editTextAge.getText().toString();

                if(age.length() == 0) {
                    Toast.makeText(context, "Please input age.", Toast.LENGTH_SHORT).show();
                    age = "0";
                }

                Log.d(TAG, "age = " + age);

                // 取得 height weight
                String height = editTextHeight.getText().toString();
                String weight = editTextWeight.getText().toString();

                // 任一為零則沒辦法算，所以要用 if else 去限制
                if(height.length() == 0 || weight.length() == 0) {
                    Toast.makeText(context, "Please input height and weight.", Toast.LENGTH_SHORT).show();

                }else {
                    Log.d(TAG, "height = " + height);
                    Log.d(TAG, "weight = " + weight);

                    // 將資料先轉成字串 (或到那邊再轉也可以)
                    int heightValue = Integer.parseInt(height);
                    int weightValue = Integer.parseInt(weight);

                    // 設定Intent
                    Intent bmiIntent = new Intent(context, BMIActivity.class);

                    // 丟資料
                    bmiIntent.putExtra("name", name);
                    bmiIntent.putExtra("age", age);
                    bmiIntent.putExtra("height", heightValue);
                    bmiIntent.putExtra("weight", weightValue);


                    startActivity(bmiIntent);
                }

            }
        });



    }
}

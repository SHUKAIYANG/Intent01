package com.example.intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMIActivity extends AppCompatActivity {

    private static final String TAG = "main";
    private TextView textViewBMI;
    private Intent intent;
    private Intent bmiIntent;
    private final int ReturnError = 20;
    private final int ReturnData = 30;
    private Button buttonBack;
    private String bmiString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        setTitle("BMI");   //改標題

        textViewBMI = (TextView) findViewById(R.id.textView_bmi);
        textViewBMI.setText("");

        intent = getIntent();   // 這裡的intent只是一個變數名稱。

        String name = intent.getStringExtra("name");
        Log.d(TAG, "bmi name = " + name);

        String age = intent.getStringExtra("age");
        Log.d(TAG, "bmi age = " + age);

        double height = intent.getIntExtra("height", 1);
        double weight = intent.getIntExtra("weight", 1);
        Log.d(TAG, "bmi height = " + height);
        Log.d(TAG, "bmi weight = " + weight);

        // 限制身高 若大於 250 則結束 bmiActivity
        if(height  > 250) {
            Log.d(TAG, "height > 250 , bmiActivity has been finished.");

            // 設定若大於 250 要傳回給 mainActivity的資料
            bmiIntent = new Intent();
            bmiIntent.putExtra("bmierror", "Your height is error.");
            setResult(ReturnError, bmiIntent);
            finish();
        }

        double bmiValue = (weight * 100 * 100) / (height * height);
        Log.d(TAG, "bmiValue = " + bmiValue);

        // 將數據格式化，其結果為字串型態。
        NumberFormat nf = new DecimalFormat("##.00");
        bmiString = nf.format(bmiValue);
        Log.d(TAG, "bmiString = " + bmiString);

        textViewBMI.setText("Name :" + name + ", age : " + age + "\n");
        textViewBMI.append("height = " + height + ", weight = " + weight + "\n");
        textViewBMI.append("BMI = " + bmiString + "\n\n");

        // 呼叫用 bmi 判斷是否過重的 getBMIMessage 方法
       String message = getBMIMessage(bmiValue);
       textViewBMI.append(message);


       buttonBack = (Button) findViewById(R.id.button_back);
       buttonBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent bmiData = new Intent();
               bmiData.putExtra("bmivalue", bmiString);
               setResult(ReturnData, bmiData);
               finish();
           }
       });



    }

    //用 bmi 判斷是否過重的方法
    private String getBMIMessage(double bmiValue) {

        String message;

        if(bmiValue < 20) {
            message = "BMI value is too low.";

        }else if(bmiValue >= 20 && bmiValue < 26) {
            message = "BMI value is standard.";

        }else if(bmiValue >= 26 && bmiValue < 30) {
            message = "BMI value is high.";

        }else if(bmiValue >= 30 && bmiValue <50 ) {
            message = "BMI value is too high.";

        }else {
            message = "BMI value is error.";

        }

        return  message;

    }

}

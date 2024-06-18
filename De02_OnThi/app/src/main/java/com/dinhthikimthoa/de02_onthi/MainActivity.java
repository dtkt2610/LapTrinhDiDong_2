package com.dinhthikimthoa.de02_onthi;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dinhthikimthoa.de02_onthi.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int randNumb;
    Random random = new Random();


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            int randNumb = (int) msg.obj;
            int i = msg.arg1;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15,15,15,15);


            if (i % 2 == 0){
                Button button = new Button(MainActivity.this);
                button.setHeight(100);
                button.setWidth(100);
                button.setText(String.valueOf(randNumb));
                button.setBackgroundColor(Color.rgb(12,0,189));
                binding.containerLayout.addView(button);
            }else {
                EditText editText = new EditText(MainActivity.this);
                editText.setText(String.valueOf(randNumb));
                binding.containerLayout.addView(editText);
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execBackgroundThread();
            }
        });
    }

    private void execBackgroundThread() {
        binding.containerLayout.removeAllViews();
        int numbOfViews = Integer.parseInt(binding.edtNhapSo.getText().toString());
        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= numbOfViews; i++){
                    Message message = handler.obtainMessage();
                    message.obj = random.nextInt(100);
                    message.arg1 = i;
                    handler.sendMessage(message);
                    SystemClock.sleep(100);
                }
            }
        });
        backGroundThread.start();
    }
}
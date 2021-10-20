package com.example.a6labmeniuirgijos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;
    String chosenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);

        registerForContextMenu(textView);
        registerForContextMenu(textView2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        chosenText = ((TextView) v).getText().toString();

        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item1: firstOptionSelected();
                return true;
            case R.id.item2: secondOptionSelected();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void firstOptionSelected() {

        String text = "Tekste yra " + chosenText.length() + " simbolių";
        textView2.setText(text);
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setTitle("Simbolių skaičius");

        alertDialog1.setMessage(text);

        alertDialog1.show();
    }

    private void secondOptionSelected() {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = chosenText.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= chosenText.charAt(i[0]);
                textView3.setText(String.valueOf(c));
                i[0]++;
            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 700);
    }
}
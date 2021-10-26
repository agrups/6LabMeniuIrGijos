package com.example.a6labmeniuirgijos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;
    String chosenText;
    int hour;
    int minute;
    int currentTimeInMinutes;
    int diff;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.Item1){
            
                    Calendar currentTime = Calendar.getInstance();
                    hour = currentTime.get(Calendar.HOUR_OF_DAY);
                    minute = currentTime.get(Calendar.MINUTE);
                    currentTimeInMinutes = hour * 60 + minute;
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selHour, int selMinute) {

                            diff = Math.abs(selHour*60 + selMinute - currentTimeInMinutes);
                            String text = "Skirtumas tarp dabar ir nurodyto laiko yra " + diff + " minutes";
                            textView.setText(text);
                            differenceTimeAlertDialog();
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Pasirinkite laika");
                    mTimePicker.show();
                }

        return super.onOptionsItemSelected(item);
    }

    private void differenceTimeAlertDialog() {

        String text = "Skirtumas tarp dabar ir nurodyto laiko yra " + diff + " minutes";
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                MainActivity.this).create();

        alertDialog1.setTitle("Skirtumas minutemis");

        alertDialog1.setMessage(text);

        alertDialog1.show();
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
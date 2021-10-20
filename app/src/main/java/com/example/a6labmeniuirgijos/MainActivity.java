package com.example.a6labmeniuirgijos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    String chosenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);

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
    }
}
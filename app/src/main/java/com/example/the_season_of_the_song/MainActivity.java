package com.example.the_season_of_the_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button confirm;
    ArrayList<String> years = new ArrayList<String>();
    int theYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirm = (Button) findViewById(R.id.confirm_age);

        //initLoadDB();

        for (int i = 1900; i <= 2016; i++) {
            years.add(Integer.toString(i));
        }
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, years
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(95);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                theYear = position + 1900;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), theYear+"년생 고객님 환영합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SelectMenu.class);
                intent.putExtra("birthYear", theYear);
                startActivityForResult(intent, 101);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 ) {
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(),"메뉴화면으로부터 응답 : " + name, Toast.LENGTH_LONG).show();
        }
    }
}

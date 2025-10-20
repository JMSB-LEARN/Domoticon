package com.example.domoticon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.domoticon.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        Spinner spinner = binding.spinnerDevices;

        String[] options = {getString(R.string.default_option_spinner),"2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, options);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accionSpinner(parent, position);
                ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void accionSpinner(AdapterView parent, int position) {
        String numberDevices = parent.getItemAtPosition(position).toString();
        if (numberDevices != null) {
            Intent intent = null;
            switch (numberDevices) {
                case "2":
                    intent = new Intent(MainActivity.this, TwoBulbsActivity.class);
                    Log.d("case", "2");
                    break;
                case "3":
                    intent = new Intent(MainActivity.this, ThreeBulbsActivity.class);
                    Log.d("case", "3");
                    break;
                default:
            }
            if (intent != null) startActivity(intent);
        }

    }
}
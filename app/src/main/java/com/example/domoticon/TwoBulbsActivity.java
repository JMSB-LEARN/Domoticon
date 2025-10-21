package com.example.domoticon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.domoticon.databinding.ActivityTwoBulbsBinding;

public class TwoBulbsActivity extends AppCompatActivity {
    private ActivityTwoBulbsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityTwoBulbsBinding.inflate(getLayoutInflater())).getRoot());
    }
}
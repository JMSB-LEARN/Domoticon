package com.example.domoticon;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.domoticon.databinding.ActivityMainBinding;
import com.example.domoticon.databinding.ActivityTwoBulbsBinding;

public class TwoBulbsActivity extends AppCompatActivity {

    private ActivityTwoBulbsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityTwoBulbsBinding.inflate(getLayoutInflater())).getRoot());
    }
}
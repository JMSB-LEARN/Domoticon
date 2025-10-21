package com.example.domoticon;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.domoticon.databinding.ActivityThreeBulbsBinding;
import com.example.domoticon.databinding.ActivityTwoBulbsBinding;

public class BulbsActivity extends AppCompatActivity {

    Boolean locked = false;

    ImageView[] imageViews;
    CheckBox[] checkBoxes;
    ToggleButton[] toggleButtons;
    ImageButton resetButton;
    SwitchCompat lockSC;

    int bulbCount;

    final String KEY_STATE_TOGGLE_BUTTON = "tbs";
    final String KEY_STATE_CHECK_BOXES = "cbs";
    final String KEY_STATE_LOCK = "scLocks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the bulb count from the Intent (default 3)
        bulbCount = getIntent().getIntExtra("bulbCount", 3);

        if (bulbCount == 2) {
            // Two bulbs layout
            ActivityTwoBulbsBinding binding2 = ActivityTwoBulbsBinding.inflate(getLayoutInflater());
            setContentView(binding2.getRoot());

            imageViews = new ImageView[]{binding2.imageView1, binding2.imageView2};
            checkBoxes = new CheckBox[]{binding2.cb1, binding2.cb2};
            toggleButtons = new ToggleButton[]{binding2.toggleButton1, binding2.toggleButton2};
            resetButton = binding2.ibReset;
            lockSC = binding2.bsLock;

        } else {
            // Three bulbs layout
            ActivityThreeBulbsBinding binding3 = ActivityThreeBulbsBinding.inflate(getLayoutInflater());
            setContentView(binding3.getRoot());

            imageViews = new ImageView[]{binding3.imageView1, binding3.imageView2, binding3.imageView3};
            checkBoxes = new CheckBox[]{binding3.cb1, binding3.cb2, binding3.cb3};
            toggleButtons = new ToggleButton[]{binding3.toggleButton1, binding3.toggleButton2, binding3.toggleButton3};
            resetButton = binding3.ibReset;
            lockSC = binding3.bsLock;
        }

        // Restore state if activity is recreated
        if (savedInstanceState != null) {
            for (int i = 0; i < toggleButtons.length; i++) {
                boolean isChecked = savedInstanceState.getBoolean(KEY_STATE_TOGGLE_BUTTON + i);
                toggleButtons[i].setChecked(isChecked);
                setImageToState(imageViews[i], isChecked);

                checkBoxes[i].setChecked(savedInstanceState.getBoolean(KEY_STATE_CHECK_BOXES + i));
            }
            if (savedInstanceState.getBoolean(KEY_STATE_LOCK)) {
                setLockStatus(true);
            }
        }

        // Toggle button listeners
        for (int i = 0; i < toggleButtons.length; i++) {
            int I = i;
            toggleButtons[i].setOnCheckedChangeListener((button, checkStatus) -> {
                if (!locked) setImageToState(imageViews[I], checkStatus);
            });
        }

        // Reset button
        resetButton.setOnClickListener(v -> {
            if (!locked) switchState();
        });

        // Lock switch
        lockSC.setOnCheckedChangeListener((button, state) -> setLockStatus(state));
    }

    private void setLockStatus(boolean state) {
        locked = state;

        for (CheckBox checkBox : checkBoxes) checkBox.setEnabled(!locked);
        for (ToggleButton toggleButton : toggleButtons) toggleButton.setEnabled(!locked);
    }

    private void switchState() {
        for (int i = 0; i < toggleButtons.length; i++) {
            if (checkBoxes[i].isChecked()) toggleButtons[i].toggle();
        }
    }

    private void setImageToState(ImageView imageView, boolean checkStatus) {
        imageView.setImageResource(checkStatus ? R.drawable.bulb_lit : R.drawable.bulb_unlit);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < toggleButtons.length; i++) {
            outState.putBoolean(KEY_STATE_TOGGLE_BUTTON + i, toggleButtons[i].isChecked());
            outState.putBoolean(KEY_STATE_CHECK_BOXES + i, checkBoxes[i].isChecked());
        }
        outState.putBoolean(KEY_STATE_LOCK, lockSC.isChecked());
        super.onSaveInstanceState(outState);
    }
}

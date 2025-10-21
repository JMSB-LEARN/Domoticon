package com.example.domoticon;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.domoticon.databinding.ActivityThreeBulbsBinding;

//Se me ha ocurrido que si en la creacion cambiara a que vista apunta el binding en base a un dato numerico (2 o 3) que pasara en el intent podria fusionar los archivos de java de ThreeBulbsActivity y TwoBulbsActivity
public class ThreeBulbsActivity extends AppCompatActivity {
    private ActivityThreeBulbsBinding binding;
    Boolean locked = false;
    ImageView[] imageViews;
    CheckBox[] checkBoxes;
    ToggleButton[] toggleButtons;
    ImageButton resetButton;
    SwitchCompat lockSC;

    final String KEY_STATE_TOGGLE_BUTTON = "tbs", KEY_STATE_CHGECK_BOXES = "cbs", KEY_STATE_LOCK = "scLocks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityThreeBulbsBinding.inflate(getLayoutInflater())).getRoot());

        imageViews = new ImageView[]{binding.imageView1, binding.imageView2, binding.imageView3};
        checkBoxes = new CheckBox[]{binding.cb1, binding.cb2, binding.cb3};
        toggleButtons = new ToggleButton[]{binding.toggleButton1, binding.toggleButton2, binding.toggleButton3};
        resetButton = binding.ibReset;
        lockSC = binding.bsLock;

        if (savedInstanceState != null) {
            for (int i = 0; i < toggleButtons.length; i++) {
                boolean isChecked = savedInstanceState.getBoolean(KEY_STATE_TOGGLE_BUTTON + i);
                toggleButtons[i].setChecked(isChecked);
                setImageToState(imageViews[i], isChecked);

                checkBoxes[i].setChecked(savedInstanceState.getBoolean(KEY_STATE_CHGECK_BOXES + i));
            }
            if (savedInstanceState.getBoolean(KEY_STATE_LOCK)) setLockStatus(true);
        }


        for (int i = 0; i < toggleButtons.length; i++) {
            int I = i;
            toggleButtons[i].setOnCheckedChangeListener((button, checkStatus) -> {
                if (!locked) setImageToState(imageViews[I], checkStatus);
            });
        }

        resetButton.setOnClickListener(v -> {
            if (!locked) switchState();
        });

        lockSC.setOnCheckedChangeListener((compoundButtone, state) -> {
            setLockStatus(state);
        });
    }

    private void setLockStatus(boolean state) {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setEnabled(locked);
        }
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setEnabled(locked);
        }
        locked = !locked;
    }

    private void switchState() {
        for (int i = 0; i < toggleButtons.length; i++) {
            if (checkBoxes[i].isChecked())
                toggleButtons[i].toggle();

        }
    }


    private void setImageToState(ImageView imageView, boolean checkStatus) {
        if (checkStatus) imageView.setImageResource(R.drawable.bulb_lit);
        else imageView.setImageResource(R.drawable.bulb_unlit);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < toggleButtons.length; i++) {
            outState.putBoolean(KEY_STATE_TOGGLE_BUTTON + i, toggleButtons[i].isChecked());
            outState.putBoolean(KEY_STATE_CHGECK_BOXES + i, checkBoxes[i].isChecked());
        }
        outState.putBoolean(KEY_STATE_LOCK, lockSC.isChecked());
        super.onSaveInstanceState(outState);
    }
}
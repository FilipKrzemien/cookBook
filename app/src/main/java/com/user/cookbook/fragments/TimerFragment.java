package com.user.cookbook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.user.cookbook.R;
import com.user.cookbook.timer.TimerService;

import java.util.Timer;

public class TimerFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.timer_message)
                .setTitle(R.string.timer);
        final EditText inputText = new EditText(getActivity());
        inputText.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputText.setRawInputType(Configuration.KEYBOARD_12KEY);
        builder.setView(inputText)
                .setPositiveButton(R.string.set_timer, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent serviceIntent = new Intent(getActivity(), TimerService.class);
                        serviceIntent.putExtra("TIME", inputText.getText().toString());
                        getActivity().startService(serviceIntent);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

}

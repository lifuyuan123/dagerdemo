package com.example.atmf.daggerdemo.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.atmf.daggerdemo.R;

public class ProgresDialog extends Dialog {

    public ProgresDialog(@NonNull Context context) {
        super(context, R.style.dialog_progress);
        setContentView(R.layout.public_dialog_porgress);
        setCanceledOnTouchOutside(false);
    }
}
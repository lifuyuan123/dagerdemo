package com.example.atmf.daggerdemo.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.atmf.daggerdemo.R;

import net.moyokoo.diooto.interfaces.IProgress;
import net.moyokoo.diooto.tools.LoadingView;

/**
 * 加载图片的progress
 */
public class LoadProgress implements IProgress {
    private SparseArray<ProgressBar> progressBarArray = new SparseArray();

    public LoadProgress() {
    }


    public void attach(int position, FrameLayout parent) {
        Context context = parent.getContext();
        int progressSize = LoadingView.dip2Px(context, 30.0F);
        FrameLayout.LayoutParams progressLp = new FrameLayout.LayoutParams(progressSize, progressSize);
        progressLp.gravity = Gravity.CENTER;
        ProgressBar loadingView = new ProgressBar(context);
        loadingView.setLayoutParams(progressLp);
        loadingView.setIndeterminateDrawable(parent.getContext().getResources().getDrawable(R.drawable.public_loading_ani));
        parent.addView(loadingView);
        this.progressBarArray.put(position, loadingView);
    }

    public void onStart(int position) {
    }

    public void onProgress(int position, int progress) {
    }

    public void onFinish(int position) {
        ProgressBar loadingView = this.progressBarArray.get(position);
        loadingView.setVisibility(View.GONE);
    }

    public void onFailed(int position) {
        ProgressBar loadingView = this.progressBarArray.get(position);
        loadingView.setVisibility(View.GONE);
    }

    public View getProgressView(int position) {
        return this.progressBarArray.get(position);
    }
}

package com.example.atmf.basemodule.view;

import android.animation.Animator;

/**
 * Created by zhangke on 2016-11-3.
 */
public interface TransitionCustomListener {

    public void onTransitionStart(Animator animator);

    public void onTransitionEnd(Animator animator);

    public void onTransitionCancel(Animator animator);

}

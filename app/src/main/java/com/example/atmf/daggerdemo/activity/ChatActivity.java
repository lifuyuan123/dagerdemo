package com.example.atmf.daggerdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.tans.ActivityTransition;
import com.example.atmf.daggerdemo.tans.ExitActivityTransition;
import com.example.atmf.daggerdemo.utils.FKJShareElement;
import com.example.atmf.daggerdemo.utils.ShareElementInfo;
import com.example.atmf.daggerdemo.view.TransitionController;

import cn.icheny.transition.ElementTransition;

@Route(path = "/act/ChatActivity")
public class ChatActivity extends AppCompatActivity {
    private Interpolator interpolator;
    private FKJShareElement mShareElement;
    private ImageView iv;
    private RelativeLayout limn;
    private ExitActivityTransition exitTransition;
    @Autowired()
    String img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        iv=findViewById(R.id.chat_iv);
        limn=findViewById(R.id.lin4);
        setupWindowAnimations(savedInstanceState);


    }



    private void setupWindowAnimations(final Bundle savedInstanceState) {
        String url=getIntent().getStringExtra("img");
            Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        setupEnterAnimations();
                    }else {
//                        ShareElementInfo info = getIntent().getExtras().getParcelable("info");
//                        mShareElement = new FKJShareElement(info, ChatActivity.this, limn);
//                        mShareElement.convert(iv)
//                                .setDuration(500)
//                                .setInterpolator(new LinearInterpolator())
//                                .startEnterAnimator();

//                        TransitionController.getInstance().show(ChatActivity.this,getIntent());
//
                        /**
                         * 共享元素过渡动画
                         */
//                        ElementTransition.runEnterAnim(ChatActivity.this, 1000, new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                                super.onAnimationEnd(animation);
//
//                            }
//                        });

                        exitTransition = ActivityTransition
                                .with(getIntent())
                                .enterListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        Log.d("TAG", "onEnterAnimationEnd!!");
                                    }

                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        Log.d("TAG", "onOEnterAnimationStart!!");
                                    }
                                })
                                .to(iv)
                                .start(savedInstanceState);
                        exitTransition.exitListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                Log.d("TAG", "onOutAnimationStart!!");
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                Log.d("TAG", "onOutAnimationEnd!!");
                            }
                        });


                    }

                    return false;
                }
            }).into(iv);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimations() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Removing listener here is very important because shared element transition is executed again backwards on exit. If we don't remove the listener this code will be triggered again.
                transition.removeListener(this);
                hideTarget();
            }
            @Override
            public void onTransitionCancel(Transition transition) { }
            @Override
            public void onTransitionPause(Transition transition) { }
            @Override
            public void onTransitionResume(Transition transition) { }
        });
    }

    private void hideTarget() {
        findViewById(R.id.chat_iv).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
//            mShareElement.convert(iv)
//                    .setDuration(500)
//                    .setInterpolator(new LinearInterpolator())
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            finish();
//                            overridePendingTransition(0, 0);
//                        }
//                    })
//                    .startExitAnimator();
//            TransitionController.getInstance().exitActivity(ChatActivity.this);

//            ElementTransition.runExitAnim(this, 1000);

            exitTransition.exit(this);

        }
    }

}

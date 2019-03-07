package com.example.atmf.daggerdemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.atmf.baselibrary.HelloService;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.activity.ChatActivity;
import com.example.atmf.daggerdemo.activity.SharedActivity;
import com.example.atmf.daggerdemo.tans.ActivityTransitionLauncher;
import com.example.atmf.daggerdemo.utils.ShareElementInfo;
import com.example.atmf.daggerdemo.view.HelloServiceImpl;

import cn.icheny.transition.ElementTransition;

public class ShardAdapter extends RecyclerView.Adapter<ShardAdapter.MyViewholder> {
    private Activity context;

    public ShardAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.item_shard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewholder holder, int position) {
        Glide.with(context).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=783340072,1312243264&fm=27&gp=0.jpg").into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(context,ChatActivity.class);
                 intent.putExtra("img","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=783340072,1312243264&fm=27&gp=0.jpg");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context, holder.imageView,"transition_reveal1");
                    context.startActivity(intent, transitionActivityOptions.toBundle());
//                    ARouter.getInstance().build("/act/ChatActivity")
//                            .withString("img","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=783340072,1312243264&fm=27&gp=0.jpg")
//                            .withOptionsCompat(transitionActivityOptions)
//                            .navigation();
                } else {
//                    ShareElementInfo info = new ShareElementInfo();
//                    info.convertOriginalInfo(holder.imageView);
//                    intent.putExtra("info", info);
//                    context.startActivity(intent);
//                    context.overridePendingTransition(0, 0);

//                    Intent intent1=new Intent(context,ChatActivity.class);
//                    intent1.putExtra("img","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=783340072,1312243264&fm=27&gp=0.jpg");
//                    TransitionController.getInstance().startActivity(context,intent1,holder.imageView,R.id.chat_iv);

//                     ElementTransition.startActivity(intent, context, holder.imageView);

                    ActivityTransitionLauncher.with(context).from(holder.imageView).launch(intent);
                    HelloService helloService=(HelloService) ARouter.getInstance().build("/service/hello").navigation();
                    helloService.getid(123);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewholder extends RecyclerView.ViewHolder{

           ImageView imageView;
        public MyViewholder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_iv);
        }
    }
}

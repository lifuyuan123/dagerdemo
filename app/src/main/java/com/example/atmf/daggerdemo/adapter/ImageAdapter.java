package com.example.atmf.daggerdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.atmf.daggerdemo.GlideApp;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.view.LoadProgress;
import com.example.atmf.daggerdemo.view.TextIndicator;

import net.moyokoo.diooto.Diooto;
import net.moyokoo.diooto.config.DiootoConfig;
import net.moyokoo.diooto.interfaces.DefaultCircleProgress;

import java.util.ArrayList;
import java.util.List;

import me.panpf.sketch.SketchImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

        private List<String> list = new ArrayList<>();
        private Context context;
        private String[] imags;
        private RecyclerView rv;

        public ImageAdapter(List<String> list, Context context,RecyclerView rv) {
            this.list = list;
            this.rv=rv;
            this.context = context;
            imags = new String[list.size()];
            imags=list.toArray(imags);
        }

        @NonNull
        @Override
        public ImageAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_image_explain, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ImageAdapter.MyHolder holder, final int position) {

            GlideApp.with(context)
                    .load(list.get(position))
                    .circleCrop()
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.iv);

            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //图片模式
                    new Diooto(context)
                            .urls(imags)
                            //图片或者视频
                            .type(DiootoConfig.PHOTO)
                            //点击的位置
                            .position(holder.getAdapterPosition())
                            //可以传recylcerview自动识别(需要传在item布局中的viewId)  也可以手动传view数组
                            .views(rv, R.id.iv_explain)
                            //设置选择器 默认CircleIndexIndicator  可实现IIndicator接口自定义
                            .setIndicator(new TextIndicator())
                            //设置进度条样式  默认DefaultProgress 可实现IProgress接口自定义
                            .setProgress(new LoadProgress())
                            //在显示原图之前显示的图片  如果你列表使用Glide加载  这里也使用Glide加载
                            .loadPhotoBeforeShowBigImage(new Diooto.OnLoadPhotoBeforeShowBigImageListener() {
                                @Override
                                public void loadView(SketchImageView sketchImageView, int position) {
//                                    sketchImageView.getOptions().setResize(10000000,10000000);
                                    sketchImageView.getZoomer().setReadMode(true);
                                    sketchImageView.displayImage(imags[holder.getAdapterPosition()]);

                                }
                            })
                            .start();
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            ImageView iv;

            public MyHolder(View itemView) {
                super(itemView);
                iv=itemView.findViewById(R.id.iv_explain);
            }
        }
    }
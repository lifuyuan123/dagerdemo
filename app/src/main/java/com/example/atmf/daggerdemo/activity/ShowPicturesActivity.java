package com.example.atmf.daggerdemo.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmf.daggerdemo.GlideApp;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.adapter.ImageAdapter;
import com.example.atmf.daggerdemo.view.TextIndicator;
import net.moyokoo.diooto.Diooto;
import net.moyokoo.diooto.config.DiootoConfig;
import net.moyokoo.diooto.interfaces.DefaultCircleProgress;

import java.util.ArrayList;
import java.util.List;

import me.panpf.sketch.SketchImageView;


public class ShowPicturesActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ImageAdapter adapter;
    private LinearLayoutManager manager;
    private List<List<String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pictures);
        rv = findViewById(R.id.rv_explain);

        initdata();

        manager = new LinearLayoutManager(this);
        adapter = new ImageAdapter(list, this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

    }

    private void initdata() {
        list.clear();
        List<String> mlist=new ArrayList<>();
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto1.png?raw=true");
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto2.jpg?raw=true");
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto3.jpg?raw=true");
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto4.jpg?raw=true");
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto5.jpg?raw=true");
        mlist.add("https://github.com/moyokoo/Media/blob/master/diooto6.jpg?raw=true");

        list.add(mlist);
        list.add(mlist);
        list.add(mlist);
        list.add(mlist);
        list.add(mlist);
        list.add(mlist);

    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

        private List<List<String>> list = new ArrayList<>();
        private Context context;
        private String[] imags;

        public ImageAdapter(List<List<String>> list, Context context) {
            this.list = list;
            this.context = context;
//            imags = new String[list.size()];
//            imags=list.toArray(imags);
        }

        @NonNull
        @Override
        public ImageAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ImageAdapter.MyHolder holder, final int position) {
            holder.tv.setText(holder.getAdapterPosition()+1+"");
            holder.rv.setLayoutManager(new GridLayoutManager(ShowPicturesActivity.this,3));
            holder.rv.setAdapter(new com.example.atmf.daggerdemo.adapter.ImageAdapter(list.get(position),holder.itemView.getContext(),holder.rv));

//            GlideApp.with(context)
//                    .load(list.get(position))
//                    .circleCrop()
//                    .centerCrop()
//                    .error(R.mipmap.ic_launcher)
//                    .into(holder.iv);

//            holder.iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //图片模式
//                    new Diooto(context)
//                            .urls(imags)
//                            //图片或者视频
//                            .type(DiootoConfig.PHOTO)
//                            //点击的位置
//                            .position(holder.getAdapterPosition())
//                            //可以传recylcerview自动识别(需要传在item布局中的viewId)  也可以手动传view数组
//                            .views(rv, R.id.iv_explain)
//                            //设置选择器 默认CircleIndexIndicator  可实现IIndicator接口自定义
//                            .setIndicator(new TextIndicator())
//                            //设置进度条样式  默认DefaultProgress 可实现IProgress接口自定义
//                            .setProgress(new DefaultCircleProgress())
//                            //在显示原图之前显示的图片  如果你列表使用Glide加载  这里也使用Glide加载
//                            .loadPhotoBeforeShowBigImage(new Diooto.OnLoadPhotoBeforeShowBigImageListener() {
//                                @Override
//                                public void loadView(SketchImageView sketchImageView, int position) {
////                                    sketchImageView.getOptions().setResize(10000000,10000000);
//                                    sketchImageView.getZoomer().setReadMode(true);
//                                    sketchImageView.displayImage(imags[holder.getAdapterPosition()]);
//
//                                }
//                            })
//                            .start();
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            RecyclerView rv;
            TextView tv;

            public MyHolder(View itemView) {
                super(itemView);
                rv = itemView.findViewById(R.id.item_rv);
                tv=itemView.findViewById(R.id.tv_item);
            }
        }
    }

}

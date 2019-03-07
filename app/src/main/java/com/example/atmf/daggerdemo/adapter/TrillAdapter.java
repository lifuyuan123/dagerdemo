package com.example.atmf.daggerdemo.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmf.daggerdemo.GlideApp;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.activity.TrillActivity;
import com.example.atmf.daggerdemo.entry.ActivityBean;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class TrillAdapter extends RecyclerView.Adapter<TrillAdapter.TrillHolder> {

   private List<ActivityBean> list=new ArrayList<>();
   private Context context;

    public TrillAdapter(List<ActivityBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TrillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrillHolder(LayoutInflater.from(context).inflate(R.layout.trill_item,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull TrillHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getName());

        //http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv
        //http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4
        switch (position&3){
            case 0:
                holder.player.setUp("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv"
                        ,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL
                        ,       "蓝莲花");
                break;
            case 1:
                holder.player.setUp("http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-five.mp4"
                        ,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL
                        ,       "蓝莲花");
                break;
            case 2:
                holder.player.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                        ,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL
                        ,       "蓝莲花");
                break;
        }


//        JZVideoPlayerStandard.startFullscreen(context,
//                JZVideoPlayerStandard.class,
//                "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
//                "蓝莲花");
        GlideApp.with(context)
                .load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
                .into(holder.player.thumbImageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TrillHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        JZVideoPlayerStandard player;

        public TrillHolder(View itemView) {
            super(itemView);
            player=itemView.findViewById(R.id.jzvideo);
            tvTitle=itemView.findViewById(R.id.trill_tv_title);
        }
    }
}

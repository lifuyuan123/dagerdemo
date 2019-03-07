package com.example.atmf.daggerdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.Food;
import com.example.atmf.daggerdemo.entry.FoodsBean;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    public void setList(List<FoodsBean> list) {
        if (list!=null){
            list.clear();
            list.addAll(list);
            notifyItemRangeInserted(0,list.size());
        }
    }

    private List<FoodsBean> list;
    private Context context;

    public FoodAdapter(@NonNull List<FoodsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
          Glide.with(context).load(list.get(position).getImgUrl()).into(holder.iv);
          holder.name.setText(list.get(position).getName());
          holder.material.setText(list.get(position).getMaterial());
          holder.cardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(onCallBack!=null){
                      onCallBack.onItemClickListener(position);
                  }
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,material;
        private ImageView iv;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            material=itemView.findViewById(R.id.tv_material);
            iv=itemView.findViewById(R.id.iv_pic);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }

    public interface onCallBack{
       void onItemClickListener(int position);
    }
    private onCallBack onCallBack;

    public void setOnCallBack(FoodAdapter.onCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }
}

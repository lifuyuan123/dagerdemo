package com.example.atmf.daggerdemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.atmf.daggerdemo.R;


import java.util.ArrayList;
import java.util.List;

public class BinderActivity extends AppCompatActivity {
    private IBinder mPlusBinder;
    private boolean plus=false;
    private ServiceConnection mServiceConnPlus = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "mServiceConnPlus onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.e("client", " mServiceConnPlus onServiceConnected");
            mPlusBinder = service;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        bindService(null);
    }

    public void bindService(View view) {
        Intent intentPlus = new Intent();
        intentPlus.setAction("asdfsdfsdfasdf");
        intentPlus.setPackage("com.example.atmf.daggerdemo");//隐式调用是除了设置setAction()外，还需要设置setPackage();
         plus = bindService(intentPlus, mServiceConnPlus,
                Context.BIND_AUTO_CREATE);
         if (plus){
             Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
         }

        Log.e("plus", plus + "");
    }

    public void unbindService(View view){
        if (plus){
            unbindService(mServiceConnPlus);
            plus=false;
            Toast.makeText(this, "反注册成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "并未注册", Toast.LENGTH_SHORT).show();
        }

    }

    public void mulInvoked(View view){

        if (mPlusBinder == null) {
            Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
        } else {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            List<String> strings=new ArrayList<>();
            strings.add("dfsdaf");
            int _result;
            try
            {
                _data.writeInterfaceToken("CalcPlusService");
                _data.writeInt(50);
                _data.writeInt(12);
                _data.writeList(strings);
                mPlusBinder.transact(0x110, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
                Toast.makeText(this, _result + "    "+_reply.readArrayList(null).toString(), Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

    }

    public void divInvoked(View view) {

        if (mPlusBinder == null) {
            Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
        } else {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            int _result;
            try {
                _data.writeInterfaceToken("CalcPlusService");
                _data.writeInt(36);
                _data.writeInt(12);
                mPlusBinder.transact(0x111, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
                Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

    }
}

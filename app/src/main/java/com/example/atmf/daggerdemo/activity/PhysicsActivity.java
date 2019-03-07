package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.jawnnypoo.physicslayout.Physics;
import com.jawnnypoo.physicslayout.PhysicsConfig;
import com.jawnnypoo.physicslayout.PhysicsFrameLayout;
import com.jawnnypoo.physicslayout.PhysicsLinearLayout;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public class PhysicsActivity extends AppCompatActivity {

    private TextView tv;
    private PhysicsFrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);
        tv=findViewById(R.id.text);
        frameLayout=findViewById(R.id.fram);
        frameLayout.getPhysics().enablePhysics();
        frameLayout.getPhysics().enableFling();
    }
}

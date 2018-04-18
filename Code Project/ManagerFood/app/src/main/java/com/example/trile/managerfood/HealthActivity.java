package com.example.trile.managerfood;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class HealthActivity extends AppCompatActivity {

    private ImageView imgReturn;
    private ImageView imgSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_layout);
        initView();


        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(HealthActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });

        final  Dialog dialog = new Dialog(HealthActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_health);
        
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void initView() {
        imgReturn = (ImageView) findViewById(R.id.img_return_home_health);
        imgSetting = (ImageView) findViewById(R.id.imgSetting);
    }
}

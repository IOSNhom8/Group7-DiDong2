package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;


public class MainLayoutActivity extends AppCompatActivity {



    LinearLayout menu1;
    LinearLayout menu2;
    LinearLayout menu3;
    LinearLayout menu4;
    LinearLayout menu5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        menu1 = (LinearLayout) findViewById(R.id.menu_cook);
        menu2 = (LinearLayout) findViewById(R.id.menu_food);
        menu3 = (LinearLayout) findViewById(R.id.menu_heart);
        menu4 = (LinearLayout) findViewById(R.id.menu_list);
        menu5 = (LinearLayout) findViewById(R.id.menu_LogOut);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast();
                Intent intentEx = new Intent(MainLayoutActivity.this, ExpertActivity.class);
                startActivity(intentEx);
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast();
                Intent intentSuggest = new Intent(MainLayoutActivity.this, SuggestionActivity.class);
                startActivity(intentSuggest);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast();
                Intent intentHealt = new Intent(MainLayoutActivity.this, HealthActivity.class);
                startActivity(intentHealt);
            }
        });
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast();
                Intent intentCook = new Intent(MainLayoutActivity.this, CookActivity.class);
                startActivity(intentCook);
            }
        });

        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đăng xuất tài khoản thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }

    private void Toast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_1,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


}

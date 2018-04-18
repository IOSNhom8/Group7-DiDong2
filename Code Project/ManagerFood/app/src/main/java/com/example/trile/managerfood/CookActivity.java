package com.example.trile.managerfood;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trile.managerfood.Adapter.Adapter_ds_main;
import com.example.trile.managerfood.Adapter.Adapter_ds_top;
import com.example.trile.managerfood.Adapter.CustomLinearLayout;
import com.example.trile.managerfood.Models.md_main_ds;
import com.example.trile.managerfood.Models.md_top_ds;

import java.util.ArrayList;

public class CookActivity extends AppCompatActivity {

    private int position = 0;
    //
    private ViewPager mViewPager;
    private ImageView imgSet;
    private ImageView imgReturnHome;
    private TextView tvCloseDialog;
    // Array of Top
    private ArrayList<md_top_ds> arr;
    private Adapter_ds_top adapter_ds_top;
    private RecyclerView recyclerTop;
    // Array of Main
    private ArrayList<md_main_ds> arr2;
    private Adapter_ds_main adapter_ds_main;
    private RecyclerView recyclerMain;

    public CookActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_layout);

        // Ánh xạ ( Tham chiếu )
        initView();
        // Quản lý hiển thị của recycler
        // RecyclerView 1
        CustomLinearLayout layoutManager = new CustomLinearLayout(CookActivity.this,500);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTop.setHasFixedSize(true);
        recyclerTop.setLayoutManager(layoutManager);
        // RecyclerView 1
        CustomLinearLayout layoutManager2 = new CustomLinearLayout(CookActivity.this,500);
        recyclerMain.setHasFixedSize(true);
        recyclerMain.setLayoutManager(layoutManager2);

        // Mảng sản phẩm
        sp();

        // Adapter of TopProduct
        adapter_ds_top = new Adapter_ds_top(arr,getApplication());
        recyclerTop.setAdapter(adapter_ds_top);
        recyclerTop.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        // Adapter of MainProduct
        adapter_ds_main = new Adapter_ds_main(getApplication(),arr2);
        recyclerMain.setAdapter(adapter_ds_main);
        recyclerMain.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        // Chuyển động của recyclerView
        scrollByTime();


        final Dialog dialog = new Dialog(CookActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_cook);

        imgSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });
//        tvCloseDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.closeOptionsMenu();
//            }
//        });

        imgReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(CookActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });





    }

    private void initView() {
        imgSet = (ImageView) findViewById(R.id.imgSet);
        recyclerTop = (RecyclerView) findViewById(R.id.recyclerView_top);
        recyclerMain = (RecyclerView) findViewById(R.id.recyclerView_homeProduct);
        imgReturnHome = (ImageView) findViewById(R.id.img_return_home);
        tvCloseDialog = (TextView) findViewById(R.id.tvCloseDialog);
    }

    private void sp() {
        arr = new ArrayList<md_top_ds>();
        arr.add(new md_top_ds(R.mipmap.ga_chien,"Cánh gà chiên"));
        arr.add(new md_top_ds(R.mipmap.heo_quay,"Heo sữa quay"));
        arr.add(new md_top_ds(R.mipmap.heo_xao,"Heo xào rau củ"));
        arr.add(new md_top_ds(R.mipmap.thit_heo_kho,"Thịt kho trứng cút"));

        arr2 = new ArrayList<md_main_ds>();
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Cánh gà chiên","-500gr cánh gà\n" +
                "-1 gói bột chiên giòn\n" +
                "-Hạt tiêu, bơ, bột ngọt, bột nêm, tương ớt (bột ớt) đều được.\n" +
                "-Vài nhánh tỏi "));
        arr2.add(new md_main_ds(R.mipmap.heo_quay,"Heo sữa quay","-500gr cánh gà\n" +
                "-1 gói bột chiên giòn\n" +
                "-Hạt tiêu, bơ, bột ngọt, bột nêm, tương ớt (bột ớt) đều được.\n" +
                "-Vài nhánh tỏi"));
        arr2.add(new md_main_ds(R.mipmap.heo_xao,"Heo xào rau củ","-500gr cánh gà\n" +
                "-1 gói bột chiên giòn\n" +
                "-Hạt tiêu, bơ, bột ngọt, bột nêm, tương ớt (bột ớt) đều được.\n" +
                "-Vài nhánh tỏi"));
        arr2.add(new md_main_ds(R.mipmap.thit_heo_kho,"Thịt kho trứng cút","-500gr cánh gà\n" +
                "-1 gói bột chiên giòn\n" +
                "-Hạt tiêu, bơ, bột ngọt, bột nêm, tương ớt (bột ớt) đều được.\n" +
                "-Vài nhánh tỏi"));
    }

    private void scrollByTime(){
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                position++;
                if(position >=arr.size()){
                    position  = 0;
                }
                recyclerTop.smoothScrollToPosition(position);
                handler.postDelayed(this, 5000);
            }
        });
    }

}

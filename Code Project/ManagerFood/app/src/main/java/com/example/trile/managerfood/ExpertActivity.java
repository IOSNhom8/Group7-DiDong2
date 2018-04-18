package com.example.trile.managerfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.trile.managerfood.Adapter.Adapter_ex;
import com.example.trile.managerfood.Models.md_main_ds;

import java.util.ArrayList;

public class ExpertActivity extends AppCompatActivity {
    // Array of Main
    private ArrayList<md_main_ds> arr2;
    private Adapter_ex adapter_ex;
    private RecyclerView recyclerMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_layout);

        recyclerMain = (RecyclerView) findViewById(R.id.recyclerExpert);
        // RecyclerView 1

        /// RecyclerView 2
        LinearLayoutManager layoutManagertop = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerMain.setHasFixedSize(true);
        recyclerMain.setLayoutManager(layoutManagertop);
        sp();
        // Adapter of MainProduct
        adapter_ex = new Adapter_ex(getApplication(),arr2);
        recyclerMain.setAdapter(adapter_ex);
        recyclerMain.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

    }

    private void sp() {

        arr2 = new ArrayList<md_main_ds>();
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia dinh dưỡng","Để có sức khỏe tốt chúng ta nên ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia ẩm thực","Để có những món ăn ngon và đẹp ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên giá cả","Làm cách nào có món ăn ngon mà giá rẻ ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia sắc đẹp","Để có làm da đẹp mịn màn ..."));

        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia dinh dưỡng","Để có sức khỏe tốt chúng ta nên ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia ẩm thực","Để có những món ăn ngon và đẹp ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên giá cả","Làm cách nào có món ăn ngon mà giá rẻ ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia sắc đẹp","Để có làm da đẹp mịn màn ..."));

        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia dinh dưỡng","Để có sức khỏe tốt chúng ta nên ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia ẩm thực","Để có những món ăn ngon và đẹp ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên giá cả","Làm cách nào có món ăn ngon mà giá rẻ ..."));
        arr2.add(new md_main_ds(R.mipmap.ga_chien,"Chuyên gia sắc đẹp","Để có làm da đẹp mịn màn ..."));
    }
}

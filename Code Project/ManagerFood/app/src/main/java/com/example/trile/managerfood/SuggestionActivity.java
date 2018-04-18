package com.example.trile.managerfood;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.trile.managerfood.Adapter.Adapter_suggest;
import com.example.trile.managerfood.Models.md_suggest;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    private ImageView imgMenu;
    // Array of Suggest
    private ArrayList<md_suggest> arrSuggest;
    private Adapter_suggest adapter_suggest;
    private RecyclerView recyclerSuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_layout);

        imgMenu = (ImageView) findViewById(R.id.imgSet);
        recyclerSuggest = (RecyclerView) findViewById(R.id.recyclerSuggest);

        final Dialog dialog = new Dialog(SuggestionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_suggestion);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //LinearLayout layoutManager = new LinearLayout()
        /// RecyclerView 1
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerSuggest.setHasFixedSize(true);
        recyclerSuggest.setLayoutManager(layoutManager);

        arrSuggest = new ArrayList<md_suggest>();
        arrSuggest.add(new md_suggest(R.mipmap.boluclac_nuong,"Bò lúc lắc",""));
        arrSuggest.add(new md_suggest(R.mipmap.muc_nuong,"Mực nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.so_nuong,"Xò lông nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.suong_nuong,"Sường nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.thit_nuong,"Thị xâu nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.thitdui_nuong,"Đùi heo nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.boluclac_nuong,"Bò lúc lắc",""));
        arrSuggest.add(new md_suggest(R.mipmap.muc_nuong,"Mực nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.so_nuong,"Xò lông nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.suong_nuong,"Sường nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.thit_nuong,"Thị xâu nướng",""));
        arrSuggest.add(new md_suggest(R.mipmap.thitdui_nuong,"Đùi heo nướng",""));

        // Adapter of TopProduct
        adapter_suggest = new Adapter_suggest(arrSuggest,getApplicationContext());
        recyclerSuggest.setAdapter(adapter_suggest);

    }
}

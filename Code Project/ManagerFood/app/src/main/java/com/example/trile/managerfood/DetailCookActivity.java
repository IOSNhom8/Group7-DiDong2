package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trile.managerfood.Models.md_main_ds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailCookActivity extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    private DatabaseReference mData;
    private ImageView img;
    private TextView tvName;
    private TextView tvDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);
        mData = FirebaseDatabase.getInstance().getReference();
        img = (ImageView) findViewById(R.id.detailImgEx);
        tvName = (TextView) findViewById(R.id.detailName);
        tvDis = (TextView) findViewById(R.id.detailDiscipEx);
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        bundle.getString("tour");
        Toast.makeText(this, "^.^  Chúc bạn thành công & ăn ngon miệng nha ^.^", Toast.LENGTH_SHORT).show();
        loadData();
    }

    private void loadData() {
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        bundle.getString("tour");
        mData.child("SPDanhSach").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                md_main_ds main = dataSnapshot.getValue(md_main_ds.class);
                if (main.getName().equalsIgnoreCase(bundle.getString("tour"))) {
                    Picasso.with(DetailCookActivity.this).load(main.getImg()).into(img);
                    tvName.setText(main.getName());
                    tvDis.setText(main.getDesciption());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}

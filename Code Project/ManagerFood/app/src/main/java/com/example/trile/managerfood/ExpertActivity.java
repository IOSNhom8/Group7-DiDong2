package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.trile.managerfood.Adapter.Adapter_ex;
import com.example.trile.managerfood.Adapter.Adapter_spinner;
import com.example.trile.managerfood.Models.mdSpinner;
import com.example.trile.managerfood.Models.md_main_ds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExpertActivity extends AppCompatActivity {
    private ImageView img_return_home;
    // Array of Main
    private ArrayList<md_main_ds> arr2;
    private Adapter_ex adapter_ex;
    private RecyclerView recyclerMain;
    // Spinner
    private ArrayList<mdSpinner> spinnerDanhSach;
    private Adapter_spinner adapter_spinner;
    private Spinner spinner;

    //
    int posision;
    // FIREBASE
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_layout);
        mData = FirebaseDatabase.getInstance().getReference();

        img_return_home = (ImageView) findViewById(R.id.img_return_home);
        recyclerMain = (RecyclerView) findViewById(R.id.recyclerExpert);
        spinner = (Spinner) findViewById(R.id.spinnerChuyenGia);

        img_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(ExpertActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });

        spinnerPlace();

        LinearLayoutManager layoutManagertop = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerMain.setHasFixedSize(true);
        recyclerMain.setLayoutManager(layoutManagertop);
        arr2 = new ArrayList<md_main_ds>();
        //sp();
        loadData();

    }

    private void loadData() {

        // Adapter of MainProduct
        adapter_ex = new Adapter_ex(getApplication(),arr2);
        recyclerMain.setAdapter(adapter_ex);
        recyclerMain.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arr2.clear();
                posision = i;
                mData.child("SPChuyenGia").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        final md_main_ds list_main = dataSnapshot.getValue(md_main_ds.class);
                    /*    arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                        adapter_ex.notifyDataSetChanged();*/
                        if (posision == 0) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
                        }else if (posision == 1 && list_main.getType().equalsIgnoreCase("Người già")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
                        }else if (posision == 2 && list_main.getType().equalsIgnoreCase("Trẻ em")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
                        }else if (posision == 3 && list_main.getType().equalsIgnoreCase("Phái mạnh")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
                        }else if (posision == 4 && list_main.getType().equalsIgnoreCase("Phái đẹp")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
                        }else if (posision == 5 && list_main.getType().equalsIgnoreCase("Sinh sản")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ex.notifyDataSetChanged();
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

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spinnerPlace() {
        spinnerDanhSach = new ArrayList<mdSpinner>();
        spinnerDanhSach.add(new mdSpinner(R.drawable.food,"Tất cả"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.couple,"Người già"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.kids,"Trẻ em"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.man,"Phái mạnh"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.woman,"Phái đẹp"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.pregnancy,"Sinh sản"));


        adapter_spinner = new Adapter_spinner(getApplication(),R.layout.customview_spinner,spinnerDanhSach);
        spinner.setAdapter(adapter_spinner);
    }

    private void sp() {
        arr2 = new ArrayList<md_main_ds>();
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK3.jpg?alt=media&token=53454286-5fe6-4b5f-b707-efe3d35bf027","Bò hầm rau củ","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK7.jpg?alt=media&token=2421531c-f953-47d9-858b-f76bda971e08","Canh rau củ","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK10.jpg?alt=media&token=0ac2e7bc-5508-453d-943d-2259e35562af","Gà chiên giòn","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK4.JPG?alt=media&token=78f01ae1-aa3c-4ab8-968a-120cf967dfdd","Rau củ xào","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK1.jpg?alt=media&token=212f428d-2520-4cf7-b25b-620d0af6d722","Mực xào","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK15.jpg?alt=media&token=2dc9d5bb-bf6b-4548-a756-156e9696dc51","Cháo tôm","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK6.jpg?alt=media&token=086f0016-61d6-42ca-9e48-4917ab5145f2","Canh bí xanh","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK17.jpg?alt=media&token=235e3db0-f693-4330-afaf-051b314cefd4","Bò kho","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK18.jpg?alt=media&token=ddf0ae86-7b41-4a3d-a142-e520fbd3964e","Canh dê","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK2.jpg?alt=media&token=06b3383a-a645-4d33-8191-0acf09848b5e","Dê hầm đậu phụ","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK19.jpg?alt=media&token=4a2757e5-4ba3-4b1c-b0b0-f2a6c3ec56a3","Đuôi heo hầm đậu","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK26.jpg?alt=media&token=b53b790a-0aae-464c-858a-a5d3f0b4210d","Lương um nước cốt dừa","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK30.jpg?alt=media&token=bdab20f1-ebe3-4c49-9504-d4f3fa2585da","Thịt xào hành tây","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK22.jpg?alt=media&token=790f1365-350d-415e-94df-6eb95c486cdf","Sườn hầm ngũ sắc","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK35.jpg?alt=media&token=ca7b1218-54b2-4cac-ad18-597551840923","Trứng gà tam thất","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK32.jpg?alt=media&token=727bf077-7f57-4733-b316-77ad7ebf88b7","Tổ yến chưng","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK31.jpg?alt=media&token=0b643c6d-4893-4704-809a-404ef6e4aa6f","Thịt xào","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK25.Jpeg?alt=media&token=72c5dd9b-7135-4302-953d-e396e7829781","Canh chua cay ","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK8.jpg?alt=media&token=a0867e5a-356a-4301-b477-058178ba814f","Canh mồng tơi","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK33.jpg?alt=media&token=0d9c4de3-8c7c-4ec5-a969-4a5659e317a2","bắp nón xào thịt","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/changusac.jpg?alt=media&token=12ed4283-fea5-48d0-83e8-48a9f3ffccbf","Chả trứng","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phuongbay.jpg?alt=media&token=f27aca38-5c6f-4003-996f-a44dadd5459e","Chả giò long phụng","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/m%C3%B3nca.jpg?alt=media&token=b2759258-39d7-404d-ae74-4a84a025e86d","Thịt xào rau củ","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/b%C6%B0%E1%BB%9Bpxao.jpg?alt=media&token=41a7c1bc-fe88-4293-b295-c8dabda3daca","Salad Rau củ","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/sushica.jpg?alt=media&token=79a15126-804c-474e-b762-27dd84e795fa","Chả hấp","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ss1.jpg?alt=media&token=805c09c7-16c6-4f64-81ee-7fdb92b0ebf8","Rau củ hấp","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ss3.jpg?alt=media&token=ffacae8b-3d33-40b5-9ce6-93dd8709dfc3","Cá Chiên sốt cà","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ss6.jpg?alt=media&token=4c3f6fc5-2c58-4c48-8023-9e2b662ff920","Tôm hấp","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/caca.jpg?alt=media&token=7b8729c1-11f9-412b-9c8e-c16d79e17773","Cá rút xương chiên","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK5.jpg?alt=media&token=f45a7202-56a5-4893-b0f7-4334901a5785","Cà tím nướng mỡ hành","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/shushi.jpg?alt=media&token=5611268e-fc95-4471-be4f-68ce2f44bce5","Sushi trứng","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ss2.jpg?alt=media&token=d345f739-6e69-4aea-a075-5434a105dc71","Trứng cuốn thịt","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/r%C3%A2uxaongusac.jpg?alt=media&token=ec7e1e87-eb4f-4aaa-bbd8-caf3343554fe","Rau xào ngũ sắc","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK29.jpg?alt=media&token=5899c830-0db1-48c6-902f-e72dc1833273","Đậu phụ kho","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK36.JPG?alt=media&token=9526876a-e2e4-467b-a0ac-d374451ebbab","Chân gà trống hầm","","Sinh sản"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK14.jpg?alt=media&token=e318450d-1306-4677-b832-46f5b68f73e6","Cháo đậu đen","","Người già"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ss4.jpg?alt=media&token=040594c2-1188-4452-9790-5fce1c1ae972","Chả cá phụng hoàng","","Trẻ em"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK16.jpg?alt=media&token=ea21731f-d3fe-4fcb-b400-9ecaf050ce0a","Cơm chiên dương châu","","Phái mạnh"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK9.jpg?alt=media&token=b831bd1d-f22f-44b9-99f9-1bc26338a550","Canh rau dền","","Phái đẹp"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK24.jpg?alt=media&token=b4fe8aa7-00c6-463d-838b-17f86f4efcdd","Gỏi xoài","","Sinh sản"));
        mData.child("SPChuyenGia").setValue(arr2);
    }
}

package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trile.managerfood.Adapter.Adapter_ds_main;
import com.example.trile.managerfood.Adapter.Adapter_ds_top;
import com.example.trile.managerfood.Adapter.Adapter_spinner;
import com.example.trile.managerfood.Adapter.CustomLinearLayout;
import com.example.trile.managerfood.Models.mdSpinner;
import com.example.trile.managerfood.Models.md_main_ds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CookActivity extends AppCompatActivity {

    private int position = 0;
    //
    private ViewPager mViewPager;
    private ImageView imgSet;
    private ImageView imgReturnHome;
    private TextView tvCloseDialog;
    // Spinner 1
    private ArrayList<mdSpinner> spinnerDanhSach;
    private Adapter_spinner adapter_spinner;
    private Spinner spinner;
    //
    int posision;
    // Array of Top
    private ArrayList<md_main_ds> arr = new ArrayList<md_main_ds>();
    private Adapter_ds_top adapter_ds_top;
    private RecyclerView recyclerTop;
    // Array of Main
    private ArrayList<md_main_ds> arr2;
    private Adapter_ds_main adapter_ds_main;
    private RecyclerView recyclerMain;
    // FIREBASE
    DatabaseReference mData;
    public CookActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_layout);
        // FIREBASE
        mData = FirebaseDatabase.getInstance().getReference();
//        // Ánh xạ ( Tham chiếu )
        initView();

        // RecyclerView 1
        CustomLinearLayout layoutManager = new CustomLinearLayout(CookActivity.this,500);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTop.setHasFixedSize(true);
        recyclerTop.setLayoutManager(layoutManager);
        // RecyclerView 1
        CustomLinearLayout layoutManager2 = new CustomLinearLayout(CookActivity.this,500);
        recyclerMain.setHasFixedSize(true);
        recyclerMain.setLayoutManager(layoutManager2);

        arr = new ArrayList<md_main_ds>();
        arr2 = new ArrayList<md_main_ds>();

        //sp();
        spinnerPlace();

        loadData0();
        loadData();

        // Chuyển động của recyclerView
        scrollByTime();

        imgReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(CookActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });
    }

    private void loadData0() {
        // Adapter of TopProduct
        adapter_ds_top = new Adapter_ds_top(arr,getApplication());
        recyclerTop.setAdapter(adapter_ds_top);
        recyclerTop.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        mData.child("SPDeXuatDanhSach").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final md_main_ds list_main = dataSnapshot.getValue(md_main_ds.class);
                arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                adapter_ds_top.notifyDataSetChanged();
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

    private void loadData() {
        // Adapter of MainProduct
        adapter_ds_main = new Adapter_ds_main(getApplication(),arr2);
        recyclerMain.setAdapter(adapter_ds_main);
        recyclerMain.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arr2.clear();
                posision = i;
                mData.child("SPDanhSach").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final md_main_ds list_main = dataSnapshot.getValue(md_main_ds.class);/*
                        arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                        adapter_ds_main.notifyDataSetChanged();*/
                        if (posision == 0) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 1 && list_main.getType().equalsIgnoreCase("Thịt")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 2 && list_main.getType().equalsIgnoreCase("Cá")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 3 && list_main.getType().equalsIgnoreCase("Hải sản")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 4 && list_main.getType().equalsIgnoreCase("Rau củ")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 5 && list_main.getType().equalsIgnoreCase("Trứng")) {
                            arr2.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
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

    private void initView() {
        recyclerTop = (RecyclerView) findViewById(R.id.recyclerView_top);
        recyclerMain = (RecyclerView) findViewById(R.id.recyclerView_homeProduct);
        imgReturnHome = (ImageView) findViewById(R.id.img_return_home);
        spinner = (Spinner) findViewById(R.id.spinnerLIST);
    }


    private void sp() {
        arr = new ArrayList<md_main_ds>();
      /*  arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan7.jpg?alt=media&token=4956ab4f-0568-46bf-b1f2-27a3e473f9c7","Cua rang me","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phuongmua.jpg?alt=media&token=d9ec307f-3819-4f12-a68b-79c3e0e3b072","Chả giò phụng hoàng","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/meocon.jpg?alt=media&token=5c73df0f-a451-43f5-8d00-985d5005e0e7","Cơm nấm","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg10.jpg?alt=media&token=e549fc18-418d-4a1c-bf27-16a0609db46e","Tôm hùm uyên ương","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh12.jpg?alt=media&token=61b0ff2c-95d0-46ab-8eb9-f508cf41d1de","Tôm sốt bí đỏ","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A15.jpg?alt=media&token=8fb385ff-84e9-4011-90dc-00be2012f21c","Thịt bò áp chảo","",""));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan2.png?alt=media&token=bbd9c131-b380-432d-b964-3648157e134d","Tôm lăng bột giòn","",""));
        mData.child("SPDeXuatDanhSach").setValue(arr);*/

        arr2 = new ArrayList<md_main_ds>();
      /*  arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A15.jpg?alt=media&token=8fb385ff-84e9-4011-90dc-00be2012f21c","Thịt bò áp chảo","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A11.jpg?alt=media&token=b15d8762-ff53-4017-acd0-fafb5e8040f4","Cá chiên xù","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg10.jpg?alt=media&token=e549fc18-418d-4a1c-bf27-16a0609db46e","Tôm hùm uyên ương","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/meocon.jpg?alt=media&token=5c73df0f-a451-43f5-8d00-985d5005e0e7","Cơm nấm","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Tr%E1%BB%A9ng5.jpg?alt=media&token=674bbd95-8d56-4637-8842-399adf5f6a4d","Trứng hấp","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt1.jpg?alt=media&token=2bdb128e-312e-4e21-bdf1-cf8692ec0793","Bò kho ","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia30.jpg?alt=media&token=6377dd12-c7b6-4e02-9f59-10f6b50672b9","Cá hấp sốt cay","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh12.jpg?alt=media&token=61b0ff2c-95d0-46ab-8eb9-f508cf41d1de","Tôm sốt bí đỏ","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%E1%BB%A71.jpg?alt=media&token=8587fbe8-1518-4656-83df-6b49a5f608cc","Canh củ dền","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Tr%E1%BB%A9ng6.jpg?alt=media&token=4515fdf5-3e3b-4d81-be91-9401d223a4ad","Trứng bọc thịt chiên xù","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt9.jpg?alt=media&token=a876972f-ff34-40aa-b8d7-c8897110556a","Vịt nấu chao","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/m%C3%B3nca.jpg?alt=media&token=b2759258-39d7-404d-ae74-4a84a025e86d","Sushi cá hồi","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan7.jpg?alt=media&token=4956ab4f-0568-46bf-b1f2-27a3e473f9c7","Cua rang me","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau2.jpg?alt=media&token=18ce53df-f1aa-49c1-88cd-11b765927b1a","Salad rau trộn","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/SK35.jpg?alt=media&token=ca7b1218-54b2-4cac-ad18-597551840923","Trứng gà tiềm tam thất","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt5.jpg?alt=media&token=74b8fddd-be30-46e6-b947-0a97ed44c336","Vịt kho gừng","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A13.jpg?alt=media&token=c8287661-ade0-4bc6-941d-8548ef35b361","Cá khô nghệ","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan2.png?alt=media&token=bbd9c131-b380-432d-b964-3648157e134d","Tôm lăng bột giòn","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%E1%BB%A72.jpg?alt=media&token=ab4a3ee7-4beb-489f-ae8c-31cc9fff3519","Rau củ xào thập cẩm","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/shushi.jpg?alt=media&token=5611268e-fc95-4471-be4f-68ce2f44bce5","sushi trứng cuộn","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt10.jpg?alt=media&token=0d5a2526-540b-42fc-8a98-fc206cbaedc1","Thịt chiên nước mắm","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A16.jpg?alt=media&token=24406448-1e50-42d7-85f9-d864191ce442","Washibi cá hồi","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan4.jpg?alt=media&token=6434d553-858f-494c-b4e9-bea2ce969051","Nghêu sốt cay","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau7.jpg?alt=media&token=78e44505-c8d6-4cff-b611-3d9b573cbc79","Canh khổ qua dồn thịt","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/trungchien.jpg?alt=media&token=4a472777-c743-4b2d-ac9c-c2b5e01b7f17","Cơm chiên bọc trứng","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia34.jpg?alt=media&token=03aab011-02a5-46e5-92ea-2c571523a878","Gà tiềm hạt sen","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A12.jpg?alt=media&token=8eaad11d-a0da-46ec-a56b-b3369f9c58c5","Cá hấp hành","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan6.jpg?alt=media&token=bf9d0380-e123-4be1-8d40-2cc435aadce3","Hàu nướng phômai","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau8.jpg?alt=media&token=1b9b3abd-e75a-4c61-b1af-d189add4f7fd","Gỏi đủ đu","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/trungtronon.jpg?alt=media&token=7affe75e-a56f-47c5-8f5a-a2f605fd9133","Trứng cuộn thịt hấp","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu5.png?alt=media&token=e8dfd054-71d4-4fd6-92c6-6185b27f8299","Chân gà nướng sate","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20chua%20ca.jpg?alt=media&token=ad68762e-db42-4faf-8ce1-f74baf62ec6c","Canh chua cá lóc","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg8.jpg?alt=media&token=8ffe6a24-371b-43bb-ae9c-29d1713b6c84","Tôm hấp nước dừa","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau3.jpg?alt=media&token=7e3ceae7-1545-4fd4-a381-8bc0c8842cc9","Rau trộn trứng luộc","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Tr%E1%BB%A9ng7.jpg?alt=media&token=533e4d4d-b3f0-4460-b7dc-69cc2c9353a6","Trứng sốt cà thịt","","Trứng"));


        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt7.jpg?alt=media&token=d6b292a6-9916-4b77-bf25-eb1e85bb5b73","Thịt xào lá lốt","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/C%C3%A14.jpg?alt=media&token=4fe46677-09b5-4efc-8736-701b0d01409d","Cá khô tộ","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg5.jpg?alt=media&token=5c1277b9-b388-44bb-8610-2fdf0aa34419","Mực 1 nắng nướng","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau1.jpg?alt=media&token=0c7794cf-1aec-464f-89a5-027bb5f2d620","Rau muống xào tỏi","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/trungtron.jpg?alt=media&token=f91b66c1-3608-4b42-bbed-9a7e5c39b9e1","Trứng chiên","","Trứng"));

        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Th%E1%BB%8Bt8.jpg?alt=media&token=0c61f0d8-02ee-409f-b486-77f62e52b88b","Thịt bò xào","","Thịt"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia33.jpg?alt=media&token=296a7ce2-15b2-45c9-90fb-de39cca2f2a0","Cá chiên giòn","","Cá"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/HaiSan1.jpg?alt=media&token=d14f8dba-14de-4030-bf85-d3e833e93d6b","Tôm sốt cam","","Hải sản"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/Rau9.jpg?alt=media&token=a0e26768-356e-45bd-873d-fde404614080","Rau luộc tôm","","Rau củ"));
        arr2.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/sadsa.jpg?alt=media&token=3cbb9fc7-e384-40d6-888d-bb4b38a0143d","Trứng chiên","","Trứng"));

        mData.child("SPDanhSach").setValue(arr2);*/

    }

    private void spinnerPlace() {
        spinnerDanhSach = new ArrayList<mdSpinner>();
        spinnerDanhSach.add(new mdSpinner(R.drawable.cookbook,"Tất cả"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.steak,"Thịt"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.tuna,"Cá"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.oyster,"Hải sản"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.vegetables,"Rau củ"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.egg,"Trứng"));

        adapter_spinner = new Adapter_spinner(getApplication(),R.layout.customview_spinner,spinnerDanhSach);
        spinner.setAdapter(adapter_spinner);
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

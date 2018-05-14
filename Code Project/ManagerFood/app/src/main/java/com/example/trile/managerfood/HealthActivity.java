package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.trile.managerfood.Adapter.Adapter_ds_main;
import com.example.trile.managerfood.Adapter.Adapter_spinner;
import com.example.trile.managerfood.Adapter.Adapter_suckhoe;
import com.example.trile.managerfood.Adapter.CustomLinearLayout;
import com.example.trile.managerfood.Models.mdSpinner;
import com.example.trile.managerfood.Models.md_main_ds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {
    private int position = 0;
    private ImageView imgReturn;
    private ImageView imgSetting;

    // Array of Main
    private ArrayList<md_main_ds> arr;
    private Adapter_suckhoe adapter_ds_main;
    private RecyclerView recyclerMain;
    // FIREBASE
    DatabaseReference mData;
    // Spinner
    private ArrayList<mdSpinner> spinnerDanhSach;
    private Adapter_spinner adapter_spinner;
    private Spinner spinner;
    //
    int posision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_layout);
        // FIREBASE
        mData = FirebaseDatabase.getInstance().getReference();
        // ÁNH XẠ , THAM CHIẾU
        initView();

        // RecyclerView 1
        CustomLinearLayout layoutManager2 = new CustomLinearLayout(HealthActivity.this,500);
        recyclerMain.setHasFixedSize(true);
        recyclerMain.setLayoutManager(layoutManager2);

        // SPINNER
        spinnerPlace();
        // MẢNG THÊM DỮ LIỆU LÊN FIREBASE
        //sp();
        arr = new ArrayList<md_main_ds>();
        loadDataMain ();

        // Nút trở về
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(HealthActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });

    }

    private void spinnerPlace() {
        spinnerDanhSach = new ArrayList<mdSpinner>();
        spinnerDanhSach.add(new mdSpinner(R.drawable.doctor,"Tất cả"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.cardiogram,"Tim"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.brain,"Cao huyết áp"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.stomach,"Đau bao tử"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.sumo,"Béo phì"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.cold,"Cảm sốt"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.sugar,"Tiểu đường"));

        adapter_spinner = new Adapter_spinner(getApplication(),R.layout.customview_spinner,spinnerDanhSach);
        spinner.setAdapter(adapter_spinner);
    }

    private void loadDataMain () {
        // Adapter of MainProduct
        adapter_ds_main = new Adapter_suckhoe(getApplication(),arr);
        recyclerMain.setAdapter(adapter_ds_main);
        recyclerMain.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arr.clear();
                posision = i;
                mData.child("SPSucKhoe").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final md_main_ds list_main = dataSnapshot.getValue(md_main_ds.class);
//                        arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
//                        adapter_ds_main.notifyDataSetChanged();

                        if (posision == 0) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 1 && list_main.getType().equalsIgnoreCase("Tim")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 2 && list_main.getType().equalsIgnoreCase("Cao huyết áp")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 3 && list_main.getType().equalsIgnoreCase("Tiểu đường")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 4 && list_main.getType().equalsIgnoreCase("Béo phì")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_ds_main.notifyDataSetChanged();
                        }else if (posision == 5 && list_main.getType().equalsIgnoreCase("Cảm sốt")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
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

        private void sp() {
        arr = new ArrayList<md_main_ds>();

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/1.jpg?alt=media&token=de2e803b-1046-4d4a-8005-b72b4322a41b","Chả cá phụng hoàng","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/3.jpg?alt=media&token=3e9545c6-996c-4183-b2bb-a85151caf2ee","Cơm rau củ","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/4.jpg?alt=media&token=4e93e3db-fb3d-44e0-baee-853b6eeb6c7d","gỏi hoa mực","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/6.jpg?alt=media&token=e8ea7e45-f0b3-48cb-b4b7-7488a3be059b","Tôm chiên bột","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/7.jpg?alt=media&token=1f660f99-53cc-4d43-90a5-67df45f6bcee","Gà ngũ sắc","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/8.jpg?alt=media&token=2b78d414-cf91-4f98-a7e3-d166bcc550e0","Cơm ninja","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/9.jpg?alt=media&token=370d71ba-5980-4a8f-9030-11d7ba6ca736","Cơm cậu bé","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/12.jpg?alt=media&token=5849d09b-6eb1-4f7c-9ba1-be7a2b032833","Cơm công chúa","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/13.jpg?alt=media&token=20b545c8-62cc-49c0-8e4e-1c283eddf422","Cơm xấu xí","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/14.jpg?alt=media&token=7b8e5fa8-ab14-4c70-8c69-ed3df323c2c6","Cơm hoạt hình","","Cảm sốt"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg2.jpg?alt=media&token=13d74978-1c2a-4bec-9b12-bd2b9e2ce67d","Tôm chiên sốt bơ","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg3.jpg?alt=media&token=bd756319-991c-49b7-964d-36887ca01f83","Chả giò tôm","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg4.jpg?alt=media&token=f86f3ae9-705f-4f7f-b7b0-5f6fab7e8b5e","Mực nướng","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg5.jpg?alt=media&token=c45066ae-07df-4ba5-970e-5cb97a93ccb3","Mực sốt thái","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg1.jpg?alt=media&token=7090eeea-73ce-4844-98fc-80f08a6625d1","Cua rang me","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg6.jpg?alt=media&token=8ef915e1-5f76-49f9-a218-8198242785e2","Súp ngũ sắc","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg7.jpg?alt=media&token=22ce2c63-8b8c-4f38-a98d-7ef79599d250","Tôm nướng phômai","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg8.jpg?alt=media&token=56d56fd6-538b-469e-b35f-acc17d9bdd53","Tôm hấp nước dừa","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg9.jpg?alt=media&token=e1d8451b-1190-4857-adcb-7d2d379ea9f4","Bánh plan tôm","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg10.jpg?alt=media&token=5053da70-3c63-4ada-ae55-68cdb453dc07","Tôm hùm uyên ương","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg11.jpg?alt=media&token=6d587996-6aaa-4641-977e-9717e425a38c","Sushi cá","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg12.jpg?alt=media&token=391d82d7-af77-4fd2-b842-e9d760e4becd","Cá xoay hấp","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg13.jpg?alt=media&token=ec6a7a28-f7ae-4abd-b730-7134a3316afc","Tôm hấp","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg14.jpg?alt=media&token=65faeb81-7e90-4d98-afd5-77d418b5512b","Tôm sốt bí đỏ","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg15.jpg?alt=media&token=d81464a6-9a2e-4a82-bcd2-e47396d8d7b7","Cháo yến mạch","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg16.jpg?alt=media&token=be2e1c83-cc5e-466f-a162-7601d5089735","Miến xào rau củ","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg19.jpg?alt=media&token=b1d613ba-6c68-4846-b305-d359b989348b","Cá hấp","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg20.jpg?alt=media&token=f4deb542-5a6b-43ba-be85-ded135c825b3","Rau trộn thịt","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg21.jpg?alt=media&token=2e3af121-8df1-42d6-953d-c8882c0543c2","Gà tìm hạt sen","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg22.jpg?alt=media&token=31a94de3-7917-4947-b285-14318d1cdb93","Canh măng hầm móng héo","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg23.jpg?alt=media&token=510cae82-ebcb-4c74-8c19-f9332e9876f6","Đậu hũ kho tương","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg24.jpg?alt=media&token=1b3485c0-6265-4632-9b1f-32f592ccb16b","Gà ác tiềm","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg26.jpg?alt=media&token=d2100c36-17a9-4475-963c-7ef4028293ff","Đuôi heo nấu đậu","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg27.jpg?alt=media&token=e8bbfcc4-fb40-4bd2-8e85-0ba33c91be0b","Rau xào thịt bò","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg28.jpg?alt=media&token=329b247e-7626-4878-b023-0c74d33da617","Thịt heo nấu rau củ","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg31.jpg?alt=media&token=051d48ff-3518-4cab-825e-e70f39a50480","Đậu hũ sốt cà","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg33.jpg?alt=media&token=04c2a38a-d106-4b98-bc0b-534b9f832520","Bò xào cần tây","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg34.jpg?alt=media&token=aee45d0e-4680-4ee9-adad-ed6be2e176ae","Canh bí xanh","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg35.jpg?alt=media&token=c9f1e95b-4f58-4cb6-bcf7-dff7a2109c53","Sữa tươi chiên","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg36.jpg?alt=media&token=27e31523-f70e-45c2-9212-e52a49a19ee6","Thịt áp chảo","","Cảm sốt"));

            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg37.jpg?alt=media&token=9985c348-33e4-42a6-9e55-34d14182e694","Trứng dồn khổ qua","","Tim"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg38.jpg?alt=media&token=eb50e0a4-d0c6-431b-a314-19beb7eb3d81","Cá kho khóm","","Cao huyết áp"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cg39.jpg?alt=media&token=fc6d0f01-7c97-4da1-afe2-d740b9f0f6c9","Cháo thịt bằm","","Tiểu đường"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/10.jpg?alt=media&token=70284045-be53-42f2-942f-4e33387ace51","Cơm nấm","","Béo phì"));
            arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/11.jpg?alt=media&token=e627f711-93d2-4680-b2c8-9c8e66409cf6","Mỳ trộn cơm khô","","Cảm sốt"));

        mData.child("SPSucKhoe").setValue(arr);
    }


    private void initView() {
        imgReturn = (ImageView) findViewById(R.id.img_return_home_health);
        recyclerMain = (RecyclerView) findViewById(R.id.recyclerView_HearthMain);
        spinner = (Spinner) findViewById(R.id.spinnerHearth);
    }


}

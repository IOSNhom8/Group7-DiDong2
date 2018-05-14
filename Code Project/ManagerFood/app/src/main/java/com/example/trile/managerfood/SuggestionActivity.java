package com.example.trile.managerfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.trile.managerfood.Adapter.Adapter_spinner;
import com.example.trile.managerfood.Adapter.Adapter_suggest;
import com.example.trile.managerfood.Models.mdSpinner;
import com.example.trile.managerfood.Models.md_main_ds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    private ImageView imgMenu;
    private ImageView imgClose;
    // Array of Suggest
    private Adapter_suggest adapter_suggest;
    private RecyclerView recyclerSuggest;
    private ArrayList<md_main_ds> arr;
    // Spinner 1
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
        setContentView(R.layout.suggestion_layout);
        recyclerSuggest = (RecyclerView) findViewById(R.id.recyclerSuggest);
        spinner = (Spinner) findViewById(R.id.spinnerGoiY1);
        imgClose = (ImageView) findViewById(R.id.img_return_home);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealt = new Intent(SuggestionActivity.this, MainLayoutActivity.class);
                startActivity(intentHealt);
            }
        });


        mData = FirebaseDatabase.getInstance().getReference();
        /// RecyclerView 1
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerSuggest.setHasFixedSize(true);
        recyclerSuggest.setLayoutManager(layoutManager);

        spinnerPlace();

        arr = new ArrayList<md_main_ds>();
      /*  arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh4.png?alt=media&token=23d3cdc2-d035-4105-8006-1564109c05d5","Bò nướng than hoa","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/.200.png?alt=media&token=33dfb33a-cf6f-4b21-97cd-91c154386de2","Canh rong biển","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/c1.png?alt=media&token=f4a8c0ea-5dd1-47d3-93e9-d7c60b0dd698","Cánh gà chiên nước mắm","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu2.png?alt=media&token=633b3de9-7f83-4d41-8400-2bb843ad9c17","Mỳ xào giòn","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20b%C3%B4ng%20c%E1%BA%A3i.jpg?alt=media&token=e09497a9-4617-401f-9628-d84d7f1e17fc","Canh bông cải","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb1.jpg?alt=media&token=64e589f8-435b-455f-abe0-af2bf29a3d6a","Combo 1(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh6.png?alt=media&token=b928e67f-9222-40bd-9247-748ac8b6c436","Chân gà chiên nước mắm","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY4.png?alt=media&token=26b98086-6e32-4994-8f74-ea265303f86e","Giò heo chiên giòn","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY2.png?alt=media&token=85bdbb6d-0aa1-4de1-86d5-56db660a7c1d","Cá chiên giòn","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu1.png?alt=media&token=c74fe8b7-3bdb-44ec-9a39-a095acd538f6","Cải xào tôm","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20chua%20ca.jpg?alt=media&token=22fafbd5-109f-4b79-96f3-aab78f84a7ff","Canh chua cá","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb2.jpg?alt=media&token=cd914b9e-caa0-43ed-9e21-c571697fb3fb","Combo 2(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/100.png?alt=media&token=cd305ad1-a2ed-458f-8ad0-e81755dd3325","Canh rau củ sườn non","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY17.png?alt=media&token=3665dbe5-c65e-490e-abff-4e037ab33eec","Thịt heo xiên nướng","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY3.png?alt=media&token=4a752da5-e332-4457-947e-cf8ecdd5b8e3","Gà trùm mền","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu3.png?alt=media&token=576d4ce5-8965-4e2b-a508-647a4054dec4","Giá hẹ xào hải sản","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20khoai.jpg?alt=media&token=cef07d22-0c1f-4222-9ff0-b9951887871f","Canh khoai tím","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb3.jpg?alt=media&token=15a1d3f6-eb04-40d1-88b7-8e6d02f6fa6b","Combo 3(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/111.png?alt=media&token=fa06d84e-7d41-4e34-913e-7ae53da2f9f9","Canh cá","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ch%E1%BA%A3gio.jpg?alt=media&token=f4716b65-e0be-409b-8997-b315fb3fe123","Chả giò hải sản","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh3.png?alt=media&token=da7fe4a5-4cbb-420f-b4a8-eab0f45628a0","Vịt kho gừng","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu4.png?alt=media&token=905e7ba7-c86d-499d-bae8-8c81d67d9cc2","Bò bía","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20m%C6%B0%E1%BB%9Bp.jpg?alt=media&token=8de5465b-dbc1-425c-a1e7-50ba2ce6030a","Canh mướp nấu tôm","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb4.jpg?alt=media&token=44f7dc66-b827-4556-8e92-153306035450","Combo 4(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh5.png?alt=media&token=3590d5fa-fa28-4bee-a771-a4d9d1d0b81b","Tôm nướng","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/caca.jpg?alt=media&token=7b8729c1-11f9-412b-9c8e-c16d79e17773","Cá chiên không xương ","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY6.png?alt=media&token=9542e50c-59fa-4d54-b0d4-412e842b08d9","Tôm lăn bột","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu5.png?alt=media&token=ee1036c3-6312-490d-9943-da31748958a4","Chân gà nướng","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20rau%20c%E1%BB%A7.jpg?alt=media&token=a0cbaa9f-69ee-4777-95b8-2547a4cb4210","Canh rau củ","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb5.jpg?alt=media&token=31a4a0df-5285-41e3-9b1b-5282412d75dd","Combo 5(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/200.png?alt=media&token=d154bbac-5beb-411b-819e-f46e4cb2edab","Mực xào","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia25.jpg?alt=media&token=200dce4f-c116-4c40-a6bf-70f386883330","Khổ qua cắt lát","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY9.png?alt=media&token=4da38c2c-f4a9-429f-959f-a92a85979826","Cá khô tộ","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu8.png?alt=media&token=a98beb77-f89c-4c34-af58-a653a71e7562","Vịt lộn sốt me","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh%20rau%20d%E1%BB%81n.jpg?alt=media&token=5ed2b59c-b961-4963-980f-123438a0b740","Canh rau dền","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb7.jpg?alt=media&token=8e3a6481-27e6-4000-be5d-9d8b378511d0","Combo 6(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/50k.png?alt=media&token=f505329c-ef2c-4063-ac36-f5c6a8e1ff95","Tôm chiên ","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia28.jpg?alt=media&token=77a5aece-97b6-492d-9407-ed2c14b29227","Cháo tổ yến","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh2.png?alt=media&token=d8ec1d78-9e49-40d8-b70e-4166085ee97b","Bò hầm","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY30.png?alt=media&token=31aa810b-5b56-4b50-a2b8-cbbda1409d8b","Nghêu hấp thái","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh7.png?alt=media&token=b556dd7e-8c7d-453e-8b4c-c12375615637","Canh tôm chua cay","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb6.jpg?alt=media&token=bc1228c6-6361-4a16-9100-dc7dc73fc492","Combo 7(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh1.png?alt=media&token=57dddcdb-3573-460e-8371-0513f11989e4","Bò xào cần tây","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/ChuyenGia30.jpg?alt=media&token=6377dd12-c7b6-4e02-9f59-10f6b50672b9","Cá hấp cay","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY21.png?alt=media&token=352fa0a3-eea2-4eae-a4fe-2dd2d187c2a1","Gà luộc","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY14.png?alt=media&token=d1cf75e7-1f49-48b6-aeca-636caa3a8b1e","bò cuộn kim chi","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh8.png?alt=media&token=8570193d-3f65-44c1-8fd7-2cdffc5b8c00","Canh mồng tơi tôm khô","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cbb.jpg?alt=media&token=d7463b99-e0fc-425f-8c81-09903b785b79","Combo 8(1 mặn,1 món canh,1 món xào)","","Combo"));

        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/chinh7.png?alt=media&token=cfc97ee4-ae92-4759-886a-912a787e422b","Cá thác lác kho trứng","","100-200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/10.png?alt=media&token=a5754b30-7de5-41ef-8806-7540c4e271eb","Thịt kho đậu trứng","","> 200 K"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/GoiY13.png?alt=media&token=ec34d61e-898b-4c15-8dad-6340f39b696e","Thịt kho trứng","","Món chính"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/phu6.png?alt=media&token=144bbe4e-0fad-41d7-b281-fd90a1976fab","Khoai tây chiên","","Món phụ"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/canh9.png?alt=media&token=e862cf76-0f26-488c-8146-8f6fa2da65e3","Canh khoai tây cà rốt","","Món canh"));
        arr.add(new md_main_ds("https://firebasestorage.googleapis.com/v0/b/managerfood-8b768.appspot.com/o/cb100.jpg?alt=media&token=e2374ac9-85ff-41cb-83dc-3007c91eb776","Combo 9(1 mặn,1 món canh,1 món xào)","","Combo"));

        mData.child("SPGoiY").setValue(arr);*/
        // Adapter of TopProduct

        LoadData();

    }

    private void spinnerPlace() {
        spinnerDanhSach = new ArrayList<mdSpinner>();
        spinnerDanhSach.add(new mdSpinner(R.drawable.menu,"Tất cả"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.money,"100-200 K"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.money,"> 200 K"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.diet,"Món chính"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.diet,"Món phụ"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.diet,"Món canh"));
        spinnerDanhSach.add(new mdSpinner(R.drawable.diet,"Combo"));


        adapter_spinner = new Adapter_spinner(getApplication(),R.layout.customview_spinner,spinnerDanhSach);
        spinner.setAdapter(adapter_spinner);
    }

    private void LoadData()
    {
        adapter_suggest = new Adapter_suggest(arr,getApplicationContext());
        recyclerSuggest.setAdapter(adapter_suggest);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arr.clear();
                posision = i;
                mData.child("SPGoiY").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final md_main_ds list_main = dataSnapshot.getValue(md_main_ds.class);
                        if (posision == 0) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 1 && list_main.getType().equalsIgnoreCase("100-200 K")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 2 && list_main.getType().equalsIgnoreCase("> 200 K")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 3 && list_main.getType().equalsIgnoreCase("Món chính")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 4 && list_main.getType().equalsIgnoreCase("Món phụ")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 5 && list_main.getType().equalsIgnoreCase("Món canh")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
                        }else if (posision == 6 && list_main.getType().equalsIgnoreCase("Combo")) {
                            arr.add(new md_main_ds(list_main.getImg(),list_main.getName(),list_main.getDesciption(),list_main.getType()));
                            adapter_suggest.notifyDataSetChanged();
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
}

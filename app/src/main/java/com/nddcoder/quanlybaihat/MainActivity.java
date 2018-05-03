package com.nddcoder.quanlybaihat;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BaiHatDB baiHatDB;
    List<BaiHat> dsBaiHat;
    BaiHatAdapter baiHatAdapter;

    EditText txtTimKiem;
    ListView lvBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        khoiTaoDuLieuDB();
        anhXa();
        themSuKien();
    }

    private void themSuKien() {
        txtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                baiHatAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvBaiHat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        BaiHat baiHat = (BaiHat) baiHatAdapter.getItem(position);
                        baiHatDB.xoaBaiHat(baiHat.getMaBaiHat());
                        dsBaiHat.remove(position);
                        baiHatAdapter.xoaBaiHat(baiHat.getMaBaiHat());
                        baiHatAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
    }

    private void anhXa() {
        txtTimKiem = findViewById(R.id.txtTimKiem);
        lvBaiHat = findViewById(R.id.lvBaiHat);

        dsBaiHat = baiHatDB.layTatCaBaiHat();
        baiHatAdapter = new BaiHatAdapter(dsBaiHat, this);

        lvBaiHat.setAdapter(baiHatAdapter);

    }

    private void khoiTaoDuLieuDB() {
        baiHatDB = new BaiHatDB(this, "db_baihat", null, 1);
        baiHatDB.themBaiHat(new BaiHat("1", "ten 1", "ca si 1", "5:00"));
        baiHatDB.themBaiHat(new BaiHat("2", "ten 2", "ca si 2", "5:00"));
        baiHatDB.themBaiHat(new BaiHat("3", "ten 3", "ca si 3", "5:00"));
        baiHatDB.themBaiHat(new BaiHat("4", "ten 4", "ca si 2", "5:00"));
    }
}

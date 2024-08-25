package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class PhimSapchieuThi extends AppCompatActivity {


    private List<userFilm> userFilms;
    private userFilmAdapter adapter;
    private RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_phim_sap_chieu);

        SQLite sqLite = new SQLite(PhimSapchieuThi.this);
        rcv = findViewById(R.id.rcv_full_psc);
        adapter = new userFilmAdapter(PhimSapchieuThi.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PhimSapchieuThi.this, 2);
        rcv.setLayoutManager(gridLayoutManager);

        adapter.setData(PhimSapchieuThi.this,sqLite.getListPhimSapChieu());
        rcv.setAdapter(adapter);
    }


}
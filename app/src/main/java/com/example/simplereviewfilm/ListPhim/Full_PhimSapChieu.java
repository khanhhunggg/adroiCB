package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class Full_PhimSapChieu extends AppCompatActivity {


    private Button btnSapxep;
    private List<userFilm> userFilms;
    private List<userFilm> lst;
    private RecyclerView recyclerView;
    private userFilmAdapter adapter;
    private userFilmAdapterQLY adapterQLY;
    private RecyclerView rcv;
    private SQLite sqLite;

    public void admin(List<userFilm> lst){
        this.lst = lst;
        adapterQLY.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_phim_sap_chieu);

        SQLite sqLite = new SQLite(Full_PhimSapChieu.this);
        rcv = findViewById(R.id.rcv_full_psc);
        adapter = new userFilmAdapter(Full_PhimSapChieu.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Full_PhimSapChieu.this, 2);
        rcv.setLayoutManager(gridLayoutManager);

        adapter.setData(Full_PhimSapChieu.this,sqLite.getListPhimSapChieu());
        rcv.setAdapter(adapter);
       // getRCV();
    }

    @Override
    protected void onResume() {
        super.onResume();
       lst = sqLite.getALLPhimSX();
        adapterQLY.setData(Full_PhimSapChieu.this, lst);
        adapterQLY.notifyDataSetChanged();
    }
    public void getRCV()
    {
              lst = sqLite.getALLPhimSX();

        adapterQLY = new userFilmAdapterQLY(Full_PhimSapChieu.this);
        adapterQLY.setData(Full_PhimSapChieu.this, lst);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Full_PhimSapChieu.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterQLY);
    }
}
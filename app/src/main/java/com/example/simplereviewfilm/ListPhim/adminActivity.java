package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.simplereviewfilm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class adminActivity extends AppCompatActivity {
    public static SQLite film;
    private RecyclerView recyclerView;
    private List<userFilm> lst;
    private userFilmAdapter adapterPSC;

    private userFilmAdapterQLY adapterQLY;
    private SQLite sqLite;
    private Button btnLocSapchieu;
    private Button btnLocDangchieu;

    private FloatingActionButton FABtn;

    private boolean b= false;

    public adminActivity(){}
    public void admin(List<userFilm> lst){
        this.lst = lst;
        adapterQLY.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
     //   lst = sqLite.getALLPhim();
        lst = sqLite.getALLPhimSX();
        adapterQLY.setData(adminActivity.this, lst);
        adapterQLY.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FABtn = findViewById(R.id.flBtn_Them);
        sqLite = new SQLite(adminActivity.this);
        btnLocSapchieu=findViewById(R.id.btnLocSapchieu);
        btnLocDangchieu=findViewById(R.id.btnLocDangchieu);

//        film = new SQLite(getActivity());
//        adapterPSC = new userFilmAdapter(getActivity());
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        //adapterPSC.setData(getActivity(),lstSC);

        FABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, QlyPhim.class);
                intent.putExtra("chucNang", "them");
                startActivity(intent);
            }
        });
        btnLocSapchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PhimSapchieu();
            }
        });

        recyclerView = findViewById(R.id.rcv_sua);
        getRCV();
    }
    public  void PhimSapchieu(){
        btnLocSapchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getIntent(), Full_PhimSapChieu.class);
               // startActivity(intent);
            }
        });
    }



    public void getRCV()
    {
        lst = sqLite.getALLPhimSX();
   //     lst = sqLite.getALLPhim();
        adapterQLY = new userFilmAdapterQLY(adminActivity.this);
        adapterQLY.setData(adminActivity.this, lst);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(adminActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterQLY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_ac).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterQLY.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterQLY.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
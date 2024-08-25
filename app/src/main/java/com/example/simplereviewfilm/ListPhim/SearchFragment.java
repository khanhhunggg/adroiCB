package com.example.simplereviewfilm.ListPhim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    AutoCompleteTextView auto;
    private RecyclerView recyclerView;
    private List<userFilm> lst;
    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        auto = view.findViewById(R.id.auto_txt);
        recyclerView = view.findViewById(R.id.rcv_search);
        SQLite sql = new SQLite(getActivity());
        Cursor cursor = sql.getData("Select sTenPhim from tblPhim");
        List<String> arr = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                arr.add(cursor.getString(cursor.getColumnIndex("sTenPhim")));
            }while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,arr);
        auto.setAdapter(adapter);
        auto.setThreshold(1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lst = sql.getListSearch(auto.getText().toString());
                Toast.makeText(getActivity(),auto.getText().toString(), Toast.LENGTH_SHORT).show();
                userFilmAdapter adapter1 = new userFilmAdapter(getActivity());
                adapter1.setData(getActivity(),lst);
                recyclerView.setAdapter(adapter1);
            }
        });

        return  view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}

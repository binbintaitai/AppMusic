package com.android.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DanhSachNgheSi extends AppCompatActivity {
    ListView lvNgheSi;
    List<NgheSi> arrayNgheSi;
    NgheSiAdapter adapter;
    ArrayList<String> arrayNameNgheSi;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nghe_si);

        Mapping();
        addListSongs();
        adapter = new NgheSiAdapter(this,R.layout.dong_danh_sach_nghe_si,arrayNgheSi);
        lvNgheSi.setAdapter(adapter);
        arrayNameNgheSi = new ArrayList<String>();
        for(int i=0;i<arrayNgheSi.size();i++){
            arrayNameNgheSi.add(arrayNgheSi.get(i).getName());
        }

    }

    private void Mapping(){
        lvNgheSi = (ListView) findViewById(R.id.lvMusic);

    }
    public void addListSongs(){
        arrayNgheSi = new ArrayList<>();
        arrayNgheSi.add(new NgheSi("Slander",R.drawable.loveisgone));

    }


}

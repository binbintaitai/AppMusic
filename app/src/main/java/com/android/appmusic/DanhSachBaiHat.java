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

public class DanhSachBaiHat extends AppCompatActivity {
    ListView lvMusic;
    List<Song> arraySong;
    MusicAdapter adapter;
    EditText edtSearch;
    Button btnSearch;
    ArrayList<String> arrayNameMusic;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);

        Mapping();
        addListSongs();
        adapter = new MusicAdapter(this,R.layout.dong_danh_sach_bai_hat,arraySong);
        lvMusic.setAdapter(adapter);
        arrayNameMusic = new ArrayList<String>();
        for(int i=0;i<arraySong.size();i++){
            arrayNameMusic.add(arraySong.get(i).getName());
        }


        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  Intent intent = new Intent(DanhSachBaiHat.this,PlayMusic.class);
                  int position = i;
                  intent.putExtra("dulieu",position);
                  startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().isEmpty()){
                    Toast.makeText(DanhSachBaiHat.this,
                            "Please enter search music",Toast.LENGTH_SHORT).show();
                }
                else{
                    String search = edtSearch.getText().toString();
                    arraySong.clear();


                }

            }
        });

    }
    private void Mapping(){
        lvMusic = (ListView) findViewById(R.id.lvMusic);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }
   public void addListSongs(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("To the moon", "Hooligan","Sad Song",R.drawable.tothemoon, R.raw.tothemoon));
        arraySong.add(new Song("Dacing with your ghost", "Sasha Alex Sloan","Sad Song",R.drawable.dancingwithyourghost, R.raw.dancingwithyoughost));
        arraySong.add(new Song("Double take", "Dhruv","Sad Song",R.drawable.doubletake, R.raw.doubletake));
        arraySong.add(new Song("I love you 3000", "Stephanie Poetri","Sad Song",R.drawable.iloveuou, R.raw.iloveyou));
        arraySong.add(new Song("Love is gone", "Slander","Sad Song",R.drawable.loveisgone, R.raw.loveisgone));
        arraySong.add(new Song("See you again", " Wiz Khalifa","Sad Song",R.drawable.seeyouagain, R.raw.seeyouagain));
        arraySong.add(new Song("Something else", "Gary Jules","Sad Song",R.drawable.sonethingelse, R.raw.somethingelse));
        arraySong.add(new Song("You are the reason", "Calum Scott","Sad Song",R.drawable.youarethereason, R.raw.youarethereason));
        arraySong.add(new Song("Bad Liar", "Imagine Dragons","Sad Song",R.drawable.barliar, R.raw.barliar));
        arraySong.add(new Song("So far away", "David Guetta","Sad Song",R.drawable.sofaraway, R.raw.sofaraway));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
            SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            return true;
    }
    @Override
    public  void onBackPressed(){
        if(!searchView.isIconfiedByDefault()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
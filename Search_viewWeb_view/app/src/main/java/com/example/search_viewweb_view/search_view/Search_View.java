package com.example.search_viewweb_view.search_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.search_viewweb_view.R;

import java.util.ArrayList;

public class Search_View extends AppCompatActivity {
    SearchView search_view;
    ListView listview_search;

    ArrayList name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        search_view=findViewById(R.id.search_view);
        listview_search=findViewById(R.id.listview_search);

        name=new ArrayList<>();
        name.add("Hari");
        name.add("Ram");
        name.add("Gopal");
        name.add("Krishna");
        name.add("Ved");
        name.add("Naman");
        name.add("Sanyam");
        name.add("Shital");
        name.add("Tej");

        ArrayAdapter arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,name);
        listview_search.setAdapter(arrayAdapter);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}
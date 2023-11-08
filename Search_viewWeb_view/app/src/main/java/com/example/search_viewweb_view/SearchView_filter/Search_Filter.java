package com.example.search_viewweb_view.SearchView_filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.search_viewweb_view.R;

import java.util.ArrayList;

public class Search_Filter extends AppCompatActivity {
    SearchView searchview_filter;
    ListView listview_filter;
    ArrayList names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        searchview_filter=findViewById(R.id.searchview_filter);
        listview_filter=findViewById(R.id.listview_filter);

        names=new ArrayList();
        names.add("Hari");
        names.add("Ram");
        names.add("Gopal");
        names.add("Krishna");
        names.add("Ved");
        names.add("Naman");
        names.add("Sanyam");
        names.add("Shital");
        names.add("Tej");

    SearchAdaptor searchAdaptor= new SearchAdaptor(this,names);
    listview_filter.setAdapter(searchAdaptor);

    searchview_filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchAdaptor.myfilter(newText);
            return false;
        }
    });


    }
}
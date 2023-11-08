package com.example.simple_adaptordemo.simple_adaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.simple_adaptordemo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Simple_Adaptor extends AppCompatActivity {

    ListView list_view_simple;
    int[] animals_img={R.drawable.dog,R.drawable.fox,R.drawable.rabbit,R.drawable.sheep,R.drawable.tiger,R.drawable.dog,R.drawable.fox,R.drawable.rabbit,R.drawable.sheep,R.drawable.tiger};
    String[] animals_name={"Dog","Fox","Rabbit","Sheep","Tiger","Dog","Fox","Rabbit","Sheep","Tiger"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adaptor);

        list_view_simple=findViewById(R.id.list_view_simple);

        ArrayList<HashMap<String,?>> arrayList=new ArrayList<HashMap<String,?>>();

         for (int i=0;i<animals_img.length;i++){
             HashMap<String,String> hm= new HashMap<String,String>();
             hm.put("image", animals_img[i]+"");
             hm.put("name", animals_name[i]);
             arrayList.add(hm);
         }

         String [] from={"image","name"};
         int[] to={R.id.simpleadaptor_image,R.id.simpleadaptor_text};


        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.ui_simpleadaptor,from,to);
        list_view_simple.setAdapter(simpleAdapter);

        list_view_simple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Simple_Adaptor.this, "You clicked "+animals_name[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
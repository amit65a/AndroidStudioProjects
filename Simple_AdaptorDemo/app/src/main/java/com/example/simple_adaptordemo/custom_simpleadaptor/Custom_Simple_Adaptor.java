package com.example.simple_adaptordemo.custom_simpleadaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.simple_adaptordemo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Custom_Simple_Adaptor extends AppCompatActivity {

    ListView custom_simple_adaptor_listview;
    int[] animals_img={R.drawable.dog,R.drawable.fox,R.drawable.rabbit,R.drawable.sheep,R.drawable.tiger,R.drawable.dog,R.drawable.fox,R.drawable.rabbit,R.drawable.sheep,R.drawable.tiger};
    String[] animals_name={"Dog","Fox","Rabbit","Sheep","Tiger","Dog","Fox","Rabbit","Sheep","Tiger"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_simple_adaptor);

        custom_simple_adaptor_listview=findViewById(R.id.custom_simple_adaptor_listview);

        ArrayList<HashMap<String,?>> arrayList=new ArrayList<HashMap<String,?>>();
        for (int i=0;i<animals_img.length;i++){
            HashMap<String,String> hm= new HashMap<String,String>();
            hm.put("image", animals_img[i]+"");
            hm.put("name", animals_name[i]);
            arrayList.add(hm);
        }

        String [] from={"image","name"};
        int[] to={R.id.customsimpleadaptor_image,R.id.customsimpleadaptor_text};

        Custom_Adaptor custom_adaptor=new Custom_Adaptor(this,arrayList,R.layout.ui_customsimpleadaptor,from,to);
        custom_simple_adaptor_listview.setAdapter(custom_adaptor);
    }

}
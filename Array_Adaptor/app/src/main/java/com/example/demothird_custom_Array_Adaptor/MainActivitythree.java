package com.example.demothird_custom_Array_Adaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.array_adaptor.R;

import java.util.ArrayList;

public class MainActivitythree extends AppCompatActivity {

    ListView listview_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitythree);

        listview_three=findViewById(R.id.listview_three);

        ArrayList animals_list=new ArrayList();
        animals_list.add(new animallist(R.drawable.dog,"Dog"));
        animals_list.add(new animallist(R.drawable.fox,"Fog"));
        animals_list.add(new animallist(R.drawable.tiger,"Tiger"));
        animals_list.add(new animallist(R.drawable.rabbit,"Rabbit"));
        animals_list.add(new animallist(R.drawable.sheep,"Sheep"));
        animals_list.add(new animallist(R.drawable.dog,"Dog"));
        animals_list.add(new animallist(R.drawable.fox,"Fog"));
        animals_list.add(new animallist(R.drawable.tiger,"Tiger"));
        animals_list.add(new animallist(R.drawable.rabbit,"Rabbit"));
        animals_list.add(new animallist(R.drawable.sheep,"Sheep"));

        Adaptor_three adaptor_three=new Adaptor_three(this,R.layout.uiview_three,animals_list);
        listview_three.setAdapter(adaptor_three);
    }
}
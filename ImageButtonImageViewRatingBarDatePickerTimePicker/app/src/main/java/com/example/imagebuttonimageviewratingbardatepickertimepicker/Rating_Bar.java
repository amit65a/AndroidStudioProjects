package com.example.imagebuttonimageviewratingbardatepickertimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rating_Bar extends AppCompatActivity {
    RatingBar rating_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        rating_bar=(RatingBar) findViewById(R.id.rating_bar);
    }
    public void getrating(View view){
        float f=rating_bar.getRating();
        int num=rating_bar.getNumStars();
        Toast.makeText(this, "Rating Value = "+f+"\nNumbers of stars = "+num, Toast.LENGTH_SHORT).show();
    }

}
package com.example.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Image_Switcher extends AppCompatActivity {
    ImageSwitcher image_switcher;
    int [] image={R.drawable.img,R.drawable.img1,R.drawable.img_1};
    int length;
    int img_index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);

        image_switcher=findViewById(R.id.image_switcher);
        length=image.length;
        image_switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                return imageView;
            }
        });
        image_switcher.setImageResource(image[0]);

    }
    public void nextbtn(View view){
        img_index=img_index+1;
        if (img_index==3){
            img_index=0;
        }
        image_switcher.setImageResource(image[img_index]);


    }

    public void prevbtn(View view){
        img_index=img_index-1;
        if (img_index==-1){
            img_index=2;
        }
        image_switcher.setImageResource(image[img_index]);



    }
}
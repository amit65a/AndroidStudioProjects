package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.zip.Inflater;

public class Option_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int item_id=item.getItemId();
        switch (item_id){
            case R.id.optionone:
                Toast.makeText(this, "Option one clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.optiontwo:
                Toast.makeText(this, "Option two clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.optionthree:
                Toast.makeText(this, "Option three clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.optionfour:
                Toast.makeText(this, "Option four clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }
}
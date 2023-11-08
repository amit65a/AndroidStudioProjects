package demotwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.adaptors_android.R;

public class MainActivitytwo extends AppCompatActivity {

    ListView listview;
    int []nature_images={R.drawable.firstimage,R.drawable.secondimage,R.drawable.thirdimage,R.drawable.fourthimage,R.drawable.fifthimage,R.drawable.firstimage,R.drawable.secondimage,R.drawable.thirdimage,R.drawable.fourthimage,R.drawable.fifthimage};
    String [] name={"First image","Second Image","Third Image","Fourth Image","Fifth Image","First image","Second Image","Third Image","Fourth Image","Fifth Image"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitytwo);
        listview=findViewById(R.id.listview);


        adaptor_list adaptor=new adaptor_list(getApplicationContext(),nature_images,name);
        listview.setAdapter(adaptor);
    }
}
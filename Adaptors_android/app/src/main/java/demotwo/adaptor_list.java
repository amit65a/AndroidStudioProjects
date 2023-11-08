package demotwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaptors_android.R;

public class adaptor_list extends BaseAdapter {
    Context context;
    int [] image;
    String [] name;

    LayoutInflater inflater;

    adaptor_list(Context context,int [] image,String [] name){
        this.context=context;
        this.image=image;
        this.name=name;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.uicomponent_listview,null);
        ImageView imageView= view.findViewById(R.id.image_list);
        TextView textView=view.findViewById(R.id.text_list);
        imageView.setImageResource(image[i]);
        textView.setText(name[i]);
        return view;
    }
}

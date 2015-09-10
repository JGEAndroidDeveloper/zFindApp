package google.com.jge.zfind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gaming Dethklok on 8/4/2015.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;
    Context context;

    MyAdapter(Context c, ArrayList<SingleRow> list){
        this.context = c; //Gets the context of the ListViewActivity
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.complex_row, parent, false);


        TextView title = (TextView) row.findViewById(R.id.textView);
        TextView desc = (TextView) row.findViewById(R.id.textView2);
        ImageView img = (ImageView) row.findViewById(R.id.imageView);

        SingleRow temp = list.get(position);

        title.setText(temp.title);
        desc.setText(temp.description);
        img.setImageResource(temp.image);





        return row;
    }
    ;
       /** list = new ArrayList<SingleRow>(); //Assigns list to ArrayList<SingleRow>
        Resources res = c.getResources();//gets the context's resources
        String[] titles = res.getStringArray(R.array.titles); //Gets the String Arrays from the menu xml file and assigns it to title as an array
        String[] description = res.getStringArray(R.array.desc); //Gets the String Arrays from the menu xml file and assigns it to description as an array
        int[] images = {R.mipmap.ic_launcher,R.mipmap.ic_home_white_24dp };//

        for(int i=0;i<titles.length;i++) {
            list.add(new SingleRow(titles[i], description[i], images[i]));
        }
    }*/




    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    Button b;

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}

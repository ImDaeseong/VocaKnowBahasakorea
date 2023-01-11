package com.im.daeseong.bahasakorea.MainTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.im.daeseong.bahasakorea.Database.KataItems;
import com.im.daeseong.bahasakorea.R;
import java.util.List;

public class KataAdapter extends ArrayAdapter<KataItems> {

    private Context context;

    public KataAdapter(List<KataItems> list, Context context){
        super(context, R.layout.kata_list_item, list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View layout = LayoutInflater.from(context).inflate(R.layout.kata_list_item, parent, false);
        TextView tv1 = layout.findViewById(R.id.tv1);
        TextView tv2 = layout.findViewById(R.id.tv2);
        TextView tv3 = layout.findViewById(R.id.tv3);

        /*
        int ncolor = Color.parseColor("#000000");
        tv1.setTextColor(ncolor);
        tv2.setTextColor(ncolor);
        tv3.setTextColor(ncolor);
        */

        tv1.setText(getItem(position).getKataKor());
        tv2.setText(getItem(position).getKataIndo());
        tv3.setText(getItem(position).getKataEng());

        return layout;
    }
}

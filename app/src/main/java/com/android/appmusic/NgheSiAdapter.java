package com.android.appmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NgheSiAdapter  extends BaseAdapter   {

    private Context context;
    private int layout;
    private List<NgheSi>arrayNgheSi;


    public NgheSiAdapter(Context context, int layout, List<NgheSi>arrayNgheSi) {
        this.context = context;
        this.layout = layout;
        this.arrayNgheSi = arrayNgheSi;

    }

    @Override
    public int getCount() {
        return arrayNgheSi.size();
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    private class ViewHolder{
        ImageView img;
        TextView txtName;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      ViewHolder holder;
        if(view == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            holder = new ViewHolder();

            holder.img =(ImageView) view.findViewById(R.id.img);
            holder.txtName = (TextView) view.findViewById(R.id.txtName);

            view.setTag(holder);
        }
        else{
            holder =(ViewHolder) view.getTag();
        }

        NgheSi nghesi= arrayNgheSi.get(i);
        holder.img.setImageResource(nghesi.getImg());
        holder.txtName.setText(nghesi.getName());


        Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list);
        view.startAnimation(animation);

        return view;

    }


}


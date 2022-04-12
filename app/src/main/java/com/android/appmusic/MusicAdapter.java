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

import java.util.List;

public class MusicAdapter extends BaseAdapter  {
    private Context context;
    private int layout;
    private List<Song>arraySong;



    public MusicAdapter(Context context, int layout, List<Song> arraySong) {
        this.context = context;
        this.layout = layout;
        this.arraySong = arraySong;
    }

    @Override
    public int getCount() {
        return arraySong.size();
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
           ImageView imgMusic;
           TextView txtNameMusic, txtSinger,txtCategory;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            holder = new ViewHolder();

            holder.imgMusic =(ImageView) view.findViewById(R.id.imgMusic);
            holder.txtNameMusic = (TextView) view.findViewById(R.id.txtNameMusic);
            holder.txtSinger = (TextView) view.findViewById(R.id.txtSinger);
            holder.txtCategory = (TextView) view.findViewById(R.id.txtCategory);
            view.setTag(holder);
        }
        else{
             holder =(ViewHolder) view.getTag();
        }

        Song songs= arraySong.get(i);
        holder.imgMusic.setImageResource(songs.getImg());
        holder.txtNameMusic.setText(songs.getName());
        holder.txtSinger.setText(songs.getSinger());
        holder.txtCategory.setText(songs.getType());

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}

package com.imic.admin.startservice_music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mitaly on 4/6/17.
 */

public class AdapterClass extends ArrayAdapter<SongObject> {
    Context cxt;
    int res;
    ArrayList<SongObject> list;

    public AdapterClass(Context context, int resource, ArrayList<SongObject> objects) {
        super(context, resource, objects);
        cxt=context;
        res=resource;
        list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;

        //Initializing view which will point to layout file list_item
        view= LayoutInflater.from(cxt).inflate(res,parent,false);

        //Initializing TextView
        TextView fileName=(TextView)view.findViewById(R.id.textSong);

        SongObject sdOb=list.get(position);
        //Setting the Icon and FileName
        fileName.setText(sdOb.getFileName());

        return view;
    }
}

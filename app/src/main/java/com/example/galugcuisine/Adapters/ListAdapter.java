package com.example.galugcuisine.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.galugcuisine.Models.Favorite;
import com.example.galugcuisine.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    ArrayList<Favorite> list;
    Context context;

    public ListAdapter(ArrayList<Favorite> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.favorite, null);
        TextView title = view.findViewById(R.id.textView_title_favorite);

        title.setText(list.get(i).TitreRecette);
        return view;
    }
}

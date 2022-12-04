package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<Recipe> recipeList;
    private ArrayList<Recipe> arrayList;

    public ListViewAdapter(Context context, ArrayList<Recipe> recipeList){
        this.context = context;
        this.recipeList = recipeList;
        this.arrayList = new ArrayList<Recipe>();
        this.arrayList.addAll(recipeList);
        this.inflater = LayoutInflater.from(this.context);
    }

    public class ViewHolder{
        TextView name;
    }

    @Override
    public int getCount(){
        return this.recipeList.size();
    }
    @Override
    public Recipe getItem(int position){
        return recipeList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent){
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(recipeList.get(position).recipeName);
        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        System.out.println(recipeList.size());
        System.out.println(charText.length());
        recipeList.clear();
        if(charText.length() == 0){
            recipeList.addAll(arrayList);
        }
        else{
            for(Recipe r : arrayList){
                if(r.recipeName.toLowerCase(Locale.getDefault()).contains(charText)){
                    recipeList.add(r);
                }
                else if(r.mainIngredient.toLowerCase(Locale.getDefault()).contains(charText)){
                    recipeList.add(r);
                }
                else if(r.secondaryIngredient.toLowerCase(Locale.getDefault()).contains(charText)){
                    recipeList.add(r);
                }
            }
        }

        System.out.println(recipeList.size());
        notifyDataSetChanged();
    }
}

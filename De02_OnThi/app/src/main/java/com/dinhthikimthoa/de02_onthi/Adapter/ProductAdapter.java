package com.dinhthikimthoa.de02_onthi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dinhthikimthoa.de02_onthi.Model.Product;
import com.dinhthikimthoa.de02_onthi.R;
import com.dinhthikimthoa.de02_onthi.Sqlite;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Sqlite context;
    int list_items;
    List<Product> products;

    public ProductAdapter(Sqlite context, int list_items, List<Product> products) {
        this.context = context;
        this.list_items = list_items;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(list_items, null);
            holder.tvNamePro = convertView.findViewById(R.id.tv_NamePro);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        // binding data
        Product p = products.get(position);
        holder.tvNamePro.setText(p.getTenSp());

        return convertView;
    }

    private class   ViewHolder {

        TextView tvNamePro;

    }
}

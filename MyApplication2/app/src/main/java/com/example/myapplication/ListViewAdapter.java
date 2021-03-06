package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layoutId;
    private ArrayList<ListItemEntity> items;

    static class ViewHolder{
        TextView date;
        TextView title;
        TextView contents;
    }

    ListViewAdapter(Context context, int itemLayoutId, ArrayList<ListItemEntity> argItems){
        super();
        this.inflater = LayoutInflater.from(context);
        this.layoutId = itemLayoutId;
        this.items = argItems;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(layoutId,null);
            holder = new ViewHolder();
            holder.date = convertView.findViewById(R.id.textDate);
            holder.title = convertView.findViewById(R.id.textTitle);
            holder.contents = convertView.findViewById(R.id.textContents);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 内部のリストからposition番目の要素を取得
        ListItemEntity item = this.items.get(position);
        //ViewHolderに値をセットする
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        holder.date.setText(dateFormat.format(item.getDate()));
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());
        return convertView;
    }
}

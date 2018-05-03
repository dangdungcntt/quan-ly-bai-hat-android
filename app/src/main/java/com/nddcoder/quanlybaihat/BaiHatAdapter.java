package com.nddcoder.quanlybaihat;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BaiHatAdapter extends BaseAdapter implements Filterable {

    private List<BaiHat> dsBaiHat;
    private List<BaiHat> dsBaiHatGoc;
    private Activity activity;

    BaiHatAdapter(List<BaiHat> dsBaiHat, Activity activity) {
        this.dsBaiHat = dsBaiHat;
        this.dsBaiHatGoc = new ArrayList<>();
        dsBaiHatGoc.addAll(dsBaiHat);
        this.activity = activity;
    }

    public void xoaBaiHat(String maBH) {
        int index = -1;
        int length = dsBaiHatGoc.size();
        for (int i = 0; i < length; i++) {
            if (dsBaiHatGoc.get(i).getMaBaiHat().equalsIgnoreCase(maBH)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        dsBaiHatGoc.remove(index);

    }

    @Override
    public int getCount() {
        return dsBaiHat.size();
    }

    @Override
    public Object getItem(int position) {
        return dsBaiHat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_bai_hat, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenBaiHat = convertView.findViewById(R.id.txtTenBaiHat);
            viewHolder.txtTenCaSi = convertView.findViewById(R.id.txtTenCaSi);
            viewHolder.txtThoiLuong = convertView.findViewById(R.id.txtThoiLuong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BaiHat bh = (BaiHat) getItem(position);

        viewHolder.txtTenBaiHat.setText(bh.getTenBaiHat());
        viewHolder.txtTenCaSi.setText(bh.getTenCaSi());
        viewHolder.txtThoiLuong.setText(bh.getThoiLuong());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                List<BaiHat> ketQua = new ArrayList<>();

                for (BaiHat bh :
                        dsBaiHatGoc) {
                    if (bh.getTenCaSi().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        ketQua.add(bh);
                    }
                }

                filterResults.values = ketQua;
                filterResults.count = ketQua.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dsBaiHat.clear();
                dsBaiHat.addAll((List<BaiHat>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {
        TextView txtTenBaiHat, txtTenCaSi, txtThoiLuong;
    }
}

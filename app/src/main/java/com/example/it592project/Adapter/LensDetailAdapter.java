package com.example.it592project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.it592project.Model.Lens;
import com.example.it592project.R;

import java.util.List;

public class LensDetailAdapter extends ArrayAdapter<Lens> {
    public LensDetailAdapter(Context context, List<Lens>objects){
        super(context,android.R.layout.simple_list_item_1,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.lens_detail_row_layout,parent,false);

            holder=new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_lens_barcode_detail().setText(getItem(position).getBarcode_number());
        holder.getTxt_lens_brand_detail().setText(getItem(position).getBrand());
        holder.getTxt_lens_num_detail().setText(String.valueOf(getItem(position).getNumber()));
        holder.getTxt_lens_numcyl_detail().setText(String.valueOf(getItem(position).getNumber_cyl()));
        holder.getTxt_lens_aks_detail().setText(String.valueOf(getItem(position).getAks()));

        Lens l=getItem(position);

        if(l.isNumber_sferik_konkav()){
            holder.getTxt_lens_sfrk_detail().setText("+");
        }else{
            holder.getTxt_lens_sfrk_detail().setText("-");
        }

        if(l.isNumber_cyl_sferik_konkav()){
            holder.getTxt_lens_cyl_sfrk_detail().setText("+");
        }else{
            holder.getTxt_lens_cyl_sfrk_detail().setText("-");
        }

        if(l.isRight_eye()){
            holder.getTxt_lens_right_left().setText("Right Eye");
        }else{
            holder.getTxt_lens_right_left().setText("Left Eye");
        }

        return row;
    }

    class ViewHolder{
        TextView txt_lens_right_left;
        TextView txt_lens_sfrk_detail;
        TextView txt_lens_num_detail;
        TextView txt_lens_cyl_sfrk_detail;
        TextView txt_lens_numcyl_detail;
        TextView txt_lens_barcode_detail;
        TextView txt_lens_brand_detail;
        TextView txt_lens_aks_detail;
        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_lens_right_left() {
            if (txt_lens_right_left == null) {
                txt_lens_right_left = base.findViewById(R.id.txt_lens_right_left);
            }
            return txt_lens_right_left;
        }

        public TextView getTxt_lens_sfrk_detail() {
            if (txt_lens_sfrk_detail == null) {
                txt_lens_sfrk_detail = base.findViewById(R.id.txt_lens_sfrk_detail);
            }
            return txt_lens_sfrk_detail;
        }

        public TextView getTxt_lens_num_detail() {
            if (txt_lens_num_detail == null) {
                txt_lens_num_detail = base.findViewById(R.id.txt_lens_num_detail);
            }
            return txt_lens_num_detail;
        }

        public TextView getTxt_lens_cyl_sfrk_detail() {
            if (txt_lens_cyl_sfrk_detail == null) {
                txt_lens_cyl_sfrk_detail = base.findViewById(R.id.txt_lens_cyl_sfrk_detail);
            }
            return txt_lens_cyl_sfrk_detail;
        }

        public TextView getTxt_lens_numcyl_detail() {
            if (txt_lens_numcyl_detail == null) {
                txt_lens_numcyl_detail = base.findViewById(R.id.txt_lens_numcyl_detail);
            }
            return txt_lens_numcyl_detail;
        }

        public TextView getTxt_lens_barcode_detail() {
            if (txt_lens_barcode_detail == null) {
                txt_lens_barcode_detail = base.findViewById(R.id.txt_lens_barcode_detail);
            }
            return txt_lens_barcode_detail;
        }

        public TextView getTxt_lens_brand_detail() {
            if (txt_lens_brand_detail == null) {
                txt_lens_brand_detail = base.findViewById(R.id.txt_lens_brand_detail);
            }
            return txt_lens_brand_detail;
        }

        public TextView getTxt_lens_aks_detail() {
            if (txt_lens_aks_detail == null) {
                txt_lens_aks_detail = base.findViewById(R.id.txt_lens_aks_detail);
            }
            return txt_lens_aks_detail;
        }
    }
}

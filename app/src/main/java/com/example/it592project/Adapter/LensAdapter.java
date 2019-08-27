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

public class LensAdapter extends ArrayAdapter<Lens> {
    public LensAdapter(Context context, List<Lens>objects){
        super(context,android.R.layout.simple_list_item_1,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.lens_row_layout,parent,false);

            holder=new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_lens_barcode().setText(getItem(position).getBarcode_number());
        holder.getTxt_lens_brand().setText(getItem(position).getBrand());
        holder.getTxt_lens_date().setText(getItem(position).getAddedDate());
        holder.getTxt_lens_num().setText(String.valueOf(getItem(position).getNumber()));
        holder.getTxt_lens_numcyl().setText(String.valueOf(getItem(position).getNumber_cyl()));

        Lens l=getItem(position);

        if(l.isNumber_sferik_konkav()){
            holder.getTxt_lens_sfrk().setText("+");
        }else{
            holder.getTxt_lens_sfrk().setText("-");
        }

        if(l.isNumber_cyl_sferik_konkav()){
            holder.getTxt_lens_cyl_sfrk().setText("+");
        }else{
            holder.getTxt_lens_cyl_sfrk().setText("-");
        }

        return row;
    }

    class ViewHolder{
        TextView txt_lens_sfrk;
        TextView txt_lens_num;
        TextView txt_lens_cyl_sfrk;
        TextView txt_lens_numcyl;
        TextView txt_lens_barcode;
        TextView txt_lens_brand;
        TextView txt_lens_date;
        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_lens_sfrk() {
            if (txt_lens_sfrk == null) {
                txt_lens_sfrk = base.findViewById(R.id.txt_lens_sfrk);
            }
            return txt_lens_sfrk;
        }

        public TextView getTxt_lens_num() {
            if (txt_lens_num == null) {
                txt_lens_num = base.findViewById(R.id.txt_lens_num);
            }
            return txt_lens_num;
        }

        public TextView getTxt_lens_cyl_sfrk() {
            if (txt_lens_cyl_sfrk == null) {
                txt_lens_cyl_sfrk = base.findViewById(R.id.txt_lens_cyl_sfrk);
            }
            return txt_lens_cyl_sfrk;
        }

        public TextView getTxt_lens_numcyl() {
            if (txt_lens_numcyl == null) {
                txt_lens_numcyl = base.findViewById(R.id.txt_lens_numcyl);
            }
            return txt_lens_numcyl;
        }

        public TextView getTxt_lens_barcode() {
            if (txt_lens_barcode == null) {
                txt_lens_barcode = base.findViewById(R.id.txt_lens_barcode);
            }
            return txt_lens_barcode;
        }

        public TextView getTxt_lens_brand() {
            if (txt_lens_brand == null) {
                txt_lens_brand = base.findViewById(R.id.txt_lens_brand);
            }
            return txt_lens_brand;
        }

        public TextView getTxt_lens_date() {
            if (txt_lens_date == null) {
                txt_lens_date = base.findViewById(R.id.txt_lens_date);
            }
            return txt_lens_date;
        }
    }
}

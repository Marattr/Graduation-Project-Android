package com.example.it592project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.it592project.Model.Glass;
import com.example.it592project.R;

import java.util.List;

public class GlassAdapter extends ArrayAdapter<Glass> {
    public GlassAdapter(Context context, List<Glass> objects){

        super(context,android.R.layout.simple_list_item_1,objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.glass_row_layout,parent,false);

            holder=new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }


        holder.getTxt_glass_antirefle().setText(String.valueOf(getItem(position).isAntirefle()));
        holder.getTxt_glass_barcode().setText(getItem(position).getBarcode_number());
        holder.getTxt_glass_brand().setText(getItem(position).getBrand());
        holder.getTxt_glass_color().setText(getItem(position).getColor());
        holder.getTxt_glass_date().setText(getItem(position).getAddedDate());
        holder.getTxt_glass_num().setText(String.valueOf(getItem(position).getNumber()));
        holder.getTxt_glass_num_cyl().setText(String.valueOf(getItem(position).getNumber_cyl()));
        holder.getTxt_glass_type().setText(getItem(position).getType());

        Glass g=getItem(position);
        String numsfrk="";
        if(g.isNumber_sferik_konkav()){
            numsfrk="+";
        }else{
            numsfrk="-";
        }
        holder.getTxt_glass_num_sfrk().setText(numsfrk);

        String numcylsfrk="";
        if(g.isNumber_cyl_sferik_konkav()){
            numcylsfrk="+";
        }else{
            numcylsfrk="-";
        }
        holder.getTxt_glass_num_cyl_sfrk().setText(numcylsfrk);



        return row;
    }

    class ViewHolder {


        TextView txt_glass_num_sfrk;
        TextView txt_glass_num;
        TextView txt_glass_num_cyl;
        TextView txt_glass_num_cyl_sfrk;
        TextView txt_glass_type;
        TextView txt_glass_color;
        TextView txt_glass_antirefle;
        TextView txt_glass_date;
        TextView txt_glass_brand;
        TextView txt_glass_barcode;
        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_glass_num_sfrk() {
            if (txt_glass_num_sfrk == null) {
                txt_glass_num_sfrk = base.findViewById(R.id.txt_glass_num_sfrk);
            }
            return txt_glass_num_sfrk;
        }

        public TextView getTxt_glass_num() {
            if (txt_glass_num == null) {
                txt_glass_num = base.findViewById(R.id.txt_glass_num);
            }
            return txt_glass_num;
        }

        public TextView getTxt_glass_num_cyl() {
            if (txt_glass_num_cyl == null) {
                txt_glass_num_cyl = base.findViewById(R.id.txt_glass_num_cyl);
            }
            return txt_glass_num_cyl;
        }

        public TextView getTxt_glass_num_cyl_sfrk() {
            if (txt_glass_num_cyl_sfrk == null) {
                txt_glass_num_cyl_sfrk = base.findViewById(R.id.txt_glass_num_cyl_sfrk);
            }
            return txt_glass_num_cyl_sfrk;
        }

        public TextView getTxt_glass_type() {
            if (txt_glass_type == null) {
                txt_glass_type = base.findViewById(R.id.txt_glass_type);
            }
            return txt_glass_type;
        }

        public TextView getTxt_glass_color() {
            if (txt_glass_color == null) {
                txt_glass_color = base.findViewById(R.id.txt_glass_color);
            }
            return txt_glass_color;
        }

        public TextView getTxt_glass_antirefle() {
            if (txt_glass_antirefle == null) {
                txt_glass_antirefle = base.findViewById(R.id.txt_glass_antirefle);
            }
            return txt_glass_antirefle;
        }

        public TextView getTxt_glass_date() {
            if (txt_glass_date == null) {
                txt_glass_date = base.findViewById(R.id.txt_glass_date);
            }
            return txt_glass_date;
        }

        public TextView getTxt_glass_brand() {
            if (txt_glass_brand == null) {
                txt_glass_brand = base.findViewById(R.id.txt_glass_brand);
            }
            return txt_glass_brand;
        }

        public TextView getTxt_glass_barcode() {
            if (txt_glass_barcode == null) {
                txt_glass_barcode = base.findViewById(R.id.txt_glass_barcode);
            }
            return txt_glass_barcode;
        }
    }
}

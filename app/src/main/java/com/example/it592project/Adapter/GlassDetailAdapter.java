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

public class GlassDetailAdapter extends ArrayAdapter<Glass> {
    public GlassDetailAdapter(Context context, List<Glass> objects){
        super(context,android.R.layout.simple_list_item_1,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.glass_detail_row_layout,parent,false);

            holder=new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }


        holder.getTxt_glass_antirefle().setText(String.valueOf(getItem(position).isAntirefle()));
        holder.getTxt_glass_barcode().setText(getItem(position).getBarcode_number());
        holder.getTxt_glass_brand().setText(getItem(position).getBrand());
        holder.getTxt_glass_color().setText(getItem(position).getColor());
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

        if(g.isRight_eye()){
            holder.getTxt_glass_right_left().setText("Right Eye");
        }else{
            holder.getTxt_glass_right_left().setText("Left Eye");
        }


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
        TextView txt_glass_brand;
        TextView txt_glass_barcode;
        TextView txt_glass_right_left;
        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_glass_num_sfrk() {
            if (txt_glass_num_sfrk == null) {
                txt_glass_num_sfrk = base.findViewById(R.id.txt_glass_num_sfrk_detail);
            }
            return txt_glass_num_sfrk;
        }

        public TextView getTxt_glass_num() {
            if (txt_glass_num == null) {
                txt_glass_num = base.findViewById(R.id.txt_glass_num_detail);
            }
            return txt_glass_num;
        }

        public TextView getTxt_glass_num_cyl() {
            if (txt_glass_num_cyl == null) {
                txt_glass_num_cyl = base.findViewById(R.id.txt_glass_num_cyl_detail);
            }
            return txt_glass_num_cyl;
        }

        public TextView getTxt_glass_num_cyl_sfrk() {
            if (txt_glass_num_cyl_sfrk == null) {
                txt_glass_num_cyl_sfrk = base.findViewById(R.id.txt_glass_num_cyl_sfrk_detail);
            }
            return txt_glass_num_cyl_sfrk;
        }

        public TextView getTxt_glass_type() {
            if (txt_glass_type == null) {
                txt_glass_type = base.findViewById(R.id.txt_glass_type_detail);
            }
            return txt_glass_type;
        }

        public TextView getTxt_glass_color() {
            if (txt_glass_color == null) {
                txt_glass_color = base.findViewById(R.id.txt_glass_color_detail);
            }
            return txt_glass_color;
        }

        public TextView getTxt_glass_antirefle() {
            if (txt_glass_antirefle == null) {
                txt_glass_antirefle = base.findViewById(R.id.txt_glass_antirefle_detail);
            }
            return txt_glass_antirefle;
        }

        public TextView getTxt_glass_brand() {
            if (txt_glass_brand == null) {
                txt_glass_brand = base.findViewById(R.id.txt_glass_brand_detail);
            }
            return txt_glass_brand;
        }

        public TextView getTxt_glass_barcode() {
            if (txt_glass_barcode == null) {
                txt_glass_barcode = base.findViewById(R.id.txt_glass_barcode_detail);
            }
            return txt_glass_barcode;
        }

        public TextView getTxt_glass_right_left() {
            if (txt_glass_right_left == null) {
                txt_glass_right_left = base.findViewById(R.id.txt_glass_right_left);
            }
            return txt_glass_right_left;
        }
    }

}

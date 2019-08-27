package com.example.it592project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.it592project.Model.SunGlasses;
import com.example.it592project.R;

import java.util.List;

public class SunGlassesAdapter extends ArrayAdapter<SunGlasses> {
    public SunGlassesAdapter(Context context, List<SunGlasses> objects){

        super(context,android.R.layout.simple_list_item_1,objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.sunglasses_row_layout,parent,false);

            holder= new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_sunglases_barcode().setText(getItem(position).getBarcode_number());
        holder.getTxt_sunglases_brand().setText(getItem(position).getBrand());
        holder.getTxt_sunglases_colorcode().setText(getItem(position).getSunglasses_colorcode());
        holder.getTxt_sunglases_date().setText(getItem(position).getAddedDate());
        holder.getTxt_sunglases_modelcode().setText(getItem(position).getSunglasses_modelcode());

        return row;
    }

    class ViewHolder {
        TextView txt_sunglases_brand;
        TextView txt_sunglases_barcode;
        TextView txt_sunglases_date;
        TextView txt_sunglases_modelcode;
        TextView txt_sunglases_colorcode;

        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_sunglases_brand() {
            if (txt_sunglases_brand == null) {
                txt_sunglases_brand = base.findViewById(R.id.txt_sunglases_brand);
            }
            return txt_sunglases_brand;
        }

        public TextView getTxt_sunglases_barcode() {
            if (txt_sunglases_barcode == null) {
                txt_sunglases_barcode = base.findViewById(R.id.txt_sunglases_barcode);
            }
            return txt_sunglases_barcode;
        }

        public TextView getTxt_sunglases_date() {
            if (txt_sunglases_date == null) {
                txt_sunglases_date = base.findViewById(R.id.txt_sunglases_date);
            }
            return txt_sunglases_date;
        }

        public TextView getTxt_sunglases_modelcode() {
            if (txt_sunglases_modelcode == null) {
                txt_sunglases_modelcode = base.findViewById(R.id.txt_sunglases_modelcode);
            }
            return txt_sunglases_modelcode;
        }

        public TextView getTxt_sunglases_colorcode() {
            if (txt_sunglases_colorcode == null) {
                txt_sunglases_colorcode = base.findViewById(R.id.txt_sunglases_colorcode);
            }
            return txt_sunglases_colorcode;
        }
    }
}

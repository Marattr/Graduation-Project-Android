package com.example.it592project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.it592project.Model.EyeGlasses;
import com.example.it592project.R;

import java.util.List;

public class EyeGlassesAdapter extends ArrayAdapter<EyeGlasses> {
    public EyeGlassesAdapter(Context context, List<EyeGlasses> objects){

        super(context,android.R.layout.simple_list_item_1,objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.eyeglasses_row_layout,parent,false);

            holder= new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_eyeglases_barcode().setText(getItem(position).getBarcode_number());
        holder.getTxt_eyeglases_brand().setText(getItem(position).getBrand());
        holder.getTxt_eyeglases_colorcode().setText(getItem(position).getEyeglasses_colorcode());
        holder.getTxt_eyeglases_date().setText(getItem(position).getAddedDate());
        holder.getTxt_eyeglases_modelcode().setText(getItem(position).getEyeglasses_modelcode());

        return row;
    }

    class ViewHolder {
        TextView txt_eyeglases_brand;
        TextView txt_eyeglases_barcode;
        TextView txt_eyeglases_date;
        TextView txt_eyeglases_modelcode;
        TextView txt_eyeglases_colorcode;

        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_eyeglases_brand() {
            if (txt_eyeglases_brand == null) {
                txt_eyeglases_brand = base.findViewById(R.id.txt_eyeglases_brand);
            }
            return txt_eyeglases_brand;
        }

        public TextView getTxt_eyeglases_barcode() {
            if (txt_eyeglases_barcode == null) {
                txt_eyeglases_barcode = base.findViewById(R.id.txt_eyeglases_barcode);
            }
            return txt_eyeglases_barcode;
        }

        public TextView getTxt_eyeglases_date() {
            if (txt_eyeglases_date == null) {
                txt_eyeglases_date = base.findViewById(R.id.txt_eyeglases_date);
            }
            return txt_eyeglases_date;
        }

        public TextView getTxt_eyeglases_modelcode() {
            if (txt_eyeglases_modelcode == null) {
                txt_eyeglases_modelcode = base.findViewById(R.id.txt_eyeglases_modelcode);
            }
            return txt_eyeglases_modelcode;
        }

        public TextView getTxt_eyeglases_colorcode() {
            if (txt_eyeglases_colorcode == null) {
                txt_eyeglases_colorcode = base.findViewById(R.id.txt_eyeglases_colorcode);
            }
            return txt_eyeglases_colorcode;
        }
    }
}

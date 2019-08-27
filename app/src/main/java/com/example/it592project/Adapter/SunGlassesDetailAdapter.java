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

public class SunGlassesDetailAdapter extends ArrayAdapter<SunGlasses> {
    public SunGlassesDetailAdapter(Context context, List<SunGlasses> objects){

        super(context,android.R.layout.simple_list_item_1,objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.sunglasses_detail_row_layout,parent,false);

            holder= new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_sunglases_barcode_detail().setText(getItem(position).getBarcode_number());
        holder.getTxt_sunglases_brand_detail().setText(getItem(position).getBrand());
        holder.getTxt_sunglases_colorcode_detail().setText(getItem(position).getSunglasses_colorcode());
        holder.getTxt_sunglases_modelcode_detail().setText(getItem(position).getSunglasses_modelcode());

        return row;
    }

    class ViewHolder {
        TextView txt_sunglases_brand_detail;
        TextView txt_sunglases_barcode_detail;
        TextView txt_sunglases_date_detail;
        TextView txt_sunglases_modelcode_detail;
        TextView txt_sunglases_colorcode_detail;

        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_sunglases_brand_detail() {
            if (txt_sunglases_brand_detail == null) {
                txt_sunglases_brand_detail = base.findViewById(R.id.txt_sunglases_brand_detail);
            }
            return txt_sunglases_brand_detail;
        }

        public TextView getTxt_sunglases_barcode_detail() {
            if (txt_sunglases_barcode_detail == null) {
                txt_sunglases_barcode_detail = base.findViewById(R.id.txt_sunglases_barcode_detail);
            }
            return txt_sunglases_barcode_detail;
        }

        public TextView getTxt_sunglases_modelcode_detail() {
            if (txt_sunglases_modelcode_detail == null) {
                txt_sunglases_modelcode_detail = base.findViewById(R.id.txt_sunglases_modelcode_detail);
            }
            return txt_sunglases_modelcode_detail;
        }

        public TextView getTxt_sunglases_colorcode_detail() {
            if (txt_sunglases_colorcode_detail == null) {
                txt_sunglases_colorcode_detail = base.findViewById(R.id.txt_sunglases_colorcode_detail);
            }
            return txt_sunglases_colorcode_detail;
        }
    }
}

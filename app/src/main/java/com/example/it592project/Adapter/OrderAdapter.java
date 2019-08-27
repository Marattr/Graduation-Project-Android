package com.example.it592project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.it592project.Model.Order;
import com.example.it592project.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {
    public OrderAdapter(Context context, List<Order> objects){
        super(context,android.R.layout.simple_list_item_1,objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            row=((Activity)getContext()).getLayoutInflater().inflate(R.layout.order_row_layout,parent,false);

            holder= new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder=(ViewHolder)row.getTag();
        }

        holder.getTxt_order_customername().setText(getItem(position).getCustomerNameLastname());
        holder.getTxt_order_customer_phone().setText(getItem(position).getCustomerPhone());
        holder.getTxt_order_orderdate().setText(getItem(position).getOrderdate());
        holder.getTxt_order_paymenttype().setText(getItem(position).getPaymentType());
        holder.getTxt_order_saletype().setText(getItem(position).getSaleType());
        holder.getTxt_order_totalbill().setText(String.valueOf(getItem(position).getTotalbill()));


        return row;
    }

    class ViewHolder{
        TextView txt_order_customername;
        TextView txt_order_customer_phone;
        TextView txt_order_totalbill;
        TextView txt_order_saletype;
        TextView txt_order_paymenttype;
        TextView txt_order_orderdate;

        View base;

        public ViewHolder(View base){

            this.base=base;
        }

        public TextView getTxt_order_customername() {
            if (txt_order_customername == null) {
                txt_order_customername = base.findViewById(R.id.txt_order_customername);
            }
            return txt_order_customername;
        }

        public TextView getTxt_order_customer_phone() {
            if (txt_order_customer_phone == null) {
                txt_order_customer_phone = base.findViewById(R.id.txt_order_customer_phone);
            }
            return txt_order_customer_phone;
        }

        public TextView getTxt_order_totalbill() {
            if (txt_order_totalbill == null) {
                txt_order_totalbill = base.findViewById(R.id.txt_order_totalbill);
            }
            return txt_order_totalbill;
        }

        public TextView getTxt_order_saletype() {
            if (txt_order_saletype == null) {
                txt_order_saletype = base.findViewById(R.id.txt_order_saletype);
            }
            return txt_order_saletype;
        }

        public TextView getTxt_order_paymenttype() {
            if (txt_order_paymenttype == null) {
                txt_order_paymenttype = base.findViewById(R.id.txt_order_paymenttype);
            }
            return txt_order_paymenttype;
        }

        public TextView getTxt_order_orderdate() {
            if (txt_order_orderdate == null) {
                txt_order_orderdate = base.findViewById(R.id.txt_order_orderdate);
            }
            return txt_order_orderdate;
        }
    }
}

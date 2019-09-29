package com.sourcey.materiallogindemo.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourcey.materiallogindemo.DetailOrderActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.model.Hotel;
import com.sourcey.materiallogindemo.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context mContext;
    private List<Order> orderList;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public OrderAdapter(Context mContext, ArrayList<Order> orderList){
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order, parent, false);
        OrderAdapter.ViewHolder vh = new OrderAdapter.ViewHolder(mView); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.ViewHolder holder, final int position) {
        final Order order = orderList.get(position);

        holder.tv_nama_hotel.setText(order.getNama_hotel());
        holder.tv_nama_kamar.setText(order.getNama_kamar());
        holder.tv_tgl_pesan.setText(order.getCheckin() + " - " + order.getCheckout() + " (" + order.getTot_malam() + " malam)");
        holder.tv_status.setText(order.getStatus());
        holder.cv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("id_reservasi", order.getId_reservasi());
                intent.putExtra("id_hotel", order.getId_hotel());
                intent.putExtra("checkin", order.getCheckin());
                intent.putExtra("checkout", order.getCheckout());
                intent.putExtra("tot_malam", order.getTot_malam());
                intent.putExtra("nama", order.getNama());
                intent.putExtra("metode", order.getMetode());
                intent.putExtra("tot_kamar", order.getTot_kamar());
                intent.putExtra("total_harga", formatRupiah.format((double)order.getTotal_harga()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_order;
        TextView tv_nama_hotel, tv_nama_kamar, tv_tgl_pesan, tv_status;

        public ViewHolder(View itemView) {
            super(itemView);

            cv_order = (CardView) itemView.findViewById(R.id.cv_order);
            tv_nama_hotel = (TextView) itemView.findViewById(R.id.tv_nama_hotel);
            tv_nama_kamar = (TextView) itemView.findViewById(R.id.tv_nama_kamar);
            tv_tgl_pesan = (TextView) itemView.findViewById(R.id.tv_tgl_pesan);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }

    public void clear(){
        orderList.clear();
        notifyDataSetChanged();
    }

}

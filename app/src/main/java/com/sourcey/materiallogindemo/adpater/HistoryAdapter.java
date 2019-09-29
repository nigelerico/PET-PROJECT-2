package com.sourcey.materiallogindemo.adpater;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<Order> orderList;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public HistoryAdapter(Context mContext, ArrayList<Order> orderList){
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        HistoryAdapter.ViewHolder vh = new HistoryAdapter.ViewHolder(mView); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(final HistoryAdapter.ViewHolder holder, final int position) {
        final Order order = orderList.get(position);

        holder.tv_nama_hotel.setText(order.getNama_hotel());
        holder.tv_nama_kamar.setText(order.getNama_kamar());
        holder.tv_tgl_pesan.setText(order.getCheckin() + " - " + order.getCheckout() + " (" + order.getTot_malam() + " malam)");

        holder.btn_rincian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
                View mView = layoutInflaterAndroid.inflate(R.layout.dialog_rincian_order, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setView(mView);

                TextView tv_id_reservasi, tv_status, tv_metode, tv_nama_hot, tv_nama_kamar, tv_tgl_pesan, tv_tot_kamar,
                        tv_total_harga, tv_nama_tamu, tv_nama_hotel;
                ImageView iv_back;

                tv_id_reservasi = (TextView) mView.findViewById(R.id.tv_id_reservasi);
                tv_status = (TextView) mView.findViewById(R.id.tv_status);
                tv_metode = (TextView) mView.findViewById(R.id.tv_metode);
                tv_nama_hot = (TextView) mView.findViewById(R.id.tv_nama_hot);
                tv_nama_kamar = (TextView) mView.findViewById(R.id.tv_nama_kamar);
                tv_tgl_pesan = (TextView) mView.findViewById(R.id.tv_tgl_pesan);
                tv_tot_kamar = (TextView) mView.findViewById(R.id.tv_tot_kamar);
                tv_total_harga = (TextView) mView.findViewById(R.id.tv_total_harga);
                tv_nama_tamu = (TextView) mView.findViewById(R.id.tv_nama_tamu);
                tv_nama_hotel = (TextView) mView.findViewById(R.id.tv_nama_hotel);
                iv_back = (ImageView) mView.findViewById(R.id.iv_back);

                tv_id_reservasi.setText(order.getId_reservasi());
                tv_status.setText(order.getStatus());
                tv_metode.setText(order.getMetode());
                tv_nama_hot.setText(order.getNama_hotel());
                tv_nama_kamar.setText(order.getNama_kamar());
                tv_tgl_pesan.setText(order.getCheckin() + " - " + order.getCheckout() + " (" + order.getTot_malam() + " malam)");
                tv_tot_kamar.setText(order.getTot_kamar() + " kamar");
                tv_total_harga.setText(formatRupiah.format((double)order.getTotal_harga()));
                tv_nama_tamu.setText(order.getNama());
                tv_nama_hotel.setText(order.getNama_hotel());

                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                iv_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_order;
        TextView tv_nama_hotel, tv_nama_kamar, tv_tgl_pesan;
        Button btn_rincian;

        public ViewHolder(View itemView) {
            super(itemView);

            cv_order = (CardView) itemView.findViewById(R.id.cv_order);
            tv_nama_hotel = (TextView) itemView.findViewById(R.id.tv_nama_hotel);
            tv_nama_kamar = (TextView) itemView.findViewById(R.id.tv_nama_kamar);
            tv_tgl_pesan = (TextView) itemView.findViewById(R.id.tv_tgl_pesan);
            btn_rincian = (Button) itemView.findViewById(R.id.btn_rincian);
        }
    }

    public void clear(){
        orderList.clear();
        notifyDataSetChanged();
    }
}

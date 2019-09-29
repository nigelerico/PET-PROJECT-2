package com.sourcey.materiallogindemo.adpater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.DetailHotelActivity;
import com.sourcey.materiallogindemo.PesanActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.model.Hotel;
import com.sourcey.materiallogindemo.model.Kamar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.ViewHolder> {
    private Context mContext;
    private List<Kamar> kamarList;
    String checkin, checkout, tamu, tot_kamar, tot_malam, tujuan;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

//    Intent intent = ((Activity) mContext).getIntent();
//    // 2. get message value from intent
//    String checkin = intent.getStringExtra("checkin");
//    String checkout = intent.getStringExtra("checkout");
//    String tamu = intent.getStringExtra("tamu");
//    String kamar = intent.getStringExtra("kamar");
//    String tot_malam = intent.getStringExtra("tot_malam");
//    String tujuan = intent.getStringExtra("tujuan");

    public KamarAdapter(Context mContext, ArrayList<Kamar> kamarList,
    String checkin, String checkout, String tamu, String tot_kamar, String tot_malam, String tujuan){
        this.mContext = mContext;
        this.kamarList = kamarList;
        this.checkin = checkin;
        this.checkout = checkout;
        this.tamu = tamu;
        this.tot_kamar = tot_kamar;
        this.tot_malam = tot_malam;
        this.tujuan = tujuan;
    }

    @Override
    public KamarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kamar, parent, false);
        KamarAdapter.ViewHolder vh = new KamarAdapter.ViewHolder(mView); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(final KamarAdapter.ViewHolder holder, final int position) {
        final Kamar kamar = kamarList.get(position);



        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(kamarList.get(position).getFoto1(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.foto1.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        holder.tv_nama_kamar.setText(kamar.getNama_kamar());
        holder.tv_max_orang.setText("Maks. " +kamar.getMax_orang() + " orang");
        holder.tv_ukuran.setText(kamar.getUkuran_kamar() + " m");
        holder.tv_terpesan.setVisibility(View.GONE);
        if (kamar.getSarapan().equals("yes")){
            holder.tv_sarapan.setText("Termasuk sarapan");
        } else{
            holder.tv_sarapan.setText("Tanpa sarapan");
        }
        if (kamar.getWifi().equals("yes")){
            holder.tv_wifi.setText("Wi-fi gratis");
        } else {
            holder.tv_wifi.setText("Tanpa Wi-fi");
        }

        if (kamar.getStok() == 0){
            holder.btn_pesan_kamar.setVisibility(View.GONE);
            holder.tv_terbatas.setVisibility(View.GONE);
            holder.tv_terpesan.setVisibility(View.VISIBLE);
        }




        holder.tv_tipe_kasur.setText("Tipe kasur : " +kamar.getTipe_kasur());
        holder.tv_harga_kamar.setText(formatRupiah.format((double)kamar.getHarga_kamar()));
        holder.tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Detail kamar", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn_pesan_kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), PesanActivity.class);
                intent.putExtra("id_hotel", kamarList.get(position).getId_hotel());
                intent.putExtra("id_kamar", kamarList.get(position).getId_kamar());
                intent.putExtra("nama_hotel", kamarList.get(position).getNama_hotel());
                intent.putExtra("nama_kamar", kamarList.get(position).getNama_kamar());
                intent.putExtra("tipe_kasur", kamarList.get(position).getTipe_kasur());
                intent.putExtra("harga_kamar", kamarList.get(position).getHarga_kamar());
                intent.putExtra("max_orang", kamarList.get(position).getMax_orang());
                intent.putExtra("ukuran_kamar", kamarList.get(position).getUkuran_kamar());
                intent.putExtra("sarapan", kamarList.get(position).getSarapan());
                intent.putExtra("wifi", kamarList.get(position).getWifi());
                intent.putExtra("checkin",checkin);
                intent.putExtra("checkout",checkout);
                intent.putExtra("tamu",tamu);
                intent.putExtra("kamar", tot_kamar);
                intent.putExtra("tot_malam",tot_malam);
                intent.putExtra("tujuan",tujuan);
                mContext.startActivity(intent);
                Toast.makeText(mContext, "Pesan dong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return kamarList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_kamar, tv_max_orang, tv_ukuran, tv_sarapan, tv_wifi, tv_tipe_kasur, tv_harga_kamar;
        TextView tv_detail;
        TextView tv_terpesan,tv_terbatas;
        Button btn_pesan_kamar;
        ImageView foto1;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_nama_kamar = itemView.findViewById(R.id.tv_nama_kamar);
            tv_max_orang = itemView.findViewById(R.id.tv_max_orang);
            tv_ukuran = itemView.findViewById(R.id.tv_ukuran);
            tv_sarapan = itemView.findViewById(R.id.tv_sarapan);
            tv_wifi = itemView.findViewById(R.id.tv_wifi);
            tv_tipe_kasur = itemView.findViewById(R.id.tv_tipe_kasur);
            tv_harga_kamar = itemView.findViewById(R.id.tv_harga_kamar);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            btn_pesan_kamar = itemView.findViewById(R.id.btn_pesan_kamar);
            tv_terpesan = itemView.findViewById(R.id.label_kamar_terpesan);
            tv_terbatas = itemView.findViewById(R.id.label_kamar_terbatas);
            foto1 = itemView.findViewById(R.id.imageView);
        }

    }

    public void clear(){
        kamarList.clear();
        notifyDataSetChanged();
    }

}

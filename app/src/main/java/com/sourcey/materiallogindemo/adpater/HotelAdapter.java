package com.sourcey.materiallogindemo.adpater;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.DetailHotelActivity;
import com.sourcey.materiallogindemo.ListHotelActivity;
import com.sourcey.materiallogindemo.ListKamarActivity;
import com.sourcey.materiallogindemo.MainActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.model.Hotel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context mContext;
    private List<Hotel> hotelList;
    String checkin, checkout, tamu, kamar, tot_malam, tujuan;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);



    public HotelAdapter(Context mContext, ArrayList<Hotel> hotelList,
                        String checkin, String checkout, String tamu, String kamar, String tot_malam, String tujuan){
        this.mContext = mContext;
        this.hotelList = hotelList;
        this.checkin = checkin;
        this.checkout = checkout;
        this.tamu = tamu;
        this.kamar = kamar;
        this.tot_malam = tot_malam;
        this.tujuan = tujuan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel, parent, false);
        ViewHolder vh = new ViewHolder(mView); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Hotel hotel = hotelList.get(position);

        ImageLoader imageLoader = MySingleton.getInstance(mContext).getImageLoader();
        imageLoader.get(hotelList.get(position).getFoto(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.foto.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        holder.tv_nama_hotel.setText(hotel.getName_hotel());
        holder.ratingBar.setRating(hotel.getRating());
        holder.tv_alamat.setText(hotel.getAlamat());
        holder.tv_harga_per_malam.setText(formatRupiah.format((double)hotel.getHarga_per_malam()));

        holder.cv_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailHotelActivity.class);
                intent.putExtra("id_hotel",hotelList.get(position).getId_hotel());
                intent.putExtra("nama_hotel",hotelList.get(position).getName_hotel());
                intent.putExtra("alamat",hotelList.get(position).getAlamat());
                intent.putExtra("rating",hotelList.get(position).getRating());
                intent.putExtra("harga_per_malam",hotelList.get(position).getHarga_per_malam());
                intent.putExtra("detail_informasi",hotelList.get(position).getDetail_informasi());
                intent.putExtra("sarapan",hotelList.get(position).getSarapan());
                intent.putExtra("wifi",hotelList.get(position).getWifi());
                intent.putExtra("refundable",hotelList.get(position).getRefundable());
                intent.putExtra("foto",hotelList.get(position).getFoto());
                intent.putExtra("checkin",checkin);
                intent.putExtra("checkout",checkout);
                intent.putExtra("tamu",tamu);
                intent.putExtra("kamar",kamar);
                intent.putExtra("tot_malam",tot_malam);
                intent.putExtra("tujuan",tujuan);



                mContext.startActivity(intent);
                Toast.makeText(mContext, "Ke List Hotel " + hotelList.get(position).getName_hotel(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext, "Rate " +hotelList.get(position).getHarga_per_malam(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_hotel, tv_rating, tv_alamat, tv_harga_per_malam;
        CardView cv_hotel;
        RatingBar ratingBar;
        ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);

            cv_hotel = itemView.findViewById(R.id.cv_hotel);
            tv_nama_hotel = itemView.findViewById(R.id.tv_nama_hotel);
//            tv_rating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.tv_rating);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_harga_per_malam = itemView.findViewById(R.id.tv_harga_kamar);
            foto = itemView.findViewById(R.id.imageView);
        }

    }

    public void clear(){
        hotelList.clear();
        notifyDataSetChanged();
    }
}

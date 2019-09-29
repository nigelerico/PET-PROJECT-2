package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InstruksiPembayaran extends AppCompatActivity {

    String id_reservasi, metode, tot_harga;
    TextView tv_metode, tv_id_reservasi, tv_harga_bayar, tv_jumlah_bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruksi_pembayaran);

        Intent intent = getIntent();
        id_reservasi = intent.getStringExtra("id_reservasi");
        tot_harga = intent.getStringExtra("tot_harga");
        metode = intent.getStringExtra("metode");

        tv_metode = (TextView) findViewById(R.id.tv_metode);
        tv_id_reservasi = (TextView) findViewById(R.id.tv_id_reservasi);
        tv_harga_bayar = (TextView) findViewById(R.id.tv_harga_bayar);
        tv_jumlah_bayar = (TextView) findViewById(R.id.tv_jumlah_bayar);

        tv_metode.setText(metode);
        tv_id_reservasi.setText("Nomor pesanan : " + id_reservasi);
        tv_harga_bayar.setText(tot_harga);
        tv_jumlah_bayar.setText(tot_harga);
    }

    public void back(View view){
        super.onBackPressed();
    }
}

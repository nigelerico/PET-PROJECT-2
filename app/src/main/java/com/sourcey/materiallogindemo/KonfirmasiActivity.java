package com.sourcey.materiallogindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import static com.sourcey.materiallogindemo.LoginActivity.TAG_EMAIL;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_NAMA;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_TELP;

public class KonfirmasiActivity extends AppCompatActivity {

    String id_hotel, id_kamar, nama_hotel, nama_kamar, checkin, checkout, tot_malam, tot_tamu, tot_kamar, perjalanan,
            max_orang, ukuran_kamar, sarapan, wifi, tipe_kasur;
    TextView tv_nama_hotel, tv_checkin, tv_checkout, tv_jenis_kamar, tv_max_orang, tv_ukuran, tv_sarapan, tv_wifi,
            tv_tipe_kasur, tv_nama_pemesan, tv_tipe_kamar, tv_harga_per_malam, tv_sub_total, tv_total_harga
            ,tv_tot_malam_kamar;
    Double harga_kamar, sub_total;
    Button btn_lanjut;

    SharedPreferences sharedPreferences;
    String nama, email, no_telp;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        email =sharedPreferences.getString(TAG_EMAIL,null);
        nama =sharedPreferences.getString(TAG_NAMA,null);
        no_telp = sharedPreferences.getString(TAG_TELP, null);


        tv_nama_hotel = (TextView) findViewById(R.id.tv_nama_hotel);
        tv_checkin = (TextView) findViewById(R.id.tv_checkin);
        tv_checkout = (TextView) findViewById(R.id.tv_checkout);
        tv_jenis_kamar = (TextView) findViewById(R.id.tv_jenis_kamar);
        tv_max_orang = (TextView) findViewById(R.id.tv_max_orang);
        tv_ukuran = (TextView) findViewById(R.id.tv_ukuran);
        tv_sarapan = (TextView) findViewById(R.id.tv_sarapan);
        tv_wifi = (TextView) findViewById(R.id.tv_wifi);
        tv_tipe_kasur = (TextView) findViewById(R.id.tv_tipe_kasur);
        tv_nama_pemesan = (TextView) findViewById(R.id.tv_nama_pemesan);
        tv_tipe_kamar = (TextView) findViewById(R.id.tv_tipe_kamar);
        tv_harga_per_malam = (TextView) findViewById(R.id.tv_harga_per_malam);
        tv_tot_malam_kamar = (TextView) findViewById(R.id.tv_tot_malam_kamar);
        tv_sub_total = (TextView) findViewById(R.id.tv_sub_total);
        tv_total_harga = (TextView) findViewById(R.id.tv_total_harga);
        btn_lanjut = (Button) findViewById(R.id.btn_lanjut);

        Intent intent = getIntent();
        id_hotel = intent.getStringExtra("id_hotel");
        id_kamar = intent.getStringExtra("id_kamar");
        nama_hotel = intent.getStringExtra("nama_hotel");
        nama_kamar = intent.getStringExtra("nama_kamar");
        harga_kamar = intent.getDoubleExtra("harga_kamar", 0);
        checkin = intent.getStringExtra("checkin");
        checkout = intent.getStringExtra("checkout");
        tot_malam = intent.getStringExtra("tot_malam");
        tot_tamu = intent.getStringExtra("tot_tamu");
        tot_kamar = intent.getStringExtra("tot_kamar");
        perjalanan = intent.getStringExtra("perjalanan");

        tipe_kasur = intent.getStringExtra("tipe_kasur");
        max_orang = intent.getStringExtra("max_orang");
        ukuran_kamar = intent.getStringExtra("ukuran_kamar");
        sarapan = intent.getStringExtra("sarapan");
        wifi = intent.getStringExtra("wifi");

        if (sarapan.equals("yes")){
            tv_sarapan.setText("Termasuk Sarapan");
        } else{
            tv_sarapan.setText("Tanpa sarapan");
        }
        if (wifi.equals("yes")){
            tv_wifi.setText("Wi-fi gratis");
        } else {
            tv_wifi.setText("Tanpa Wi-fi");
        }

        tv_tipe_kasur.setText("Tipe kasur : " +tipe_kasur);
        tv_max_orang.setText("Maks. " + max_orang + " orang");
        tv_ukuran.setText(ukuran_kamar + " m");

        tv_nama_hotel.setText(nama_hotel);
        tv_checkin.setText(checkin);
        tv_checkout.setText(checkout);
        tv_jenis_kamar.setText(nama_kamar);
        tv_nama_pemesan.setText(nama);
        tv_tipe_kamar.setText(nama_kamar);
        tv_tot_malam_kamar.setText(tot_malam + " Malam - " + tot_kamar + " Kamar");
        tv_harga_per_malam.setText(formatRupiah.format((double)harga_kamar));

        sub_total =((double)harga_kamar * Double.parseDouble(tot_kamar)) * Double.parseDouble(tot_malam);

        tv_sub_total.setText(formatRupiah.format((double)sub_total));
        tv_total_harga.setText(formatRupiah.format((double)sub_total));

//                Toast.makeText(this,
//                "ID HOTEL : " +id_hotel + "\n" +
//                        "ID KAMAR : " +id_kamar + "\n" +
//                        "NAMA HOTEL : " +nama_hotel + "\n" +
//                        "NAMA KAMAR : " +nama_kamar + "\n" +
//                        "HARGA KAMAR : " +harga_kamar + "\n" +
//                        "CHECK IN : " +checkin + "\n" +
//                        "CHECK OUT : " +checkout + "\n" +
//                        "TAMU : " +tot_tamu + "\n" +
//                        "TOTAL KAMAR : " +tot_kamar + "\n" +
//                        "TOTAL MALAM : " +tot_malam + "\n" +
//                        "TUJUAN : " +perjalanan + "\n", Toast.LENGTH_SHORT).show();
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BayarActivity.class);
                intent.putExtra("id_hotel", id_hotel);
                intent.putExtra("id_kamar", id_kamar);
                intent.putExtra("nama_hotel", nama_hotel);
                intent.putExtra("nama_kamar", nama_kamar);
                intent.putExtra("harga_kamar", harga_kamar);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                intent.putExtra("tot_malam", tot_malam);
                intent.putExtra("tot_tamu", tot_tamu);
                intent.putExtra("tot_kamar", tot_kamar);
                intent.putExtra("perjalanan", perjalanan);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void back(View view){
        super.onBackPressed();
    }
}

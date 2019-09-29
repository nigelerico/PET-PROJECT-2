package com.sourcey.materiallogindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.sourcey.materiallogindemo.LoginActivity.TAG_EMAIL;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_NAMA;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_TELP;

public class PesanActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String nama, email, no_telp;

    String id_hotel, id_kamar, nama_kamar, tipe_kasur, nama_hotel, alamat_hotel,
            checkin, checkout, tamu, tot_kamar, tot_malam, tujuan, max_orang, ukuran_kamar, sarapan, wifi;
    Double harga_kamar;
    TextView tv_nama_hotel, tv_jenis_kamar, tv_tgl_pesan, tv_orang, tv_nama_pemesan, tv_email_pemesan;
    EditText et_nomor_telepon;

    Button btn_liburan, btn_bisnis, btn_lainnya, btn_lanjut;
    String perjalanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        email =sharedPreferences.getString(TAG_EMAIL,null);
        nama =sharedPreferences.getString(TAG_NAMA,null);
        no_telp = sharedPreferences.getString(TAG_TELP, null);

        Intent intent = getIntent();
        id_hotel = intent.getStringExtra("id_hotel");
        id_kamar = intent.getStringExtra("id_kamar");
        nama_kamar = intent.getStringExtra("nama_kamar");
        tipe_kasur = intent.getStringExtra("tipe_kasur");
        max_orang = intent.getStringExtra("max_orang");
        ukuran_kamar = intent.getStringExtra("ukuran_kamar");
        sarapan = intent.getStringExtra("sarapan");
        wifi = intent.getStringExtra("wifi");
        harga_kamar = intent.getDoubleExtra("harga_kamar", 0);
        nama_hotel = intent.getStringExtra("nama_hotel");
        alamat_hotel = intent.getStringExtra("alamat_hotel");
        checkin = intent.getStringExtra("checkin");
        checkout = intent.getStringExtra("checkout");
        tamu = intent.getStringExtra("tamu");
        tot_kamar = intent.getStringExtra("kamar");
        tot_malam = intent.getStringExtra("tot_malam");
        tujuan = intent.getStringExtra("tujuan");

        tv_nama_hotel = (TextView) findViewById(R.id.tv_nama_hotel);
        tv_jenis_kamar = (TextView) findViewById(R.id.tv_kamar);
        tv_orang = (TextView) findViewById(R.id.tv_orang);
        tv_tgl_pesan = (TextView) findViewById(R.id.tv_tgl_pesan);
        tv_nama_pemesan = (TextView) findViewById(R.id.tv_nama_pemesan);
        tv_email_pemesan = (TextView) findViewById(R.id.tv_email_pemesan);
        et_nomor_telepon = (EditText) findViewById(R.id.et_nomor_telepon);
        btn_bisnis = (Button) findViewById(R.id.btn_bisnis);
        btn_liburan = (Button) findViewById(R.id.btn_liburan);
        btn_lainnya = (Button) findViewById(R.id.btn_lainnya);
        btn_lanjut = (Button) findViewById(R.id.btn_lanjut);

        tv_nama_hotel.setText(nama_hotel);
        tv_jenis_kamar.setText(nama_kamar);
        tv_orang.setText(tamu + " Tamu - " + tot_kamar + " Kamar");
        tv_tgl_pesan.setText(checkin + " - " + checkout + " " + " (" +tot_malam + " Malam)");
        tv_nama_pemesan.setText(nama);
        tv_email_pemesan.setText(email);
        et_nomor_telepon.setText(no_telp);

        btn_liburan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perjalanan = "Liburan";
                btn_liburan.setBackgroundResource(R.drawable.apply2);
                btn_liburan.setTextColor(getApplication().getResources().getColor(R.color.white));

                btn_bisnis.setBackgroundResource(R.drawable.apply);
                btn_bisnis.setTextColor(getApplication().getResources().getColor(R.color.black));

                btn_lainnya.setBackgroundResource(R.drawable.apply);
                btn_lainnya.setTextColor(getApplication().getResources().getColor(R.color.black));

                Toast.makeText(PesanActivity.this, perjalanan, Toast.LENGTH_SHORT).show();
            }
        });

        btn_bisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perjalanan = "Bisnis";
                btn_liburan.setBackgroundResource(R.drawable.apply);
                btn_liburan.setTextColor(getApplication().getResources().getColor(R.color.black));

                btn_bisnis.setBackgroundResource(R.drawable.apply2);
                btn_bisnis.setTextColor(getApplication().getResources().getColor(R.color.white));

                btn_lainnya.setBackgroundResource(R.drawable.apply);
                btn_lainnya.setTextColor(getApplication().getResources().getColor(R.color.black));

                Toast.makeText(PesanActivity.this, perjalanan, Toast.LENGTH_SHORT).show();
            }
        });

        btn_lainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perjalanan = "Lainnya";
                btn_liburan.setBackgroundResource(R.drawable.apply);
                btn_liburan.setTextColor(getApplication().getResources().getColor(R.color.black));

                btn_bisnis.setBackgroundResource(R.drawable.apply);
                btn_bisnis.setTextColor(getApplication().getResources().getColor(R.color.black));

                btn_lainnya.setBackgroundResource(R.drawable.apply2);
                btn_lainnya.setTextColor(getApplication().getResources().getColor(R.color.white));

                Toast.makeText(PesanActivity.this, perjalanan, Toast.LENGTH_SHORT).show();
            }
        });

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KonfirmasiActivity.class);
                intent.putExtra("id_hotel", id_hotel);
                intent.putExtra("id_kamar", id_kamar);
                intent.putExtra("nama_hotel", nama_hotel);
                intent.putExtra("nama_kamar", nama_kamar);
                intent.putExtra("harga_kamar", harga_kamar);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                intent.putExtra("tot_malam", tot_malam);
                intent.putExtra("tot_tamu", tamu);
                intent.putExtra("tot_kamar", tot_kamar);
                intent.putExtra("perjalanan", perjalanan);


                intent.putExtra("max_orang", max_orang);
                intent.putExtra("ukuran_kamar", ukuran_kamar);
                intent.putExtra("sarapan", sarapan);
                intent.putExtra("wifi", wifi);
                intent.putExtra("tipe_kasur", tipe_kasur);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

//        Toast.makeText(this,
//                "ID HOTEL : " +id_hotel + "\n" +
//                        "ID KAMAR : " +id_kamar + "\n" +
//                        "NAMA KAMAR : " +nama_kamar + "\n" +
//                        "TIPE KASUR : " +tipe_kasur + "\n" +
//                        "HARGA KAMAR : " +harga_kamar + "\n" +
//                        "NAMA HOTEL : " +nama_hotel + "\n" +
//                        "ALAMAT HOTEL : " +alamat_hotel + "\n" +
//                        "CHECK IN : " +checkin + "\n" +
//                        "CHECK OUT : " +checkout + "\n" +
//                        "TAMU : " +tamu + "\n" +
//                        "TOTAL KAMAR : " +tot_kamar + "\n" +
//                        "TOTAL MALAM : " +tot_malam + "\n" +
//                        "TUJUAN : " +tujuan + "\n", Toast.LENGTH_SHORT).show();
    }

    public void back(View view){
        super.onBackPressed();
    }
}

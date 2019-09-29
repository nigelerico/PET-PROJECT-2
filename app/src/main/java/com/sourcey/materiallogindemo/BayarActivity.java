package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.sourcey.materiallogindemo.R;

public class BayarActivity extends AppCompatActivity {

    String id_hotel, id_kamar, nama_hotel, nama_kamar, checkin, checkout, tot_malam, tot_tamu, tot_kamar, perjalanan;
    Double harga_kamar;
    CardView cv_tranfer, cv_bayar_ditempat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        cv_tranfer = (CardView) findViewById(R.id.cv_tranfer);
        cv_bayar_ditempat = (CardView) findViewById(R.id.cv_bayar_ditempat);

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

//                        Toast.makeText(this,
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

                        cv_tranfer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), TranferActivity.class);
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

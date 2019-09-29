package com.sourcey.materiallogindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.Server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.sourcey.materiallogindemo.LoginActivity.TAG_EMAIL;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_ID;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_NAMA;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_TELP;

public class TranferActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String id_user, nama, email, no_telp;

    EditText et_nama_rekening;
    TextView tv_harga_bayar, tv_nama_hotel, tv_nama_kamar, tv_in_out, tv_tot_kamar_malam, tv_harga_sub_total,
            tv_total;
    Button btn_bayar;

    String id_hotel, id_kamar, nama_hotel, nama_kamar, checkin, checkout, tot_malam, tot_tamu, tot_kamar, perjalanan, metode;
    Double harga_kamar, sub_total;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    private static final String URL_SEND= Server.URL + "Android/send_reservasi.php";

    private static final String TAG_SUCCESS = "value";
    private static final String TAG = TranferActivity.class.getSimpleName();
    int success;
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfer);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_user =sharedPreferences.getString(TAG_ID, null);
        email =sharedPreferences.getString(TAG_EMAIL,null);
        nama =sharedPreferences.getString(TAG_NAMA,null);
        no_telp = sharedPreferences.getString(TAG_TELP, null);

        et_nama_rekening = (EditText) findViewById(R.id.et_nama_rekening);
        tv_harga_bayar = (TextView) findViewById(R.id.tv_harga_bayar);
        tv_nama_hotel = (TextView) findViewById(R.id.tv_nama_hotel);
        tv_nama_kamar = (TextView) findViewById(R.id.tv_nama_kamar);
        tv_in_out = (TextView) findViewById(R.id.tv_in_out);
        tv_tot_kamar_malam = (TextView) findViewById(R.id.tv_tot_kamar_malam);
        tv_harga_sub_total = (TextView) findViewById(R.id.tv_harga_sub_total);
        tv_total = (TextView) findViewById(R.id.tv_total);
        btn_bayar = (Button) findViewById(R.id.btn_bayar);

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

        sub_total =((double)harga_kamar * Double.parseDouble(tot_kamar)) * Double.parseDouble(tot_malam);
        metode = "Tranfer BCA";

        et_nama_rekening.setText(nama);
        tv_harga_bayar.setText(formatRupiah.format((double)sub_total));
        tv_nama_hotel.setText(nama_hotel);
        tv_nama_kamar.setText(nama_kamar);
        tv_in_out.setText(checkin + " - " + checkout);
        tv_tot_kamar_malam.setText(tot_malam + " Malam - " + tot_kamar + " Kamar");
        tv_harga_sub_total.setText(formatRupiah.format((double)sub_total));
        tv_total.setText(formatRupiah.format((double)sub_total));

        btn_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan();
            }
        });
    }

    private void pesan() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_SEND,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            // Check for error node in json
                            if (success == 1) {

                                Log.e("Successfully Order!", jObj.toString());

                                Toast.makeText(getApplicationContext(),
                                        jObj.getString("message"), Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);



                            } else {
                                Toast.makeText(getApplicationContext(), "error gan",
                                        Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "error bro",
                                Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("id_hotel", String.valueOf(id_hotel));
                params.put("id_kamar", String.valueOf(id_kamar));
                params.put("nama_hotel", String.valueOf(nama_hotel));
                params.put("nama_kamar", String.valueOf(nama_kamar));
                params.put("checkin", String.valueOf(checkin));
                params.put("checkout", String.valueOf(checkout));
                params.put("tot_malam", String.valueOf(tot_malam));
                params.put("tot_tamu", String.valueOf(tot_tamu));
                params.put("tot_kamar", String.valueOf(tot_kamar));
                params.put("id_user", String.valueOf(id_user));
                params.put("nama", String.valueOf(nama));
                params.put("email", String.valueOf(email));
                params.put("no_telp", String.valueOf(no_telp));
                params.put("tujuan", String.valueOf(perjalanan));
                params.put("total_harga", String.valueOf(sub_total));
                params.put("metode", String.valueOf(metode));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
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

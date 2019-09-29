package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Server.Server;
import com.sourcey.materiallogindemo.adpater.UlasanAdapter;
import com.sourcey.materiallogindemo.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetailHotelActivity extends AppCompatActivity {

    TextView
            tv_nama_hotel,
            tv_alamat,
            txt_nama_hotel,
            txt_alamat,
    txt_detail,
    txt_harga_per_malam;
    RatingBar tv_rating;
    Button btn_pilih_kamar,btn_lihat_lokasi;
    int rating = 0;
    public View view;
    int id_hoteel;
    ImageView gambar_hotel;
    String foto;

    private static final String URL_COMMENT = Server.URL + "Android/ApiComment.php";
    private UlasanAdapter ulasanAdapter;
    ArrayList<Comment> mComment = new ArrayList<>();
    RecyclerView mRecyclerViewComment;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        gambar_hotel = (ImageView) findViewById(R.id.iv_gambar_hotel);
        // 1. get passed intent
        Intent intent = getIntent();
        final String id_hotel = intent.getStringExtra("id_hotel");
        final String nama_hotel = intent.getStringExtra("nama_hotel");
        final String alamat_hotel = intent.getStringExtra("alamat");
        Integer rating_hotel =  intent.getIntExtra("rating",0);
        Double harga_per_malam = intent.getDoubleExtra("harga_per_malam",0);
        String detail_informasi = intent.getStringExtra("detail_informasi");
        foto = intent.getStringExtra("foto");
        showImage(foto);

        id_hoteel = Integer.parseInt(id_hotel);

        // 2. get message value from intent
        final String checkin = intent.getStringExtra("checkin");
        final String checkout = intent.getStringExtra("checkout");
        final String tamu = intent.getStringExtra("tamu");
        final String kamar = intent.getStringExtra("kamar");
        final String tot_malam = intent.getStringExtra("tot_malam");
        final String tujuan = intent.getStringExtra("tujuan");


        //rating = Integer.parseInt(rating_hotel);

       // Toast.makeText(getApplicationContext(), ""+intent.getIntExtra("rating", 0) , Toast.LENGTH_SHORT).show();

        tv_nama_hotel = (TextView) findViewById(R.id.tv_nama_hotel);
        tv_alamat = (TextView) findViewById(R.id.tv_alamat);
        txt_nama_hotel = (TextView) findViewById(R.id.txt_nama_hotel);
        tv_rating = (RatingBar) findViewById(R.id.tv_rating);
        txt_alamat = (TextView) findViewById(R.id.txt_alamat);
        txt_detail = (TextView) findViewById(R.id.txt_detail);
        txt_harga_per_malam = (TextView) findViewById(R.id.txt_harga_per_malam);
        btn_pilih_kamar = (Button) findViewById(R.id.btn_pilih_kamar);
        btn_lihat_lokasi = (Button) findViewById(R.id.btn_lihat_alamat);



        tv_nama_hotel.setText(nama_hotel);
        tv_alamat.setText(alamat_hotel);
        txt_nama_hotel.setText(nama_hotel);
        tv_rating.setRating(rating_hotel);
        txt_alamat.setText(alamat_hotel);
        txt_detail.setText(detail_informasi);
        txt_harga_per_malam.setText(""+formatRupiah.format((double)harga_per_malam));

        mRecyclerViewComment = findViewById(R.id.rl_review);


        btn_pilih_kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListKamarActivity.class);
                intent.putExtra("id_hotel",id_hotel);
                intent.putExtra("nama_hotel",nama_hotel);
                intent.putExtra("alamat_hotel",alamat_hotel);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                intent.putExtra("tamu", tamu);
                intent.putExtra("kamar", kamar);
                intent.putExtra("tot_malam", tot_malam);
                intent.putExtra("tujuan", tujuan);
//                Toast.makeText(DetailHotelActivity.this, id_hotel + "\n"
//                        + nama_hotel + "\n"
//                        + alamat_hotel + "\n"
//                        + checkin + "\n"
//                        + checkout + "\n"
//                        + tamu + "\n"
//                        + kamar + "\n"
//                        + tot_malam + "\n"
//                        + tujuan + "\n", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btn_lihat_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsDirection.class);
                intent.putExtra("alamat_hotel",alamat_hotel);
//                Toast.makeText(DetailHotelActivity.this, id_hotel + "\n"
//                        + nama_hotel + "\n"
//                        + alamat_hotel + "\n"
//                        + checkin + "\n"
//                        + checkout + "\n"
//                        + tamu + "\n"
//                        + kamar + "\n"
//                        + tot_malam + "\n"
//                        + tujuan + "\n", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        comment(view);

    }

    private void comment(View view ) {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_COMMENT,
                new Response.Listener<String>() {
                    public void onResponse(String response){
//                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                if (jsonObject.getInt("id_hotel") == id_hoteel) {
                                    Comment item = new Comment();
                                    item.setId_hotel(jsonObject.getInt("id_hotel"));
                                    item.setId_user(jsonObject.getInt("id_user"));
                                    item.setNama(jsonObject.getString("nama"));
                                    item.setComment(jsonObject.getString("comment"));
                                    mComment.add(item);


                                    mRecyclerViewComment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    mRecyclerViewComment.setHasFixedSize(true);
                                    ulasanAdapter = new UlasanAdapter(getApplicationContext(), mComment);
                                    mRecyclerViewComment.setAdapter(ulasanAdapter);
                                    ulasanAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void showImage(String linkImage){
        ImageLoader imageLoader = MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                gambar_hotel.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    public void back(View view){
        super.onBackPressed();
    }
}

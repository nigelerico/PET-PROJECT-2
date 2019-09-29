package com.sourcey.materiallogindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.Server.Server;
import com.sourcey.materiallogindemo.adpater.HotelAdapter;
import com.sourcey.materiallogindemo.adpater.UlasanAdapter;
import com.sourcey.materiallogindemo.model.Comment;
import com.sourcey.materiallogindemo.model.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListHotelActivity extends AppCompatActivity {
    private static final String URL_HOTEL = Server.URL + "Android/ApiHotel.php";
    private static final String URL_COMMENT = Server.URL + "Android/ApiComment.php";

    private UlasanAdapter ulasanAdapter;
    private HotelAdapter myAdapter;
    ArrayList<Hotel> mItems = new ArrayList<>();
    ArrayList<Comment> mComment = new ArrayList<>();

    RecyclerView mRecyclerView;
    RecyclerView mRecyclerViewComment;

    SwipeRefreshLayout swipeRefreshLayout;
    public View view;
    int tot_kamar;
    TextView tv_checkin, tv_lokasi, tv_malam, tv_kamar;
    ImageView ic_search;
    String checkin, checkout, tamu, kamar, tot_malam, tujuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);

        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        // 1. get passed intent
        Intent intent = getIntent();
        // 2. get message value from intent
        checkin = intent.getStringExtra("checkin");
        checkout = intent.getStringExtra("checkout");
        tamu = intent.getStringExtra("tamu");
        kamar = intent.getStringExtra("kamar");
        tot_malam = intent.getStringExtra("tot_malam");
        tujuan = intent.getStringExtra("tujuan");



        tot_kamar = Integer.parseInt(kamar);

        tv_lokasi = (TextView) findViewById(R.id.tv_lokasi);
        tv_checkin = (TextView) findViewById(R.id.tv_checkin);
        tv_malam = (TextView) findViewById(R.id.tv_malam);
        tv_kamar = (TextView) findViewById(R.id.tv_kamar);

        tv_lokasi.setText(tujuan);
        tv_checkin.setText(checkin);
        tv_malam.setText(tot_malam + " Malam");
        tv_kamar.setText(kamar + " Kamar");

        ic_search = (ImageView) findViewById(R.id.ic_search);
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                openSearch();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view_hotel);
        mRecyclerViewComment = findViewById(R.id.rl_review);



        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        myAdapter.clear();
                        loadJson(view);
                    }
                }, 1000);
            }
        });
//        Toast.makeText(getApplicationContext(),String.valueOf(tot_kamar),Toast.LENGTH_SHORT).show();
        loadJson(view);
     //   comment(view);
    }

    private void loadJson(View view ) {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_HOTEL,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                if (jsonObject.getInt("stok_kamar")>= tot_kamar) {
                                    mItems.add(new Hotel(jsonObject.getString("id_hotel"),
                                    jsonObject.getString("nama_hotel"),
                                    jsonObject.getString("alamat"),
                                    jsonObject.getInt("rating"),
                                    jsonObject.getInt("stok_kamar"),
                                    jsonObject.getDouble("harga_per_malam"),
                                    jsonObject.getString("detail_informasi"),
                                    jsonObject.getString("max_orang"),
                                    jsonObject.getString("sarapan"),
                                    jsonObject.getString("ukuran_kamar"),
                                    jsonObject.getString("wifi"),
                                    jsonObject.getString("refundable"),
                                    Server.URL_FOTO +  jsonObject.getString("foto")));
                                }

                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                mRecyclerView.setHasFixedSize(true);
                                myAdapter = new HotelAdapter(getApplicationContext(), mItems,
                                        checkin, checkout, tamu, kamar, tot_malam, tujuan);
                                mRecyclerView.setAdapter(myAdapter);
                                myAdapter.notifyDataSetChanged();
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

//
//    private void comment(View view ) {
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_COMMENT,
//                new Response.Listener<String>() {
//                    public void onResponse(String response){
//                        progressBar.setVisibility(View.INVISIBLE);
//                        Log.d("json", response.toString());
//
//                        try {
//                            JSONArray jsonArray =  new JSONArray(response);
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//
//                                    Comment item = new Comment();
//                                    item.setId_hotel(jsonObject.getInt("id_hotel"));
//                                    item.setId_user(jsonObject.getInt("id_user"));
//                                    item.setNama(jsonObject.getString("nama"));
//                                    item.setComment(jsonObject.getString("comment"));
//                                    mComment.add(item);
//
//
//                                mRecyclerViewComment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                                mRecyclerViewComment.setHasFixedSize(true);
//                                ulasanAdapter = new UlasanAdapter(getApplicationContext(), mComment);
//                                mRecyclerViewComment.setAdapter(ulasanAdapter);
//                                myAdapter.notifyDataSetChanged();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
//    }
//
//



    public void back(View view){
        super.onBackPressed();
    }

    public  void openSearch() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_search, null);
        final AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getApplicationContext());
        alertDialogBuilderUserInput.setView(mView);

        final ImageView ic_close = (ImageView) mView.findViewById(R.id.ic_close);

        final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();

        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogAndroid.dismiss();
            }
        });

        alertDialogAndroid.show();
        Toast.makeText(getApplicationContext(), "bisa lah", Toast.LENGTH_SHORT).show();
    }
}

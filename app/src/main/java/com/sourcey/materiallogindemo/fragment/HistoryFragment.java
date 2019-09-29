package com.sourcey.materiallogindemo.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.LoginActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Server.Server;
import com.sourcey.materiallogindemo.adpater.HistoryAdapter;
import com.sourcey.materiallogindemo.adpater.OrderAdapter;
import com.sourcey.materiallogindemo.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.sourcey.materiallogindemo.LoginActivity.TAG_EMAIL;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_ID;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_NAMA;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_TELP;

public class HistoryFragment extends Fragment {

    private static final String URL_ORDER = Server.URL + "Android/ApiOrder.php";

    SharedPreferences sharedPreferences;
    String id_user, nama, email, no_telp;

    private HistoryAdapter myAdapter;
    ArrayList<Order> mItems = new ArrayList<>();
    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_history, container, false);

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString(TAG_ID, null);
        email =sharedPreferences.getString(TAG_EMAIL,null);
        nama =sharedPreferences.getString(TAG_NAMA,null);
        no_telp = sharedPreferences.getString(TAG_TELP, null);

        Toolbar topToolBar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(topToolBar);

        mRecyclerView = view.findViewById(R.id.recycler_view_history);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
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

        loadJson(view);

        return view;
    }

    private void loadJson(View view ) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ORDER,
                new Response.Listener<String>() {
                    public void onResponse(String response){
                        Log.d("json", response.toString());

                        try {
                            JSONArray jsonArray =  new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                if (jsonObject.getString("id_user").equals(id_user)) {
                                    Order item = new Order();
                                    item.setId_reservasi(jsonObject.getString("id_reservasi"));
                                    item.setId_hotel(jsonObject.getString("id_hotel"));
                                    item.setId_kamar(jsonObject.getString("id_kamar"));
                                    item.setNama_hotel(jsonObject.getString("nama_hotel"));
                                    item.setNama_kamar(jsonObject.getString("nama_kamar"));
                                    item.setCheckin(jsonObject.getString("checkin"));
                                    item.setCheckout(jsonObject.getString("checkout"));
                                    item.setId_user(jsonObject.getString("id_user"));
                                    item.setNama(jsonObject.getString("nama"));
                                    item.setTotal_harga(jsonObject.getDouble("total_harga"));
                                    item.setTot_malam(jsonObject.getString("tot_malam"));
                                    item.setTot_kamar(jsonObject.getString("tot_kamar"));
                                    item.setMetode(jsonObject.getString("metode"));
                                    item.setStatus(jsonObject.getString("status"));

                                    mItems.add(item);
                                }

                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                mRecyclerView.setHasFixedSize(true);
                                myAdapter = new HistoryAdapter(getContext(), mItems);
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
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}

package com.sourcey.materiallogindemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sourcey.materiallogindemo.App.MySingleton;
import com.sourcey.materiallogindemo.App.VolleyMultipartRequest;
import com.sourcey.materiallogindemo.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailOrderActivity extends AppCompatActivity {

    private static final String URL_DETAIL = Server.URL + "Android/ApiDetailOrder.php";
    private static final String URL_BUKTI = Server.URL + "Android/ApiBukti.php";
    private static final String URL_UPDATE = Server.URL + "Android/update_reservasi.php";
    private static final String URL_SEND_BUKTI = Server.URL + "Android/send_bukti.php?apicall=uploadpic";

    String id_reservasi, id_hotel, metode;
    String tot_harga, checkin, checkout, tot_malam, nama, tot_kamar;
    ImageView iv_bukti;
    TextView tv_nama_hotel, tv_id_reservasi, tv_status, tv_metode, tv_nama_hot, tv_alamat, tv_nama_kamar,
            tv_tgl_pesan, tv_tot_kamar, tv_total_harga, tv_nama_tamu;
    Button btn_instruksi, btn_unggah, btn_select_image;

    RelativeLayout rl_bukti;
    private Bitmap bitmap;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        tv_nama_hotel = (TextView) findViewById(R.id.tv_nama_hotel);
        tv_id_reservasi = (TextView) findViewById(R.id.tv_id_reservasi);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_metode = (TextView) findViewById(R.id.tv_metode);
        tv_nama_hot = (TextView) findViewById(R.id.tv_nama_hot);
        tv_alamat = (TextView) findViewById(R.id.tv_alamat);
        tv_nama_kamar = (TextView) findViewById(R.id.tv_nama_kamar);
        tv_tgl_pesan = (TextView) findViewById(R.id.tv_tgl_pesan);
        tv_tot_kamar = (TextView) findViewById(R.id.tv_tot_kamar);
        tv_total_harga = (TextView) findViewById(R.id.tv_total_harga);
        tv_nama_tamu = (TextView) findViewById(R.id.tv_nama_tamu);
        btn_instruksi = (Button) findViewById(R.id.btn_instruksi);
        btn_unggah = (Button) findViewById(R.id.btn_unggah);
        btn_select_image = (Button) findViewById(R.id.btn_select_image);
        iv_bukti = (ImageView) findViewById(R.id.iv_bukti);
        rl_bukti = (RelativeLayout) findViewById(R.id.rl_bukti);

        btn_instruksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InstruksiPembayaran.class);
                intent.putExtra("id_reservasi", id_reservasi);
                intent.putExtra("metode", metode);
                intent.putExtra("tot_harga", tot_harga);
                startActivity(intent);
            }
        });

        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_image();
            }
        });

        btn_unggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Bukti Pembayaran");
                alertbox.setTitle("Attention");
                alertbox.setIcon(R.drawable.ic_confirm);
                alertbox.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uploadBukti(bitmap);
                        updateReservasi();
                    }
                }).setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();

                alertbox.show();

            }
        });

        Intent intent = getIntent();
        id_reservasi = intent.getStringExtra("id_reservasi");
        id_hotel = intent.getStringExtra("id_hotel");
        tot_harga = intent.getStringExtra("total_harga");
        checkin = intent.getStringExtra("checkin");
        checkout = intent.getStringExtra("checkout");
        tot_malam = intent.getStringExtra("tot_malam");
        nama = intent.getStringExtra("nama");
        tot_kamar = intent.getStringExtra("tot_kamar");
        metode = intent.getStringExtra("metode");

//        Toast.makeText(this, tot_harga, Toast.LENGTH_SHORT).show();
        tv_total_harga.setText(tot_harga);
        tv_tgl_pesan.setText(checkin + " - " + checkout + " (" + tot_malam + " malam)");
        tv_tot_kamar.setText(tot_kamar + " kamar");
        tv_nama_tamu.setText(nama);

        detailOrder();

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 0);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");
                    iv_bukti.setImageBitmap(bitmap);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK && data != null) {

                    Uri imageUri = data.getData();
                    try {
                        //getting bitmap object from uri
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                        //displaying selected image to imageview
                        iv_bukti.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void select_image()
    {
        iv_bukti.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailOrderActivity.this);
        builder.setTitle("Unggah Bukti Pembayaran");
        builder.setIcon(R.drawable.ic_photo_camera_black_24dp);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
//                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    fileUri = getOutputMediaFileUri();
//                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
//                    startActivityForResult(intent, REQUEST_CAMERA);
                    dispatchTakePictureIntent();
                    Toast.makeText(DetailOrderActivity.this, "Kamera", Toast.LENGTH_SHORT).show();
                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBukti(final Bitmap bitmap){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL_SEND_BUKTI,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), "Success Upload", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DetailOrderActivity.class);
                            intent.putExtra("id_reservasi", id_reservasi);
                            intent.putExtra("id_hotel", id_hotel);
                            intent.putExtra("checkin", checkin);
                            intent.putExtra("checkout", checkout);
                            intent.putExtra("tot_malam", tot_malam);
                            intent.putExtra("nama", nama);
                            intent.putExtra("metode", metode);
                            intent.putExtra("tot_kamar", tot_kamar);
                            intent.putExtra("total_harga", tot_harga);
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_reservasi", id_reservasi);
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new VolleyMultipartRequest.DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
    }

    private void updateReservasi() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void
            onResponse(String response) {
                Intent mIntent = new Intent(getApplicationContext(), DetailOrderActivity.class);
                intent.putExtra("id_reservasi", id_reservasi);
                intent.putExtra("id_hotel", id_hotel);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                intent.putExtra("tot_malam", tot_malam);
                intent.putExtra("nama", nama);
                intent.putExtra("metode", metode);
                intent.putExtra("tot_kamar", tot_kamar);
                intent.putExtra("total_harga", tot_harga);
                startActivity(mIntent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_reservasi", id_reservasi);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void detailOrder() {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DETAIL,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        String json = response;
                        Log.e("Response :", response);
                        try {

                            JSONArray array = new JSONArray(json);


                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                tv_nama_hotel.setText(jsonObject.getString("nama_hotel"));
                                tv_id_reservasi.setText(jsonObject.getString("id_reservasi"));
                                tv_status.setText(jsonObject.getString("status"));
                                tv_metode.setText(jsonObject.getString("metode"));
                                tv_nama_hot.setText(jsonObject.getString("nama_hotel"));
                                tv_nama_kamar.setText(jsonObject.getString("nama_kamar"));
//                                tv_tot_kamar.setText(jsonObject.getString("tot_kamar" + " kamar"));
//                                tv_tgl_pesan.setText(jsonObject.getString("checkin") + " - " +
//                                                jsonObject.getString("checkout") + " (" +
//                                        jsonObject.getString("tot_malam") + "malam)");
//                                tv_nama_tamu.setText(jsonObject.getString("nama"));

                                if (jsonObject.getString("status").equals("Menunggu konfirmasi")){
                                    btn_select_image.setVisibility(View.GONE);
                                    btn_unggah.setVisibility(View.GONE);
                                    showBukti();
                                }
                                if (jsonObject.getString("status").equals("Pesanan anda telah di konfirmasi")){
                                    btn_select_image.setVisibility(View.GONE);
                                    btn_unggah.setVisibility(View.GONE);
                                    showBukti();
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_reservasi", id_reservasi);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void showBukti() {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BUKTI,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        String json = response;
                        Log.e("Response :", response);
                        try {

                            JSONArray array = new JSONArray(json);


                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                showImage(Server.URL_BUKTI + jsonObject.getString("gambar"));
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_reservasi", id_reservasi);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void showImage(String linkImage){
        ImageLoader imageLoader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                iv_bukti.setImageBitmap(response.getBitmap());
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

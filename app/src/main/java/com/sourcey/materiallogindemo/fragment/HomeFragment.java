package com.sourcey.materiallogindemo.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.sourcey.materiallogindemo.DateRangePickerActivity;
import com.sourcey.materiallogindemo.ListHotelActivity;
import com.sourcey.materiallogindemo.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    Button btn_cari;
    CarouselView carouselView;
    TextView et_checkin, et_checkout, et_total, et_kamar, tot_malam, et_tujuan;
    String mDateStart;
    String mDateEnd;
    Date date1, date2;
    long malam;
    Unbinder unbinder;
    int[] sampleImages = {R.drawable.ads1, R.drawable.ads2, R.drawable.ads3};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        Locale id = new Locale("in", "ID");
        String pattern = "E, dd MMM yy";

        Date today = new Date();

        // Gets formatted date specify by the given pattern for
        // Indonesian Locale no changes for default date format
        // is applied here.
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, id);
        mDateStart = sdf.format(today);

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        Date checkout = c.getTime();

        mDateEnd = sdf.format(checkout);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        et_checkout = (TextView) view.findViewById(R.id.et_checkout);
        et_checkin = (TextView) view.findViewById(R.id.et_checkin);
        tot_malam = (TextView) view.findViewById(R.id.tot_malam);
        try {
            date1= sdf.parse(mDateStart);
            date2 = sdf.parse(mDateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        malam = date2.getTime()-date1.getTime();
        tot_malam.setText(TimeUnit.DAYS.convert(malam , TimeUnit.MILLISECONDS) +" Malam");

        et_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateRangePicker();
            }
        });

        et_checkin.setText(mDateStart);
        et_checkout.setText(mDateEnd);

        et_total = (TextView) view.findViewById(R.id.et_total);

        et_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
                View mView = layoutInflaterAndroid.inflate(R.layout.dialog_scroll_choice, null);
                final AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
                alertDialogBuilderUserInput.setView(mView);

                final ScrollChoice scrollChoice = (ScrollChoice) mView.findViewById(R.id.scroll_choice);

                final Button btn_simpan = mView.findViewById(R.id.btn_simpan);

                final List<String> data = new ArrayList<>();
                data.add("1");
                data.add("2");
                data.add("3");
                data.add("4");
                data.add("5");
                data.add("6");
                data.add("7");
                data.add("8");
                data.add("9");
                data.add("10");

                String getTamu = et_total.getText().toString();
                final int set_tamu = Integer.parseInt(getTamu)-1;

                final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                scrollChoice.addItems(data, set_tamu);

                scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(ScrollChoice scrollChoice, final int position, String name) {
                        btn_simpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                et_total.setText(String.valueOf(data.get(position)));
                                alertDialogAndroid.dismiss();
                            }
                        });
                    }
                });

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alertDialogAndroid.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                alertDialogAndroid.show();
                alertDialogAndroid.getWindow().setAttributes(lp);

//                alertDialogAndroid.show();
            }
        });

        et_kamar = (TextView) view.findViewById(R.id.et_kamar);
        et_kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
                View mView = layoutInflaterAndroid.inflate(R.layout.dialog_scroll_choice2, null);
                final AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
                alertDialogBuilderUserInput.setView(mView);

                final ScrollChoice scrollChoice2 = (ScrollChoice) mView.findViewById(R.id.scroll_choice2);
                final Button btn_simpan = mView.findViewById(R.id.btn_simpan);

                final List<String> kamar = new ArrayList<>();
                kamar.add("1");
                kamar.add("2");
                kamar.add("3");
                kamar.add("4");
                kamar.add("5");
                kamar.add("6");
                kamar.add("7");
                kamar.add("8");
                kamar.add("9");
                kamar.add("10");

                String getKamar = et_kamar.getText().toString();
                final int set_kamar = Integer.parseInt(getKamar)-1;

                final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();

                scrollChoice2.addItems(kamar, set_kamar);
                scrollChoice2.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(ScrollChoice scrollChoice, final int position, String name) {
                        btn_simpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                et_kamar.setText(String.valueOf(kamar.get(position)));
                                alertDialogAndroid.dismiss();
                            }
                        });
                    }
                });

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alertDialogAndroid.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                alertDialogAndroid.show();
                alertDialogAndroid.getWindow().setAttributes(lp);
            }
        });

        et_tujuan = (TextView) view.findViewById(R.id.et_tujuan);

        btn_cari = (Button) view.findViewById(R.id.btn_cari);
        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malam = date2.getTime()-date1.getTime();
                String getCheckin = et_checkin.getText().toString();
                String getCheckout = et_checkout.getText().toString();
                String getTotMalam = String.valueOf(TimeUnit.DAYS.convert(malam , TimeUnit.MILLISECONDS));
                String getTujuan = et_tujuan.getText().toString();
                String getTamu = et_total.getText().toString();
                int set_tamu = Integer.parseInt(getTamu);
                String getKamar = et_kamar.getText().toString();
                int set_kamar = Integer.parseInt(getKamar);
                if (set_tamu<set_kamar) {
                    Snackbar.make(v, "Jumlah kamar tidak bisa melebihi jumlah tamu", Snackbar.LENGTH_LONG).show();
//                    Toast.makeText(getContext(),"Jumlah kamar tidak bisa melebihi jumlah tamu",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), ListHotelActivity.class);
                    intent.putExtra("checkin", getCheckin);
                    intent.putExtra("checkout", getCheckout);
                    intent.putExtra("tamu", getTamu);
                    intent.putExtra("kamar", getKamar);
                    intent.putExtra("tot_malam", getTotMalam);
                    intent.putExtra("tujuan", getTujuan);
                    startActivity(intent);
                    Toast.makeText(getContext(), getTotMalam, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


        ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void openDateRangePicker(){
        SublimePickerFragment pickerFrag = new SublimePickerFragment();


        pickerFrag.setCallback(new SublimePickerFragment.Callback() {
            @Override
            public void onCancelled() {
                Toast.makeText(getContext(), "User cancel",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateTimeRecurrenceSet(final SelectedDate selectedDate, int hourOfDay, int minute,
                                                SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                                String recurrenceRule) {

                @SuppressLint("SimpleDateFormat")
                Locale id = new Locale("in", "ID");
                SimpleDateFormat formatDate = new SimpleDateFormat("E, dd MMM yy", id);


                mDateStart = formatDate.format(selectedDate.getStartDate().getTime());
                mDateEnd = formatDate.format(selectedDate.getEndDate().getTime());
                try {
                    date1= formatDate.parse(mDateStart);
                    date2 = formatDate.parse(mDateEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long malam = date2.getTime()-date1.getTime();
                tot_malam.setText(TimeUnit.DAYS.convert(malam , TimeUnit.MILLISECONDS) +" Malam");

                // set date start ke textview
                et_checkin.setText(mDateStart);
                // set date end ke textview
                et_checkout.setText(mDateEnd);


            }
        });

        // ini configurasi agar library menggunakan method Date Range Picker
        SublimeOptions options = new SublimeOptions();
        options.setCanPickDateRange(true);
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);

        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getActivity().getSupportFragmentManager(), "SUBLIME_PICKER");
    }

}

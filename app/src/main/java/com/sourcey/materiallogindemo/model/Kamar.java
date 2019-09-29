package com.sourcey.materiallogindemo.model;

public class Kamar {
    private String id_kamar;
    private String id_hotel;
    private String nama_hotel;
    private String nama_kamar;
    private String max_orang;
    private int stok;
    private String ukuran_kamar;
    private String sarapan;
    private String wifi;
    private String tipe_kasur;
    private Double harga_kamar;
    private String deskripsi_kamar;
    private String foto1;
    private String foto2;

    public Kamar(String id_kamar, String id_hotel, String nama_hotel, String nama_kamar, String max_orang, int stok, String ukuran_kamar, String sarapan, String wifi, String tipe_kasur, Double harga_kamar, String deskripsi_kamar, String foto1, String foto2) {
        this.id_kamar = id_kamar;
        this.id_hotel = id_hotel;
        this.nama_hotel = nama_hotel;
        this.nama_kamar = nama_kamar;
        this.max_orang = max_orang;
        this.stok=stok;
        this.ukuran_kamar = ukuran_kamar;
        this.sarapan = sarapan;
        this.wifi = wifi;
        this.tipe_kasur = tipe_kasur;
        this.harga_kamar = harga_kamar;
        this.deskripsi_kamar = deskripsi_kamar;
        this.foto1 = foto1;
        this.foto2 = foto2;

    }

    public Kamar() {

    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNama_hotel() {
        return nama_hotel;
    }

    public void setNama_hotel(String nama_hotel) {
        this.nama_hotel = nama_hotel;
    }

    public String getNama_kamar() {
        return nama_kamar;
    }

    public void setNama_kamar(String nama_kamar) {
        this.nama_kamar = nama_kamar;
    }

    public String getMax_orang() {
        return max_orang;
    }

    public void setMax_orang(String max_orang) {
        this.max_orang = max_orang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }


    public String getUkuran_kamar() {
        return ukuran_kamar;
    }

    public void setUkuran_kamar(String ukuran_kamar) {
        this.ukuran_kamar = ukuran_kamar;
    }

    public String getSarapan() {
        return sarapan;
    }

    public void setSarapan(String sarapan) {
        this.sarapan = sarapan;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getTipe_kasur() {
        return tipe_kasur;
    }

    public void setTipe_kasur(String tipe_kasur) {
        this.tipe_kasur = tipe_kasur;
    }

    public Double getHarga_kamar() {
        return harga_kamar;
    }

    public void setHarga_kamar(Double harga_kamar) {
        this.harga_kamar = harga_kamar;
    }

    public String getDeskripsi_kamar() {
        return deskripsi_kamar;
    }

    public void setDeskripsi_kamar(String deskripsi_kamar) {
        this.deskripsi_kamar = deskripsi_kamar;
    }


    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }
}

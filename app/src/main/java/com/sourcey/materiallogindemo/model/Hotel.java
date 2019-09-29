package com.sourcey.materiallogindemo.model;

public class Hotel {
    private String id_hotel;
    private String name_hotel;
    private String alamat;
    private int rating;
    private int stok_kamar;
    private Double harga_per_malam;
    private String detail_informasi;
    private String max_orang;
    private String sarapan;
    private String ukuran_kamar;
    private String wifi;
    private String refundable;
    private  String foto;

    public Hotel(String id_hotel, String name_hotel, String alamat, int rating, int stok_kamar, Double harga_per_malam, String detail_informasi, String max_orang, String sarapan, String ukuran_kamar, String wifi, String refundable, String foto) {
        this.id_hotel = id_hotel;
        this.name_hotel = name_hotel;
        this.alamat = alamat;
        this.rating = rating;
        this.stok_kamar = stok_kamar;
        this.harga_per_malam = harga_per_malam;
        this.detail_informasi = detail_informasi;
        this.max_orang = max_orang;
        this.sarapan = sarapan;
        this.ukuran_kamar = ukuran_kamar;
        this.wifi = wifi;
        this.refundable = refundable;
        this.foto = foto;
    }

    public Hotel() {

    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getName_hotel() {
        return name_hotel;
    }

    public void setName_hotel(String name_hotel) {
        this.name_hotel = name_hotel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStok_kamar() {
        return stok_kamar;
    }

    public void setStok_kamar(int stok_kamar) {
        this.stok_kamar = stok_kamar;
    }

    public Double getHarga_per_malam() {
        return harga_per_malam;
    }

    public void setHarga_per_malam(Double harga_per_malam) {
        this.harga_per_malam = harga_per_malam;
    }

    public String getDetail_informasi() {
        return detail_informasi;
    }

    public void setDetail_informasi(String detail_informasi) {
        this.detail_informasi = detail_informasi;
    }

    public String getMax_orang() {
        return max_orang;
    }

    public void setMax_orang(String max_orang) {
        this.max_orang = max_orang;
    }

    public String getSarapan() {
        return sarapan;
    }

    public void setSarapan(String sarapan) {
        this.sarapan = sarapan;
    }

    public String getUkuran_kamar() {
        return ukuran_kamar;
    }

    public void setUkuran_kamar(String ukuran_kamar) {
        this.ukuran_kamar = ukuran_kamar;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = refundable;
    }

}

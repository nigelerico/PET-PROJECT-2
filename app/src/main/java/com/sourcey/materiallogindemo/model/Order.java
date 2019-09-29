package com.sourcey.materiallogindemo.model;

public class Order {
    public Order(String id_reservasi, String id_hotel, String id_kamar, String nama_hotel, String nama_kamar, String checkin, String checkout, String tot_malam, String tot_tamu, String tot_kamar, String id_user, String nama, String email, String no_telp, String tujuan, Double total_harga, String metode, String status) {
        this.id_reservasi = id_reservasi;
        this.id_hotel = id_hotel;
        this.id_kamar = id_kamar;
        this.nama_hotel = nama_hotel;
        this.nama_kamar = nama_kamar;
        this.checkin = checkin;
        this.checkout = checkout;
        this.tot_malam = tot_malam;
        this.tot_tamu = tot_tamu;
        this.tot_kamar = tot_kamar;
        this.id_user = id_user;
        this.nama = nama;
        this.email = email;
        this.no_telp = no_telp;
        this.tujuan = tujuan;
        this.total_harga = total_harga;
        this.metode = metode;
        this.status = status;
    }

    private String id_reservasi;
    private String id_hotel;
    private String id_kamar;
    private String nama_hotel;
    private String nama_kamar;
    private String checkin;
    private String checkout;
    private String tot_malam;
    private String tot_tamu;
    private String tot_kamar;
    private String id_user;
    private String nama;
    private String email;
    private String no_telp;
    private String tujuan;
    private Double total_harga;
    private String metode;
    private String status;

    public Order() {

    }

    public String getId_reservasi() {
        return id_reservasi;
    }

    public void setId_reservasi(String id_reservasi) {
        this.id_reservasi = id_reservasi;
    }

    public String getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(String id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
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

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getTot_malam() {
        return tot_malam;
    }

    public void setTot_malam(String tot_malam) {
        this.tot_malam = tot_malam;
    }

    public String getTot_tamu() {
        return tot_tamu;
    }

    public void setTot_tamu(String tot_tamu) {
        this.tot_tamu = tot_tamu;
    }

    public String getTot_kamar() {
        return tot_kamar;
    }

    public void setTot_kamar(String tot_kamar) {
        this.tot_kamar = tot_kamar;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Double total_harga) {
        this.total_harga = total_harga;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

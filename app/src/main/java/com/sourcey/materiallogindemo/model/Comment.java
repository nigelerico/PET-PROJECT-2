package com.sourcey.materiallogindemo.model;

public class Comment {

    private int id;
    private int id_hotel;
    private int id_user;
    private String nama;
    private String comment;
    private String foto;

    public Comment() {
    }

    public Comment(int id, int id_hotel, int id_user, String nama, String comment, String foto) {
        this.id = id;
        this.id_hotel = id_hotel;
        this.id_user = id_user;
        this.nama = nama;
        this.comment = comment;
        this.foto = foto;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}

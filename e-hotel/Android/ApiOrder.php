<?php

//database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'root');
 define('DB_PASS', '');
 define('DB_NAME', 'db_hotel');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 //creating a query
 $stmt = $conn->prepare("SELECT * FROM tb_reservasi;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id_reservasi,$id_hotel, $id_kamar, $nama_hotel, $nama_kamar, $checkin, $checkout, $tot_malam, $tot_tamu, $tot_kamar, $id_user, $nama, $email, $no_telp, $tujuan, $total_harga, $metode, $status);
 
 $tb_reservasi = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id_reservasi'] = $id_reservasi; 
 $temp['id_hotel'] = $id_hotel; 
 $temp['id_kamar'] = $id_kamar; 
 $temp['nama_hotel'] = $nama_hotel; 
 $temp['nama_kamar'] = $nama_kamar; 
 $temp['checkin'] = $checkin;
 $temp['checkout'] = $checkout;
 $temp['tot_malam'] = $tot_malam; 
 $temp['tot_tamu'] = $tot_tamu;
 $temp['tot_kamar'] = $tot_kamar;
 $temp['id_user'] = $id_user;
 $temp['nama'] = $nama;
 $temp['email'] = $email;
 $temp['no_telp'] = $no_telp;
 $temp['tujuan'] = $tujuan;
 $temp['total_harga'] = $total_harga;
 $temp['metode'] = $metode;
 $temp['status'] = $status;
 array_push($tb_reservasi, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($tb_reservasi);
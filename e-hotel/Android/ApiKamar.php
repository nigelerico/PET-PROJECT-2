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
 $stmt = $conn->prepare("SELECT * FROM tb_kamar;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id_kamar,$id_hotel, $nama_hotel, $nama_kamar, $max_orang, $stok_kamar, $ukuran_kamar, $sarapan, $wifi, $tipe_kasur, $harga_kamar, $deskripsi_kamar, $foto1, $foto2);
 
 $tb_kamar = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id_kamar'] = $id_kamar; 
 $temp['id_hotel'] = $id_hotel; 
 $temp['nama_hotel'] = $nama_hotel; 
 $temp['nama_kamar'] = $nama_kamar; 
 $temp['max_orang'] = $max_orang; 
 $temp['stok_kamar'] = $stok_kamar; 
 $temp['ukuran_kamar'] = $ukuran_kamar;
 $temp['sarapan'] = $sarapan;
 $temp['wifi'] = $wifi; 
 $temp['tipe_kasur'] = $tipe_kasur;
 $temp['harga_kamar'] = $harga_kamar;
 $temp['deskripsi_kamar'] = $deskripsi_kamar;
 $temp['foto1'] = $foto1;
 $temp['foto2'] = $foto2;

 array_push($tb_kamar, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($tb_kamar);
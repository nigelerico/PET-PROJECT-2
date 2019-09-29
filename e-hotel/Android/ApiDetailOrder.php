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
 
 $id_reservasi = $_POST['id_reservasi'];
 $id_kamar = $_POST['id_kamar'];
 //creating a query
 if($conn){
     $query = "select * from tb_reservasi where id_reservasi='$id_reservasi'";
     $hasil = mysqli_query($conn,$query);
     
     $data = array();
     while($row=$hasil->fetch_assoc()){
         $data[]=$row;
     }
     
     echo json_encode($data);
 }
 else {
     die("CHECK YOUR NETWORK");
 }
 ?>
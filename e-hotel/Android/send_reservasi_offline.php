<?php
if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $id_hotel = $_POST['id_hotel'];
   $id_kamar = $_POST['id_kamar'];
   $nama_hotel = $_POST['nama_hotel'];
   $nama_kamar = $_POST['nama_kamar'];
   $checkin = $_POST['checkin'];
   $checkout = $_POST['checkout'];
   $tot_malam = $_POST['tot_malam'];
   $tot_tamu = $_POST['tot_tamu'];
   $tot_kamar = $_POST['tot_kamar'];
   $id_user = "0";
   $nama = $_POST['nama'];
   $email = "-";
   $no_telp = $_POST['no_telp'];
   $tujuan = $_POST['tujuan'];
   $total_harga = $_POST['total_harga'];
   $metode = "Offline";
   $status = "Menunggu pembayaran";

   require_once('dbConnect.php');

        
         $sql = "INSERT INTO tb_reservasi (id_reservasi,id_hotel,id_kamar,nama_hotel,nama_kamar,checkin,checkout,tot_malam,tot_tamu,tot_kamar,id_user,nama,email,no_telp,tujuan,total_harga,metode,status) VALUES(0,'$id_hotel','$id_kamar','$nama_hotel','$nama_kamar','$checkin','$checkout','$tot_malam','$tot_tamu','$tot_kamar','$id_user','$nama','$email','$no_telp','$tujuan','$total_harga','$metode','$status')";
         if(mysqli_query($con,$sql)) {
           $response["value"] = 1;
           $response["message"] = "Sukses, silahkan cek di Menu MyOrder";
           echo json_encode($response);
         } else {
           $response["value"] = 0;
           $response["message"] = "oops! Coba lagi!";
           echo json_encode($response);
         }

   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi!";
  echo json_encode($response);
}
<?php 
	header('Access-Control-Allow-Origin:*');
	header('Access-Control-Allow-Methods:PUT,GET,DELETE,POST,OPTIONS');
	header('Access-Control-Allow-Headers:Content-Type,x-xsrf-token');

	$host 	= "localhost";
	$user 	= "root";
	$pass 	= "";
	$db		= "db_hotel";


	$connect = mysqli_connect ($host,$user,$pass,$db);

	if($connect){
		//$data = json_encode(file_get_contents('php://input'));

		$id_kamar =  $_POST['id_kamar'];
        $status = "Reserved";
		// echo $nim.'-'.$nama.'-'.$alamat.'-'.$jurusan;

		//kalo sdh buat query inserts
		
			$query = "UPDATE tb_kamar SET status='$status' where id_kamar='$id_kamar'";
			$result = mysqli_query($connect, $query);
			if($result) {
				echo "success";
			}
			else {
				echo "failed";
			}
		
	}
	else {
		die("check your network or server configuration");
	}
?>
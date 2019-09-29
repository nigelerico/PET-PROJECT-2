<?php 
	
	header('Access-Control-Allow-Origin:*');

	$host 	= "localhost";
	$user 	= "root";
	$pass 	= "";
	$db		= "db_hotel";

	$connect = mysqli_connect ($host,$user,$pass,$db);
 
	if($connect){
		$query = "SELECT tb_hotel.*, SUM(stok_kamar)as stok_kamar, max_orang FROM tb_kamar 
            LEFT JOIN tb_hotel on tb_kamar.id_hotel = tb_hotel.id_hotel
            GROUP BY id_hotel";
		$hasil = mysqli_query($connect,$query);

		$data = array();
		while ($row=$hasil->fetch_assoc()){
			$data[]=$row;
		}
		echo json_encode($data);
	}
	else {
		die("check your network or server configuration");
	}
?>
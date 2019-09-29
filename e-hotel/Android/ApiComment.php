<?php 
	
	header('Access-Control-Allow-Origin:*');

	$host 	= "localhost";
	$user 	= "root";
	$pass 	= "";
	$db		= "db_hotel";

	$connect = mysqli_connect ($host,$user,$pass,$db);
 
	if($connect){
		$query = "SELECT * FROM tb_comment";
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
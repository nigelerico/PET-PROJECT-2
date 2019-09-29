<?php

	//=================== KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI RI UNREMARK ========
	include_once "koneksi.php";

	class usr{}
	
	$email = $_POST["email"];
	$password = md5($_POST["password"]);
	
	if ((empty($email)) || (empty($password))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom tidak boleh kosong"; 
		die(json_encode($response));
	}
	
	$query = mysqli_query($con, "SELECT * FROM tb_staff WHERE email='$email' AND password='$password'");
	
	$row = mysqli_fetch_array($query);
	
	if (!empty($row)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row['nama'];
		$response->id_staff = $row['id_staff'];
		$response->nama = $row['nama'];
		$response->email = $row['email'];
		$response->id_hotel = $row['id_hotel'];
		$response->nama_hotel = $row['nama_hotel'];
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Email atau password salah";
		die(json_encode($response));
	}
	
	mysqli_close($con);

?>
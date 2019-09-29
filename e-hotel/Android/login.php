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
	
	$query = mysqli_query($con, "SELECT * FROM tb_user WHERE email='$email' AND password='$password'");
	
	$row = mysqli_fetch_array($query);
	
	if (!empty($row)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row['nama'];
		$response->id_user = $row['id_user'];
		$response->level = $row['level'];
		$response->nama = $row['nama'];
		$response->alamat = $row['alamat'];
		$response->email = $row['email'];
		$response->no_telp = $row['no_telp'];
		$response->kota_domisili = $row['kota_domisili'];
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Email atau password salah";
		die(json_encode($response));
	}
	
	mysqli_close($con);

?>
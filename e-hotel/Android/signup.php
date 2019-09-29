<?php
/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	include_once "koneksi.php";

	class usr{}

	$password = md5($_POST["password"]);
	$confirm_password = md5($_POST["confirm_password"]);
	$level = 'member';
	$nama = $_POST["nama"];
	$alamat = $_POST["alamat"];
	$email = $_POST["email"];
	$no_telp = $_POST["no_telp"];
	$foto = 'foto/default.jpg';
	$kota_domisili = $_POST["kota_domisili"];
	
	if ((empty($nama))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom nama tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($email))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom email tidak boleh kosong";
		die(json_encode($response));
	}  else if ((empty($password))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom password tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($confirm_password)) || $password != $confirm_password) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Konfirmasi password tidak sama";
		die(json_encode($response));
	} else {
		if (!empty($email) && !empty($nama) && $password == $confirm_password){
			$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM tb_user WHERE email='".$email."'"));

			if ($num_rows == 0){
				$query = mysqli_query($con, "INSERT INTO tb_user (id_user, password, level, nama, alamat, email, no_telp, foto, kota_domisili ) VALUES(0,'".$password."','".$level."','".$nama."','".$alamat."','".$email."','".$no_telp."','".$foto."','".$kota_domisili."')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
					$response->message = "Username sudah ada";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "Username sudah ada";
				die(json_encode($response));
			}
		}
	}

	mysqli_close($con);
	
?>
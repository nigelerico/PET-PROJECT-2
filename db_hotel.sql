-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 29, 2019 at 02:05 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_bukti_pembayaran`
--

CREATE TABLE `tb_bukti_pembayaran` (
  `id_bukti` int(11) NOT NULL,
  `id_reservasi` int(11) NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `confirm` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_bukti_pembayaran`
--

INSERT INTO `tb_bukti_pembayaran` (`id_bukti`, `id_reservasi`, `gambar`, `confirm`) VALUES
(4, 3, '1566645703426.png', '-'),
(5, 16, '1568397566592.png', '-'),
(6, 16, '1568764668258.png', '-');

-- --------------------------------------------------------

--
-- Table structure for table `tb_comment`
--

CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama` varchar(191) NOT NULL,
  `comment` varchar(191) NOT NULL,
  `foto` varchar(191) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_comment`
--

INSERT INTO `tb_comment` (`id`, `id_hotel`, `id_user`, `nama`, `comment`, `foto`) VALUES
(1, 2, 9, 'Nigel Erico Pangestu', 'Hotelnya Nyaman Sekali', ''),
(2, 2, 2, 'Bram Kareem', 'Hotelnya Bau Busuk !!!!!!', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_hotel`
--

CREATE TABLE `tb_hotel` (
  `id_hotel` int(11) NOT NULL,
  `nama_hotel` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` text COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(11) NOT NULL,
  `harga_per_malam` double NOT NULL,
  `detail_informasi` text COLLATE utf8_unicode_ci NOT NULL,
  `sarapan` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL,
  `ukuran_kamar` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `wifi` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL,
  `refundable` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL,
  `foto` varchar(191) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tb_hotel`
--

INSERT INTO `tb_hotel` (`id_hotel`, `nama_hotel`, `alamat`, `rating`, `harga_per_malam`, `detail_informasi`, `sarapan`, `ukuran_kamar`, `wifi`, `refundable`, `foto`) VALUES
(2, 'Hotel Margosuko', 'Jl. KH Ahmad Dahlan No.40, Sukoharjo', 3, 175000, 'Nyaman sekali', 'yes', '4x3', 'yes', 'yes', 'hotel-margosuko.jpg'),
(3, 'RedDoorz Syariah near Stasiun Kepanjen', 'l. Anjasmoro No.37, Banurejo, Kepanjen', 4, 99000, 'Enak bet dah', 'no', '4x3', 'yes', 'no', 'RedDoorz-Hotel.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `id_kamar` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `nama_hotel` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nama_kamar` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `max_orang` int(5) NOT NULL,
  `stok_kamar` int(11) NOT NULL,
  `ukuran_kamar` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `sarapan` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL,
  `wifi` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL,
  `tipe_kasur` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `harga_kamar` double NOT NULL,
  `deskripsi_kamar` text COLLATE utf8_unicode_ci NOT NULL,
  `foto1` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `foto2` varchar(191) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tb_kamar`
--

INSERT INTO `tb_kamar` (`id_kamar`, `id_hotel`, `nama_hotel`, `nama_kamar`, `max_orang`, `stok_kamar`, `ukuran_kamar`, `sarapan`, `wifi`, `tipe_kasur`, `harga_kamar`, `deskripsi_kamar`, `foto1`, `foto2`) VALUES
(1, 2, 'Hotel Margosuko', 'Deluxe Room', 2, 1, '4x4', 'yes', 'yes', 'King Bed', 350000, 'Kamar nyaman dan mewah.', 'room1.jpg', 'room2.jpg'),
(2, 2, 'Hotel Margosuko', 'Standart Room', 2, 1, '4x4', 'yes', 'yes', 'King Bed', 350000, 'Kamar nyaman dan mewah.', 'room3.jpg', 'room4.jpg'),
(3, 3, 'Red Dorz', 'Deluxe Room', 10, 4, '10x10', 'yes', 'yes', 'King Bed', 350000, 'bagus', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_reservasi`
--

CREATE TABLE `tb_reservasi` (
  `id_reservasi` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `id_kamar` int(11) NOT NULL,
  `nama_hotel` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nama_kamar` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `checkin` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `checkout` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tot_malam` int(3) NOT NULL,
  `tot_tamu` int(3) NOT NULL,
  `tot_kamar` int(3) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `no_telp` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `tujuan` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `total_harga` double NOT NULL,
  `metode` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tb_reservasi`
--

INSERT INTO `tb_reservasi` (`id_reservasi`, `id_hotel`, `id_kamar`, `nama_hotel`, `nama_kamar`, `checkin`, `checkout`, `tot_malam`, `tot_tamu`, `tot_kamar`, `id_user`, `nama`, `email`, `no_telp`, `tujuan`, `total_harga`, `metode`, `status`) VALUES
(3, 2, 1, 'Hotel Margosuko', 'Deluxe Room', 'Rab, 14 Agt 19', 'Kam, 15 Agt 19', 1, 1, 1, 3, 'Advent Christopher', 'adventchristopher21@gmail.com', '082139130058', 'Bisnis', 350000, 'Tranfer BCA', 'kadaluarsa'),
(4, 2, 1, 'Hotel Margosuko', 'Deluxe Room', 'Jum, 16 Agt 19', 'Sab, 17 Agt 19', 1, 1, 1, 6, 'Dadang', 'dadang@gmail.com', '08123262605', 'Bisnis', 350000, 'Tranfer BCA', 'kadaluarsa'),
(16, 2, 1, 'Hotel Margosuko', 'Deluxe Room', 'Sab, 14 Sep 19', 'Min, 15 Sep 19', 1, 1, 1, 9, 'Nigel Erico Pangestu', 'nigelerico1@gmail.com', '089654252938', 'Liburan', 350000, 'Tranfer BCA', 'Menunggu konfirmasi'),
(19, 2, 2, 'Hotel Margosuko', 'Deluxe Room', 'Min, 15 Sep 19', 'Sen, 16 Sep 19', 1, 1, 1, 0, 'tes', '-', '5888888843', 'Bisnis', 350000, 'Offline', 'checkout'),
(20, 2, 2, 'Hotel Margosuko', 'Deluxe Room', 'Min, 15 Sep 19', 'Min, 15 Sep 19', 1, 2, 2, 0, 'tes', '-', '123', 'Bisnis', 700000, 'Offline', 'Menunggu konfirmasi'),
(21, 2, 1, 'Hotel Margosuko', 'Deluxe Room', 'Kam, 26 Sep 19', 'Jum, 27 Sep 19', 1, 1, 1, 9, 'Nigel Erico Pangestu', 'nigelerico1@gmail.com', '089654252938', 'Liburan', 350000, 'Tranfer BCA', 'Menunggu pembayaran');

--
-- Triggers `tb_reservasi`
--
DELIMITER $$
CREATE TRIGGER `Kurang Stok` AFTER INSERT ON `tb_reservasi` FOR EACH ROW BEGIN
UPDATE tb_kamar set stok_kamar=stok_kamar-NEW.tot_kamar
WHERE id_kamar=NEW.id_kamar;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Tambah Stok` AFTER UPDATE ON `tb_reservasi` FOR EACH ROW BEGIN
IF NEW.status = 'checkout' OR NEW.status = 'kadaluarsa' THEN
UPDATE tb_kamar set stok_kamar=stok_kamar+NEW.tot_kamar 
WHERE id_kamar = NEW.id_kamar;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_staff`
--

CREATE TABLE `tb_staff` (
  `id_staff` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `nama_hotel` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_staff`
--

INSERT INTO `tb_staff` (`id_staff`, `nama`, `email`, `password`, `id_hotel`, `nama_hotel`) VALUES
(1, 'Bram Kareem', 'bram@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 2, 'Hotel Margosuko');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` enum('member','admin','staff') NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` text NOT NULL,
  `email` varchar(50) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `kota_domisili` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `password`, `level`, `nama`, `alamat`, `email`, `no_telp`, `foto`, `kota_domisili`) VALUES
(1, '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Administrator', 'Malang', 'admin@hotel.com', '0819999999', '', 'Jakarta'),
(2, 'aa08769cdcb26674c6706093503ff0a3', 'member', 'Bram Kareem', 'Ngantang', 'bram@gmail.com', '0811111111', '', 'Borneo'),
(3, '827ccb0eea8a706c4c34a16891f84e7b', 'member', 'Advent Christopher', 'Jl. Ikan Layur IV No.23 Malang', 'adventchristopher21@gmail.com', '082139130058', 'foto/default.jpg', 'Kota Malang'),
(4, '827ccb0eea8a706c4c34a16891f84e7b', 'member', 'tito', 'malang', 'tito@gmail.com', '082139130858', 'foto/default.jpg', 'malang'),
(5, '827ccb0eea8a706c4c34a16891f84e7b', 'member', 'toto', 'malang', 'toto@gmail.com', '082139130058', 'foto/default.jpg', 'malang'),
(6, '827ccb0eea8a706c4c34a16891f84e7b', 'member', 'Dadang', 'Malang', 'dadang@gmail.com', '08123262605', 'foto/default.jpg', 'malang'),
(7, '827ccb0eea8a706c4c34a16891f84e7b', 'member', 'tester', 'test', 'test@email.com', '088888888', 'foto/default.jpg', 'jakarta'),
(8, '827ccb0eea8a706c4c34a16891f84e7b', 'staff', 'Staff Margosuko', 'Jl. KH Ahmad Dahlan No.40, Sukoharjo', 'staff.margosuko@gmail.com', '(0341) 325270', '', 'Malang'),
(9, '8bf8d5a635e47c5bbd6c4d25b3012441', 'member', 'Nigel Erico Pangestu', 'JL Emas no 118', 'nigelerico1@gmail.com', '089654252938', 'foto/default.jpg', 'Malang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bukti_pembayaran`
--
ALTER TABLE `tb_bukti_pembayaran`
  ADD PRIMARY KEY (`id_bukti`);

--
-- Indexes for table `tb_comment`
--
ALTER TABLE `tb_comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_hotel`
--
ALTER TABLE `tb_hotel`
  ADD PRIMARY KEY (`id_hotel`);

--
-- Indexes for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  ADD PRIMARY KEY (`id_kamar`);

--
-- Indexes for table `tb_reservasi`
--
ALTER TABLE `tb_reservasi`
  ADD PRIMARY KEY (`id_reservasi`);

--
-- Indexes for table `tb_staff`
--
ALTER TABLE `tb_staff`
  ADD PRIMARY KEY (`id_staff`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_bukti_pembayaran`
--
ALTER TABLE `tb_bukti_pembayaran`
  MODIFY `id_bukti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_comment`
--
ALTER TABLE `tb_comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_hotel`
--
ALTER TABLE `tb_hotel`
  MODIFY `id_hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  MODIFY `id_kamar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_reservasi`
--
ALTER TABLE `tb_reservasi`
  MODIFY `id_reservasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tb_staff`
--
ALTER TABLE `tb_staff`
  MODIFY `id_staff` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

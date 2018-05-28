-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2018 at 09:18 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `infinitycourse`
--

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `kd_kelas` char(4) NOT NULL,
  `kapasitas` int(11) NOT NULL,
  `jadwal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`kd_kelas`, `kapasitas`, `jadwal`) VALUES
('K101', 10, 'Senin'),
('K102', 14, 'Senin'),
('K103', 16, 'Senin'),
('K201', 20, 'Selasa'),
('K202', 22, 'Kamis'),
('K205', 22, 'Sabtu'),
('K211', 8, 'Kamis'),
('K301', 18, 'Rabu'),
('K302', 22, 'Selasa'),
('K400', 24, 'Senin'),
('K401', 23, 'Jumat');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` char(8) NOT NULL,
  `nama_mhs` varchar(40) NOT NULL,
  `jk` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `jurusan` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama_mhs`, `jk`, `semester`, `jurusan`) VALUES
('14116125', 'Rani', 'Perempuan', 2, 'Mesin'),
('14116129', 'Reno', 'Laki-Laki', 2, 'Mesin'),
('14116161', 'Yolla', 'Perempuan', 4, 'IF'),
('14116190', 'Parsaoran', 'Perempuan', 2, 'IF'),
('14116199', 'Boy William', 'Laki-Laki', 4, 'akuntansi'),
('21116069', 'Marudut', 'Laki-Laki', 4, 'Teknik Sipil'),
('23116123', 'Andre', 'Laki-Laki', 2, 'Geomatika'),
('24116126', 'Danu', 'Laki-Laki', 2, 'Kimia'),
('25116110', 'Lety', 'Perempuan', 2, 'Biologi');

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `kd_mk` char(4) NOT NULL,
  `nama_mk` varchar(40) NOT NULL,
  `sks` int(11) NOT NULL,
  `harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`kd_mk`, `nama_mk`, `sks`, `harga`) VALUES
('FI01', 'Fisika Dasar 1', 4, 41550),
('FI02', 'Fisika Dasar 2', 4, 43000),
('IF01', 'Strategi Algoritma', 3, 45000),
('IF10', 'TBFO', 3, 47000),
('IF11', 'Logika Informatika', 2, 50000),
('IF21', 'PBO', 3, 47000),
('IF22', 'Probabilitas dan Statistika', 3, 51000),
('IF33', 'Matematika Diskrit', 3, 51000),
('KI01', 'Kmia Dasar', 3, 39900),
('MA01', 'Matematika Dasar', 4, 40000),
('MA02', 'Matematika Dasar 4', 4, 40000),
('SI01', 'Mekanika Tanah', 4, 35000);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `idpegawai` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(225) NOT NULL,
  `nama_pgw` varchar(40) NOT NULL,
  `jk` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`idpegawai`, `username`, `password`, `nama_pgw`, `jk`) VALUES
(1, 'jose', '662eaa47199461d01a623884080934ab', 'Jose', 'Laki-laki'),
(2, 'hemat', 'f99051709152243f0fb7d7b2979fcac3', 'Hemat', 'Laki-laki'),
(3, 'joddy', '02778d28932b27a0157c6ed17fab239d', 'Joddy', 'Laki-laki'),
(4, 'sitanggang', '70962a4b8c8c054101b51442600fd27e', 'Sitanggang', 'Laki-laki'),
(5, 'yoas', 'cf7bf39052a2659c18a95e05271a8f3c', 'Yoas', 'Laki-Laki'),
(6, 'boy', '1a699ad5e06aa8a6db3bcf9cfb2f00f2', 'boy', 'Laki-Laki'),
(7, 'faizal', '22bf59bcca5b77263b4021cf4695288d', 'faizal', 'Laki-Laki'),
(8, 'pardede', '576dd9b01ab87e833c5d83e2674e08e5', 'Pardede', 'Laki-Laki'),
(9, 'agus', 'fdf169558242ee051cca1479770ebac3', 'agus', 'Laki-Laki'),
(10, 'parsaoran', '827ccb0eea8a706c4c34a16891f84e7b', 'Parsaoran', 'Laki-Laki');

-- --------------------------------------------------------

--
-- Table structure for table `pengajar`
--

CREATE TABLE `pengajar` (
  `id_pengajar` int(4) UNSIGNED ZEROFILL NOT NULL,
  `nama_pengajar` varchar(40) NOT NULL,
  `jk` varchar(10) NOT NULL,
  `pendidikan` varchar(40) NOT NULL,
  `kd_mk` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengajar`
--

INSERT INTO `pengajar` (`id_pengajar`, `nama_pengajar`, `jk`, `pendidikan`, `kd_mk`) VALUES
(0004, 'Jose Sitanggang', 'Laki-Laki', 'S1', 'IF22'),
(0005, 'Jose Sitanggang', 'Laki-Laki', 'S1', 'IF33'),
(0006, 'Jose Sitanggang', 'Laki-Laki', 'S1', 'IF01'),
(0007, 'Hemat Simamora', 'Laki-Laki', 'S1', 'IF10'),
(0008, 'Hemat Simamora', 'Laki-Laki', 'S1', 'IF21'),
(0009, 'Hemat Simamora', 'Laki-Laki', 'S1', 'KI01'),
(0010, 'Joddy Siregar', 'Laki-Laki', 'S1', 'IF01'),
(0011, 'Joddy Siregar', 'Laki-Laki', 'S1', 'IF33'),
(0012, 'Joddy Siregar', 'Laki-Laki', 'S1', 'MA02'),
(0013, 'Ivan', 'Laki-Laki', 'S1', 'FI01'),
(0014, 'Ivan', 'Laki-Laki', 'S1', 'FI02'),
(0015, 'Firman', 'Laki-Laki', 'S1', 'MA02'),
(0016, 'Firman', 'Laki-Laki', 'S1', 'MA01'),
(0017, 'Yoas', 'Laki-Laki', 'S1', 'IF11'),
(0018, 'Yoas', 'Laki-Laki', 'S1', 'IF21'),
(0019, 'Jojo', 'Laki-Laki', 'S3', 'IF11'),
(0020, 'Wini', 'Perempuan', 'S2', 'SI01');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `kd_transaksi` int(11) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `nim` char(8) NOT NULL,
  `id_pengajar` int(4) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`kd_transaksi`, `tgl_transaksi`, `nim`, `id_pengajar`) VALUES
(24, '2018-05-18', '24116126', 0013),
(25, '2018-05-18', '25116110', 0004),
(26, '2018-05-18', '21116069', 0014),
(27, '2018-05-18', '25116110', 0012),
(29, '2018-05-18', '14116199', 0013),
(30, '2018-05-18', '14116129', 0004),
(31, '2018-05-18', '14116125', 0017),
(32, '2018-05-18', '14116161', 0005),
(33, '2018-05-19', '14116190', 0004);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`kd_kelas`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`kd_mk`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`idpegawai`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `username_2` (`username`);

--
-- Indexes for table `pengajar`
--
ALTER TABLE `pengajar`
  ADD PRIMARY KEY (`id_pengajar`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`kd_transaksi`),
  ADD KEY `id_pengajar` (`id_pengajar`),
  ADD KEY `nim` (`nim`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `idpegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pengajar`
--
ALTER TABLE `pengajar`
  MODIFY `id_pengajar` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `kd_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pengajar`
--
ALTER TABLE `pengajar`
  ADD CONSTRAINT `pengajar_ibfk_1` FOREIGN KEY (`kd_mk`) REFERENCES `matakuliah` (`kd_mk`) ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pengajar`) REFERENCES `pengajar` (`id_pengajar`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

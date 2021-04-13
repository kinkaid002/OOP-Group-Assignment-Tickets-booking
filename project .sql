-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 13, 2021 at 09:46 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Gender` tinyint(1) NOT NULL,
  `Age` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Phone_No` varchar(10) NOT NULL,
  `Address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`id`, `Name`, `Gender`, `Age`, `Username`, `Password`, `Phone_No`, `Address`) VALUES
(1, 'Abhiyant Gwalani', 0, 19, 'abhiyant', 'e10adc3949ba59abbe56e057f20f883e', '1010101010', 'jjjjjjjjjjjjjjj'),
(2, 'Nabeel Khan', 0, 55, 'nabeel', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', '1111111111', 'IIIIIIIIIIIII'),
(3, 'Nitesh Dani', 0, 80, 'nitesh', '875f26fdb1cecf20ceb4ca028263dec6', '2222222222', 'iiiiiiiiiiiiiii'),
(4, 'Varun Kalbhore', 0, 12, 'varun', 'c1f68ec06b490b3ecb4066b1b13a9ee9', '3333333333', 'nnnnnnn'),
(13, 'Arvind Kumar', 1, 101, 'arvindkejriwal', '6b7850830ec34ccae7cf2179ff8b509e', '1234567899', 'jbdvhkbfdjhbzvx');

-- --------------------------------------------------------

--
-- Table structure for table `flight_details`
--

CREATE TABLE `flight_details` (
  `flight id` int(11) NOT NULL,
  `flight number` varchar(6) NOT NULL,
  `flight from` varchar(50) NOT NULL,
  `flight to` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Departure Time` time NOT NULL,
  `Duration` time NOT NULL,
  `Name of the Airline` varchar(50) NOT NULL,
  `Available Seats` int(3) NOT NULL,
  `Price` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flight_details`
--

INSERT INTO `flight_details` (`flight id`, `flight number`, `flight from`, `flight to`, `Date`, `Departure Time`, `Duration`, `Name of the Airline`, `Available Seats`, `Price`) VALUES
(1, 'DEMU02', 'DELHI', 'MUMBAI', '2021-05-02', '12:31:00', '01:05:00', 'SpiceJet', 17, 2200),
(2, 'DEMU00', 'DELHI', 'MUMBAI', '2021-05-01', '10:00:00', '01:10:00', 'IndiGo', 30, 2000),
(3, 'MUGO07', 'MUMBAI', 'GOA', '2021-05-02', '17:17:00', '02:09:00', 'SpiceJet', 32, 2900),
(4, 'NGGU11', 'NAGPUR', 'GUJARAT', '2021-04-17', '21:02:00', '03:33:00', 'Air India', 22, 3300),
(5, 'BGKE22', 'BANGALORE', 'KERALA', '2021-04-19', '09:20:00', '00:42:00', 'SpiceJet', 13, 1800),
(6, 'NGHD23', 'NAGPUR', 'HYDERABAD', '2021-04-16', '14:55:24', '01:29:00', 'SpiceJet', 37, 2500),
(7, 'HDKE22', 'HYDERABAD', 'KERALA', '2021-04-24', '05:22:00', '01:02:30', 'Air India', 22, 3600),
(8, 'KSKK01', 'KASHMIR', 'KANYAKUMARI', '2021-04-30', '10:59:20', '05:59:20', 'IndiGo', 33, 5000),
(9, 'PUGU90', 'PUNJAB', 'GUJARAT', '2021-04-24', '22:00:41', '01:00:41', 'SpiceJet', 13, 2300),
(10, 'HDGO11', 'HYDERABAD', 'GOA', '2021-04-23', '16:02:00', '04:11:00', 'IndiGo', 29, 4999),
(11, 'MUOR00', 'MUMBAI', 'ORISSA', '2021-05-05', '23:06:37', '04:06:37', 'IndiGo', 41, 7000),
(12, 'CHHD31', 'CHANDIGARH', 'HYDERABAD', '2021-04-25', '21:11:52', '05:11:52', 'Air India', 44, 3300),
(13, 'NGDE09', 'NAGPUR', 'DELHI', '2021-04-17', '19:13:20', '01:18:20', 'SpiceJet', 30, 2999),
(14, 'MUGU22', 'MUMBAI', 'GUJARAT', '2021-04-27', '05:20:00', '03:00:00', 'Air India', 38, 3999),
(15, 'KSDE88', 'KASHMIR', 'DELHI', '2021-04-20', '20:15:00', '04:18:33', 'IndiGo', 51, 6999);

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `rating` double NOT NULL,
  `features` varchar(255) NOT NULL,
  `dist` varchar(255) NOT NULL,
  `cost` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `city`, `name`, `rating`, `features`, `dist`, `cost`) VALUES
(1, 'Goa', 'Holiday Inn Resort Goa', 4.6, 'Private Beach,Spa,Lounge', '0.4km', 'Rs 6,600'),
(2, 'Goa', 'Hotel Baga Grande', 4.9, 'Tours and Treks,Lounge,Nearby Beach', '0.49km', 'Rs 1,305'),
(3, 'Goa', 'The Lalit Golf & Spa Resort', 4.3, 'Free Parking,Kids Play Area,Outdoor Sports', '15km', 'Rs 7,499'),
(4, 'Goa', 'ITC Grand Goa', 4.4, 'Free Parking,Kids Play Area,Childcare Services', '13.5km', 'Rs 11,500'),
(5, 'New Delhi', 'Country Inn and Suites by Radisson', 4.2, 'Spa,Lounge,Gym', '27.5km', 'Rs 2,277'),
(6, 'New Delhi', 'Eros Hotel New Dehli', 4.2, 'Golf,Lounge,Butler Services', '16.5km', 'Rs 3,299'),
(7, 'Nagpur', 'Le Meridien Nagpur', 4.2, 'Spa, Indoor Games,Outdoor Sports', '18.8km', 'Rs 4,199'),
(8, 'Nagpur', 'Radisson Blu Hotel Nagpur', 4.3, 'Spa,Lounge,Gym', '3.4km', 'Rs 4,500'),
(9, 'Nagpur', 'The Pride Hotel Nagpur', 3.7, 'Spa,Lounge,Gym,Free Breakfast ', '0.4km', 'Rs 3,499'),
(10, 'Nagpur', 'Ashirwad Homestay', 4.2, 'Kitchenette ,Care Taker ,Living Room', '4.9km', 'Rs 1,000'),
(11, 'Nagpur', 'MatoShree', 3.5, 'Doctor on Call, Kitchenette ,Care Taker', '4.3km', 'Rs 1,128'),
(12, 'Bengaluru', 'Fortune Select JP Cosmos', 4.1, 'Gym , Business Center, Jacuzzi', '25km', 'Rs 2,080'),
(13, 'Bengaluru', 'Vividus', 4.1, 'Gym , Free Parking , Spa', '30km', 'Rs 1,904'),
(14, 'Pune', 'The Orchid Hotel,Hinjewadi,Pune', 4.1, 'Indoor Games,Gym,Conference Rooms', '10.5km', 'Rs 2,499'),
(15, 'Pune', '7 Apple Hotel', 4.2, 'Free Parking, Business Center,Conference Room', '3.1km', 'Rs 2,007'),
(16, 'Pune', 'Oyo Home 49984 Pleasant Stay Baner', 4.2, 'CareTaker, Power Backup ,Elevator/Lift', '10km', 'Rs 1,184'),
(17, 'Hyderabad', 'The Park Hyderabad ', 3.8, 'Jacuzzi, Yoga, Spa', '8.6km', 'Rs 2,212'),
(18, 'Hyderabad', 'Marigold By Green Park', 4.4, 'Lounge, Gym, Swimming Pool', '4.5km', 'Rs 2,296'),
(19, 'Hyderabad', 'Hotel Saptagiri Hyderabad', 3.9, 'Free Parking, Business Center, Conference Room', '8.6km', 'Rs 1,672'),
(20, 'Hyderabad', 'Royalton Hyderabad Abids', 4.1, 'Spa, Lounge, Gym', '0.24km', 'Rs 1,520');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credentials`
--
ALTER TABLE `credentials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flight_details`
--
ALTER TABLE `flight_details`
  ADD PRIMARY KEY (`flight id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credentials`
--
ALTER TABLE `credentials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `flight_details`
--
ALTER TABLE `flight_details`
  MODIFY `flight id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 02-06-2023 a las 02:30:46
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `last_log` date DEFAULT NULL,
  `session` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`mail`, `pass`, `name`, `last_log`, `session`) VALUES
('banner@doctor.com', '123', 'Bruce Banner', '2023-05-31', 982123008),
('parker@doctor.com', '123', 'Peter Parker', '2023-06-02', 956200403),
('rogers@doctor.com', '123', 'Steve Rogers', '2023-05-30', 40688472),
('stark@doctor.com', '123', 'Tony Stark', '2023-06-02', 673779416),
('strange@doctor.com', '123', 'Stephen Strange', '2023-06-02', 580183682);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `tmax` double DEFAULT NULL,
  `tmin` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `tmax`, `tmin`) VALUES
(1, 'Aspirin', 30, 15),
(2, 'Paracetamol', 60, 30),
(3, 'Ibuprofen', 45, 20),
(4, 'Amoxicillin', 90, 45),
(5, 'Loratadine', 15, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `mail` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `patient`
--

INSERT INTO `patient` (`mail`, `name`) VALUES
('draco@paciente.com', 'Draco Malfoy'),
('harry@paciente.com', 'Harry Potter'),
('hermione@paciente.com', 'Hermione Granger'),
('ron@paciente.com', 'Ron Weasley'),
('voldemort@paciente.com', 'Voldemort');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xip`
--

CREATE TABLE `xip` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) DEFAULT NULL,
  `id_medicine` bigint(20) UNSIGNED DEFAULT NULL,
  `id_patient` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `xip`
--

INSERT INTO `xip` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(1, 'banner@doctor.com', 1, 'draco@paciente.com', '2023-06-24'),
(2, 'banner@doctor.com', 2, 'harry@paciente.com', '2023-08-16'),
(3, 'parker@doctor.com', 3, 'hermione@paciente.com', '2023-10-16'),
(4, 'parker@doctor.com', 4, 'ron@paciente.com', '2023-08-19'),
(5, 'rogers@doctor.com', 5, 'voldemort@paciente.com', '2023-06-30'),
(6, 'rogers@doctor.com', 3, 'draco@paciente.com', '2023-09-04'),
(7, 'stark@doctor.com', 5, 'harry@paciente.com', '2023-09-08'),
(8, 'stark@doctor.com', 2, 'hermione@paciente.com', '2023-08-16'),
(9, 'strange@doctor.com', 3, 'ron@paciente.com', '2023-12-24'),
(10, 'strange@doctor.com', 1, 'voldemort@paciente.com', '2024-06-16');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xip`
--
ALTER TABLE `xip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `doctor_mail` (`doctor_mail`),
  ADD KEY `id_medicine` (`id_medicine`),
  ADD KEY `id_patient` (`id_patient`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `xip`
--
ALTER TABLE `xip`
  ADD CONSTRAINT `xip_ibfk_1` FOREIGN KEY (`doctor_mail`) REFERENCES `doctor` (`mail`),
  ADD CONSTRAINT `xip_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id`),
  ADD CONSTRAINT `xip_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

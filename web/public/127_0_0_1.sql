-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-03-2022 a las 00:47:45
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tradetodo`
--
CREATE DATABASE IF NOT EXISTS `tradetodo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tradetodo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `ciudad` varchar(20) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre`, `direccion`, `telefono`, `ciudad`, `foto`) VALUES
(1, 'Simon Bolivar', 'Kra11#9-56', '7702291', 'Cali', NULL),
(2, 'Mark Zuckerberg', 'Cll 21#95-52', '+57-315291', 'Medellin', NULL),
(3, 'Drew Barrymore', 'Kra52#65-05', '3125359456', 'Cali', NULL),
(4, 'Larry Page', 'Cll 05#52-95', '7872296', 'Tunka', NULL),
(5, 'Tom Delonge', 'Cll 52#65-56', '792293', 'Medellin', NULL),
(6, 'Simon Bolivar', 'Kra 21#65-52', '982295', 'Bogota', NULL),
(7, 'Mark Hoppus', 'Cll 11#95-9', '8952294', 'Bogota', NULL),
(8, 'Britney Spears', 'Cll 05#52-56', '7705295', 'Tunja', NULL),
(9, 'John Forbes Nash', 'Kra 21#05-56', '776622966', 'Cali', NULL),
(10, 'Tom Delonge', 'Kra05#65-05', '6702293', 'Medellin', NULL),
(11, 'Sergey Brin', 'Cll 11#65-11', '9702299', 'Medellin', NULL),
(12, 'Emma Watson', 'Kra 9#9-95', '31569638', 'Tunja', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `precio` int(10) DEFAULT NULL,
  `foto_producto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `descripcion`, `precio`, `foto_producto`) VALUES
(1, 'Coca-Cola 2L', 2400, NULL),
(2, 'Doritos', 1000, NULL),
(3, 'Salchicha', 3600, NULL),
(4, 'Pan', 500, NULL),
(5, 'Queso', 1000, NULL),
(6, 'Sandia', 8000, NULL),
(7, 'Leche 1L', 4563, NULL),
(8, 'Atun', 1800, NULL),
(9, 'Pescado', 7856, NULL),
(10, 'Cicla Estatica', 1800, NULL),
(11, 'Camiseta', 12000, NULL),
(12, 'Blue-Jean', 7800, NULL),
(13, 'Papaya', 1400, NULL),
(14, 'Agua en Bolsa', 1800, NULL),
(15, 'Red Bull', 1200, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_ventas` int(11) NOT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_ventas`, `cantidad`, `id_cliente`, `id_producto`) VALUES
(1, 1, 1, 5),
(2, 2, 2, 6),
(3, 4, 3, 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_ventas`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_producto` (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_ventas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.27 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-04-22 19:08:07
-- --------------------------------------------------------

drop database if exists seguros;

create database seguros;

use seguros; 

-- Dumping structure for table seguros.seguro
DROP TABLE IF EXISTS `seguro`;
CREATE TABLE IF NOT EXISTS `seguro` (
  `idSeguro` int(11) NOT NULL AUTO_INCREMENT,
  `nif` varchar(255) NOT NULL UNIQUE,
  `nombre` varchar(255) DEFAULT NULL,
  `ape1` varchar(255) DEFAULT NULL,
  `ape2` varchar(255) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `sexo` int(11) DEFAULT NULL,
  `casado` char(1) DEFAULT NULL,
  `numHijos` int(11) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `tipoSeguro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idSeguro`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- Dumping data for table seguros.seguro: ~0 rows (approximately)
/*!40000 ALTER TABLE `seguro` DISABLE KEYS */;
INSERT INTO `seguro` (`idSeguro`, `nif`, `nombre`, `ape1`, `ape2`, `edad`, `sexo`, `casado`, `numHijos`, `fechaCreacion`,`tipoSeguro`) VALUES
	(1, '17546586R', 'JOAQUIM', 'SORIA', 'SORIA', 19, 1, 'Y', 0, '2013-04-22 19:05:11', 'HOGAR'),
	(2, '22737883Z', 'FELIX EDUARDO', 'EVA EUGENIA', 'IVORRA', 40, 1, 'Y', 3,'2013-04-22 19:05:12', 'HOGAR'),
	(3, '41179582Z', 'MARIA INES', 'MARCO', 'LIBEROS', 26, 1, 'Y', 2,'2013-04-22 19:05:12', 'COCHE'),
	(4, '55386997F', 'NURIA', 'PUERTO', 'VILLANOVA', 46, 1, 'Y', 2, '2013-04-22 19:05:12', 'HOGAR'),
	(5, '66738365R', 'JOAQUIN', 'SAYAS', 'SENABRE', 25, 0, 'N', 1,'2013-04-22 19:05:12', 'MOTO'),
	(6, '42749118F', 'JOSEF BENANTZIO', 'SAVALL', 'RABASCO', 40, 1, 'N', 2,'2013-04-22 19:05:12', 'MOTO'),
	(7, '78172651K', 'CRISTINA', 'PEREZ', 'HARILLO', 56, 0,'Y', 1,'2013-04-22 19:05:12', 'HOGAR'),
	(8, '51256964S', 'MARIA SANDRA', 'CASES', 'ROIG', 58, 1, 'N', 3,'2013-04-22 19:05:12', 'VIAJE')
	

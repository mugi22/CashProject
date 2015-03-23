# Host: 10.2.21.62  (Version: 5.5.41-0ubuntu0.14.04.1)
# Date: 2015-03-23 16:25:11
# Generator: MySQL-Front 5.3  (Build 4.191)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "REVINFO"
#

DROP TABLE IF EXISTS `REVINFO`;
CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "REVINFO"
#


#
# Structure for table "TBL_BRANCH"
#

DROP TABLE IF EXISTS `TBL_BRANCH`;
CREATE TABLE `TBL_BRANCH` (
  `BRANCH_CODE` varchar(5) NOT NULL,
  `ALTERNATE_ID` varchar(6) DEFAULT NULL,
  `CLOSE_DATE` date DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `IS_KONVENSIONAL` varchar(1) DEFAULT NULL,
  `KELAS` varchar(1) DEFAULT NULL,
  `LVL` varchar(1) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `NOREK_KAS_CABANG` varchar(15) DEFAULT NULL,
  `OPEN_DATE` date DEFAULT NULL,
  `PARENT_ADMINISTRASI` varchar(5) DEFAULT NULL,
  `PARENT_ID` varchar(5) DEFAULT NULL,
  `PARENT_PEMERIKSAAN` varchar(5) DEFAULT NULL,
  `PINCA` varchar(10) DEFAULT NULL,
  `PRINTER` longtext,
  `PRODUK_DITERIMA` varchar(100) DEFAULT NULL,
  `PRODUK_EKSTERNAL_DITERIMA` varchar(100) DEFAULT NULL,
  `SK_PINCA` varchar(60) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `STATUS_OPERASIONAL` varchar(1) DEFAULT NULL,
  `TELP` varchar(15) DEFAULT NULL,
  `TGL_SK_PINCA` date DEFAULT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  `VERSISBG` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`BRANCH_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_BRANCH"
#

INSERT INTO `TBL_BRANCH` VALUES ('00002',NULL,NULL,NULL,NULL,NULL,NULL,'0','KPPP',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.00,NULL);

#
# Structure for table "TBL_GROUP"
#

DROP TABLE IF EXISTS `TBL_GROUP`;
CREATE TABLE `TBL_GROUP` (
  `GROUP_ID` decimal(19,2) NOT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `GROUP_NAME` varchar(100) NOT NULL,
  `JABATAN` varchar(5) DEFAULT NULL,
  `PARAMS` longtext,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_GROUP"
#

INSERT INTO `TBL_GROUP` VALUES (1.00,NULL,NULL,'ADMIN','Siaga','Penggalang','P81035','2015-03-18',1.00),(2.00,'P81035','2015-03-18','Group2','12345','12345',NULL,NULL,NULL);

#
# Structure for table "TBL_KABUPATEN"
#

DROP TABLE IF EXISTS `TBL_KABUPATEN`;
CREATE TABLE `TBL_KABUPATEN` (
  `KODE_PROVINSI` varchar(2) NOT NULL,
  `KODE_KABUPATEN` varchar(4) NOT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `NAMA_KABUPATEN` varchar(200) NOT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`KODE_PROVINSI`,`KODE_KABUPATEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_KABUPATEN"
#


#
# Structure for table "TBL_LOOKUP"
#

DROP TABLE IF EXISTS `TBL_LOOKUP`;
CREATE TABLE `TBL_LOOKUP` (
  `LOOKUP_VALUE` varchar(100) NOT NULL,
  `LOOKUP_NAME` varchar(100) NOT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `LOOKUP_LABEL` varchar(200) NOT NULL,
  `NO_URUT` decimal(19,2) DEFAULT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`LOOKUP_VALUE`,`LOOKUP_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_LOOKUP"
#


#
# Structure for table "TBL_MENU"
#

DROP TABLE IF EXISTS `TBL_MENU`;
CREATE TABLE `TBL_MENU` (
  `MENU_ID` decimal(19,2) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  `HAVE_CHILD` char(1) NOT NULL,
  `IS_USING_GROOVY` varchar(1) DEFAULT NULL,
  `MENU_NAME` varchar(200) NOT NULL,
  `MENU_PAGE` varchar(200) DEFAULT NULL,
  `NO_URUT` decimal(19,2) NOT NULL,
  `PARAMS` longtext,
  `PARENT_ID` decimal(19,2) DEFAULT NULL,
  `SCREEN_CLASS` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_MENU"
#

INSERT INTO `TBL_MENU` VALUES (100.00,NULL,NULL,'P81035','2015-03-19 08:29:31',NULL,'1','','User Group Menu','mainPage.htm',10.00,'',0.00,''),(101.00,NULL,NULL,NULL,NULL,NULL,'0',NULL,'Menu','menu.htm',10.00,NULL,100.00,'menu.htm'),(102.00,'P81035','2015-03-18 09:35:47','P81035','2015-03-18 15:50:12',NULL,'0','','User','userList.htm',4.00,'',100.00,''),(103.00,'P81035','2015-03-18 09:37:58',NULL,NULL,NULL,'0','','Group','group.htm',11.00,'',100.00,''),(104.00,'P81035','2015-03-18 09:38:48',NULL,NULL,NULL,'0','','Priviledge','priviledge.htm',12.00,'',100.00,''),(105.00,'P81035','2015-03-18 14:39:12','P81035','2015-03-18 14:51:32',NULL,'0','','User Group','userGroup.htm',13.00,'',100.00,''),(106.00,'P81035','2015-03-20 14:43:22',NULL,NULL,NULL,'0','','Pegawai','pegawai.htm',10.00,'',100.00,''),(200.00,'P81035','2015-03-18 16:10:52',NULL,NULL,NULL,'1','','Parameter','utamaMain.htm',2.00,'',0.00,''),(201.00,'P81035','2015-03-18 16:11:27','P81035','2015-03-18 16:15:48',NULL,'0','','Parameter','param.htm',1.00,'',200.00,''),(800.00,'P81035','2015-03-19 08:24:44','P81035','2015-03-19 08:28:06',NULL,'1','','Development','mainPage.htm',99.00,'',0.00,''),(801.00,'P81035','2015-03-19 08:30:50',NULL,NULL,NULL,'0','','Test Form','testForm.htm',1.00,'',800.00,''),(802.00,'P81035','2015-03-23 11:08:26',NULL,NULL,NULL,'0','','Pojo Gen','pojogen.htm',2.00,'',800.00,''),(900.00,'P81035','2015-03-18 16:23:13','P81035','2015-03-23 10:34:56',NULL,'0','','Code Generator','xxx.htm',100.00,'',800.00,'');

#
# Structure for table "TBL_PARAM"
#

DROP TABLE IF EXISTS `TBL_PARAM`;
CREATE TABLE `TBL_PARAM` (
  `KEY_` varchar(100) NOT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `DESCRIPTION_` varchar(200) NOT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VALUE_` longtext NOT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_PARAM"
#

INSERT INTO `TBL_PARAM` VALUES ('Test','P81035','2015-03-18','Test Parameter','P81035','2015-03-18','12',NULL);

#
# Structure for table "TBL_PEGAWAI"
#

DROP TABLE IF EXISTS `TBL_PEGAWAI`;
CREATE TABLE `TBL_PEGAWAI` (
  `nik` varchar(20) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  `branch_code` varchar(5) DEFAULT NULL,
  `cif` varchar(16) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `nama` varchar(200) NOT NULL,
  `status_aktif` varchar(3) DEFAULT NULL,
  `status_pegawai` varchar(3) DEFAULT NULL,
  `tgl_lahir` date NOT NULL,
  PRIMARY KEY (`nik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_PEGAWAI"
#

INSERT INTO `TBL_PEGAWAI` VALUES ('P123','P81035','2015-03-23 09:22:09','P81035','2015-03-23 09:36:16',NULL,'0002','123','11','Nama hhhhh','A','P','1987-11-23'),('P81035','P81035','2015-03-20 15:12:17','P81035','2015-03-23 09:12:22',NULL,'00002','123456','1','Mandra Gader','a','a','2015-03-20');

#
# Structure for table "TBL_PRIVILEDGE"
#

DROP TABLE IF EXISTS `TBL_PRIVILEDGE`;
CREATE TABLE `TBL_PRIVILEDGE` (
  `MENU_ID` decimal(19,2) NOT NULL,
  `GROUP_ID` decimal(19,2) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  `IS_ADD` char(1) NOT NULL,
  `IS_DELETE` char(1) NOT NULL,
  `IS_UPDATE` char(1) NOT NULL,
  `IS_VIEW` char(1) NOT NULL,
  PRIMARY KEY (`MENU_ID`,`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_PRIVILEDGE"
#

INSERT INTO `TBL_PRIVILEDGE` VALUES (100.00,1.00,NULL,NULL,NULL,NULL,NULL,'1','1','1','1'),(101.00,1.00,NULL,NULL,NULL,NULL,NULL,'1','1','1','1'),(102.00,1.00,'P81035','2015-03-18 14:07:46',NULL,NULL,NULL,'1','1','1','1'),(103.00,1.00,'P81035','2015-03-18 14:09:57',NULL,NULL,NULL,'1','1','1','1'),(104.00,1.00,NULL,NULL,NULL,NULL,NULL,'1','1','1','1'),(105.00,1.00,'P81035','2015-03-18 14:39:31','P81035','2015-03-18 15:45:57',NULL,'1','1','1','1'),(106.00,1.00,'P81035','2015-03-20 14:43:45',NULL,NULL,NULL,'1','1','1','1'),(200.00,1.00,'P81035','2015-03-18 16:11:55',NULL,NULL,NULL,'1','1','1','1'),(201.00,1.00,'P81035','2015-03-18 16:12:12',NULL,NULL,NULL,'1','1','1','1'),(800.00,1.00,'P81035','2015-03-19 08:25:48',NULL,NULL,NULL,'1','1','1','1'),(801.00,1.00,'P81035','2015-03-19 08:31:08',NULL,NULL,NULL,'1','1','1','1'),(802.00,1.00,'P81035','2015-03-23 11:08:43',NULL,NULL,NULL,'1','1','1','1'),(900.00,1.00,'P81035','2015-03-18 16:23:36',NULL,NULL,NULL,'1','1','1','1');

#
# Structure for table "TBL_PROVINSI"
#

DROP TABLE IF EXISTS `TBL_PROVINSI`;
CREATE TABLE `TBL_PROVINSI` (
  `KODE_PROVINSI` varchar(2) NOT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `NAMA_PROVINSI` varchar(100) NOT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`KODE_PROVINSI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_PROVINSI"
#


#
# Structure for table "TBL_SEQ"
#

DROP TABLE IF EXISTS `TBL_SEQ`;
CREATE TABLE `TBL_SEQ` (
  `SEQ_NAME` varchar(100) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  `KETERANGAN` varchar(100) NOT NULL,
  `SEQ_NUM` bigint(20) NOT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_SEQ"
#


#
# Structure for table "TBL_SEQ_AUD"
#

DROP TABLE IF EXISTS `TBL_SEQ_AUD`;
CREATE TABLE `TBL_SEQ_AUD` (
  `SEQ_NAME` varchar(100) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `KETERANGAN` varchar(100) DEFAULT NULL,
  `SEQ_NUM` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`,`REV`),
  KEY `FK_a848jfsf93itmyu5n9u6g3hiy` (`REV`),
  CONSTRAINT `FK_a848jfsf93itmyu5n9u6g3hiy` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_SEQ_AUD"
#


#
# Structure for table "TBL_TEST"
#

DROP TABLE IF EXISTS `TBL_TEST`;
CREATE TABLE `TBL_TEST` (
  `NIK` varchar(255) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  `NAMA` varchar(255) NOT NULL,
  `TGLLAHIR` date DEFAULT NULL,
  PRIMARY KEY (`NIK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_TEST"
#


#
# Structure for table "TBL_USER"
#

DROP TABLE IF EXISTS `TBL_USER`;
CREATE TABLE `TBL_USER` (
  `USER_ID` varchar(10) NOT NULL,
  `BRANCH_CODE` varchar(5) NOT NULL,
  `BRANCH_MOBILE_ID` varchar(10) DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ENABLED` varchar(1) DEFAULT NULL,
  `END_TIME` date NOT NULL,
  `LAST_CHANGE_PWD` date DEFAULT NULL,
  `LAST_LOG_IN` date DEFAULT NULL,
  `LIMIT_APPROVAL` decimal(12,0) DEFAULT NULL,
  `LOGIN_FAIL_COUNT` bigint(20) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `START_TIME` date NOT NULL,
  `UPDATE_BY` varchar(20) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  `VERSI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_USER"
#

INSERT INTO `TBL_USER` VALUES ('P81035','00002','00002','ADMIN','2020-12-31',NULL,NULL,'2020-12-31','2020-12-31','2020-12-31',NULL,NULL,'MUGI','138FD3F8C2C86123','2012-12-12','ADMIN','2020-12-31',NULL),('P81036','00002',NULL,'P81035','2015-03-20','mandra@hotmail.com','Y','2015-03-20',NULL,NULL,NULL,NULL,'MANDRA','138FD3F8C2C86123','2015-03-20',NULL,NULL,NULL);

#
# Structure for table "TBL_USER_GROUP"
#

DROP TABLE IF EXISTS `TBL_USER_GROUP`;
CREATE TABLE `TBL_USER_GROUP` (
  `USER_ID` varchar(10) NOT NULL,
  `GROUP_ID` decimal(19,2) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `versi` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "TBL_USER_GROUP"
#

INSERT INTO `TBL_USER_GROUP` VALUES ('P81035',1.00,'ADMIN',NULL,NULL,NULL,1.00);

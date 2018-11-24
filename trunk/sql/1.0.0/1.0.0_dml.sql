INSERT INTO `sys_menu` VALUES ('49f3bb08c546467298688deb5072bb5f', '商户审核', null, '', null, null, '', '1', '4', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-16 00:27:53', null, null, '0');
INSERT INTO `sys_menu` VALUES ('57521e6a6a284bf4a4297ec1d149412d', '广告审核', null, 'sys/advertise/checklist', '49f3bb08c546467298688deb5072bb5f', '49f3bb08c546467298688deb5072bb5f/', 'sys:advertise:checklist', '1', '1', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-16 00:29:54', null, null, '0');
INSERT INTO `sys_menu` VALUES ('5db3c50e9fa74e18a6163515eb4bc119', '广告规则', null, 'sys/advertiserule/list', '04273e3a84c04cc796207c6f9c15acee', '04273e3a84c04cc796207c6f9c15acee/', 'sys:advertiserule:list', '1', '1', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:18:09', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:26:55', '0');
INSERT INTO `sys_menu` VALUES ('6788931550f541baaf589585a491d5fa', '广告管理', null, 'sys/advertise/list', '32a82b9d009841ecb4b4091f342c0f8b', '32a82b9d009841ecb4b4091f342c0f8b/', 'sys:advertise:list', '1', '1', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-14 20:41:52', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-14 20:52:58', '0');
INSERT INTO `sys_menu` VALUES ('900f2cf3c64148a7b88b4f6794f148ff', '商户广告规则', null, 'sys/advertiserulerelation/list', '04273e3a84c04cc796207c6f9c15acee', '04273e3a84c04cc796207c6f9c15acee/', 'sys:advertiserulerelation:list', '1', '2', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:21:25', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:28:04', '0');
INSERT INTO `sys_menu` VALUES ('04273e3a84c04cc796207c6f9c15acee', '商户管理', null, '', null, null, '', '1', '3', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:16:29', null, null, '0');
INSERT INTO `sys_menu` VALUES ('32a82b9d009841ecb4b4091f342c0f8b', '广告管理', null, '', null, null, '', '1', '1', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-14 20:41:03', null, null, '0');


INSERT INTO `wgboo_capital` VALUES ('68e559b1ec0a11e8b9f114dda97cf991', '0', null, '2018-11-19 22:48:24', null, '2018-11-19 22:48:28', '0', null);

-- 添加商户审核
INSERT INTO  sys_menu ( id,  menu_icon,    url,  permission,    sort,create_by,create_date,    del_flag,  remarks,  `name`,parent_id,parent_ids )
VALUES ( '1cb80c74480b4dc38dd2dc6e95b183be',  '',    'sys/merchantregister/list',  'sys:merchantregister:list',    2,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 09:08:21',    '1',  '',  '商户审核','49f3bb08c546467298688deb5072bb5f','49f3bb08c546467298688deb5072bb5f/' );

-- 添加商户状态数据字典
INSERT INTO sys_dict_group ( id,  `name`,  remarks,  code,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '05d3d61378e24d8cac2d6bbdc144b6e8',  '商家状态',  '',  'sjsh','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:26:01','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:26:01','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( 'a84d368c31324affa906856aba895fa2',  '05d3d61378e24d8cac2d6bbdc144b6e8',  '待审核',  '1',  '',  2,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:28:47','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:28:47','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '7b109aeabf5345c990c4c406eb005a49',  '05d3d61378e24d8cac2d6bbdc144b6e8',  '审核通过',  '2',  '',  2,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:30:18','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:30:18','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '9b00b168a5414efd8de8e98c8b80a77b',  '05d3d61378e24d8cac2d6bbdc144b6e8',  '审核失败',  '3',  '',  3,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:31:14','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 14:31:14','0' );

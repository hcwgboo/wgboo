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

INSERT INTO `sys_menu` (`id`, `name`, `type`, `url`, `parent_id`, `parent_ids`, `permission`, `isshow`, `sort`, `menu_icon`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('bca4ad14fe144cbeacb5f81693ea4516', '会员管理', NULL, '', NULL, NULL, '', '1', '5', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-24 18:26:52', NULL, NULL, '0');

INSERT INTO `sys_menu` (`id`, `name`, `type`, `url`, `parent_id`, `parent_ids`, `permission`, `isshow`, `sort`, `menu_icon`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('46995b53c47b4651b8b10a0ee43fe5f4', '会员管理', NULL, 'sys/member/list', 'bca4ad14fe144cbeacb5f81693ea4516', 'bca4ad14fe144cbeacb5f81693ea4516/', 'sys:member:list', '1', '1', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-24 18:28:52', NULL, NULL, '0');

INSERT INTO sys_dict_group ( id,  `name`,  remarks,  code,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '8d10c0d0b65d44cb90527b41e19b1ee8',  '性别',  '',  'xb','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:47:03','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:47:03','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '2e06d8d4896b4406a6a29c75ed9a59fd',  '8d10c0d0b65d44cb90527b41e19b1ee8',  '未知',  '0',  '',  0,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:14','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:14','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( 'ce43d86c5fbc425999d2e98a3115fa43',  '8d10c0d0b65d44cb90527b41e19b1ee8',  '男',  '1',  '',  1,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:23','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:23','0' );

INSERT INTO sys_dict ( id,  gid,  `label`,  `value`,  remarks,  sort,create_by,create_date,update_by,update_date,del_flag )
VALUES ( '2cb393cc1f074e9bb86674bd5060f3ad',  '8d10c0d0b65d44cb90527b41e19b1ee8',  '女',  '2',  '',  2,'4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:57','4028ea815a3d2a8c015a3d2f8d2a0002','2018-11-24 18:48:57','0' );

INSERT INTO `sys_role` (`id`, `name`, `code`, `is_sys`, `usable`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES ('402880e45b5d7636015b5d8baca60000', '系统普通用户', 'normal', '1', '1', NULL, '2017-04-11 23:04:46', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 10:26:29', '', '0');

INSERT INTO `sys_role` (`id`, `name`, `code`, `is_sys`, `usable`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES ('e47fc019c9b647c8a682bc385443bab2', '普通商家', 'merchant', '1', '1', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 10:27:06', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 10:27:25', '', '0');

INSERT INTO `sys_role` (`id`, `name`, `code`, `is_sys`, `usable`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES ('f7a43636456842b787c25f6622cbd599', 'vip商家', 'vipMerchant', '1', '1', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 10:27:50', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 10:27:50', '', '0');

INSERT INTO `sys_menu` (`id`, `name`, `type`, `url`, `parent_id`, `parent_ids`, `permission`, `isshow`, `sort`, `menu_icon`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('b76bdb41f420402bb8ad2dd36fe03357', '商户资金', NULL, 'sys/mercapital/list', '04273e3a84c04cc796207c6f9c15acee', '04273e3a84c04cc796207c6f9c15acee/', 'sys:mercapital:list', '1', '3', '', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 19:26:55', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 21:12:47', '0');





INSERT INTO `sys_dict_group` (`id`, `name`, `code`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('151447f3fda04200bde50d598a7d80f2', '商户类型', 'shlx', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:35:10', NULL, NULL, '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('a14bedd9b101497c99017e7b6b6cf05c', '151447f3fda04200bde50d598a7d80f2', '平台', '2', '2', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:35:41', NULL, NULL, '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('ab5dbbfc448e4d6886896213ee0935f0', '151447f3fda04200bde50d598a7d80f2', '商户', '1', '1', '1', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:35:27', NULL, NULL, '0');



INSERT INTO `sys_dict_group` (`id`, `name`, `code`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('e4ced5a26f734d55915a8806b25351c6', '上架状态', 'sjzt', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:22', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:22', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('b7883addd94446198e3a8a9dd1cb5f56', 'e4ced5a26f734d55915a8806b25351c6', '上架', '1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:45', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:45', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('d98e03081c0e4282a4cf891e5b3e14b4', 'e4ced5a26f734d55915a8806b25351c6', '下架', '0', '0', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:36', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-19 20:15:36', '0');

INSERT INTO `sys_dict_group` (`id`, `name`, `code`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('94773eddee804300ba02c1b2825f547d', '审核状态', 'shzt', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:34:27', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:34:27', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('2511f6b56264411cabf55c6b3302e9c6', '94773eddee804300ba02c1b2825f547d', '审核中', '1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:35', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:35', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('5b82a7ffeb6948a5afe0689958677619', '94773eddee804300ba02c1b2825f547d', '审核通过', '2', '2', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:43', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:43', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('ca91eef1ca65488085e40d2333d1fbb8', '94773eddee804300ba02c1b2825f547d', '未提交', '0', '0', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:12', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:35:12', '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('f73b0a1324ea459dae1f3a03fa936c2c', '94773eddee804300ba02c1b2825f547d', '审核失败', '3', '3', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:36:04', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-18 17:36:04', '0');


INSERT INTO `sys_dict_group` (`id`, `name`, `code`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('a837a20f190f4409b19a76d38d437276', '任务类型', 'rwlx', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:33:16', NULL, NULL, '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('3e7f420aa4794305b2339d86c52e9eaa', 'a837a20f190f4409b19a76d38d437276', '转发', '1', '1', '1', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:33:34', NULL, NULL, '0');

INSERT INTO `sys_dict` (`id`, `gid`, `label`, `value`, `sort`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`)
VALUES ('4e5324553c4b4559bda06d3a201a7a40', 'a837a20f190f4409b19a76d38d437276', '浏览', '2', '2', '', '4028ea815a3d2a8c015a3d2f8d2a0002', '2018-11-15 20:33:51', NULL, NULL, '0');


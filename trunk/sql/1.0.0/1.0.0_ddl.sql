CREATE TABLE `adv_advertise` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `increment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `merchant_id` varchar(32) NOT NULL COMMENT '商户id',
  `title` varchar(60) NOT NULL COMMENT '广告标题',
  `rule_main` varchar(1024) DEFAULT NULL COMMENT '奖励规则',
  `content` varchar(4000) DEFAULT NULL COMMENT '内容',
  `type` varchar(2) DEFAULT NULL COMMENT '广告类别',
  `forward_money` decimal(10,2) DEFAULT NULL COMMENT '转发一次金额',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `min_money` decimal(10,2) DEFAULT NULL COMMENT '最小金额',
  `now_view_times` int(10) DEFAULT NULL COMMENT '当前浏览次数',
  `now_share_times` int(10) DEFAULT NULL COMMENT '当前分享次数',
  `release_status` varchar(2) DEFAULT NULL COMMENT '上下架（0.下架，1.上架）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（0.创建，1.待审核，2.审核通过，3.拒绝）',
  `region` varchar(3072) DEFAULT NULL COMMENT '开放区域',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`increment_id`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='广告';

CREATE TABLE `adv_advertise_atta_relation` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `adv_id` varchar(32) DEFAULT NULL COMMENT '广告id',
  `attachment_id` varchar(32) DEFAULT NULL COMMENT '附件id',
  `img_url` varchar(1024) DEFAULT NULL COMMENT '图片url',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  `del_flag` varchar(10) DEFAULT '0' COMMENT '是否删除（0否 1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告图片关联';

CREATE TABLE `adv_advertise_detial` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `adv_id` varchar(32) DEFAULT NULL COMMENT '广告id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '商户id',
  `dis_member_id` varchar(32) DEFAULT NULL COMMENT '会员id',
  `parent_dis_member_id` varchar(32) DEFAULT NULL COMMENT '上级会员id',
  `superior_money` decimal(10,0) DEFAULT NULL COMMENT '上级会员所得',
  `current_monkey` decimal(10,0) DEFAULT NULL COMMENT '自己所得',
  `source` varchar(2) DEFAULT NULL COMMENT '转发来源（0.平台1.上级分销）',
  `the_same_day` varchar(20) DEFAULT NULL COMMENT '当天的年月日（2018-10-28）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告明细';

CREATE TABLE `adv_advertise_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `adv_id` varchar(32) DEFAULT NULL COMMENT '广告id',
  `status` varchar(2) DEFAULT NULL COMMENT '（0.创建，1.待审核，2.审核通过，3.拒绝）',
  `release` varchar(2) DEFAULT NULL COMMENT '上下架（0.下架，1.上架）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='adv_advertise_log';

CREATE TABLE `adv_advertise_rule` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '规则名称',
  `ratio` double(10,2) DEFAULT NULL COMMENT '平台佣金比例',
  `min_money` decimal(10,0) DEFAULT NULL COMMENT '最低金额',
  `task_type` varchar(2) DEFAULT NULL COMMENT '任务类型（1,2）',
  `describe` varchar(2000) DEFAULT NULL COMMENT '描述',
  `type` varchar(2) DEFAULT NULL COMMENT '商户类型（0.平台，1.商户）',
  `min_size` int(11) DEFAULT NULL COMMENT '最少条数',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则';

CREATE TABLE `adv_advertise_rule_relation` (
  `id` varchar(32) NOT NULL,
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '广告表id',
  `adv_rule_id` varchar(32) DEFAULT NULL COMMENT '规则表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告规则关联表';

CREATE TABLE `adv_advertise_view` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `adv_id` varchar(32) DEFAULT NULL COMMENT '广告id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '商户id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '会员id',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览';

CREATE TABLE `dis_capital_account` (
  `id` varchar(32) NOT NULL,
  `account` varchar(60) DEFAULT NULL COMMENT '账号',
  `dis_member_id` varchar(32) DEFAULT NULL COMMENT '会员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员账号表';

CREATE TABLE `dis_member` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '当前用户的标识，小程序用来区分用户',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(512) DEFAULT NULL COMMENT '头像',
  `gender` varchar(32) DEFAULT NULL COMMENT '性别（0：未知、1：男、2：女）',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `openid` varchar(64) DEFAULT NULL COMMENT 'openid',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_id`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `dis_member_capital` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dis_member_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `balance_money` decimal(10,2) DEFAULT NULL COMMENT '账户余额',
  `freeze_money` decimal(10,2) DEFAULT NULL COMMENT '冻结金额',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '历史总收益',
  `pay_account` varchar(32) DEFAULT NULL COMMENT '帐号',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销会员资金';

CREATE TABLE `dis_member_capital_detail` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dis_member_capital_id` varchar(32) DEFAULT NULL COMMENT '分销会员资金表id',
  `dis_member_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `source_id` varchar(32) DEFAULT NULL COMMENT '来源id',
  `type` varchar(2) DEFAULT NULL COMMENT '类型(1.直接转发，2.下级转发提成3.提现成功4.提现失败退回5.提现审核中）',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `pay_account` varchar(60) DEFAULT NULL COMMENT '交易账号',
  `pay_type` varchar(2) DEFAULT NULL COMMENT '支付宝',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员资金明细';

CREATE TABLE `mer_capital` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '商家id',
  `balance_money` decimal(10,2) DEFAULT NULL COMMENT '账户余额',
  `freeze_money` decimal(10,2) DEFAULT NULL COMMENT '冻结金额',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家资金';

CREATE TABLE `mer_capital_detail` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '商家id',
  `capital_id` varchar(32) DEFAULT NULL COMMENT '资金表id',
  `consume_id` varchar(32) DEFAULT NULL COMMENT '消费id（广告）',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `residue_money` varchar(10) DEFAULT NULL COMMENT '剩余金额',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(0.广告消费，1.充值, 2.升级，3.提现)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家资金明细';

CREATE TABLE `micro_radio_capital_detail` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `merchant_id` varchar(32) NOT NULL COMMENT '商家id',
  `source` varchar(2) DEFAULT NULL COMMENT '业务源（0.广告1.待定）',
  `type` varchar(2) DEFAULT NULL COMMENT '类型0.消费，1.提现',
  `balance_money` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `now_money` decimal(10,0) DEFAULT NULL COMMENT '当前金额',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='潢川微广播网络科技有限公司资金表';


CREATE TABLE `wgboo_capital` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `balance_money` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台资金总表';

CREATE TABLE `sys_message` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dis_member_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(512) DEFAULT NULL COMMENT '模版内容',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  `del_flag` varchar(10) DEFAULT '0' COMMENT '是否删除（0否 1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发送消息';


alter table adv_advertise add column `superior_commission_ratio` double(10,2) DEFAULT NULL COMMENT '上级分销佣金占比';
alter table adv_advertise_rule add column  `superior_commission_ratio` double(10,2) DEFAULT NULL COMMENT '上级分销佣金占比';

alter table adv_advertise add column `sub_commission_ratio` double(10,2) DEFAULT NULL COMMENT '下级分销佣金占比';

alter table adv_advertise_rule add column `sub_commission_ratio` double(10,2) DEFAULT NULL COMMENT '下级分销佣金占比';

alter table adv_advertise_detial add column `wgb_money` decimal(10,2) DEFAULT NULL COMMENT '平台所得';


alter table sys_user add column  `companyname` varchar(100) DEFAULT NULL COMMENT '公司名';
alter table sys_user add column  `type` varchar(50) DEFAULT NULL COMMENT '用户类型（1.平台，2.商家）';

-- 商家注册
CREATE TABLE `sys_merchant_register` (
  `id` varchar(32) NOT NULL,
  `companyname` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实名称',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(2) DEFAULT NULL COMMENT '商家状态（1.待审核，2.审核通过，3.审核失败）',
  `ids` varchar(1024) DEFAULT NULL COMMENT '附件ids',
  `create_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `log_smscode` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `type` int(2) DEFAULT NULL COMMENT '短信类型(1.注册验证码2.修改手机号验证码)',
  `telphone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `code` varchar(20) DEFAULT NULL COMMENT '验证码',
  `result` varchar(1024) DEFAULT NULL COMMENT '返回结果集',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信日志表';

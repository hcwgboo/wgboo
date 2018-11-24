
-- ----------------------------
-- Table structure for codegen_table
-- ----------------------------
DROP TABLE IF EXISTS `codegen_table`;
CREATE TABLE `codegen_table` (
  `id` varchar(32) NOT NULL COMMENT '生成方案主键',
  `title` varchar(200) DEFAULT NULL COMMENT '实体名称',
  `table_name` varchar(200) DEFAULT NULL COMMENT '实体名称',
  `table_type` varchar(10) DEFAULT NULL COMMENT '表的类型',
  `table_pk_type` varchar(5) DEFAULT NULL COMMENT '数据主键类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `sync_database` tinyint(1) DEFAULT NULL COMMENT '是否同步数据库',
  `class_name` varchar(50) DEFAULT NULL COMMENT '实体名称',
  PRIMARY KEY (`id`),
  KEY `codegen_scheme_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成信息';

-- ----------------------------
-- Table structure for codegen_column
-- ----------------------------
DROP TABLE IF EXISTS `codegen_column`;
CREATE TABLE `codegen_column` (
  `id` varchar(32) NOT NULL COMMENT '字段主键',
  `table_id` varchar(32) DEFAULT NULL COMMENT '关联表的ID',
  `column_name` varchar(200) DEFAULT NULL COMMENT '字段名',
  `type_name` varchar(200) DEFAULT NULL COMMENT '字段类型名称',
  `column_size` varchar(10) DEFAULT NULL COMMENT '字段长度',
  `decimal_digits` varchar(10) DEFAULT NULL COMMENT '小数部分的位数',
  `parmary_key` tinyint(2) DEFAULT NULL COMMENT '是否为主键',
  `imported_key` tinyint(2) DEFAULT NULL COMMENT '是否为外键',
  `column_def` varchar(200) DEFAULT NULL COMMENT '默认值',
  `is_nullable` tinyint(200) DEFAULT NULL COMMENT '是否允许为空',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段',
  `is_form` varchar(45) DEFAULT NULL COMMENT '是否表单显示',
  `query_type` varchar(200) DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `input_type` varchar(200) DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_group` varchar(200) DEFAULT NULL COMMENT '字典分组',
  `sort` int(10) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `regex_valid` varchar(20) DEFAULT NULL COMMENT '验证规则',
  `valid_type` varchar(20) DEFAULT NULL COMMENT '校验类型',
  `max_size` int(5) DEFAULT NULL COMMENT '最大长度',
  `min_size` int(5) DEFAULT NULL COMMENT '最小长度',
  `max_value` varchar(30) DEFAULT NULL COMMENT '最大值',
  `min_value` varchar(30) DEFAULT NULL COMMENT '最小值',
  `nullmsg` varchar(255) DEFAULT NULL COMMENT '为空提示',
  `query_model` varchar(20) DEFAULT NULL COMMENT '查询模式',
  `form_type` varchar(20) DEFAULT NULL COMMENT '显示表单类型',
  `foreign_table` varchar(45) DEFAULT NULL COMMENT '外键表',
  PRIMARY KEY (`id`),
  KEY `codegen_table_column_del_flag` (`del_flag`),
  KEY `table_id` (`table_id`),
  CONSTRAINT `codegen_column_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `codegen_table` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码字段表';


-- ----------------------------
-- Table structure for codegen_scheme
-- ----------------------------
DROP TABLE IF EXISTS `codegen_scheme`;
CREATE TABLE `codegen_scheme` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `table_type` varchar(2000) DEFAULT NULL COMMENT '表类型',
  `path_name` varchar(2000) DEFAULT NULL COMMENT '代码生成目录',
  `package_name` varchar(500) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `entity_name` varchar(30) DEFAULT NULL COMMENT '实体名',
  `table_name` varchar(30) DEFAULT NULL COMMENT '表名',
  `sub_module_name` varchar(30) DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(500) DEFAULT NULL COMMENT '生成功能名',
  `function_desc` varchar(100) DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(100) DEFAULT NULL COMMENT '生成功能作者',
  `table_id` varchar(200) DEFAULT NULL COMMENT '生成表编号',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `codegen_scheme_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成方案';

-- ----------------------------
-- Table structure for email_send_log
-- ----------------------------
DROP TABLE IF EXISTS `email_send_log`;
CREATE TABLE `email_send_log` (
  `id` varchar(32) NOT NULL,
  `email` longtext NOT NULL COMMENT '联系电话',
  `subject` varchar(255) DEFAULT NULL COMMENT '主题',
  `content` text COMMENT '模板类型',
  `senddata` varchar(255) NOT NULL COMMENT '发送数据',
  `response_date` datetime DEFAULT NULL COMMENT '响应时间',
  `business_type` varchar(4) DEFAULT NULL COMMENT '业务类型',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `status` varchar(4) NOT NULL COMMENT '发送状态',
  `msg` varchar(40) DEFAULT NULL COMMENT '返回消息',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for email_template
-- ----------------------------
DROP TABLE IF EXISTS `email_template`;
CREATE TABLE `email_template` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '模版名称',
  `code` varchar(255) NOT NULL COMMENT '模版编码',
  `business_type` varchar(4) NOT NULL COMMENT '业务类型',
  `template_subject` varchar(255) NOT NULL COMMENT '模版主题',
  `template_content` text NOT NULL COMMENT '模版内容',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oa_notification
-- ----------------------------
DROP TABLE IF EXISTS `oa_notification`;
CREATE TABLE `oa_notification` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '内容',
  `status` varchar(4) NOT NULL COMMENT '发布状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sms_send_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_log`;
CREATE TABLE `sms_send_log` (
  `id` varchar(32) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `business_type` varchar(4) NOT NULL COMMENT '业务类型',
  `phone` longtext NOT NULL COMMENT '联系电话',
  `template_id` varchar(8) DEFAULT NULL COMMENT '模板ID',
  `template_content` varchar(255) DEFAULT NULL COMMENT '模板类型',
  `senddata` varchar(255) NOT NULL COMMENT '发送数据',
  `status` varchar(4) NOT NULL COMMENT '发送状态',
  `smsid` varchar(50) DEFAULT NULL COMMENT '发送响应消息ID',
  `code` varchar(40) DEFAULT NULL COMMENT '返回码',
  `msg` varchar(40) DEFAULT NULL COMMENT '返回消息',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `response_date` datetime DEFAULT NULL COMMENT '响应时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '模版名称',
  `code` varchar(255) NOT NULL COMMENT '模版编码',
  `business_type` varchar(4) NOT NULL COMMENT '业务类型',
  `template_id` varchar(20) NOT NULL COMMENT '模版ID',
  `template_content` varchar(255) NOT NULL COMMENT '模版内容',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_attachment` (
  `id` varchar(32) NOT NULL,
  `filename` varchar(50) NOT NULL COMMENT '文件名称',
  `userid` varchar(32) NOT NULL COMMENT '用户ID',
  `uploadtime` datetime NOT NULL COMMENT '上传时间',
  `uploadip` varchar(15) NOT NULL COMMENT '上传的ID',
  `fileext` varchar(10) NOT NULL COMMENT '文件扩展名',
  `path` varchar(1024) DEFAULT NULL,
  `filepath` varchar(1024) NOT NULL COMMENT '文件路径',
  `filesize` int(10) NOT NULL DEFAULT '0' COMMENT '文件大小',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for sys_data_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_source`;
CREATE TABLE `sys_data_source` (
  `id` varchar(36) NOT NULL,
  `db_key` varchar(50) NOT NULL COMMENT '索引关键字',
  `description` varchar(50) NOT NULL COMMENT '描述',
  `driver_class` varchar(50) NOT NULL COMMENT '驱动',
  `url` varchar(200) NOT NULL COMMENT 'URL',
  `db_user` varchar(50) NOT NULL COMMENT '帐号',
  `db_password` varchar(50) DEFAULT NULL COMMENT '密码',
  `db_type` varchar(50) DEFAULT NULL COMMENT '数据库类型',
  `db_name` varchar(50) DEFAULT NULL COMMENT '数据库名称',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_dict_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_group`;
CREATE TABLE `sys_dict_group` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '分组名称',
  `code` varchar(100) DEFAULT NULL COMMENT '分组编码',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典分组';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `gid` varchar(32) DEFAULT NULL COMMENT '分组ID',
  `label` varchar(100) DEFAULT NULL COMMENT '键值键',
  `value` varchar(100) DEFAULT NULL COMMENT '值',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_dict_groupid_key` (`gid`),
  CONSTRAINT `sys_dict_groupid_key` FOREIGN KEY (`gid`) REFERENCES `sys_dict_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';


-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `content` varchar(1000) DEFAULT '' COMMENT '日志内容',
  `logtype` varchar(4) DEFAULT '' COMMENT '操作方式',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(200) DEFAULT NULL COMMENT '点击后前往的地址',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(1000) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `isshow` tinyint(1) DEFAULT '0' COMMENT '是否显示',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `remarks` varchar(255) DEFAULT NULL COMMENT '摘要',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父节点',
  `parent_ids` varchar(1000) DEFAULT NULL COMMENT '父节点路径',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标记',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统数据',
  `usable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编号',
  `role_id` varchar(32) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `sys_role_menu_menuid` (`menu_id`),
  KEY `sys_role_menu_roleid` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';


-- ----------------------------
-- Table structure for sys_sessions
-- ----------------------------
DROP TABLE IF EXISTS `sys_sessions`;
CREATE TABLE `sys_sessions` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `session` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实名称',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `portrait` varchar(250) DEFAULT NULL COMMENT '头像',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(255) DEFAULT NULL COMMENT '系统用户的状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  UNIQUE KEY `idx_sys_user_email` (`email`),
  UNIQUE KEY `idx_sys_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_user_last_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_last_online`;
CREATE TABLE `sys_user_last_online` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `host` varchar(100) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `system_host` varchar(100) DEFAULT NULL,
  `last_login_timestamp` datetime DEFAULT NULL,
  `last_stop_timestamp` datetime DEFAULT NULL,
  `login_count` bigint(20) DEFAULT NULL,
  `total_online_time` bigint(20) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_user_last_online_sys_user_id` (`user_id`),
  KEY `idx_sys_user_last_online_username` (`username`),
  KEY `idx_sys_user_last_online_host` (`host`),
  KEY `idx_sys_user_last_online_system_host` (`system_host`),
  KEY `idx_sys_user_last_online_last_login_timestamp` (`last_login_timestamp`),
  KEY `idx_sys_user_last_online_last_stop_timestamp` (`last_stop_timestamp`),
  KEY `idx_sys_user_last_online_user_agent` (`user_agent`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(32) DEFAULT '',
  `username` varchar(100) DEFAULT NULL,
  `host` varchar(100) DEFAULT NULL,
  `system_host` varchar(100) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `start_timestsamp` datetime DEFAULT NULL,
  `last_access_time` datetime DEFAULT NULL,
  `online_timeout` bigint(20) DEFAULT NULL,
  `online_session` mediumtext,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_user_online_sys_user_id` (`user_id`),
  KEY `idx_sys_user_online_username` (`username`),
  KEY `idx_sys_user_online_host` (`host`),
  KEY `idx_sys_user_online_system_host` (`system_host`),
  KEY `idx_sys_user_online_start_timestsamp` (`start_timestsamp`),
  KEY `idx_sys_user_online_last_access_time` (`last_access_time`),
  KEY `idx_sys_user_online_user_agent` (`user_agent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_organization`;
CREATE TABLE `sys_user_organization` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户主键',
  `organization_id` varchar(32) NOT NULL COMMENT '部门主键',
  PRIMARY KEY (`id`),
  KEY `sys_user_role_userid` (`user_id`),
  KEY `sys_user_role_roleid` (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门';


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `role_id` varchar(32) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `sys_user_role_userid` (`user_id`),
  KEY `sys_user_role_roleid` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';


-- ----------------------------
-- Table structure for task_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_job`;
CREATE TABLE `task_schedule_job` (
  `id` varchar(32) NOT NULL,
  `cron_expression` varchar(255) NOT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) NOT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_import_mapping` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `mapping_templet` varchar(60) NOT NULL COMMENT '配置模板ID',
  `mapping_templet_name` varchar(60) NOT NULL COMMENT '配置模板名',
  `templet_type` varchar(60) NOT NULL COMMENT '模板类型',
  `start_row_no` varchar(8) NOT NULL COMMENT '数据读取开始行号',
  `header_row_no` varchar(8) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_mapping_templet` (`mapping_templet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导入配置主表';

CREATE TABLE `sys_import_column_mapping` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `import_mapping_id` varchar(32) DEFAULT NULL COMMENT '导入配置主表id',
  `property_cname` varchar(60) NOT NULL COMMENT '字段中文名',
  `property_ename` varchar(60) NOT NULL COMMENT '字段英文名',
  `column_no` varchar(8) NOT NULL COMMENT '对应excel模板列序号从0开始',
  `item_type` varchar(60) NOT NULL COMMENT '字段类型例：date，string，number等',
  `code` varchar(50) DEFAULT NULL COMMENT '字典code（当类型是字典的时候必填）',
  `is_null` char(1) NOT NULL DEFAULT '0' COMMENT '是否允许为空0：可以为空即不作空判断（默认值） 1：不可为空 需进行空值判断，若空则算失败件数，不作导入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导入列配置表';

CREATE TABLE `sys_address` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL,
  `parent` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_event` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `title` varchar(50) DEFAULT NULL COMMENT '接口名称',
  `request_uri` varchar(50) DEFAULT NULL COMMENT '接口url',
  `parameters` varchar(500) DEFAULT NULL COMMENT '参数',
  `method` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `client_host` varchar(50) DEFAULT NULL COMMENT '请求ip地址',
  `user_agent` varchar(300) DEFAULT NULL COMMENT '客户端类型',
  `status` int(10) DEFAULT NULL COMMENT '请求状态码',
  `del_flag` char(1) DEFAULT '0' COMMENT 'del_flag',
  `remarks` text COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL COMMENT 'create_by',
  `create_date` datetime NOT NULL COMMENT 'create_date',
  `update_by` varchar(50) DEFAULT NULL COMMENT 'update_by',
  `update_date` datetime NOT NULL COMMENT 'update_date',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sys_event';

DROP TRIGGER IF EXISTS `trigger_sys_user_off_online`;
DELIMITER ;;
CREATE TRIGGER `trigger_sys_user_off_online` AFTER DELETE ON `sys_user_online` FOR EACH ROW begin
   if OLD.`user_id` is not null then
      if not exists(select `user_id` from `sys_user_last_online` where `user_id` = OLD.`user_id`) then
        insert into `sys_user_last_online`
                  (`user_id`, `username`, `uid`, `host`, `user_agent`, `system_host`,
                   `last_login_timestamp`, `last_stop_timestamp`, `login_count`, `total_online_time`)
                values
                   (OLD.`user_id`,OLD.`username`, OLD.`id`, OLD.`host`, OLD.`user_agent`, OLD.`system_host`,
                    OLD.`start_timestsamp`, OLD.`last_access_time`,
                    1, (OLD.`last_access_time` - OLD.`start_timestsamp`));
      else
        update `sys_user_last_online`
          set `username` = OLD.`username`, `uid` = OLD.`id`, `host` = OLD.`host`, `user_agent` = OLD.`user_agent`,
            `system_host` = OLD.`system_host`, `last_login_timestamp` = OLD.`start_timestsamp`,
             `last_stop_timestamp` = OLD.`last_access_time`, `login_count` = `login_count` + 1,
             `total_online_time` = `total_online_time` + (OLD.`last_access_time` - OLD.`start_timestsamp`)
        where `user_id` = OLD.`user_id`;
      end if ;
   end if;
end
;;
DELIMITER ;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_dept`;
-- CREATE TABLE `sys_dept`  (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `parent_id` int(11) NOT NULL,
--   `simple_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `version` int(11) DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '总公司', 0, '总公司', NULL);
INSERT INTO `sys_dept` VALUES (2, '开发部', 1, '开发部', NULL);
INSERT INTO `sys_dept` VALUES (3, '战略部', 1, '战略部', NULL);
INSERT INTO `sys_dept` VALUES (4, '运营部', 1, '运营部', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_role`;
-- CREATE TABLE `sys_role`  (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `dept_id` int(11) DEFAULT NULL,
--   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `parent_id` int(11) DEFAULT NULL,
--   `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `version` int(11) DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, '超级管理员', 'admin', NULL);
INSERT INTO `sys_role` VALUES (2, 2, '编辑', 'edit', NULL);
INSERT INTO `sys_role` VALUES (3, 3, '测试员男', 'test_man', NULL);
INSERT INTO `sys_role` VALUES (4, 4, '测试员女', 'test_woman', NULL);

-- ----------------------------
-- Table structure for sys_small_pict_setup
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_small_pict_setup`;
-- CREATE TABLE `sys_small_pict_setup`  (
--   `small_pict_setup_id` int(11) NOT NULL AUTO_INCREMENT,
--   `business_class_nm` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `business_field_nm` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `small_pict_spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   PRIMARY KEY (`small_pict_setup_id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_small_pict_setup
-- ----------------------------
INSERT INTO `sys_small_pict_setup` VALUES (1, 'user', 'userHeadPictId', '150x150,200x200,100x100,330x330');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_user`;
-- CREATE TABLE `sys_user`  (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `birthday` datetime(0) DEFAULT NULL,
--   `create_time` datetime(0) DEFAULT NULL,
--   `dept_id` int(11) NOT NULL,
--   `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `role_id` int(11) NOT NULL,
--   `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
--   `sex` int(11) NOT NULL,
--   `status` int(11) NOT NULL,
--   `user_head_pict_id` int(11) DEFAULT NULL,
--   `version` int(11) DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admon', '2018-10-07 11:44:54', '2018-10-01 11:45:00', 1, '806352388@qq.com', '我是谁，我在哪', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '18813244587', 1, NULL, 1, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'edit', '2018-10-07 11:45:54', '2018-10-01 11:46:00', 1, '706352388@qq.com', '编辑', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '18813244589', 2, NULL, 1, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'test3', '2018-10-11 11:47:48', '2018-10-18 11:47:52', 3, '80232564@ww.cc', 'test3', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '17325869854', 3, NULL, 1, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, 'test4', '2018-10-25 11:48:56', '2018-10-01 11:48:59', 4, '265236@aa.cn', 'test4', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '13698569852', 4, NULL, 2, 1, NULL, NULL);

# CREATE TABLE `sys_menu`  (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   `is_menu` int(11) NOT NULL,
#   `is_open` int(11) NOT NULL,
#   `levels` int(11) NOT NULL,
#   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   `num` int(11) NOT NULL,
#   `p_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   `p_codes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   `status` int(11) NOT NULL,
#   `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#   PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'system', 1, 1, 1, '系统管理', 1, '0', '[0],', 1, '1', '/system');
INSERT INTO `sys_menu` VALUES (2, 'user', 1, 1, 2, '用户管理', 1, 'system', '[0],[system],', 1, '1', '/system/user');
INSERT INTO `sys_menu` VALUES (3, 'user_add', 0, 1, 3, '添加用户', 1, 'user', '[0],[system],[user],', 1, '1', '/system/user/add');
INSERT INTO `sys_menu` VALUES (4, 'user_edit', 0, 1, 3, '修改用户', 2, 'user', '[0],[system],[user],', 1, '1', '/system/user/edit');
INSERT INTO `sys_menu` VALUES (5, 'user_delete', 0, 1, 3, '删除用户', 3, 'user', '[0],[system],[user],', 1, '1', '/system/user/delete');
INSERT INTO `sys_menu` VALUES (6, 'role', 1, 1, 2, '角色管理', 1, 'system', '[0],[system],', 1, '1', '/system/role');
INSERT INTO `sys_menu` VALUES (7, 'role_add', 0, 1, 3, '添加角色', 1, 'role', '[0],[system],[role],', 1, '1', '/system/role/add');
INSERT INTO `sys_menu` VALUES (8, 'role_edit', 0, 1, 3, '修改角色', 2, 'role', '[0],[system],[role],', 1, '1', '/system/role/edit');
INSERT INTO `sys_menu` VALUES (9, 'role_delete', 0, 1, 3, '删除角色', 3, 'role', '[0],[system],[role],', 1, '1', '/system/role/delete');
INSERT INTO `sys_menu` VALUES (10, 'dept', 1, 1, 2, '部门管理', 1, 'system', '[0],[system],', 1, '1', '/system/dept');
INSERT INTO `sys_menu` VALUES (11, 'dept_add', 0, 1, 3, '添加部门', 1, 'dept', '[0],[system],[dept],', 1, '1', '/system/dept/add');
INSERT INTO `sys_menu` VALUES (12, 'dept_edit', 0, 1, 3, '修改部门', 2, 'dept', '[0],[system],[dept],', 1, '1', '/system/dept/edit');
INSERT INTO `sys_menu` VALUES (13, 'dept_delete', 0, 1, 3, '删除部门', 3, 'dept', '[0],[system],[dept],', 1, '1', '/system/dept/delete');

/*
 Navicat Premium Data Transfer

 Source Server         : nancy
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 24/10/2023 15:59:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `create_date` timestamp NOT NULL COMMENT '评论创建时间',
  `uid` int NOT NULL COMMENT '评论者id',
  `post_id` int NOT NULL COMMENT '对应帖子id',
  `reply_id` int NULL DEFAULT -1 COMMENT '被回复者id，默认-1(没有被回复者)',
  `top_id` int NULL DEFAULT -1 COMMENT '最顶部评论id，默认-1(没有顶部评论)',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `good` int NULL DEFAULT 0 COMMENT '点赞数量',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (4, '2023-09-21 12:10:26', 12, 31, -1, -1, '我胡汉三又回来了', 0);
INSERT INTO `comment` VALUES (5, '2023-09-21 12:12:04', 12, 14, -1, -1, '你好，管理原', 0);
INSERT INTO `comment` VALUES (6, '2023-09-21 13:19:48', 12, 31, -1, -1, '12312313', 1);
INSERT INTO `comment` VALUES (7, '2023-09-21 13:30:43', 32, 33, -1, -1, '请大家点点赞', 1);
INSERT INTO `comment` VALUES (8, '2023-09-21 13:30:56', 32, 33, -1, -1, '今天也要开心哟', 1);
INSERT INTO `comment` VALUES (10, '2023-09-22 02:20:22', 12, 33, -1, -1, '你好你好', 0);
INSERT INTO `comment` VALUES (11, '2023-09-22 02:34:33', 12, 33, -1, -1, '123', 2);
INSERT INTO `comment` VALUES (12, '2023-09-22 03:05:08', 12, 33, -1, -1, '321', 0);
INSERT INTO `comment` VALUES (13, '2023-09-22 03:33:04', 12, 33, -1, -1, '评论消息', 0);
INSERT INTO `comment` VALUES (14, '2023-09-22 03:33:25', 12, 33, 32, 8, '回复消息', 1);
INSERT INTO `comment` VALUES (15, '2023-09-22 07:12:36', 12, 33, 12, 11, '321', 0);
INSERT INTO `comment` VALUES (16, '2023-09-22 14:21:45', 31, 33, -1, -1, '你好我好大家好', 1);
INSERT INTO `comment` VALUES (17, '2023-09-22 14:27:20', 31, 33, 12, 8, '你要回复啥', 1);
INSERT INTO `comment` VALUES (19, '2023-09-22 14:47:26', 31, 33, 12, 8, '哇哈哈哈', 0);
INSERT INTO `comment` VALUES (20, '2023-09-22 14:53:05', 31, 32, -1, -1, '快来水楼！！', 1);
INSERT INTO `comment` VALUES (21, '2023-09-22 15:45:50', 31, 33, 12, 11, '提莫队长，正在待命！！！', 0);
INSERT INTO `comment` VALUES (22, '2023-09-22 15:49:07', 30, 33, 12, 11, '上山打老虎', 0);
INSERT INTO `comment` VALUES (23, '2023-09-22 15:51:15', 30, 33, 31, 8, '你好吗', 0);
INSERT INTO `comment` VALUES (24, '2023-09-22 15:52:56', 30, 34, -1, -1, '恭喜恭喜', 2);
INSERT INTO `comment` VALUES (25, '2023-09-22 15:53:07', 30, 32, 31, 20, '我来也', 0);
INSERT INTO `comment` VALUES (26, '2023-09-22 15:55:04', 30, 32, 30, 20, '再补充一点', 0);
INSERT INTO `comment` VALUES (27, '2023-09-22 15:55:42', 30, 32, 30, 20, '再补充两点', 0);
INSERT INTO `comment` VALUES (28, '2023-09-22 15:57:09', 30, 32, 31, 20, '我再补充第三点', 0);
INSERT INTO `comment` VALUES (29, '2023-09-22 15:58:22', 30, 31, 12, 4, '666', 0);
INSERT INTO `comment` VALUES (30, '2023-09-22 16:00:32', 30, 33, 30, 11, '宝塔镇河妖', 0);
INSERT INTO `comment` VALUES (31, '2023-09-22 16:01:45', 30, 32, 30, 20, '我再再再再补充第四点\n', 0);
INSERT INTO `comment` VALUES (32, '2023-09-22 16:04:41', 12, 34, -1, -1, '你干啥了？', 0);
INSERT INTO `comment` VALUES (33, '2023-09-22 16:04:54', 12, 34, 30, 24, '你真好', 2);
INSERT INTO `comment` VALUES (34, '2023-09-22 16:05:31', 12, 32, 30, 20, '哈哈哈哈', 0);
INSERT INTO `comment` VALUES (35, '2023-09-23 14:03:21', 32, 34, 12, 24, '同乐同乐', 1);
INSERT INTO `comment` VALUES (36, '2023-09-25 07:34:50', 12, 13, -1, -1, 'er', 0);
INSERT INTO `comment` VALUES (37, '2023-09-25 07:34:54', 12, 13, 12, 36, 'erer', 0);
INSERT INTO `comment` VALUES (38, '2023-09-25 07:35:09', 12, 13, 12, 36, 'ererer', 0);
INSERT INTO `comment` VALUES (39, '2023-10-12 12:51:15', 12, 35, -1, -1, 'abc', 0);
INSERT INTO `comment` VALUES (40, '2023-10-12 12:51:19', 12, 35, 12, 39, 'abc', 0);
INSERT INTO `comment` VALUES (41, '2023-10-12 12:58:11', 12, 35, 12, 39, '123', 0);
INSERT INTO `comment` VALUES (42, '2023-10-12 12:58:20', 12, 35, 12, 39, '456', 0);
INSERT INTO `comment` VALUES (43, '2023-10-12 12:58:40', 12, 34, 30, 24, '123', 0);
INSERT INTO `comment` VALUES (44, '2023-10-12 12:59:43', 12, 10, -1, -1, '123', 0);
INSERT INTO `comment` VALUES (45, '2023-10-12 13:07:00', 12, 10, 12, 44, '321', 0);
INSERT INTO `comment` VALUES (46, '2023-10-12 13:07:56', 12, 10, 12, 44, '2321', 0);
INSERT INTO `comment` VALUES (47, '2023-10-12 13:10:20', 12, 10, 12, 44, '211111', 0);
INSERT INTO `comment` VALUES (48, '2023-10-12 13:10:56', 12, 10, -1, -1, 'asd', 0);
INSERT INTO `comment` VALUES (49, '2023-10-12 13:11:02', 12, 10, 12, 48, 'sdfdsf', 0);
INSERT INTO `comment` VALUES (50, '2023-10-12 13:11:28', 12, 10, 12, 48, 'sdfsf', 0);
INSERT INTO `comment` VALUES (51, '2023-10-12 13:11:50', 12, 10, 12, 48, 'aaaaa', 0);
INSERT INTO `comment` VALUES (52, '2023-10-12 13:12:33', 12, 10, -1, -1, 'asda', 0);
INSERT INTO `comment` VALUES (53, '2023-10-12 13:12:39', 12, 10, 12, 52, '123123', 0);
INSERT INTO `comment` VALUES (54, '2023-10-12 13:14:32', 12, 35, -1, -1, '123', 0);
INSERT INTO `comment` VALUES (55, '2023-10-12 13:14:37', 12, 35, 12, 54, '123', 0);
INSERT INTO `comment` VALUES (56, '2023-10-12 13:15:15', 12, 35, 12, 54, '1231313', 0);
INSERT INTO `comment` VALUES (57, '2023-10-12 13:15:30', 12, 9, -1, -1, '123123213', 0);
INSERT INTO `comment` VALUES (58, '2023-10-12 13:15:36', 12, 9, 12, 57, '123131313', 0);
INSERT INTO `comment` VALUES (59, '2023-10-12 13:17:53', 12, 9, 12, 57, 'asdb', 0);
INSERT INTO `comment` VALUES (60, '2023-10-12 13:22:19', 32, 14, -1, -1, '123', 0);
INSERT INTO `comment` VALUES (61, '2023-10-12 13:22:41', 32, 14, 12, 5, '哈哈哈哈', 0);
INSERT INTO `comment` VALUES (62, '2023-10-12 13:29:26', 32, 14, 32, 5, '123', 0);
INSERT INTO `comment` VALUES (63, '2023-10-12 13:30:11', 32, 14, 32, 5, '234', 0);
INSERT INTO `comment` VALUES (64, '2023-10-12 13:33:59', 32, 14, 32, 60, '1233', 0);
INSERT INTO `comment` VALUES (65, '2023-10-12 13:34:07', 32, 14, 32, 60, '23333', 0);
INSERT INTO `comment` VALUES (66, '2023-10-12 13:34:34', 32, 8, -1, -1, '这是啊', 0);
INSERT INTO `comment` VALUES (67, '2023-10-12 13:34:37', 32, 8, 32, 66, '123', 0);
INSERT INTO `comment` VALUES (68, '2023-10-12 13:34:42', 32, 8, 32, 66, '123123', 0);
INSERT INTO `comment` VALUES (69, '2023-10-12 13:34:46', 32, 8, 32, 66, '12313', 0);
INSERT INTO `comment` VALUES (70, '2023-10-12 13:36:05', 32, 11, -1, -1, '123', 0);
INSERT INTO `comment` VALUES (71, '2023-10-12 13:36:07', 32, 11, 32, 70, '123', 0);
INSERT INTO `comment` VALUES (72, '2023-10-12 13:37:17', 32, 35, 12, 39, 'qwewqe', 0);
INSERT INTO `comment` VALUES (73, '2023-10-12 13:37:38', 32, 34, 12, 32, '123', 0);
INSERT INTO `comment` VALUES (74, '2023-10-12 13:37:57', 32, 34, 12, 32, '12213', 0);
INSERT INTO `comment` VALUES (75, '2023-10-12 13:38:35', 32, 34, 12, 32, '12313', 0);
INSERT INTO `comment` VALUES (76, '2023-10-12 13:39:39', 32, 35, 12, 54, 'abc', 0);
INSERT INTO `comment` VALUES (77, '2023-10-12 13:40:03', 32, 35, 12, 54, 'abc', 0);
INSERT INTO `comment` VALUES (78, '2023-10-12 13:40:45', 32, 34, 30, 24, '123', 0);
INSERT INTO `comment` VALUES (79, '2023-10-12 13:45:50', 32, 31, 12, 6, '2321', 0);
INSERT INTO `comment` VALUES (80, '2023-10-12 13:47:42', 32, 31, 12, 6, '算法', 0);
INSERT INTO `comment` VALUES (81, '2023-10-12 13:50:34', 32, 31, 12, 4, '666', 0);
INSERT INTO `comment` VALUES (82, '2023-10-12 13:51:27', 32, 31, 12, 4, '12313', 0);
INSERT INTO `comment` VALUES (83, '2023-10-12 13:53:32', 32, 31, 12, 4, '1231', 0);
INSERT INTO `comment` VALUES (84, '2023-10-12 13:53:49', 32, 31, 12, 6, '驱蚊器饿饿', 0);
INSERT INTO `comment` VALUES (85, '2023-10-12 13:54:03', 32, 7, -1, -1, '666', 0);
INSERT INTO `comment` VALUES (86, '2023-10-12 13:55:39', 32, 7, 32, 85, '1', 0);
INSERT INTO `comment` VALUES (87, '2023-10-12 13:56:39', 32, 7, 32, 85, '2', 0);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '帖子主键id',
  `create_id` int NULL DEFAULT NULL COMMENT '帖子创建者id',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '帖子创建时间',
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '帖子内容',
  `view` int NULL DEFAULT 0 COMMENT '帖子浏览量',
  `good` int NULL DEFAULT 0 COMMENT '帖子点赞量',
  `discuss` int NULL DEFAULT 0 COMMENT '帖子评论量',
  `collect` int NULL DEFAULT 0 COMMENT '帖子收藏量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (6, 12, '2023-09-14 10:57:49', '高能贴', '用毒蛇的毒毒毒蛇毒蛇会被毒毒死吗', 19, 10, 0, 0);
INSERT INTO `post` VALUES (7, 12, '2023-09-14 11:37:46', '好消息', '江南皮革厂倒闭了', 4, 3, 3, 1);
INSERT INTO `post` VALUES (8, 12, '2023-09-14 12:06:09', '什么是牛马论坛', '全都是牛马', 3, 1, 4, 0);
INSERT INTO `post` VALUES (9, 12, '2023-09-14 12:06:58', '123', '123123', 3, 0, 3, 0);
INSERT INTO `post` VALUES (10, 12, '2023-09-14 12:07:38', '12312', '3123131', 1, 1, 10, 0);
INSERT INTO `post` VALUES (11, 12, '2023-09-15 13:34:45', '这是一个测试', '让我来', 3, 1, 2, 0);
INSERT INTO `post` VALUES (12, 12, '2023-09-17 05:09:10', 'token测试', '我要测试token请求头', 3, 1, 0, 1);
INSERT INTO `post` VALUES (13, 12, '2023-09-17 05:12:53', 'token测试', '', 6, 2, 3, 1);
INSERT INTO `post` VALUES (14, 30, '2023-09-18 12:35:37', '我是管理原', '哈哈哈哈', 11, 0, 7, 1);
INSERT INTO `post` VALUES (31, 30, '2023-09-19 11:13:46', '没想到吧', '我又回来啦！！！！！！！！！', 45, 1, 9, 1);
INSERT INTO `post` VALUES (32, 31, '2023-09-19 14:38:25', '新人驾到', '大家好，我叫小鹿。', 148, 2, 7, 1);
INSERT INTO `post` VALUES (33, 32, '2023-09-21 13:26:48', '大家好', '我是麦克，麦乐克', 54, 1, 16, 1);
INSERT INTO `post` VALUES (34, 31, '2023-09-22 14:14:22', '大功告成', '好耶！！！！！！！！！！1', 27, 2, 9, 2);
INSERT INTO `post` VALUES (35, 12, '2023-10-12 12:51:04', '123', '123', 7, 0, 10, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户名',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `signature` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '这个人很懒，什么都没留下。' COMMENT '个性签名',
  `head_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'D:\\PersonalProject\\Forum\\forum_front\\src\\assets\\img\\default.jpg' COMMENT '头像地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, 'nancy', '小牛马', '123', '这个人很懒，什么都没留下', 'a13b2c1181fc47febe678c65516e5693');
INSERT INTO `user` VALUES (30, 'admin', 'boss', '123', '我是老大', 'default');
INSERT INTO `user` VALUES (31, 'lucy', '露西', '123', 'I\'m lucy!', 'a6a32cffac104e3aa47b436a734d7ede');
INSERT INTO `user` VALUES (32, 'mike', '麦克', '123', '麦乐克', '13dbbb4aabe44131a691a2383bd6a70b');

SET FOREIGN_KEY_CHECKS = 1;

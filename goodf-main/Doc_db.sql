/*
Navicat MySQL Data Transfer

Source Server         : hospital
Source Server Version : 50736
Source Host           : 120.55.125.213:3306
Source Database       : Doc_db

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2022-07-11 09:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地区表的id',
  `area` varchar(255) NOT NULL COMMENT '地区表的地区名称',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='这个是地区表，显示所有地区。';

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '广西');
INSERT INTO `area` VALUES ('2', '北京');
INSERT INTO `area` VALUES ('3', '上海');
INSERT INTO `area` VALUES ('4', '广东');
INSERT INTO `area` VALUES ('5', '河北');
INSERT INTO `area` VALUES ('6', '天津');
INSERT INTO `area` VALUES ('7', '内蒙古');
INSERT INTO `area` VALUES ('8', '辽宁');
INSERT INTO `area` VALUES ('9', '吉林');
INSERT INTO `area` VALUES ('10', '黑龙江');

-- ----------------------------
-- Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `tel` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '注册的手机',
  `create_time` datetime NOT NULL COMMENT '发送验证码时间，五分钟内发一次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2061332483 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('2061332482', '18476263262', '2022-07-05 14:40:03');

-- ----------------------------
-- Table structure for `deparment`
-- ----------------------------
DROP TABLE IF EXISTS `deparment`;
CREATE TABLE `deparment` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室表ID',
  `dep_name` varchar(255) NOT NULL COMMENT '科室表名称',
  `dc_id` int(11) NOT NULL COMMENT '所属科室分类id',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='科室';

-- ----------------------------
-- Records of deparment
-- ----------------------------
INSERT INTO `deparment` VALUES ('1', '神经内科', '1');
INSERT INTO `deparment` VALUES ('2', '心胸外科', '2');
INSERT INTO `deparment` VALUES ('3', '妇科', '4');
INSERT INTO `deparment` VALUES ('4', '小儿消化科', '3');
INSERT INTO `deparment` VALUES ('5', '眼科', '5');
INSERT INTO `deparment` VALUES ('6', '皮肤科', '6');

-- ----------------------------
-- Table structure for `depclass`
-- ----------------------------
DROP TABLE IF EXISTS `depclass`;
CREATE TABLE `depclass` (
  `dc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室分类表ID',
  `dc_name` varchar(255) NOT NULL COMMENT '科室分类名',
  PRIMARY KEY (`dc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='科室分类';

-- ----------------------------
-- Records of depclass
-- ----------------------------
INSERT INTO `depclass` VALUES ('1', '外科');
INSERT INTO `depclass` VALUES ('2', '内科');
INSERT INTO `depclass` VALUES ('3', '儿科');
INSERT INTO `depclass` VALUES ('4', '妇产科');
INSERT INTO `depclass` VALUES ('5', '五官科');
INSERT INTO `depclass` VALUES ('6', '皮肤科');

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
  `d_name` varchar(255) NOT NULL COMMENT '医生姓名',
  `h_id` int(11) NOT NULL COMMENT '医院id',
  `h_name` varchar(255) NOT NULL COMMENT '医院姓名',
  `dc_id` int(11) NOT NULL COMMENT '科室分类id',
  `dc_name` varchar(255) NOT NULL COMMENT '科室分类名',
  `dep_id` int(11) NOT NULL COMMENT '科室id',
  `dep_name` varchar(255) NOT NULL COMMENT '科室名',
  `re_money` double NOT NULL COMMENT '挂号费',
  `start_time` datetime DEFAULT NULL COMMENT '出诊时间',
  `end_time` datetime DEFAULT NULL COMMENT '医生出诊时间',
  `doc_photo` varchar(255) DEFAULT NULL COMMENT '医生照片',
  `doc_introduction` varchar(255) DEFAULT NULL COMMENT '医生简介',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='医生';

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '蒋海山', '1', '南方医科大学南方医院', '1', '内科', '1', '神经内科', '120', '2022-07-06 09:55:32', '2022-07-12 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术。');
INSERT INTO `doctor` VALUES ('2', '王永勇', '2', '广西医科大学第一附属医院', '2', '外科', '2', '心胸外科', '12', '2022-07-06 16:42:34', '2022-07-13 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('3', '颜伟慧', '3', '上海交通大学医学院附属新华医院', '3', '儿科', '3', '儿童消化科', '40', '2022-07-06 16:44:17', '2022-07-20 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('4', '庞战军', '4', '南方医科大学南方医院', '4', '妇产科', '4', '妇科', '12', '2022-07-06 09:46:58', '2022-07-11 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('5', '王佳薇', '5', '河北医科大学第二医院', '5', '五官科', '5', '眼科', '15', '2022-07-06 15:06:04', '2022-07-11 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('6', '罗素菊', '6', '天津医科大学总医院', '6', '皮肤科', '6', '皮肤科', '30', '2022-07-06 15:09:08', '2022-07-21 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('10', '多啦A蒙', '4', '北京协和医院', '4', '妇产科', '3', '妇科', '300', '2022-07-06 12:00:00', '2022-07-12 00:00:00', 'https://gulimall-liruimin.oss-cn-guangzhou.aliyuncs.com/2022-07-01/8ea4ab1e-37e9-4f34-85b5-c29980d36da5_1.jpg', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('11', '123', '2', '123', '1', '123', '1', '123', '1', '2022-07-06 09:53:04', '2022-07-12 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('12', '321', '2', '123', '1', '123', '1', '123', '123', '2022-07-06 10:38:15', '2022-07-14 00:00:00', 'https://n4.hdfimg.com/g5/M07/50/85/0IYBAF_0HFWAYlbtAAmoYrXJ_xg466_200_200_1.png?9e79', '蒋医生毕业于著名的南方以可大学，已在神经科工作了十几年，具有丰富的经验和精湛的技术');
INSERT INTO `doctor` VALUES ('13', 'wqeqw', '2', '广西医科大学附属医院', '2', '内科', '2', '心胸外科', '123', '2022-07-06 11:32:50', '2022-07-19 11:32:51', 'https://gulimall-liruimin.oss-cn-guangzhou.aliyuncs.com/2022-07-06/924a707a-f80f-4c2a-8961-92272d93cb00_dog.jpg', '312312');
INSERT INTO `doctor` VALUES ('14', '雷', '1', '南方医科大学南方医院', '4', '妇产科', '3', '妇科', '20', '2022-07-08 09:00:00', '2022-12-16 17:00:00', 'https://gulimall-liruimin.oss-cn-guangzhou.aliyuncs.com/2022-07-08/8d8232e8-8fcf-4725-8d5d-f389b0625d56_微信截图_20220420195657.png', '好棒大夫');

-- ----------------------------
-- Table structure for `hospital`
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医院id',
  `h_name` varchar(255) NOT NULL COMMENT '医院姓名',
  `tel` varchar(255) DEFAULT NULL COMMENT '医院电话',
  `a_id` int(11) DEFAULT NULL COMMENT '医院所属地址的id',
  `area` varchar(255) DEFAULT NULL COMMENT '医院所属地区',
  `h_bi` varchar(8000) DEFAULT NULL COMMENT '医院的简介',
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='医院';

-- ----------------------------
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES ('1', '南方医科大学南方医院', '020-61641888', '4', '广东', '南方医院系南方医科大学（原第一军医大学）第一附属医院、第一临床医学院，是一所集医疗、教学、科研和预防保健为一体的大型综合性三级甲等医院，全国百佳医院。医院创建于1941年，2004年8月随大学由军队移交广东省。\r\n　　医院坐落在广东省广州市白云山东麓麒麟岗上，拥有广东省最大的花园式院区，曾被评为“全国部门造林绿化400佳单位”。现占地面积20.3万平方米，建筑面积45.8万平方米；展开床位2225张，医疗设备总值近10亿元；2010年门急诊量265万余人次，年收容量6.2万余人次。');
INSERT INTO `hospital` VALUES ('2', '广西医科大学附属医院', '0771-12580-6', '1', '广西', '广西医科大学第一附属医院创建于1934年，是广西首家三级甲等综合医院，广西临床医疗、医学教育、医学研究、医疗保健的中心。是全国文明单位、全国卫生计生系统先进集体、全国百佳医院、全国百姓放心示范医院、全国人文爱心医院、全国爱婴医院；是中国-东盟医院合作联盟主席单位、中国-东盟医学人才培养合作单位。\r\n　　医院综合实力连续9年位列复旦“中国医院综合排行榜”华南前三，其中进入华南区前五的专科34个（共收录40个）。连续多年入选全国综合医院排行榜TOP100。2018年全国三级公立医院绩效考核成绩A+，排名全国前6%，广西第一。\r\n医院拥有国家临床重点专科12个，广西临床重点专科（含建设项目）22个，广西各类重点学科28个。医院编制床位2750张，临床科室46个，病区70个，医技科室19个，2019年门急诊量275万人次，出院患者12万人。\r\n　　医院在职职工4306人。其中：博士445人，硕士1003人，高级职称944人，其中正高职称402人，副高职称542人，国家级高层次人才1人，国家突出贡献中青年专家2人，享受国务院特殊津贴专家40人，省部级人才59人，厅局级人才75人。300多人留学美国、英国、日本等30多个国家。中华医学会全国委员54人，广西医学各专业委员会主任委员49人、副主任委员99人。');
INSERT INTO `hospital` VALUES ('3', '上海交通大学新华医院', '021-25078999', '3', '上海', '新华医院是上海交通大学医学院附属教学医院，创建于1958年，是新中国成立以来上海自行设计建设的首家综合性医院。通过新华人五十多年的励精图治，已成为一所学科门类齐全、具有专业特色，集医教研和管理于一体的现代化综合性医院。医院是全国先进集体、全国卫生系统先进单位、全国卫生系统卫生文化建设先进单位和上海市文明单位。 自上世纪七十年代起，医院开展与其它国家的医疗机构共建合作项目，与国外逾20所大学和医院建立友好合作和学术交流，包括美国芝加哥大学普里茨克尔医学院、美国威斯康辛大学医学院、加拿大麦克马斯特大学、加拿大渥太华大学医学院、法国巴黎卫生局及其下属数十家医院等。各类国际交流活动蓬勃开展，既为医院的发展增添了活力，也充分展示了中国医院良好形象。');
INSERT INTO `hospital` VALUES ('4', '北京协和医院', '010-69156114', '2', '北京', '北京协和医院是集医疗、教学、科研于一体的现代化综合三级甲等医院，是国家卫生健康委指定的全国疑难重症诊治指导中心，最早承担干部保健和外宾医疗任务的医院之一，也是高等医学教育和住院医师规范化培训国家级示范基地，临床医学研究和技术创新的国家级核心基地。以学科齐全、技术力量雄厚、特色专科突出、多学科综合优势强大享誉海内外。在国家三级公立医院绩效考核中两次排名第一，在复旦大学医院管理研究所公布的“中国医院排行榜”中连续十二年名列榜首。\r\n　　医院建成于1921年，由洛克菲勒基金会创办。建院之初，就志在“建成亚洲最好的医学中心”。一百年来，形成了“严谨、求精、勤奋、奉献”的协和精神和兼容并蓄的特色文化风格，创立了“三基”、“三严”的现代医学教育理念，形成了以“教授、病案、图书馆”著称的协和“三宝”，培养造就了张孝骞、林巧稚等一代医学大师和多位中国现代医学的领军人物，创建了当今知名的10余家大型综合及专科医院。在总结近百年发展经验的基础上，创新性提出了“待病人如亲人，提高病人满意度；待同事如家人，提高员工幸福感”的办院理念；提炼出了“学术、品质、人文”的百年协和内涵；贯彻落实十九届四中全会精神，深入推进医疗服务体系、人才培养体系、科技创新体系、精细管理体系、开放协作体系、党建文化体系等“六大体系”建设。');
INSERT INTO `hospital` VALUES ('5', '河北医科大学第二医院', '0311-87046901', '5', '河北', '河北医科大学第二医院是一所具有百年历史的三级甲等综合医院，医疗技术水平和服务能力居于省内前列。医院党委始终高举习近平新时代中国特色社会主义思想伟大旗帜，以政治建设为引领，全面加强党的建设，认真贯彻落实党委领导下的院长负责制，建立了党委集体领导和个人分工负责相结合的议事决策机制，发挥了党委“把方向、管大局、作决策、促改革、保落实”的领导作用。\r\n\r\n    医院设有党委职能部门3个、党总支10个、党支部95个，基本实现了支部建在科室，充分发挥了基层党组织的政治核心、战斗堡垒作用和党员的先锋模范作用。医院党委牢牢把握新时代党建工作总要求，将党建工作和业务工作深度融合，以党建引领医院发展，推动医院各项事业高质量发展。');
INSERT INTO `hospital` VALUES ('6', '天津医科大学总医院', '022-60362255', '6', '天津', '天津医科大学总医院始建于1946年，是天津市最大的集医疗、教学、科研、预防为一体的综合性大学医院和天津市西医医学中心，医院为三级甲等医院、全国“百佳医院”、全国百姓放心示范医院。从引进我国第一台大型贵重精密医疗仪器设备CT机，到90年代引进的数字胃肠机、各种X光机、CT、MRI、ECT、药物浓度检测仪、流式细胞仪、激光共聚焦显微镜、手术导航系统，该院的医疗设备水平一直走在全国前列。进入21世纪，该院又先后引进64排CT扫描和PET—CT。伴随着该院第三住院大楼的竣工启用，该市唯一一间术中可以进行核磁检查的磁共振手术室和能够集中心脏外科、神经外科、普外科等多科室手术治疗的杂交手术室也一并投入使用。先进的设备、领先的技术为该院医疗、教学、科研整体水平的持续提高奠定了坚实的基础。');
INSERT INTO `hospital` VALUES ('7', '内蒙古医科大学附属医院', '0471-3451345', '7', '内蒙古', '内蒙古医科大学附属医院始建于1958年，经过55年的发展建设，已成为内蒙古自治区一所集医疗、教学、科研和预防保健为一体的现代化综合性三级甲等医院。\r\n　　医院现开放床位1804张，年门急诊量124万人次，年出院人数70045人次，年完成手术31350台例。全院在职职工2896人，其中正高级职称296人，副高级职称375人。目前具有博士学历卫生技术人员106人，享受国务院政府特殊津贴专家41人。\r\n　　医院现有36个临床科室，15个医技科室，44个病区。拥有神经外科学、普通外科、蒙医科3个国家临床重点专科；呼吸内科学、普通外科学、胸外科学、妇科学、耳鼻咽喉科学、麻醉学6个自治区临床医学领先学科；消化内科学、神经外科学、产科学、儿科学、放射肿瘤学、医学影像学、临床检验学7个自治区临床医学重点学科和1个自治区基础医学和预防医学重点实验室、以及自治区首家“分子与功能影响工程技术研究中心”在医院挂牌成立。2009年我院挂牌成为内蒙古医学院第一临床医学院；2013年内蒙古医科大学口腔医学院在我院成立并揭牌。');
INSERT INTO `hospital` VALUES ('8', '中国医科大学附属盛京医院', '024-96615', '8', '辽宁', '中国医科大学附属盛京医院是一所现代化大型综合性、数字化大学附属医院。医院南湖院区位于沈阳市和平区三好街，滑翔院区位于沈阳市铁西区滑翔路，总占地面积13万平方米，总建筑面积54万平方米。正在建设中的沈北院区位于沈阳市沈北新区蒲河大道，总占地面积23.5万平方米。建设中的沈本医药研究教育发展基地位于沈溪新城歪头山松木堡村，使用权面积62.58万平方米。\r\n　　医院标志涵盖了“中国医科大学”英文缩写“CMU ”，它由“盛京”汉语拼音首字母 “SJ”和医疗行业标识“十”字变形组合而成，它既似一片象征生命与希望的叶子，又似一只象征和平与健康的鸽子。两条律动的曲线象征着盛京人上下一致，团结奋进的拼搏精神。标志的颜色由代表生命的“绿色”、代表科技的“蓝色”和代表纯洁的“白色”组成，寓意着科技蓝托起生命绿。');
INSERT INTO `hospital` VALUES ('9', '吉林大学第一医院', '0431-88782222', '9', '吉林', '吉林大学第一医院坐落在吉林省长春市，始建于1949年， 初始为中国人民解放军第一军医大学，曾先后定名为长春医学院内科学院、吉林医科大学第一临床学院、白求恩医科大学第一临床学院，2000年白求恩医科大学与吉林大学合并，更名为吉林大学第一医院。 \r\n　　风雨兼程六十余载，吉林大学第一医院现已发展成为集医疗、教学、科研、预防、保健、康复为一体的大型综合医院，是吉林省首家达标的三级甲等医院、全国百佳医院和“爱婴医院”。在2014年中国医学科学院公布的中国医院影响力综合排名中，进入了前50强，名列第32位。在丁香园医学网站2014 年度中国医疗机构最佳雇主综合排名中，名列第7位。 \r\n　　医院现有2个院区，总建筑面积54.95万平方米，现有职工8600余人，编制床位5939张，年门急诊量369万人次，出院病人21.6万人次。2009年开始组建医院集团，现已拥有41个成员单位，覆盖吉林省29个市县，打造出了一张以吉林大学第一医院为核心、纵跨全省的医疗协作网络。');
INSERT INTO `hospital` VALUES ('10', '哈尔滨医科大学附属医院', '0451-85556000', '10', '黑龙江', '哈尔滨医科大学附属第一医院始建于1949年，经-过60年的发展，由一所230张床位、395名职工的小型医院，发展成为集医疗、教学、科研为一体的大型综合性医院。\r\n    医院是黑龙江省最大的医疗中心，医疗专科设置齐全，技术力量雄厚。医院设有门诊部、住院处、急诊急救中心、远程会诊中心等业务机构，共有93个临床科室，54个医技科室，现有员工5111人，副高职称以上专业人员908人，拥有开放床位4,398张。2012年门、急诊量达198.8万人次，住院手术7.25万例。医院同时承担着副省级以上领导及重要外宾的保健、诊疗任务。');

-- ----------------------------
-- Table structure for `registration`
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `num_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂号表id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `h_id` int(11) DEFAULT NULL COMMENT '医院id',
  `h_name` varchar(255) DEFAULT NULL COMMENT '医院名',
  `d_id` int(11) DEFAULT NULL COMMENT '医生id',
  `d_name` varchar(255) DEFAULT NULL COMMENT '医生名称',
  `dep_id` int(11) DEFAULT NULL COMMENT '科室表id',
  `dep_name` varchar(255) DEFAULT NULL COMMENT '科室表名',
  `dc_id` int(11) DEFAULT NULL COMMENT '科室分类id',
  `dc_name` varchar(255) DEFAULT NULL COMMENT '科室分类名',
  `time` datetime DEFAULT NULL COMMENT '就诊时间',
  `payment_status` varchar(255) DEFAULT NULL COMMENT '支付状态',
  `visit_status` varchar(255) DEFAULT NULL COMMENT '就诊状态',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2094096387 DEFAULT CHARSET=utf8mb4 COMMENT='挂号';

-- ----------------------------
-- Records of registration
-- ----------------------------
INSERT INTO `registration` VALUES ('-707690494', '雷金洪', '1', '南方医科大学南方医院', '1', '蒋海山', '1', '神经内科', '1', '内科', '2022-07-08 10:23:46', '已支付', '未就诊', '1792135169');
INSERT INTO `registration` VALUES ('-368046078', '雷金洪', '2', '广西医科大学第一附属医院', '2', '王永勇', '2', '心胸外科', '2', '外科', '2022-07-08 10:42:46', '已支付', '未就诊', '1792135169');
INSERT INTO `registration` VALUES ('1', '雷金洪', '2', 'qwe', '1', 'qwe', '1', 'qwe', '1', 'qwe', '2022-06-24 09:54:59', '已支付', '未就诊', '1137803265');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '用户地址',
  `tel` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `superuser` int(11) NOT NULL COMMENT '用户权限',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `gitee_id` varchar(32) DEFAULT NULL COMMENT 'gitee第三方登录获取的id，有则表明使用gitee授权登录，反之没有',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1792135170 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '雷金洪', 'BBBbbb111', 'mbq', '18027786634', '1', null, '11111');
INSERT INTO `user` VALUES ('2', 'liruimin', '$2a$10$PSGJVxMSD/pF9RUVniayjOi.CbnBpUu6RJOWi4T7e0TLRrokj7wXa', '广东省湛江市岭南师范学院', '13025761662', '1', null, '');
INSERT INTO `user` VALUES ('3', '×××', '×××', null, null, '1', null, '10279622');
INSERT INTO `user` VALUES ('4', '×××', '×××', null, null, '1', null, '9792397');
INSERT INTO `user` VALUES ('5', '×××', '×××', null, null, '1', null, '10280352');
INSERT INTO `user` VALUES ('6', '×××', '×××', null, null, '1', null, 'null');
INSERT INTO `user` VALUES ('7', '04116', '$2a$10$MYFtA9cKTvSq885WohCfFOgNMsVcGh4xLxCdwaAye7vOJm1RwCC/a', '去去去', '13048212971', '1', null, null);
INSERT INTO `user` VALUES ('1792135169', '雷金洪', '$2a$10$w.1ycP0OPrswvcxH/aD10.OJtFO8IXsZ.gQm.k3jcrI56Ina9zqDi', '岭南师范学院', '13048212972', '1', null, null);

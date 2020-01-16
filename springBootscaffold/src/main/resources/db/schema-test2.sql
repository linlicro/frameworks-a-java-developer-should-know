DROP TABLE IF EXISTS `sc_money`;
CREATE TABLE `sc_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `acount_id` int(11) NOT NULL COMMENT '账号id',
  `basic` int(33) DEFAULT NULL COMMENT '基本工资',
  `reward` int(33) DEFAULT NULL COMMENT '奖金',
  `punishment` int(33) DEFAULT NULL COMMENT '惩罚金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
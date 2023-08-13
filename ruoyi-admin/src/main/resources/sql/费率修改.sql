ALTER TABLE qs_alipay.alipay_user_rate DROP INDEX `select`;
ALTER TABLE qs_alipay.alipay_user_rate MODIFY COLUMN channelId TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '渠道数据';
ALTER TABLE qs_alipay.alipay_chanel_fee ADD rotation varchar(100) NULL;
ALTER TABLE qs_alipay.alipay_chanel_fee MODIFY COLUMN rotation BIGINT DEFAULT 5 NULL;

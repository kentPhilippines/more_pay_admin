ALTER TABLE qs_alipay.alipay_user_rate DROP INDEX `select`;
ALTER TABLE qs_alipay.alipay_user_rate MODIFY COLUMN channelId TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '渠道数据';

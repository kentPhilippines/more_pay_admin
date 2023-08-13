package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 渠道费率对象 alipay_chanel_fee
 *
 * @author ruoyi
 * @date 2020-05-15
 */
@Data
public class AlipayChanelFee extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String channelId;
    private String productId;
    private String impl;
    private String channelRFee;
    private String channelDFee;
    private String channelNo;
    private Date submitTime;
    private String rotation;
    private Integer status;
}

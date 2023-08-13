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
    /**
     * 数据id
     */
    private Long id;

    /**
     * 渠道ID
     */
    @Excel(name = "渠道ID")
    private String channelId;

    /**
     * 产品id
     */
    @Excel(name = "产品id")
    private String productId;
    /**
     * 实体类对应关系
     */
    @Excel(name = "实体类对应关系")
    private String impl;
    private String channelRFee;
    private String channelDFee;
    private String channelNo;
    /**
     * null
     */
    @Excel(name = "null", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 1为可用  2为不可用
     */
    @Excel(name = "1为可用  2为不可用")
    private Integer status;
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("channelId", getChannelId())
                .append("productId", getProductId())
                .append("impl", getImpl())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .toString();
    }
}

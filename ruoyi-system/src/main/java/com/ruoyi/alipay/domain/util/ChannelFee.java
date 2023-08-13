package com.ruoyi.alipay.domain.util;

import com.ruoyi.alipay.domain.AlipayChanelFee;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
@Data
public class ChannelFee extends AlipayChanelFee {
    /**
     * 优先级
     */
    String priority  = "1";
    /**
     * 轮训次数
     */
    Integer rotation = 5;
}

package com.ruoyi.alipay.domain.util;

import lombok.Data;

@Data
public class McFee {
    /**
     * 渠道id
     */
    String channelId ;
    /**
     * 优先级
     */
    Integer priority = 1 ;

    Boolean checked;

}

package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户详情对象 alipay_user_info
 *
 * @author kent
 * @date 2020-03-21
 */
@Data
public class AlipayUserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String findUserId;
    private String todayDealAmount;
    private Integer autoWit;
    private String enterWitOpen;
    private String interFace;
    private String backPassword;
    private String fundPassword;
    private String ipStr;
    private String isBind;
    private Long sysUserId;
    private String loginName;
    private Integer colorIndex;

    private Integer limitBalance;
    /**
     * 1表示查询子代理
     */
    private String type;
    /**
     * 数据id
     */
    private Long id;
    /**
     * 用户id【登录账号】
     */
    private String userId;
    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String userName;
    /**
     * shiro加密秘钥【登录】
     */
    @Excel(name = "shiro加密秘钥【登录】")
    private String password;
    /**
     * shiro加密秘钥【资金】【商户则为交易秘钥】
     */
    @Excel(name = "shiro加密秘钥【资金】【商户则为交易秘钥】")
    private String payPasword;
    /**
     * 秘钥加密盐值【加密算法】
     */
    @Excel(name = "秘钥加密盐值【加密算法】")
    private String salt;
    /**
     * 用户类型,商户1 码商2
     */
    @Excel(name = "用户类型,商户1 码商2")
    private Integer userType;
    /**
     * 当前用户总开关 1开启0关闭【码商商户后台控制,该值只能在后台显示】
     */
    @Excel(name = "当前用户总开关 1开启0关闭【码商商户后台控制,该值只能在后台显示】")
    private Integer switchs;
    /**
     * 组群备注
     */
    @Excel(name = "组群备注")
    private String userNode;
    /**
     * 邮箱【修改账号秘钥邮件发送地址】
     */
    @Excel(name = "邮箱【修改账号秘钥邮件发送地址】")
    private String email;
    /**
     * 代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】
     */
    @Excel(name = "代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】")
    private String agent;
    /**
     * 是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】
     */
    @Excel(name = "是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】")
    private String isAgent;

    /**
     * 信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分
     */
    @Excel(name = "信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分")
    private Double credit;

    /**
     * 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】
     */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】")
    private Integer receiveOrderState;

    /**
     * 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】
     */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】")
    private Integer remitOrderState;

    /**
     * QQ联系方式
     */
    @Excel(name = "QQ联系方式")
    private String QQ;

    /**
     * 小飞机
     */
    @Excel(name = "小飞机")
    private String telegram;

    /**
     * skype
     */
    @Excel(name = "skype")
    private String skype;

    /**
     * 最后一次数据修改时间
     */
    @Excel(name = "最后一次数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 数据是否可用:1数据可用2数据无用
     */
    @Excel(name = "数据是否可用:1数据可用2数据无用")
    private Integer status;

    /**
     * 商户私钥
     */
    @Excel(name = "商户私钥")
    private String privateKey;

    /**
     * 商户公钥
     */
    @Excel(name = "商户公钥")
    private String publicKey;

    /**
     * 最小金额
     */
    @Excel(name = "最小金额")
    private Double minAmount;

    /**
     * 最大金额
     */
    @Excel(name = "最大金额")
    private Double maxAmount;

    /**
     * 最大金额
     */
    @Excel(name = "总金额限制")
    private Double totalAmount;

    /**
     * 下单次数
     */
    @Excel(name = "下单次数")
    private Integer timesTotal = 0;

    /**
     * 限制时间-开始时间
     */
    @Excel(name = "限制时间-开始时间")
    private String startTime;

    /**
     * 限制时间-结束时间
     */
    @Excel(name = "限制时间-结束时间")
    private String endTime;
    /**
     * 代付ip
     */
    @Excel(name = "代付ip")
    private String witip;

    /**
     * 交易IP地址
     */
    private String dealUrl;
    private String balanceUrl;

    private String qrRechargeList;

    private String queueList;


    @Excel(name = "货币类型")
    private String currency;
}

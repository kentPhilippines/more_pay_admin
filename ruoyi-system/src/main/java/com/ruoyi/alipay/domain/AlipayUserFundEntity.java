package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户资金账户对象 alipay_user_fund
 *
 * @author kent
 * @date 2020-03-17
 */
@Data
public class AlipayUserFundEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String userNode;
    private Double minAmount;
    private String idStr;
    @Excel(name = "最大金额")
    private Double maxAmount;
    private Integer colorIndex;
    private Integer timesTotal;
    private Integer limitBalance;
    private Integer receiveOrderState;
    private Integer remitOrderState;
    private Integer switchs;
    /**
     * 数据id
     */
    private Long id;

    /**
     * 账户id【登录账户】
     */
    @Excel(name = "账户id【登录账户】")
    private String userId;

    /**
     * 账户昵称【登录账户名】
     */
    @Excel(name = "账户昵称【登录账户名】")
    private String userName;

    /**
     * 现金账户【当前可取现】
     */
    @Excel(name = "现金账户【当前可取现】")
    private Double cashBalance;

    /**
     * 充值点数
     */
    @Excel(name = "充值点数")
    private Double rechargeNumber;

    /**
     * 冻结账户
     */
    @Excel(name = "冻结账户")
    private Double freezeBalance;

    /**
     * 可取现账户金额【码商账户余额=冻结金额+现金账户+充值点数】
     */
    @Excel(name = "可取现账户金额【码商账户余额=冻结金额+现金账户+充值点数】")
    private Double accountBalance;
    @Excel(name = "历史代付")
    private Double sumWitAmount;
    @Excel(name = "当日代付")
    private Double todayWitAmount;

    /**
     * 累计交易额
     */
    @Excel(name = "累计交易额")
    private Double sumDealAmount;
    @Excel(name = "预授额度")
    private BigDecimal quota;

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    /**
     * 累计充值金额【充值成功时统计记录】
     */
    @Excel(name = "累计充值金额【充值成功时统计记录】")
    private Double sumRechargeAmount;

    /**
     * 累计利润金额
     */
    @Excel(name = "累计利润金额")
    private Double sumProfit;

    /**
     * 累计代理商利润【如果当前账户为商户则该数据为0】
     */
    @Excel(name = "累计代理商利润【如果当前账户为商户则该数据为0】")
    private Double sumAgentProfit;

    /**
     * 累计接单笔数
     */
    @Excel(name = "累计接单笔数")
    private Long sumOrderCount;

    /**
     * 当日接单金额
     */
    @Excel(name = "当日接单金额")
    private Double todayDealAmount;

    /**
     * 当日接单利润【代理利润+接单利润=当日利润】
     */
    @Excel(name = "当日接单利润【代理利润+接单利润=当日利润】")
    private Double todayProfit;
    private Double todayOtherWitAmount;

    /**
     * 当日接单笔数
     */
    @Excel(name = "当日接单笔数")
    private Long todayOrderCount;

    /**
     * 当日代理商利润【如果当前账户为商户则该数据为0】
     */
    @Excel(name = "当日代理商利润【如果当前账户为商户则该数据为0】")
    private Double todayAgentProfit;

    /**
     * 用户类型,商户1 码商2
     */
    @Excel(name = "用户类型,商户1 码商2")
    private String userType;

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
    private String currency;//账户货币
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
     * 数据版本号【数据修改锁】
     */
    @Excel(name = "数据版本号【数据修改锁】")
    private Long version;


}

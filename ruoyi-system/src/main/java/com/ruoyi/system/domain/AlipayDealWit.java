package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 代付订单对象 alipay_deal_wit
 * 
 * @author ruoyi
 * @date 2023-06-16
 */
public class AlipayDealWit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderid;

    /** 关联订单号 */
    @Excel(name = "关联订单号")
    private String associatedid;

    /** 订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理 */
    @Excel(name = "订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理")
    private String orderstatus;

    /** 订单交易金额 */
    @Excel(name = "订单交易金额")
    private Double dealamount;

    /** 订单交易手续费 */
    @Excel(name = "订单交易手续费")
    private Double dealfee;

    /** 实际到账金额 */
    @Excel(name = "实际到账金额")
    private Double actualamount;

    /** 订单关联商户账号 */
    @Excel(name = "订单关联商户账号")
    private String orderaccount;

    /** 渠道账户 */
    @Excel(name = "渠道账户")
    private String chanenlid;

    /** 取款详细 */
    @Excel(name = "取款详细")
    private String witinfo;

    /** 外部订单号(下游商户请求参数,用户数据回调) */
    @Excel(name = "外部订单号(下游商户请求参数,用户数据回调)")
    private String externalorderid;

    /** 交易备注 */
    @Excel(name = "交易备注")
    private String dealdescribe;

    /** 订单异步回调地址 */
    @Excel(name = "订单异步回调地址")
    private String notify;

    /** 订单同步回调地址 */
    @Excel(name = "订单同步回调地址")
    private String back;

    /** 是否發送通知 //  YES 已發送    NO 未發送 */
    @Excel(name = "是否發送通知 //  YES 已發送    NO 未發送")
    private String isnotify;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 使用费率id */
    @Excel(name = "使用费率id")
    private Integer feeid;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 订单交易产品类型 */
    @Excel(name = "订单交易产品类型")
    private String wittype;

    /** 货币类型 */
    @Excel(name = "货币类型")
    private String currency;

    /** 说明 */
    @Excel(name = "说明")
    private String msg;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderid(String orderid) 
    {
        this.orderid = orderid;
    }

    public String getOrderid() 
    {
        return orderid;
    }
    public void setAssociatedid(String associatedid) 
    {
        this.associatedid = associatedid;
    }

    public String getAssociatedid() 
    {
        return associatedid;
    }
    public void setOrderstatus(String orderstatus) 
    {
        this.orderstatus = orderstatus;
    }

    public String getOrderstatus() 
    {
        return orderstatus;
    }
    public void setDealamount(Double dealamount) 
    {
        this.dealamount = dealamount;
    }

    public Double getDealamount() 
    {
        return dealamount;
    }
    public void setDealfee(Double dealfee) 
    {
        this.dealfee = dealfee;
    }

    public Double getDealfee() 
    {
        return dealfee;
    }
    public void setActualamount(Double actualamount) 
    {
        this.actualamount = actualamount;
    }

    public Double getActualamount() 
    {
        return actualamount;
    }
    public void setOrderaccount(String orderaccount) 
    {
        this.orderaccount = orderaccount;
    }

    public String getOrderaccount() 
    {
        return orderaccount;
    }
    public void setChanenlid(String chanenlid) 
    {
        this.chanenlid = chanenlid;
    }

    public String getChanenlid() 
    {
        return chanenlid;
    }
    public void setWitinfo(String witinfo) 
    {
        this.witinfo = witinfo;
    }

    public String getWitinfo() 
    {
        return witinfo;
    }
    public void setExternalorderid(String externalorderid) 
    {
        this.externalorderid = externalorderid;
    }

    public String getExternalorderid() 
    {
        return externalorderid;
    }
    public void setDealdescribe(String dealdescribe) 
    {
        this.dealdescribe = dealdescribe;
    }

    public String getDealdescribe() 
    {
        return dealdescribe;
    }
    public void setNotify(String notify) 
    {
        this.notify = notify;
    }

    public String getNotify() 
    {
        return notify;
    }
    public void setBack(String back) 
    {
        this.back = back;
    }

    public String getBack() 
    {
        return back;
    }
    public void setIsnotify(String isnotify) 
    {
        this.isnotify = isnotify;
    }

    public String getIsnotify() 
    {
        return isnotify;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }
    public void setFeeid(Integer feeid) 
    {
        this.feeid = feeid;
    }

    public Integer getFeeid() 
    {
        return feeid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setWittype(String wittype) 
    {
        this.wittype = wittype;
    }

    public String getWittype() 
    {
        return wittype;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderid", getOrderid())
            .append("associatedid", getAssociatedid())
            .append("orderstatus", getOrderstatus())
            .append("dealamount", getDealamount())
            .append("dealfee", getDealfee())
            .append("actualamount", getActualamount())
            .append("orderaccount", getOrderaccount())
            .append("chanenlid", getChanenlid())
            .append("witinfo", getWitinfo())
            .append("externalorderid", getExternalorderid())
            .append("dealdescribe", getDealdescribe())
            .append("notify", getNotify())
            .append("back", getBack())
            .append("isnotify", getIsnotify())
            .append("createtime", getCreatetime())
            .append("submittime", getSubmittime())
            .append("feeid", getFeeid())
            .append("status", getStatus())
            .append("wittype", getWittype())
            .append("currency", getCurrency())
            .append("msg", getMsg())
            .toString();
    }
}

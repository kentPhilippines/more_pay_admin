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
public class AlipayDealWit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderId;

    /**
     * 关联订单号
     */
    @Excel(name = "关联订单号")
    private String associatedId;

    /**
     * 订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理
     */
    @Excel(name = "订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理")
    private String orderStatus;

    /**
     * 订单交易金额
     */
    @Excel(name = "订单交易金额")
    private Double dealAmount;

    /**
     * 订单交易手续费
     */
    @Excel(name = "订单交易手续费")
    private Double dealFee;

    /**
     * 实际到账金额
     */
    @Excel(name = "实际到账金额")
    private Double actualAmount;

    /**
     * 订单关联商户账号
     */
    @Excel(name = "订单关联商户账号")
    private String orderAccount;

    /**
     * 渠道账户
     */
    @Excel(name = "渠道账户")
    private String chanenlId;

    /**
     * 取款详细
     */
    @Excel(name = "取款详细")
    private String witInfo;

    /**
     * 外部订单号(下游商户请求参数,用户数据回调)
     */
    @Excel(name = "外部订单号(下游商户请求参数,用户数据回调)")
    private String externalOrderId;

    /**
     * 交易备注
     */
    @Excel(name = "交易备注")
    private String dealDescribe;

    /**
     * 订单异步回调地址
     */
    @Excel(name = "订单异步回调地址")
    private String notify;

    /**
     * 订单同步回调地址
     */
    @Excel(name = "订单同步回调地址")
    private String back;

    /**
     * 是否發送通知 //  YES 已發送    NO 未發送
     */
    @Excel(name = "是否發送通知 //  YES 已發送    NO 未發送")
    private String isNotify;

    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 使用费率id
     */
    @Excel(name = "使用费率id")
    private Integer feeId;

    /**
     * 状态:1可使用；0不可使用
     */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /**
     * 订单交易产品类型
     */
    @Excel(name = "订单交易产品类型")
    private String witType;

    /**
     * 货币类型
     */
    @Excel(name = "货币类型")
    private String currency;

    /**
     * 说明
     */
    @Excel(name = "说明")
    private String msg;


    private String request;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getWitType() {

        return witType;
    }

    public void setWitType(String witType) {
        this.witType = witType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getIsNotify() {
        return isNotify;
    }

    public void setIsNotify(String isNotify) {
        this.isNotify = isNotify;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getDealDescribe() {
        return dealDescribe;
    }

    public void setDealDescribe(String dealDescribe) {
        this.dealDescribe = dealDescribe;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public String getWitInfo() {
        return witInfo;
    }

    public void setWitInfo(String witInfo) {
        this.witInfo = witInfo;
    }

    public String getChanenlId() {
        return chanenlId;
    }

    public void setChanenlId(String chanenlId) {
        this.chanenlId = chanenlId;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getDealFee() {
        return dealFee;
    }

    public void setDealFee(Double dealFee) {
        this.dealFee = dealFee;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "AlipayDealWit{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", associatedId='" + associatedId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", dealAmount=" + dealAmount +
                ", dealFee=" + dealFee +
                ", actualAmount=" + actualAmount +
                ", orderAccount='" + orderAccount + '\'' +
                ", chanenlId='" + chanenlId + '\'' +
                ", witInfo='" + witInfo + '\'' +
                ", externalOrderId='" + externalOrderId + '\'' +
                ", dealDescribe='" + dealDescribe + '\'' +
                ", notify='" + notify + '\'' +
                ", back='" + back + '\'' +
                ", isNotify='" + isNotify + '\'' +
                ", submitTime=" + submitTime +
                ", feeId=" + feeId +
                ", status=" + status +
                ", witType='" + witType + '\'' +
                ", currency='" + currency + '\'' +
                ", msg='" + msg + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

package com.ruoyi.system.service;

import com.ruoyi.system.domain.AlipayDealWit;

import java.util.List;

/**
 * 代付订单Service接口
 *
 * @author ruoyi
 * @date 2023-06-16
 */
public interface IAlipayDealWitService {
    /**
     * 查询代付订单
     *
     * @param id 代付订单ID
     * @return 代付订单
     */
    public AlipayDealWit selectAlipayDealWitById(Long id);

    /**
     * 查询代付订单列表
     *
     * @param alipayDealWit 代付订单
     * @return 代付订单集合
     */
    public List<AlipayDealWit> selectAlipayDealWitList(AlipayDealWit alipayDealWit);

    int upteupdataOrder(String orderId, String orderStatus);


}

package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AlipayDealWitMapper;
import com.ruoyi.system.domain.AlipayDealWit;
import com.ruoyi.system.service.IAlipayDealWitService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 代付订单Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-16
 */
@Service
public class AlipayDealWitServiceImpl implements IAlipayDealWitService {
    @Resource
    private AlipayDealWitMapper alipayDealWitMapper;

    /**
     * 查询代付订单
     *
     * @param id 代付订单ID
     * @return 代付订单
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public AlipayDealWit selectAlipayDealWitById(Long id) {
        return alipayDealWitMapper.selectAlipayDealWitById(id);
    }

    /**
     * 查询代付订单列表
     *
     * @param alipayDealWit 代付订单
     * @return 代付订单
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealWit> selectAlipayDealWitList(AlipayDealWit alipayDealWit) {
        return alipayDealWitMapper.selectAlipayDealWitList(alipayDealWit);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int upteupdataOrder(String orderId, String orderStatus) {
        return alipayDealWitMapper.upteupdataOrder(orderId,orderStatus);
    }

}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AlipayDealWitMapper;
import com.ruoyi.system.domain.AlipayDealWit;
import com.ruoyi.system.service.IAlipayDealWitService;
import com.ruoyi.common.core.text.Convert;

/**
 * 代付订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-16
 */
@Service
public class AlipayDealWitServiceImpl implements IAlipayDealWitService 
{
    @Autowired
    private AlipayDealWitMapper alipayDealWitMapper;

    /**
     * 查询代付订单
     * 
     * @param id 代付订单ID
     * @return 代付订单
     */
    @Override
    public AlipayDealWit selectAlipayDealWitById(Long id)
    {
        return alipayDealWitMapper.selectAlipayDealWitById(id);
    }

    /**
     * 查询代付订单列表
     * 
     * @param alipayDealWit 代付订单
     * @return 代付订单
     */
    @Override
    public List<AlipayDealWit> selectAlipayDealWitList(AlipayDealWit alipayDealWit)
    {
        return alipayDealWitMapper.selectAlipayDealWitList(alipayDealWit);
    }

    /**
     * 新增代付订单
     * 
     * @param alipayDealWit 代付订单
     * @return 结果
     */
    @Override
    public int insertAlipayDealWit(AlipayDealWit alipayDealWit)
    {
        return alipayDealWitMapper.insertAlipayDealWit(alipayDealWit);
    }

    /**
     * 修改代付订单
     * 
     * @param alipayDealWit 代付订单
     * @return 结果
     */
    @Override
    public int updateAlipayDealWit(AlipayDealWit alipayDealWit)
    {
        return alipayDealWitMapper.updateAlipayDealWit(alipayDealWit);
    }

    /**
     * 删除代付订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealWitByIds(String ids)
    {
        return alipayDealWitMapper.deleteAlipayDealWitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代付订单信息
     * 
     * @param id 代付订单ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealWitById(Long id)
    {
        return alipayDealWitMapper.deleteAlipayDealWitById(id);
    }
}

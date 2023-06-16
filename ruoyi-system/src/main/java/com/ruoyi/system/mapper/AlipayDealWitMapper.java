package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AlipayDealWit;
import java.util.List;

/**
 * 代付订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-16
 */
public interface AlipayDealWitMapper 
{
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

    /**
     * 新增代付订单
     * 
     * @param alipayDealWit 代付订单
     * @return 结果
     */
    public int insertAlipayDealWit(AlipayDealWit alipayDealWit);

    /**
     * 修改代付订单
     * 
     * @param alipayDealWit 代付订单
     * @return 结果
     */
    public int updateAlipayDealWit(AlipayDealWit alipayDealWit);

    /**
     * 删除代付订单
     * 
     * @param id 代付订单ID
     * @return 结果
     */
    public int deleteAlipayDealWitById(Long id);

    /**
     * 批量删除代付订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayDealWitByIds(String[] ids);
}

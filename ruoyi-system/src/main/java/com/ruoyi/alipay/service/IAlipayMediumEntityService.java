package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayMediumEntity;

import java.util.List;

/**
 * 收款媒介列Service接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayMediumEntityService {
    /**
     * 查询收款媒介列
     *
     * @param id 收款媒介列ID
     * @return 收款媒介列
     */
    public AlipayMediumEntity selectAlipayMediumEntityById(Long id);

    /**
     * 查询收款媒介种类
     *
     * @return 收款媒介列
     */
    public List<String> selectCodeByAlipayMediumEntity();

    /**
     * 查询收款媒介列列表
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 收款媒介列集合
     */
    public List<AlipayMediumEntity> selectAlipayMediumEntityList(AlipayMediumEntity alipayMediumEntity);

    /**
     * 新增收款媒介列
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    public int insertAlipayMediumEntity(AlipayMediumEntity alipayMediumEntity);

    /**
     * 修改收款媒介列
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    public int updateAlipayMediumEntity(AlipayMediumEntity alipayMediumEntity);

    /**
     * 根据code修改上限金额
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    public int updateAlipayMediumEntityByCode(AlipayMediumEntity alipayMediumEntity);

    /**
     * 批量删除收款媒介列
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayMediumEntityByIds(String ids);

    /**
     * 删除收款媒介列信息
     *
     * @param id 收款媒介列ID
     * @return 结果
     */
    public int deleteAlipayMediumEntityById(Long id);
}

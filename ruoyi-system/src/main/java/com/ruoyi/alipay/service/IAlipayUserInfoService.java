package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;

import java.util.List;

/**
 * 用户详情Service接口
 *
 * @author ruoyi
 * @date 2020-02-27
 */
public interface IAlipayUserInfoService {
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    AlipayUserInfo selectAlipayUserInfoById(Long id);

    /**
     * 查询用户详情列表
     *
     * @param alipayUserInfo 用户详情
     * @return 用户详情集合
     */
    List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo);

    /**
     * 新增用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 修改用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 批量删除用户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAlipayUserInfoByIds(String ids);

    /**
     * 删除用户详情信息
     *
     * @param id 用户详情ID
     * @return 结果
     */
    int deleteAlipayUserInfoById(Long id);

    /**
     * 验证用户名的唯一性
     *
     * @param alipayUserInfo
     * @return
     */
    String checkAlipayUserIdUnique(AlipayUserInfo alipayUserInfo);

    /**
     * 重置用户登陆密码
     *
     * @param id
     * @return 影响行数
     */
    String resetLoginPwd(Long id);

    /**
     * 重置用户提现密码
     *
     * @param id
     * @return 影响行数
     */
    String resetWithdrawalPwd(Long id);

    /**
     * 根据商户号查询商户
     *
     * @param userId
     * @return
     */
    AlipayUserInfo findMerchantInfoByUserId(String userId);

    /**
     * 查询顶代和会员
     *
     * @param alipayUserInfo
     * @return
     */
    List<AlipayUserInfo> selectAlipayUserInfoByControl(AlipayUserInfo alipayUserInfo);

    /**
     * 保存码商服务群
     * @param alipayUserInfo
     */
    int toSaveQrChargeList(AlipayUserInfo alipayUserInfo);


    /**
     * <p>查询所有的订单码商</p>
     * @return
     */
    List<AlipayUserInfo> selectdealpayUserInfoByAgent( );

    /**
     * <p>修改支付密码<p>
     * @param userId
     * @param password
     * @param salt 
     * @return
     */
	boolean updatePaypassword(String userId, String password, String salt);

    List<AlipayUserInfo> selectAllUserInfoList(AlipayUserInfo alipayUserInfo);
}

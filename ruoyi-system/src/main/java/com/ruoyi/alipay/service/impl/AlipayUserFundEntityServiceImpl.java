package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.alipay.mapper.AlipayUserFundEntityMapper;
import com.ruoyi.alipay.mapper.AlipayUserInfoMapper;
import com.ruoyi.alipay.mapper.AlipayUserRateEntityMapper;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.IAlipayUserRateEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import jdk.nashorn.internal.runtime.options.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 用户资金账户Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayUserFundEntityServiceImpl implements IAlipayUserFundEntityService {
    @Resource
    private AlipayUserFundEntityMapper alipayUserFundEntityMapper;
    @Resource
    private AlipayUserInfoMapper alipayUserInfoMapper;
    @Resource
    private AlipayUserRateEntityMapper alipayUserRateEntityMapper;

    private static final Logger log = LoggerFactory.getLogger(AlipayUserFundEntityServiceImpl.class);
    /**
     * 查询用户资金账户
     *
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserFundEntity selectAlipayUserFundEntityById(Long id) {
        return alipayUserFundEntityMapper.selectAlipayUserFundEntityById(id);
    }

    /**
     * 查询用户资金账户列表
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 用户资金账户
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> selectAlipayUserFundEntityList(AlipayUserFundEntity alipayUserFundEntity) {
        return alipayUserFundEntityMapper.selectAlipayUserFundEntityList(alipayUserFundEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> selectAlipayUserFundEntityListByUserId(List<String> idList) {
        return alipayUserFundEntityMapper.selectListByIdList(idList);
    }

    /**
     * 新增用户资金账户
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity) {
        alipayUserFundEntity.setCreateTime(DateUtils.getNowDate());
        return alipayUserFundEntityMapper.insertAlipayUserFundEntity(alipayUserFundEntity);
    }

    /**
     * 修改用户资金账户
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity) {
        return alipayUserFundEntityMapper.updateAlipayUserFundEntity(alipayUserFundEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserFundEntity findAlipayUserFundByUserId(String merchantId) {
        return alipayUserFundEntityMapper.selectAlipayUserFundByUserId(merchantId);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findChannelAccount(AlipayUserFundEntity alipayUserFundEntity) {
        List<AlipayUserFundEntity> channelAccount = alipayUserFundEntityMapper.findChannelAccount(alipayUserFundEntity);
        for (AlipayUserFundEntity channel : channelAccount) {
            AlipayUserInfo userInfo = alipayUserInfoMapper.selectMerhantInfoByUserId(channel.getUserId());
            channel.setReceiveOrderState(userInfo.getReceiveOrderState());
            channel.setSwitchs(userInfo.getSwitchs());
            channel.setRemitOrderState(userInfo.getRemitOrderState());
            channel.setMaxAmount(userInfo.getMaxAmount());
            channel.setMinAmount(userInfo.getMinAmount());
            channel.setTimesTotal(userInfo.getTimesTotal());
            AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityMapper.findDealRate(userInfo.getUserId());
            Double fee = Objects.isNull(alipayUserRateEntity) ? 0.00 : alipayUserRateEntity.getFee();
            BigDecimal sucSumCoin = alipayUserInfoMapper.findOrderByAppSucSum(channel.getUserId(), DateUtils.dayStart(), DateUtils.dayEnd());
            log.info("渠道商户：{},查询可取现金额{}",channel.getUserId(),sucSumCoin.intValue());
            channel.setAccountBalance(sucSumCoin.doubleValue());
            channel.setLimitBalance(userInfo.getLimitBalance());
            channel.setRate(fee);
            channel.setPlatName(userInfo.getPlatName());
        }
        return channelAccount;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserFundAll() {
        return alipayUserFundEntityMapper.findUserFundAll();
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserFundRate() {
        return alipayUserFundEntityMapper.findUserFundRate();
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserBakBy(String merchantId, BaseEntity baseEntity) {
        return alipayUserFundEntityMapper.findUserBakBy(merchantId, baseEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findMyUserBak(String merchantId, BaseEntity baseEntity) {
        return alipayUserFundEntityMapper.findMyUserBak(merchantId, baseEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserAppAll(BaseEntity baseEntity) {
        return alipayUserFundEntityMapper.findUserAppAll(baseEntity);
    }

    @Override
    public List<AlipayUserFundEntity> findaAcountList(AlipayUserFundEntity alipayUserFundEntity) {
        return null;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserFundEntity findSumFundM(String currency) {
        return alipayUserFundEntityMapper.findSumFundM(currency);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserFundEntity findSumFundC(String currency) {
        return alipayUserFundEntityMapper.findSumFundC(currency);
    }


    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findSumFundByUserId(String userId) {
        return alipayUserFundEntityMapper.findSumFundByUserId(userId);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> findUserByAgent(String agentUserId) {
        return alipayUserFundEntityMapper.findUserByAgent(agentUserId);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateStatus(String userId, Integer status) {
        return alipayUserFundEntityMapper.updateStatus(userId, status);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserList(List<String> userList, BaseEntity baseEntity) {
        return alipayUserFundEntityMapper.findUserList(userList, baseEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<String> findDealAfter15() {
        List<String> userList = alipayUserFundEntityMapper.findDealAfter15();
        return userList;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserFundEntity findFundBak(String starTime, String userId, String endTime) {
        return alipayUserFundEntityMapper.findFundBak(starTime, userId, endTime);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findFundBakList(AlipayUserFundEntity alipayUserFundEntity) {
        return alipayUserFundEntityMapper.findFundBakList(alipayUserFundEntity);
    }

    /**
     * 查询所有卡商
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserFundAllToBank() {
        return alipayUserFundEntityMapper.findUserFundAllToBank();
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void delectUser(String userId) {
        alipayUserFundEntityMapper.delectUser(userId);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void delete(Long id) {
        alipayUserFundEntityMapper.delete(id);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserFundEntity> findUserFundRateNew() {
        return alipayUserFundEntityMapper.findUserFundRateNew();
    }
}

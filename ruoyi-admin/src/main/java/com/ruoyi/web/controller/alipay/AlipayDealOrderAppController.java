package com.ruoyi.web.controller.alipay;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DictionaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商户订单登记Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderApp")
public class AlipayDealOrderAppController extends BaseController {
    private String prefix = "alipay/orderApp";
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private IAlipayProductService iAlipayProductService;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;

    @GetMapping()
    public String orderApp(ModelMap mmap) {
        //查询产品类型下拉菜单
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        return prefix + "/orderApp";
    }

    @GetMapping("/backOrderUrl/{id}")
    public String orderAppDeal(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(id);
        mmap.put("alipayDealOrder", alipayDealOrderApp);
        List<AlipayUserFundEntity> fund = alipayUserFundEntityService.findUserFundRateNew();
        mmap.put("channel", fund);
        return prefix + "/backOrderUrl";
    }

    /**
     * 查询商户订单登记列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        return getDataTable(list);
    }

    /**
     * 查询商户订单登记列表
     */
    @PostMapping("/listorderDeal")
    @ResponseBody
    public TableDataInfo listorderDeal(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppListDealOrder(alipayDealOrderApp);
        return getDataTable(list);
    }

    @GetMapping("/orderAppAgent")
    public String orderAppAgent() {
        return prefix + "/orderAppAgent";
    }

    /**
     * 查询代理商分润
     */
    @PostMapping("/listAgent")
    @ResponseBody
    public TableDataInfo listAgent(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.listAgent(alipayDealOrderApp);
        return getDataTable(list);
    }

    /**
     * 导出商户订单登记列表
     */
    @Log(title = "商户交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrderApp alipayDealOrderApp) {
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        ExcelUtil<AlipayDealOrderApp> util = new ExcelUtil<AlipayDealOrderApp>(AlipayDealOrderApp.class);
        return util.exportExcel(list, "orderApp");
    }

    /**
     * 显示商户订单详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(id);
        mmap.put("alipayDealOrderApp", alipayDealOrderApp);
        return prefix + "/edit";
    }

    /**
     * 转发财务
     */
    @Log(title = "商户交易订单", businessType = BusinessType.INSERT)
    @PostMapping("/updateOrder")
    @ResponseBody
    public AjaxResult updateOrder(String id) {
        AlipayDealOrderApp order = alipayDealOrderAppService.selectAlipayDealOrderAppById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = alipayDealOrderAppService.updateAlipayDealOrderApp(order);
        return toAjax(i);
    }

    /**
     * 显示统计table
     */
    @GetMapping("/statistics/merchant/table")
    public String showTable(ModelMap mmap) {
        //查询产品类型下拉菜单
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        return prefix + "/currentTable";
    }

    /**
     * 商户交易订单统计（仅当天数据）
     */
    @PostMapping("/statistics/merchant/orderApp")
    @ResponseBody
    public TableDataInfo dayStat(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderAppService.selectMerchantStatisticsDataByDay(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        BigDecimal amount = new BigDecimal("0");
        ConcurrentHashMap.KeySetView<String, AlipayUserFundEntity> strings = userCollect.keySet();
        for (String key : strings) {
            AlipayUserFundEntity alipayUserFundEntity = userCollect.get(key);
            if (alipayUserFundEntity.getUserType().equals("1")) {
                amount = amount.add(new BigDecimal(alipayUserFundEntity.getAccountBalance()));
            }
        }
        for (StatisticsEntity sta : list) {
            if (ObjectUtil.isNotNull(userCollect.get(sta.getUserId()))) {
                sta.setUserName(userCollect.get(sta.getUserId()).getUserName());
            }
            if (ObjectUtil.isNotNull(userCollect.get(sta.getUserId()))) {
                sta.setAccountAmount(userCollect.get(sta.getUserId()).getAccountBalance().toString());
            }
            if ("所有".equals(sta.getUserId())) {
                sta.setAccountAmount(amount.doubleValue() + "");
            }
            sta.setTodayAmount(sta.getSuccessAmount().subtract(new BigDecimal(sta.getSuccessFee())).toString());
        }
        return getDataTable(list);
    }

    @Autowired
    private DictionaryUtils dictionaryUtils;

    /**
     * 补单数据保存
     */
    @Log(title = "提交卡密", businessType = BusinessType.INSERT)
    @PostMapping("/backOrderSave")
    @ResponseBody
    public AjaxResult backOrderSave(AlipayDealOrderApp alipayDealOrderEntity) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(alipayDealOrderEntity.getId());
        String dealDescribe = alipayDealOrderEntity.getDealDescribe().trim();
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String orderNo, password, channel;
        orderNo = alipayDealOrderApp.getOrderId();
        password = dealDescribe;
        channel = alipayDealOrderEntity.getChannelId();
        logger.info("卡密订单号：" + orderNo);
        logger.info("卡密密码：" + password);
        logger.info("提交渠道：" + channel);
        Map map = new HashMap();
        map.put("orderNo", orderNo);
        map.put("password", password);
        map.put("channel", channel);
        map.put("manage", "manage");
        ThreadUtil.execute(() -> {
            String post1 = HttpUtil.post(ipPort + "/km/deal/enter", map);
            logger.info("响应参数：" + post1);
            JSONObject jsonObject = JSONUtil.parseObj(post1);
            String success = jsonObject.getStr("success");
        });
        return toAjax(1);

    }

    /**
     * 重新核销
     */
    @Log(title = "提交卡密", businessType = BusinessType.INSERT)
    @PostMapping("/enterAgent")
    @ResponseBody
    public AjaxResult enterAgent(String id) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(Long.valueOf(id));
        AlipayDealOrderEntity order = alipayDealOrderEntityService.findOrderByOrderIdAss(alipayDealOrderApp.getOrderId());
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String orderNo, password, channel;
        orderNo = alipayDealOrderApp.getOrderId();
        logger.info("卡密订单号：" + orderNo);
        logger.info("卡密密码：" + order.getOrderQr());
        logger.info("提交渠道：" + order.getOrderQrUser());
        Map map = new HashMap();
        map.put("orderNo", orderNo);
        map.put("password", order.getOrderQr());
        map.put("channel", order.getOrderQrUser());
        map.put("enterAgent", "enterAgent");
        ThreadUtil.execute(() -> {
            String post1 = HttpUtil.post(ipPort + "/km/deal/enter", map);
            logger.info("响应参数：" + post1);
            JSONObject jsonObject = JSONUtil.parseObj(post1);
            String success = jsonObject.getStr("success");
        });
        return toAjax(1);

    }

}

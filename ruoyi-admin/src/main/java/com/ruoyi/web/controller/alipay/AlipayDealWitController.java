package com.ruoyi.web.controller.alipay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AlipayDealWit;
import com.ruoyi.system.service.IAlipayDealWitService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代付订单Controller
 *
 * @author ruoyi
 * @date 2023-06-16
 */
@Controller
@RequestMapping("/alipay/deal_wit")
public class AlipayDealWitController extends BaseController {
    private String prefix = "alipay/deal_wit";

    @Autowired
    private IAlipayDealWitService alipayDealWitService;

    @RequiresPermissions("alipay:deal_wit:view")
    @GetMapping()
    public String deal_wit() {
        return prefix + "/deal_wit";
    }

    /**
     * 查询代付订单列表
     */
    @RequiresPermissions("alipay:deal_wit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealWit alipayDealWit) {
        startPage();
        List<AlipayDealWit> list = alipayDealWitService.selectAlipayDealWitList(alipayDealWit);
        return getDataTable(list);
    }

    /**
     * 导出代付订单列表
     */
    @RequiresPermissions("alipay:deal_wit:export")
    @Log(title = "代付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealWit alipayDealWit) {
        List<AlipayDealWit> list = alipayDealWitService.selectAlipayDealWitList(alipayDealWit);
        ExcelUtil<AlipayDealWit> util = new ExcelUtil<AlipayDealWit>(AlipayDealWit.class);
        return util.exportExcel(list, "deal_wit");
    }


    /**
     * 修改代付订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealWit alipayDealWit = alipayDealWitService.selectAlipayDealWitById(id);
        mmap.put("alipayDealWit", alipayDealWit);
        return prefix + "/edit";
    }
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;
    @Autowired
    private DictionaryUtils dictionaryUtils;
    /**
     * 交由财务处理
     */
    @PostMapping("/updataOrderSu")
    @RequiresPermissions("orderDealwit:qr:status")
    @ResponseBody
    public AjaxResult updataOrderSu(String id) {
        AlipayDealWit alipayDealWit = alipayDealWitService.selectAlipayDealWitById(Long.valueOf(id));
        List list = new ArrayList();
        list.add("1");
        list.add("4");
        list.add("5");
        if(!list.contains(alipayDealWit.getOrderStatus())){
            throw new BusinessException("该状态不允许修改");
        }
        alipayDealWit.setOrderStatus("2");//人工处理
        int i = alipayDealWitService.upteupdataOrder(alipayDealWit.getOrderId(),alipayDealWit.getOrderStatus());
        SysUser currentUser = ShiroUtils.getSysUser();
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_7);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("orderId", alipayDealWit.getAssociatedId());//订单号
        mapParam.put("userId", alipayDealWit.getOrderAccount());
        mapParam.put("orderStatus", alipayDealWit.getOrderStatus());
        mapParam.put("approval", currentUser.getLoginName());
        mapParam.put("comment", "手动成功");
        mapParam.put("channelId", alipayDealWit.getChanenlId());
        mapParam.put("witType", alipayDealWit.getWitType());
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }
    /**
     * 交由财务处理
     */
    @PostMapping("/updataOrderEr")
    @RequiresPermissions("orderDealwit:qr:status")
    @ResponseBody
    public AjaxResult updataOrderEr(String id) {
        AlipayDealWit alipayDealWit = alipayDealWitService.selectAlipayDealWitById(Long.valueOf(id));
        List list = new ArrayList();
        list.add("1");
        list.add("4");
        list.add("5");
        if(!list.contains(alipayDealWit.getOrderStatus())){
            throw new BusinessException("该状态不允许修改");
        }
        alipayDealWit.setOrderStatus("3");//人工处理
        int i = alipayDealWitService.upteupdataOrder(alipayDealWit.getOrderId(),alipayDealWit.getOrderStatus());
        return toAjax(i);
    }
}

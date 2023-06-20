package com.ruoyi.web.controller.alipay;

import java.util.List;

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



}

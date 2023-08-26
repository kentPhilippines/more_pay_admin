package com.ruoyi.web.controller.alipay;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.alipay.domain.AlipayChanelFee;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.alipay.domain.util.McFee;
import com.ruoyi.alipay.service.IAlipayChanelFeeService;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.IAlipayUserRateEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 渠道费率Controller
 * 
 * @author ruoyi
 * @date 2020-05-15
 */
@Controller
@RequestMapping("/alipay/channelFee")
public class AlipayChanelFeeController extends BaseController {
	private String prefix = "alipay/channelFee";
    @Autowired private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired IAlipayProductService iAlipayProductService;
	@Autowired private IAlipayChanelFeeService alipayChanelFeeService;
 	@RequiresPermissions("alipay:channelFee:view")
	@GetMapping()
	public String fee(ModelMap modelMap , String productId) {
		AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
		alipayProductEntity.setProductId(productId);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();//查询所有渠道账户
        modelMap.put("rateList", rateList);
		return prefix + "/fee";
	}

	/**
	 * 查询渠道费率列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayChanelFee alipayChanelFee) {
		startPage();
		List<AlipayChanelFee> list = alipayChanelFeeService.selectAlipayChanelFeeList(alipayChanelFee);
		AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        List<AlipayProductEntity> proList = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        ConcurrentHashMap<String, AlipayUserFundEntity> qrCollect = rateList.stream().collect(Collectors .toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = proList.stream().collect(Collectors .toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
		for(AlipayChanelFee rate : list) {
			AlipayUserFundEntity channel = qrCollect.get(rate.getChannelId());
			AlipayProductEntity product = prCollect.get(rate.getProductId());
			if (ObjectUtil.isNotNull(channel)) {
				rate.setChannelId(channel.getUserName());
			}
			if (ObjectUtil.isNotNull(product)) {
				rate.setProductId(product.getProductName());
			}
		}
		return getDataTable(list);
	}
	/**
	 * 查询渠道费率列表
	 */
	@PostMapping("/listFee")
	@ResponseBody
	public TableDataInfo listFee(AlipayChanelFee alipayChanelFee) {
		List<AlipayChanelFee> list = alipayChanelFeeService.selectAlipayChanelFeeList(alipayChanelFee);
		List<McFee> listFee = new ArrayList<>();
		for(AlipayChanelFee rate : list) {
			McFee mf = new McFee();
			mf.setChannelId(rate.getChannelId());
			listFee.add(mf);
		}
		return getDataTable(listFee);
	}
	@PostMapping("/listFee1")
	@ResponseBody
	public TableDataInfo listFee1(AlipayChanelFee alipayChanelFee) {
		AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(alipayChanelFee.getId());
		alipayChanelFee.setId(null);
		alipayChanelFee.setProductId(alipayUserRateEntity.getPayTypr());
		String channelId = alipayUserRateEntity.getChannelId();
		JSONArray objects = JSONUtil.parseArray(channelId);
		List<McFee> listFee = new ArrayList<>();
		Map<String,McFee> map = new HashMap<>();
		for (Object mf : objects) {
			JSONObject jsonObject = JSONUtil.parseObj(mf);
			McFee mcFee = jsonObject.toBean(McFee.class);
			String channelId1 = mcFee.getChannelId();
			map.put(channelId1,mcFee);
		}
		List<AlipayChanelFee> list = alipayChanelFeeService.selectAlipayChanelFeeList(alipayChanelFee);
		for(AlipayChanelFee rate : list) {
			McFee mf = new McFee();
			McFee mcFee = map.get(rate.getChannelId());
			if(null != mcFee){
				mf.setPriority(mcFee.getPriority());
				mf.setChecked(true);
			}
			mf.setChannelId(rate.getChannelId());
			listFee.add(mf);
		}
		return getDataTable(listFee);
	}

	/**
	 * 导出渠道费率列表
	 */
	@RequiresPermissions("alipay:channelFee:export")
	@Log(title = "渠道费率", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayChanelFee alipayChanelFee) {
		List<AlipayChanelFee> list = alipayChanelFeeService.selectAlipayChanelFeeList(alipayChanelFee);
		ExcelUtil<AlipayChanelFee> util = new ExcelUtil<AlipayChanelFee>(AlipayChanelFee.class);
		return util.exportExcel(list, "fee");
	}

	/**
	 * 新增渠道费率
	 */
	@GetMapping("/add")
	public String add(ModelMap modelMap) {
		AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        modelMap.put("rateList", rateList);
		return prefix + "/add";
		
	}

	/**
	 * 新增保存渠道费率
	 */
	@RequiresPermissions("alipay:channelFee:add") 
	@Log(title = "渠道费率", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayChanelFee alipayChanelFee) {
		return toAjax(alipayChanelFeeService.insertAlipayChanelFee(alipayChanelFee));
	}

	/**
	 * 修改渠道费率
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		String loginName = ShiroUtils.getLoginName();
		AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
		AlipayChanelFee alipayChanelFee = alipayChanelFeeService.selectAlipayChanelFeeById(id);
		mmap.put("alipayChanelFee", alipayChanelFee);
		List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
		mmap.put("productList", list);
		mmap.put("userId", loginName);
		return prefix + "/edit";
	}

	/**
	 * 修改保存渠道费率
	 */
	@RequiresPermissions("alipay:channelFee:edit")
	@Log(title = "渠道费率", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayChanelFee alipayChanelFee) {
		return toAjax(alipayChanelFeeService.updateAlipayChanelFee(alipayChanelFee));
	}

	/**
	 * 删除渠道费率
	 */
	@RequiresPermissions("alipay:channelFee:remove")
	@Log(title = "渠道费率", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayChanelFeeService.deleteAlipayChanelFeeByIds(ids));
	}
}

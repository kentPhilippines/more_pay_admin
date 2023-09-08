package com.ruoyi.web.controller.alipay;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.domain.util.McFee;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户产品费率Controller
 * 为什么不到业务层写业务代码，因为多数据源切的是业务层，数据获取灵活度不高，所以业务层写业务不好弄
 *
 * @author kent
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/rate")
public class AlipayUserRateEntityController extends BaseController {
    private String prefix = "alipay/merchant/rate";
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    IAlipayProductService iAlipayProductService;
    @Autowired
    IAlipayChanelFeeService alipayChanelFeeServiceImpl;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;
    /*
     * 查询商户产品费率列表
     */
    static final String PAY_TYPE = "1";

    @RequiresPermissions("merchant:rate:view")
    @GetMapping()
    public String rate(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
         //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        return prefix + "/rate";
    }
     @Autowired
    private IAlipayChanelFeeService alipayChanelFeeService;

    /*
     * 新增用户产品费率
     */
    @Log(title = "新增商户费率", businessType = BusinessType.INSERT)
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        AlipayUserInfo alipayUserInfo = new AlipayUserInfo();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", list);
        //查询所有的商户
        alipayUserInfo.setSwitchs(1);
        alipayUserInfo.setUserType(1);
        List<AlipayUserInfo> userInfo = alipayUserInfoService.selectAllUserInfoList(alipayUserInfo);
        modelMap.put("merList", userInfo);
        return prefix + "/add";
    }



    /**
     * 商户小数状态
     */
    @RequiresPermissions("merchant:rate:status")
    @Log(title = "小数状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatusOfDecimal")
    @ResponseBody
    public AjaxResult updateStatusOfDecimal(String id, String userId, String feeType, Integer deci) {
        logger.info("[当前处理费小数状态开启或关闭的管理员账号为：" + ShiroUtils.getSysUser().getLoginName() + "]");
        logger.info("[当前处理商户小数状态的参数为：" + deci + "]");
        return toAjax(alipayUserRateEntityService.changeStatusOfDecimal(id, userId, feeType, deci));
    }

    /*
     * 修改用户产品费率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(id);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        mmap.put("alipayUserRateEntity", alipayUserRateEntity);
        return prefix + "/edit";
    }

    @GetMapping("/edits/{ids}")
    public String edit(@PathVariable("ids") String ids, ModelMap mmap) {
        //查询产品类型下拉菜单
        mmap.put("ids", ids);
        List<AlipayUserRateEntity> rateEntityList = alipayUserRateEntityService.findRates(ids);
        StrBuilder strBuilder = StrBuilder.create();
        String type = "";
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            type = rateEntity.getPayTypr();
            strBuilder.append("id:").append(rateEntity.getId()).append(" 账号：").append(rateEntity.getUserId()).append(" ").append("原产品：").append(rateEntity.getPayTypr() + " ");
        }
        mmap.put("rete", strBuilder.toString());
        mmap.put("type", type);
      /*  List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        mmap.put("rateList", rateList);*/
        return prefix + "/edits";
    }

    @RequiresPermissions("merchant:rate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserRateEntity alipayUserRateEntity) {
        List<AlipayUserInfo> userList = new ArrayList<>();
        List<String> userNameList = new ArrayList<>();
        if (StrUtil.isNotEmpty(alipayUserRateEntity.getAgentUserId())) {
            userList = alipayUserFundEntityService.findUserByAgent(alipayUserRateEntity.getAgentUserId());
        }
        if (userList.size() > 0) {
            for (AlipayUserInfo user : userList) {
                userNameList.add(user.getUserId());
            }
            alipayUserRateEntity.setAgentList(userNameList);
        }
        startPage();
        List<AlipayUserRateEntity> list = alipayUserRateEntityService.selectAlipayUserRateEntityList(alipayUserRateEntity);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        BigDecimal a = new BigDecimal("0");
        for (AlipayUserRateEntity rate : list) {
            AlipayProductEntity product = prCollect.get(rate.getPayTypr());
            if (ObjectUtil.isNotNull(product)) {
                rate.setPayTypr(product.getProductName());
            }
        }
        return getDataTable(list);
    }

    /*
     * 新增保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:add")
    @Log(title = "新增商户费率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserRateEntity alipayUserRateEntity) {
     //  AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy(alipayUserRateEntity.getChannelId(), alipayUserRateEntity.getPayTypr());
       /* if (ObjectUtil.isNull(channel)) {
            return error("当前渠道未接通，请联系技术人员对接");
        }*/
        AlipayUserRateEntity check = alipayUserRateEntityService.checkUniqueRate(alipayUserRateEntity);
        if (null != check) {
            throw new BusinessException("操作失败，商户费率重复");
        }
        return toAjax(alipayUserRateEntityService.insertAlipayUserRateEntity(alipayUserRateEntity));
    }

    /*
     * 删除用户产品费率
     */
    @RequiresPermissions("merchant:rate:remove")
    @Log(title = "商户费率", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserRateEntityService.deleteAlipayUserRateEntityByIds(ids));
    }

    /**
     * 商户费率状态更新
     */
    @RequiresPermissions("merchant:rate:status")
    @Log(title = "商户费率修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult updateStatus(String id, String userId, String feeType, String switchs) {
        logger.info("[当前处理费率状态开启或关闭的管理员账号为：" + ShiroUtils.getSysUser().getLoginName() + "]");
        logger.info("[当前处理商户状态的参数为：" + switchs + "]");
        return toAjax(alipayUserRateEntityService.changeStatus(id, userId, feeType, switchs));
    }

    @RequiresPermissions("merchant:rate:edit")
    @Log(title = "商户费率批量修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edits")
    @ResponseBody
        public AjaxResult editsSave(String ids, final String channel) {
        List<AlipayUserRateEntity> rateEntityList = alipayUserRateEntityService.findRates(ids);
        Map map = new HashMap();
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            map.put(rateEntity.getChannelId(), rateEntity.getChannelId());
        }
        if (map.size() > 1) {
            throw new BusinessException("当前批量修改中存在多个渠道，请选择相同渠道完成批量修改");
        }
        for (AlipayUserRateEntity rateEntity : rateEntityList) {
            List<AlipayUserInfo> users = alipayUserInfoService.findAgenByUser(rateEntity.getUserId());
            if (CollUtil.isNotEmpty(users) || users.size() > 0) {
                continue;// 凡是存在下级代理商户的商户费率一律不允许批量切换，代理商费率批量结算容易造成业务bug
            }
            rateEntity.setChannelId(channel);
            try {
                alipayUserRateEntityService.clickFee(rateEntity);
                alipayUserRateEntityService.isAgentFee(rateEntity);
                JSONArray objects = JSONUtil.parseArray(rateEntity.getChannelId());
                for (Object mf : objects) {
                    JSONObject jsonObject = JSONUtil.parseObj(mf);
                    McFee mcFee = jsonObject.toBean(McFee.class);
                    AlipayChanelFee channel1 = alipayChanelFeeServiceImpl.findChannelBy( mcFee.getChannelId(), rateEntity.getPayTypr());
                    if (ObjectUtil.isNull(channel1)) {
                        return error("当前渠道未接通，请联系技术人员对接");
                    }
                }
                alipayUserRateEntityService.updateAlipayUserRateEntity(rateEntity);
            } catch (Exception e) {
              /*  ThreadUtil.execute(() -> {
                    if (e.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")) {//费率发生数据库唯一键约束
                        //停用当前费率，开启目标费率
                        int i = alipayUserRateEntityService.changeStatus(rateEntity.getId() + "", rateEntity.getUserId(), rateEntity.getFeeType() + "", "0");
                        if (i > 0) {
                            rateEntity.setStatus(null);
                            rateEntity.setSwitchs(null);
                            rateEntity.setChannelId(null);
                            List<AlipayUserRateEntity> rateEntityList1 = alipayUserRateEntityService.selectAlipayUserRateEntityList(rateEntity);
                            if (rateEntityList1.size() == 1) {
                                AlipayUserRateEntity rateEntity1 = rateEntityList1.get(0);
                                alipayUserRateEntityService.changeStatus(rateEntity1.getId() + "", rateEntity1.getUserId(), rateEntity1.getFeeType() + "", "1");
                            }
                        }
                    }
                });*/
            }

        }

        return success("修改完毕");
    }

    /*
     * 修改保存用户产品费率
     */
    @RequiresPermissions("merchant:rate:edit")
    @Log(title = "商户费率修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserRateEntity alipayUserRateEntity) {
        //1，查看当前修改完的费率是否有配置渠道费率,并检查是否有重复配置的情况
        //2，查看当前修完费率是否有配置代理商费率
        //3，如以上不存在问题，则保存当前修改完费率，且对相同产品类型的费率进行关闭
//        alipayUserRateEntityService.clickFee(alipayUserRateEntity);
//        alipayUserRateEntityService.isAgentFee(alipayUserRateEntity);
//        JSONArray objects = JSONUtil.parseArray(alipayUserRateEntity.getChannelId());
//         for (Object mf : objects) {
//            JSONObject jsonObject = JSONUtil.parseObj(mf);
//            McFee mcFee = jsonObject.toBean(McFee.class);
//             AlipayChanelFee channel = alipayChanelFeeServiceImpl.findChannelBy( mcFee.getChannelId(), alipayUserRateEntity.getPayTypr());
//             if (ObjectUtil.isNull(channel)) {
//                 return error("当前渠道未接通，请联系技术人员对接");
//             }
//         }
        return toAjax(alipayUserRateEntityService.updateAlipayUserRateEntity(alipayUserRateEntity));
    }
}

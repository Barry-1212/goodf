package cn.edu.lingnan.goodf.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.lingnan.goodf.entity.HospitalEntity;
import cn.edu.lingnan.goodf.service.HospitalService;



/**
 * 医院
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@RestController
@RequestMapping("goodf/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:hospital:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = hospitalService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * ID列表
     */
    @RequestMapping("/idList")
    public R IdList(){
        List<HospitalEntity> list = hospitalService.list();
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{hId}")
    //@RequiresPermissions("goodf:hospital:info")
    public R info(@PathVariable("hId") Integer hId){
		HospitalEntity hospital = hospitalService.getById(hId);

        return R.ok().put("hospital", hospital);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:hospital:save")
    public R save(@RequestBody HospitalEntity hospital){
		hospitalService.save(hospital);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:hospital:update")
    public R update(@RequestBody HospitalEntity hospital){
		hospitalService.updateById(hospital);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:hospital:delete")
    public R delete(@RequestBody Integer[] hIds){
		hospitalService.removeByIds(Arrays.asList(hIds));

        return R.ok();
    }

}

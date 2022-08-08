package cn.edu.lingnan.goodf.controller;

import java.util.Arrays;
import java.util.Map;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.service.DeparmentService;
import cn.edu.lingnan.goodf.service.DepclassService;
import cn.edu.lingnan.goodf.service.HospitalService;
import cn.edu.lingnan.goodf.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.edu.lingnan.goodf.entity.DoctorEntity;
import cn.edu.lingnan.goodf.service.DoctorService;



/**
 * 医生
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@RestController
@RequestMapping("goodf/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DeparmentService deparmentService;

    @Autowired
    private DepclassService depclassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:doctor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = doctorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dId}")
    //@RequiresPermissions("goodf:doctor:info")
    public R info(@PathVariable("dId") Integer dId){
		DoctorEntity doctor = doctorService.getById(dId);

        return R.ok().put("doctor", doctor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:doctor:save")
    public R save(@RequestBody DoctorEntity doctor){
        System.out.println(doctor);
		doctorService.save(doctor);

        return R.ok();
    }

    /**
     * * 后台管理系统的保存
     */
    @RequestMapping("/save2")
    public R save2(@RequestBody DoctorEntity doctor){
        System.out.println(doctor);
        doctor.setHName(hospitalService.getById(doctor.getHId()).getHName());
        doctor.setDepName(deparmentService.getById(doctor.getDepId()).getDepName());
        doctor.setDcName(depclassService.getById(doctor.getDcId()).getDcName());
        doctorService.save(doctor);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:doctor:update")
    public R update(@RequestBody DoctorEntity doctor){
		doctorService.updateById(doctor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:doctor:delete")
    public R delete(@RequestBody Integer[] dIds){
		doctorService.removeByIds(Arrays.asList(dIds));

        return R.ok();
    }




}

package cn.edu.lingnan.goodf.controller;

import java.util.Arrays;
import java.util.Map;


import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import cn.edu.lingnan.goodf.service.RegistrationService;
import org.springframework.web.servlet.ModelAndView;


/**
 * 挂号
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@RestController
@RequestMapping("goodf/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:registration:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = registrationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 查询挂号信息信息
     */
    @RequestMapping("/info/{numId}")
    //@RequiresPermissions("goodf:registration:info")
    public R info(@PathVariable("numId") Integer numId){
		RegistrationEntity registration = registrationService.getById(numId);

        return R.ok().put("registration", registration);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:registration:save")
    public R save(@RequestBody RegistrationEntity registration){
		registrationService.save(registration);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:registration:update")
    public R update(@RequestBody RegistrationEntity registration){
//        return registrationService.update(registration,model);
		registrationService.updateById(registration);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:registration:delete")
    public R delete(@RequestBody Integer[] numIds){
		registrationService.removeByIds(Arrays.asList(numIds));

        return R.ok();
    }

    /**
     *   用户查询自己id查询自己的挂号信息
     */
//    @PostMapping("/searchInfoByUserId")
//    public String searchInfoByUserId(int userId , Model model){
//        System.out.println("1111111111111");
//        return registrationService.searchInfoByUserId(userId,model);
//    }

    /**
     *   用户查询自己id查询自己的挂号信息
     */
        @RequestMapping("/searchInfoByUserId")
    public String searchInfoByUserId(int userId , Model model){
        System.out.println("1111111111111");
        return registrationService.searchDetailInfoByUserId(userId,model);
    }

}

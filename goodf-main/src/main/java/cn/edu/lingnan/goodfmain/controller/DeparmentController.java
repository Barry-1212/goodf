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

import cn.edu.lingnan.goodf.entity.DeparmentEntity;
import cn.edu.lingnan.goodf.service.DeparmentService;



/**
 * 科室
 *111
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@RestController
@RequestMapping("goodf/deparment")
public class DeparmentController {
    @Autowired
    private DeparmentService deparmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:deparment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deparmentService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * ID列表
     */
    @RequestMapping("/idList")
    public R IdList(){
        List<DeparmentEntity> list = deparmentService.list();
        return R.ok().put("list", list);
    }


      /**
     * 信息
     */
    @RequestMapping("/info/{depId}")
    //@RequiresPermissions("goodf:deparment:info")
    public R info(@PathVariable("depId") Integer depId){
        DeparmentEntity deparment = deparmentService.getById(depId);

        return R.ok().put("deparment", deparment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:deparment:save")
    public R save(@RequestBody DeparmentEntity deparment){
		deparmentService.save(deparment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:deparment:update")
    public R update(@RequestBody DeparmentEntity deparment){
		deparmentService.updateById(deparment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:deparment:delete")
    public R delete(@RequestBody Integer[] depIds){
		deparmentService.removeByIds(Arrays.asList(depIds));

        return R.ok();
    }

}

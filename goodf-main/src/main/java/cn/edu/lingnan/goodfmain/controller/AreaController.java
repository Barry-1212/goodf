package cn.edu.lingnan.goodf.controller;


import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.entity.AreaEntity;
import cn.edu.lingnan.goodf.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("goodf/area")
public class AreaController {

    @Autowired
    private AreaService areaService;





    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:deparment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = areaService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     *ID列表
     **/
    @RequestMapping("/idList")
    public R IdList(){
        List<AreaEntity> list = areaService.list();
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{aId}")
    //@RequiresPermissions("goodf:deparment:info")
    public R info(@PathVariable("aId") Integer aId){
        AreaEntity area = areaService.getById(aId);

        return R.ok().put("area", area);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:deparment:save")
    public R save(@RequestBody AreaEntity area){
        areaService.save(area);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:deparment:update")
    public R update(@RequestBody AreaEntity area){
        areaService.updateById(area);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:deparment:delete")
    public R delete(@RequestBody Integer[] aIds){
        areaService.removeByIds(Arrays.asList(aIds));
        return R.ok();
    }

}

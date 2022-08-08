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

import cn.edu.lingnan.goodf.entity.DepclassEntity;
import cn.edu.lingnan.goodf.service.DepclassService;


/**
 * 科室分类
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@RestController
@RequestMapping("goodf/depclass")
public class DepclassController {
    @Autowired
    private DepclassService depclassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:depclass:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = depclassService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * ID列表
     */
    @RequestMapping("/idList")
    public R IdList(){
        List<DepclassEntity> list = depclassService.list();
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dcId}")
    //@RequiresPermissions("goodf:depclass:info")
    public R info(@PathVariable("dcId") Integer dcId){
		DepclassEntity depclass = depclassService.getById(dcId);

        return R.ok().put("depclass", depclass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:depclass:save")
    public R save(@RequestBody DepclassEntity depclass){
		depclassService.save(depclass);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:depclass:update")
    public R update(@RequestBody DepclassEntity depclass){
		depclassService.updateById(depclass);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:depclass:delete")
    public R delete(@RequestBody Integer[] dcIds){
		depclassService.removeByIds(Arrays.asList(dcIds));

        return R.ok();
    }

}

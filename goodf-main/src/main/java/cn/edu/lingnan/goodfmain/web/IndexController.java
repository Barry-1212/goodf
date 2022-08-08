package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.service.DoctorService;
import cn.edu.lingnan.goodf.vo.IndexVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/web")
public class IndexController {

    @Autowired
    DoctorService doctorService;

    //获取地区中所有医生并返回主页
    @GetMapping("/getDoctorsByAreaId/{id}")
    public String getDoctorsByAreaId(@PathVariable("id") Integer areaId, Model model){
        IndexVO indexVO = doctorService.getDoctorsByAreaId(areaId);
        model.addAttribute("indexVo", indexVO);
        return "hospital/index";
    }

    //获取医院的所有医生
    @GetMapping("/getDoctorsByHid/{hId}")
    public String getDoctorsByHid(@PathVariable("hId") Integer hId, Model model) {
        IndexVO indexVO = doctorService.getDoctorsByHid(hId);
        model.addAttribute("indexVo", indexVO);
        return "hospital/index";
    }

    //获取该外科所有医生
    @GetMapping("/getDoctorsByDcId/{dcId}")
    public String getDoctorsByDcId(@PathVariable("dcId") Integer dcId, Model model) {
        IndexVO indexVO = doctorService.getDoctorsByDcId(dcId);
        model.addAttribute("indexVo", indexVO);
        return "hospital/index";
    }

}

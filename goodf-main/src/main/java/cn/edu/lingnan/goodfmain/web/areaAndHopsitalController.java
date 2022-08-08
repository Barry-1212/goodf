package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.service.AreaService;
import cn.edu.lingnan.goodf.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class areaAndHopsitalController {
    @Autowired
    private AreaService areaService;
 @Autowired
 private HospitalService hospitalService;

    //通过首页的找医院，点击不同的地址，获取该地址的id，随后通过该id查询所属地所有的医院
    //并且查询所有的地区一起传送到下一个页面继续使用数据
    @GetMapping("GetHospitalByAreaIdAndGetAllArea")
    public String GetHospitalByAreaIdAndGetAllArea (String area, Model model){
        return areaService.GetHospitalByAreaIdAndGetAllArea(area,model);
    }

    @GetMapping("GetHospitalByHId")
    public String GetHospitalByHId(Integer hId,Model model){
        return hospitalService.GetHospitalByHId(hId,model);
    }

    @GetMapping("GetDocInfoByHId")
    public String GetDocInfoByHId(Integer hId,Model model){
        return hospitalService.GetDocInfoByHId(hId, model);
    }


}

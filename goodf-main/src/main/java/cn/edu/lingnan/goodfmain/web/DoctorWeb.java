package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/doctorWeb")
public class DoctorWeb {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/detail")
    public String selectDoctorDetailByDid(Integer dId , Model model){
        return doctorService.SelectDoctorDetailByDId(dId,model);
    }

    @GetMapping("/toYuYue")
    public String toYuYue(HttpSession session , Integer dId , Model model){
        return doctorService.toYuYue(session,dId,model);
    }
}

package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.dao.DoctorDao;
import cn.edu.lingnan.goodf.entity.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.HospitalDao;
import cn.edu.lingnan.goodf.entity.HospitalEntity;
import cn.edu.lingnan.goodf.service.HospitalService;
import org.springframework.ui.Model;


@Service("hospitalService")
public class HospitalServiceImpl extends ServiceImpl<HospitalDao, HospitalEntity> implements HospitalService {
    @Autowired(required = false)
    private HospitalDao hospitalDao;
@Autowired(required = false)
private DoctorDao doctorDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HospitalEntity> page = this.page(
                new Query<HospitalEntity>().getPage(params),
                new QueryWrapper<HospitalEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String GetHospitalByHId(Integer hId, Model model) {
        HospitalEntity hospitalEntity =hospitalDao.selectById(hId);
        System.out.println(hospitalEntity);
        model.addAttribute("hospital",hospitalEntity);
        System.out.println("---------------");
        return "hospital/hospitalInfo";
    }

    @Override
    public List<HospitalEntity> selectAllHospital() {
        return list();
    }

//    @Override
//    public String GetDocInfoByHId(Integer hId, Model model) {
//        DoctorEntity doctorEntity = doctorDao.selectById(hId);
//        System.out.println(doctorEntity);
//        model.addAttribute("doctor",doctorEntity);
//        System.out.println("-----------------");
//        return "hospital/docInfo";
//    }

      @Override
    public String GetDocInfoByHId(Integer hId,Model model){
        QueryWrapper<DoctorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("h_id",hId);
          List<DoctorEntity> doctorEntityList = doctorDao.selectList(queryWrapper);
          System.out.println(doctorEntityList);
          System.out.println("----------------");
          model.addAttribute("doctor",doctorEntityList);
        return "hospital/docInfo";
      }
}
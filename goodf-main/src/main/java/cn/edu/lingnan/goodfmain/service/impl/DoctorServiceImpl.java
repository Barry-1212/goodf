package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.dao.DepclassDao;
import cn.edu.lingnan.goodf.entity.AreaEntity;
import cn.edu.lingnan.goodf.entity.DepclassEntity;
import cn.edu.lingnan.goodf.entity.UserEntity;
import cn.edu.lingnan.goodf.entity.HospitalEntity;
import cn.edu.lingnan.goodf.service.*;
import cn.edu.lingnan.goodf.vo.IndexVO;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.DoctorDao;
import cn.edu.lingnan.goodf.entity.DoctorEntity;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;



@Service("doctorService")
public class DoctorServiceImpl extends ServiceImpl<DoctorDao, DoctorEntity> implements DoctorService {

    @Autowired(required = false)
    private DoctorDao doctorDao;

    @Autowired
    private DepclassService depclassService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private HospitalService hospitalService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DoctorEntity> page = this.page(
                new Query<DoctorEntity>().getPage(params),
                new QueryWrapper<DoctorEntity>()
        );

        return new PageUtils(page);
    }



    @Override
    public IndexVO getIndexVo() {
        /**
         * yyh
         * user登录成功后跳转到index
         * 根据tel查询登录的user
         * 全部医生
         * 科室
         * 医院
         */
        IndexVO indexVO = new IndexVO();
        indexVO.setDoctorEntities(this.list());
        indexVO.setAreaEntities(areaService.selectAreaAll());
        indexVO.setDepclassEntities(depclassService.selectDepclass());
        indexVO.setHospitalEntities(hospitalService.selectAllHospital());
        return indexVO;
    }

    /*
    jjr
    实现从去到医生详细情况页面
     */

    @Override
    public String SelectDoctorDetailByDId(Integer dId, Model model) {
        //selectById会得到一个对象,从主页得到一个对象
        DoctorEntity doctorEntity = doctorDao.selectById(dId);
        model.addAttribute("DoctorDetail",doctorEntity);
        return "doctor/doctorDetail";
    }
    /*
    jjr
    在医生详情页面跳转到该医生的预约页面
     */
    @Override
    public String toYuYue(HttpSession session, Integer dId, Model model) {
        //根据session拿到该用户的实体类信息  感觉这步可以不判断是否登录？登不登录应该都能看到某个医生的出诊事件才对的？感觉是这样的
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        Integer userId = userEntity.getUserId();
        System.out.println(userId);
        //拿到该医生信息，并绑定到希望到达的试图
        DoctorEntity doctorEntity = doctorDao.selectById(dId);
        System.out.println("0-----------------");
        System.out.println(dId);
        model.addAttribute("doctorDetail",doctorEntity);
        return "doctor/yuYue";
    }

    @Override
    public IndexVO getDoctorsByAreaId(Integer areaId) {
        List<HospitalEntity> hospitalEntities = hospitalService.list(new QueryWrapper<HospitalEntity>().eq("a_id", areaId));
        //地区包含的所有医院id
        List<Integer> hIds = hospitalEntities.stream().map((entity) -> {
            return entity.getHId();
        }).collect(Collectors.toList());
        //查询所有hIds医院中的所有医生
        List<DoctorEntity> list = doctorDao.getDoctorsByHid(hIds);
        IndexVO indexVo = createIndexVo(list);
        return indexVo;
    }

    private IndexVO createIndexVo(List<DoctorEntity> doctors) {
        IndexVO indexVO = new IndexVO();
        indexVO.setDoctorEntities(doctors);
        //地区
        indexVO.setAreaEntities(areaService.selectAreaAll());
        //医院
        indexVO.setHospitalEntities(hospitalService.selectAllHospital());
        //科室
        indexVO.setDepclassEntities(depclassService.selectDepclass());
        return indexVO;
    }

    @Override
    public IndexVO getDoctorsByHid(Integer hId) {
        List<DoctorEntity> doctorEntities = this.list(new QueryWrapper<DoctorEntity>().eq("h_id", hId));
        IndexVO indexVo = createIndexVo(doctorEntities);
        return indexVo;
    }

    @Override
    public IndexVO getDoctorsByDcId(Integer dcId) {
        List<DoctorEntity> doctorEntities = this.list(new QueryWrapper<DoctorEntity>().eq("dc_id", dcId));
        IndexVO indexVo = createIndexVo(doctorEntities);
        return indexVo;
    }

}
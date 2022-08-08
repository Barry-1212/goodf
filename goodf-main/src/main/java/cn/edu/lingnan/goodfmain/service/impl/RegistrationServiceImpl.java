package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Pay;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.dao.DoctorDao;
import cn.edu.lingnan.goodf.entity.DoctorEntity;
import cn.edu.lingnan.goodf.entity.UserEntity;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.RegistrationDao;
import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import cn.edu.lingnan.goodf.service.RegistrationService;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;


@Service("registrationService")
public class RegistrationServiceImpl extends ServiceImpl<RegistrationDao, RegistrationEntity> implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private DoctorDao doctorDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RegistrationEntity> page = this.page(
                new Query<RegistrationEntity>().getPage(params),
                new QueryWrapper<RegistrationEntity>()
        );

        return new PageUtils(page);
    }

    // 用户根据自己id查询对应挂号信息
    @Override
    public String searchInfoByUserId(int userId , Model model) {
        // 根据i用户d查出用户的相关挂号信息
        List<RegistrationEntity> list = registrationDao.selectList(new QueryWrapper<RegistrationEntity>().eq("user_id", userId));

//        封装数据
        model.addAttribute("registrationEntityList",list);

//        if(list.size()!= 0){
//            String userName = list.get(0).getUserName();
//            model.addAttribute("userId",userId);
//            model.addAttribute("userName",userName);
//            System.out.println("11111");
//            System.out.println(list);
//        }
        return "hospital/personalCenter";
    }

    @Override
    public R yuYue(HttpSession session, Integer dId, Model model) {
        System.out.println("did"+dId);
        if(dId == null){
            return R.error(201,"请重新登录刷新医生信息");
        }
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if(userEntity == null){
            return R.error(202,"用户请先登录再进行预约功能");
        }
        Integer userId = userEntity.getUserId();
        //根据userId与did查询挂号表，是否存在
        QueryWrapper<RegistrationEntity> queryWrapper = new QueryWrapper<>();

    /*
    visit_status，该列是 就诊状态  ，若是未就诊且之前有了挂号信息，这里就会有患者的挂号信息，则不允许重新挂该医生的号，
    当然，若是时间过了，则是会改变  就诊状态  ， 只需要就诊状态不为“2”就行了，“2”表示 待就诊
     */
        queryWrapper.eq("user_id",userId).eq("d_id",dId).eq("payment_status", "已支付");
        RegistrationEntity registrationEntity = registrationDao.selectOne(queryWrapper);
        //根据dId查询这个医生的信息
        QueryWrapper<DoctorEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("d_id",dId);
        DoctorEntity doctorEntity = doctorDao.selectOne(queryWrapper1);
        if(registrationEntity != null){
            return R.error(203,"你已经预约过该医生了，请注意就诊时间");
        }
        //没有就加进去  2为 未支付，我说的
        //.setUserId(userId).setVisitStatus(2).setPaymentStatus(2);
        RegistrationEntity registrationEntity1
                = new RegistrationEntity().setDId(dId).setUserId(userId).setUserName(userEntity.getUserName())
                .setHId(doctorEntity.getHId()).setDepId(doctorEntity.getDepId()).setDcId(doctorEntity.getDcId())
                .setHName(doctorEntity.getHName()).setDName(doctorEntity.getDName()).setDepName(doctorEntity.getDepName())
                .setDcName(doctorEntity.getDcName()).setTime(new Date()).setPaymentStatus("未支付").setVisitStatus("未就诊");
        int row = registrationDao.insert(registrationEntity1);
        if(row != 1){
            return R.error(204,"未知错误，请稍后再试");
        }
        return R.ok("正在前往支付页面").put("numId" , registrationEntity1.getNumId());
    }

    // 用户根据自己id查询对应挂号信息 2.0
    @Override
    public String searchDetailInfoByUserId(int userId, Model model) {
        // 根据i用户d查出用户的相关挂号信息
//        List<RegistrationEntity> list = registrationDao.searchDetailInfoByUserId(new QueryWrapper<RegistrationEntity>().eq("user_id", userId));
        ArrayList<HashMap> list = registrationDao.searchDetailInfoByUserId(userId);

//        封装数据
        model.addAttribute("registrationEntityList",list);
        System.out.println("11111");
        System.out.println(list);
        return "hospital/personalCenter";
    }




}
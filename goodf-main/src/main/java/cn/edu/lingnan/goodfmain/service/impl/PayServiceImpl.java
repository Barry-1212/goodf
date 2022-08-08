package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.Pay;
import cn.edu.lingnan.goodf.dao.DoctorDao;
import cn.edu.lingnan.goodf.dao.RegistrationDao;
import cn.edu.lingnan.goodf.entity.DoctorEntity;
import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import cn.edu.lingnan.goodf.service.PayService;
import cn.edu.lingnan.goodf.vo.PayVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("PayService")
public class PayServiceImpl implements PayService {
    @Autowired(required = false)
    RegistrationDao registrationDao;

    @Autowired(required = false)
    DoctorDao doctorDao;

    @Override
    public String pay(String numId) throws Exception{
        RegistrationEntity registrationEntity = registrationDao.selectOne(new QueryWrapper<RegistrationEntity>().eq("num_id", numId));
        if (registrationEntity == null){
            return null;
        }
        DoctorEntity doctorEntity = doctorDao.selectOne(new QueryWrapper<DoctorEntity>().eq("d_id", registrationEntity.getDcId()));
        if (doctorEntity == null){
            return null;
        }
        String form = Pay.zhifubPay(doctorEntity.getReMoney().toString() , numId);
        return form;
    }

    @Override
    public String handlePayResult(PayVo vo) {
        int update = registrationDao.update(new RegistrationEntity().setPaymentStatus("已支付"),
                new QueryWrapper<RegistrationEntity>().eq("num_id", vo.getOut_trade_no()));
        return null;
    }
}

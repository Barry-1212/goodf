package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.common.utils.SendTelCode;
import cn.edu.lingnan.goodf.dao.CodeDao;
import cn.edu.lingnan.goodf.entity.CodeEntity;
import cn.edu.lingnan.goodf.service.CodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service("codeService")
public class CodeServiceImpl  extends ServiceImpl<CodeDao, CodeEntity> implements CodeService {

    @Autowired(required = false)
    private CodeDao codeDao;

    @Override
    public R checkAndSend(String tel , HttpSession httpSession) {
        QueryWrapper<CodeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel" , tel);
        CodeEntity codeEntity = this.baseMapper.selectOne(queryWrapper);
//        List<CodeEntity> codeEntities = this.baseMapper.selectList(queryWrapper);
//        if (codeEntity != null )
//            return R.error(201 , "该手机号码已注册");
//        System.out.println("tel"+tel+"---code"+codeEntity);
        if ( codeEntity != null){
            Date date = new Date();
            long dataNew =date.getTime();
            long creatTime = codeEntity.getCreateTime().getTime();
            long num = dataNew - creatTime;
            if (num < 5*60*1000) {
                long minutes = 5 - (num % (1000 * 60 * 60)) / (1000 * 60);
                return R.error(202, "5分钟只能发送一次，请"+ minutes + "分钟后再试");
            }
            this.baseMapper.delete(new QueryWrapper<CodeEntity>().eq("tel" , codeEntity.getTel()));
        }
        SendTelCode sendTelCode = new SendTelCode();
        boolean b =  sendTelCode.sendCode(tel , httpSession);
        if (!b) {
            return R.error(203, "验证码发送失败");
        }
        CodeEntity codeEntity1 = new CodeEntity();
        codeEntity1.setCreateTime(new Date());
        codeEntity1.setTel(tel);
        codeDao.insert(codeEntity1);
        return R.ok("验证码发送成功");
    }
}

package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.dao.AreaDao;
import cn.edu.lingnan.goodf.dao.HospitalDao;
import cn.edu.lingnan.goodf.entity.AreaEntity;
import cn.edu.lingnan.goodf.entity.HospitalEntity;
import cn.edu.lingnan.goodf.service.AreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao , AreaEntity> implements AreaService {
    @Autowired
    private  AreaDao areaDao;
    @Autowired
    private HospitalDao hospitalDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AreaEntity> page = this.page(
                new Query<AreaEntity>().getPage(params),
                new QueryWrapper<AreaEntity>()
        );

        return new PageUtils(page);
    }


    //通过首页的找医院，点击不同的地址，获取该地址的id，随后通过该id查询所属地所有的医院
    //并且查询所有的地区一起传送到下一个页面继续使用数据
    @Override
    public String GetHospitalByAreaIdAndGetAllArea(String area, Model model){
        QueryWrapper<AreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("area",area);
        AreaEntity areaEntity = areaDao.selectOne(queryWrapper);
        System.out.println(areaEntity);
        System.out.println("------------");
        QueryWrapper<HospitalEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("a_id" ,areaEntity.getAId());
        List<HospitalEntity> hospitalEntityList = hospitalDao.selectList(queryWrapper1);
        System.out.println(hospitalEntityList);
        model.addAttribute("hospitalEntityList",hospitalEntityList);
        System.out.println("---------------");

        List<AreaEntity> areaEntityList = areaDao.selectList(null);
        System.out.println(areaEntityList);
        model.addAttribute("areaEntityList",areaEntityList);
        System.out.println("-----------------");

//返回一个页面，这个页面就是显示所属地址中的所有医院以及展示其他地址
        return "hospital/areaListAndhospitlaList";
    }

    @Override
    public List<AreaEntity> selectAreaAll(){
        return this.list(new QueryWrapper<AreaEntity>());
    }
}

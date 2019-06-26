package com.integral.service.impl;


import com.integral.mapper.IntegralSpecificationsMapper;
import com.integral.mapper.IntegralSpecificationsValueMapper;
import com.integral.model.AddSepci;
import com.integral.model.IntegralSpecifications;
import com.integral.model.IntegralSpecificationsValue;
import com.integral.model.Result;
import com.integral.service.SpecificationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service.impl
 * @date:2019/5/30
 **/
@Service
@Slf4j
public class SpecificationsServiceImpl implements SpecificationsService {

    @Autowired
    private IntegralSpecificationsMapper specificationsMapper;
    @Autowired
    private IntegralSpecificationsValueMapper specificationsValueMapper;

    /**
     * 添加商品规格
     *
     * @param addSepci
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addSpec(AddSepci addSepci) {
        if(addSepci!=null) {
            IntegralSpecifications specifications = new IntegralSpecifications();
            BeanUtils.copyProperties(specifications,addSepci);
//            specifications.setSpecifications_name(addSepci.getSpeciName());
//            specifications.setGoods_level(addSepci.getLevel());
//            specifications.setGoods_type(addSepci.getType());
            Integer id = specificationsMapper.querySEQ();
            try {
                specificationsMapper.insert(specifications);
                addSepci.getValue().forEach(a -> {
                    IntegralSpecificationsValue specificationsValue = new IntegralSpecificationsValue();
                    specificationsValue.setValue(a);
                    specificationsValue.setIntegral_specifications_id(id);
                    specificationsValueMapper.insert(specificationsValue);
                });
                specifications = new IntegralSpecifications();
                specifications.setId(id);
                specificationsMapper.findByParam(specifications);
                return Result.ok(Result.build(), "", specificationsMapper.findByParam(specifications));
            } catch (Exception e) {
                log.error("com.integral.service.impl.SpecificationsServiceImpl.addSpec{}",e.getMessage());
                throw new RuntimeException("添加规格服务异常");
            }
        }
        return Result.error(Result.build(),"不能添加空规格","");
    }
}

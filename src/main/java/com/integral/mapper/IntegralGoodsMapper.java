package com.integral.mapper;


import com.integral.model.AppGoodsPage;
import com.integral.model.IntegralGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface IntegralGoodsMapper extends BaseMapper<IntegralGoods> {


    public List<AppGoodsPage> findGoods(Map<String, Object> param);


    @Select("select SEQ_INTEGRAL_GOODS.Nextval id from dual")
    public Integer querySEQ();

    /**
     * 商品详情
     * @param id
     * @return
     */
    public List<Map<String,Object>> goodsSpec(Integer id);

    /**
     * 商品种类
     * @return
     */
    public List<Map<String,Object>> getGoodsType();
}

package com.integral.service.base;

import com.github.pagehelper.PageInfo;
import com.integral.model.IntegralGoods;
import com.integral.model.Result;

import java.util.Map;

public interface GoodsService {

    /**
     * 商品分页查询
     * @param pageNum
     * @param pageSize
     * @param param
     * @return
     */
    public Result findGoodsPageBy(Integer pageNum, Integer pageSize, Map<String,Object> param);

    /**
     *
     * @param integralGoods
     */
    public void insertGoods(IntegralGoods integralGoods);

    /**
     * 编辑商品
     * @param integralGoods
     */
    public void updateGoods(IntegralGoods integralGoods);

    /**
     * 商品详情
     * @param goodId
     * @return
     */
    public Result goodsDetail(Integer goodId);

    /**
     * 商品上下架
     * @param goodId
     * @param action
     * @return
     */
    public Result upLoadOrDown(Integer goodId,String action);
}

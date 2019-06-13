package com.integral.service;


import com.integral.model.GoodsShelvesEntity;
import com.integral.model.Result;
import com.integral.model.ExchangeGoodsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service
 * @date:2019/5/27
 **/
public interface DoGoodService {

    /**
     * 商品上架
     *
     * @param goodsShelvesEntity
     */
    public Result goodsShelves(GoodsShelvesEntity goodsShelvesEntity, List<MultipartFile> parentFile, MultipartFile sunFile);

    /**
     * 商品编辑
     *
     * @param goodsShelvesEntity
     * @return
     */
    public Result editorGoods(GoodsShelvesEntity goodsShelvesEntity);

    /**
     * 商品删除
     *
     * @param id
     * @return
     */
    public Result deleteGoods(Integer id);

    /**
     * 商品兑换
     *
     * @param exchangeGoodsEntity
     * @return
     */
    public Result exchangeGoods(ExchangeGoodsEntity exchangeGoodsEntity,String pay_type,String pay_postage_type);
}

package com.integral.service.base.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.integral.constant.Constant;
import com.integral.mapper.IntegralFileMapper;
import com.integral.mapper.IntegralGoodsMapper;
import com.integral.model.*;
import com.integral.service.base.GoodsService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private IntegralGoodsMapper integralGoodsMapper;
    @Autowired
    private IntegralFileMapper fileMapper;

    @Override
    public Result findGoodsPageBy(Integer pageNum, Integer pageSize, Map<String, Object> param) {
        try {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 10 : pageSize;
            List<AppGoodsPage> goodsList = integralGoodsMapper.findGoods(param);
            goodsList.forEach(a -> {
                IntegralFile fileEntity = new IntegralFile();
                fileEntity.setGoods_id(a.getId());
                List<IntegralFile> file = fileMapper.findByParam(fileEntity);
                if (file != null && !file.isEmpty()) {
                    a.setFile_path(file.get(0).getFile_path());
                }

            });
            PageInfo<AppGoodsPage> pageInfo = new PageInfo<>(goodsList);
            return Result.ok(Result.build(), "", pageInfo);
        } catch (Exception e) {
            log.error("com.integral.service.base.impl.GoodsServiceImpl.findGoodsPageBy商品分页查询异常{}", e.getMessage());
            return Result.error(Result.build(), "", "");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertGoods(IntegralGoods integralGoods) {
        try {
            integralGoodsMapper.insert(integralGoods);
        } catch (Exception e) {
            log.error("com.integral.service.base.impl.GoodsServiceImpl.insertGoods保存商品异常{}", e.getMessage());
            throw new RuntimeException("保存商品异常");
        }
    }

    /**
     * 编辑商品
     *
     * @param integralGoods
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGoods(IntegralGoods integralGoods) {
        if (integralGoods != null) {
            IntegralGoods goods = new IntegralGoods();
            goods.setId(integralGoods.getId());
            goods.setCount(integralGoods.getCount());
            goods.setGoods_name(integralGoods.getGoods_name());
            goods.setPay_points(integralGoods.getPay_points());
            goods.setPrice(integralGoods.getPrice());
            try {
                integralGoodsMapper.update(goods);
            } catch (Exception e) {
                throw new RuntimeException("商品修改失败");
            }
        }
    }

    @Override
    public Result goodsDetail(Integer goodsId) {
        if (goodsId != null) {
            IntegralGoods goods = new IntegralGoods();
            goods.setId(goodsId);
            goods.setStatus(Constant.ENABLE);
            IntegralGoods goodsEntity = integralGoodsMapper.findOne(goods);

            GoodsDetailParam param = new GoodsDetailParam();
            if (goodsEntity != null) {
                //查询规格
                param.setGoods(goodsEntity);
                List<Map<String, Object>> spec = integralGoodsMapper.goodsSpec(goodsId);
                IntegralFile file = new IntegralFile();
                file.setGoods_id(goodsEntity.getId());
                List<IntegralFile> parentsFile = fileMapper.findByParam(file);
                param.setSpecifications(spec == null ? null : spec);
                List<String> parentsPath = new ArrayList<>();
                if (parentsFile != null && !parentsFile.isEmpty()) {
                    parentsFile.forEach(a -> {
                        parentsPath.add(a.getFile_path());
                    });
                }
                param.setParentPath(parentsPath);
                IntegralFile sunFile = null;
                Integer sunId = parentsFile != null ? 0 : parentsFile.get(0).getSun_id();
                if(sunId!=0) {
                    IntegralFile sunfile = new IntegralFile();
                    sunfile.setId(sunId);
                    sunFile = fileMapper.findOne(sunfile);
                }
                param.setSunPath(sunFile == null ? null : sunFile.getFile_path());
            }
            return Result.ok(Result.build(), "", param);
        }
        return Result.error(Result.build(), "未查询到该商品，请刷新页面", "");
    }

    /**
     * 商品上下架
     *
     * @param goodId
     * @param action
     * @return
     */
    @Override
    public Result upLoadOrDown(Integer goodId, String action) {
        IntegralGoods goods = new IntegralGoods();
        goods.setId(goodId);
        IntegralGoods goodsEntity = integralGoodsMapper.findOne(goods);
        if (goodsEntity == null) {
            return Result.error(Result.build(), "该商品不存在请刷新页面", "");
        }
        goods.setStatus(action);
        integralGoodsMapper.update(goods);
        goods.setStatus("");
        return Result.ok(Result.build(), goodsEntity.getGoods_name()+action+"成功", "");
    }


}

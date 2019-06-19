package com.integral.service.impl;

import com.integral.constant.Constant;
import com.integral.constant.PayEnum;
import com.integral.mapper.*;
import com.integral.model.*;
import com.integral.service.DoGoodService;
import com.integral.service.base.GoodsService;
import com.integral.service.base.SupplierService;
import com.integral.util.FileUtils;
import com.integral.util.NumberUtil;
import com.integral.util.TimeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service.impl
 * @date:2019/5/27
 **/
@Service
@Slf4j
public class DoGoodServiceImpl implements DoGoodService {

    @Autowired
    private IntegralGoodsMapper integralGoodsMapper;
    @Autowired
    private IntegralFileMapper integralFileMapper;
    @Autowired
    private IntegralSpecificationsMapper integralSpecificationsMapper;
    @Autowired
    private IntegralitemMapper integralitemMapper;
    @Autowired
    private IntegralitemCssMapper integralitemCssMapper;
    @Autowired
    private IntegralHistoryMapper integralHistoryMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private IntegralSupplierMapper supplierMapper;
    @Autowired
    private IntegralMemberCcsMapper memberCcsMapper;
    @Autowired
    private IntegralSpecificationsGoodsMapper specificationsGoodsMapper;
    @Autowired
    private FileUtils fileUtils;

    private List<CompletableFuture> futureList = new ArrayList<>();

    /**
     * 商品上架
     *
     * @param goodsShelvesEntity
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result goodsShelves(GoodsShelvesEntity goodsShelvesEntity, List<MultipartFile> parentFile, MultipartFile sunFile) {
        log.info("商品上架入参{}", goodsShelvesEntity.toString());
        try {
            Integer goodsId = integralGoodsMapper.querySEQ();

            IntegralFile sunFileEntity = new IntegralFile();
            Integer sun_id = integralFileMapper.querySEQ();
            String sunPath = "";
            try {
                sunPath = fileUtils.upload(sunFile);

                sunFileEntity.setId(sun_id);
                sunFileEntity.setCreate_date(new Date());
                sunFileEntity.setFile_path(sunPath);
                sunFileEntity.setStatus(Constant.ENABLE);
                integralFileMapper.insert(sunFileEntity);
            } catch (IOException e) {
                log.error("附图上传失败{}", e.getMessage());
                throw new RuntimeException("附图上传失败");
            }


            parentFile.forEach(a -> {
                Integer parentId = integralFileMapper.querySEQ();
                IntegralFile file = new IntegralFile();
                try {
                    String path = fileUtils.upload(a);
                    file.setId(parentId);
                    file.setSun_id(sun_id);
                    file.setGoods_id(goodsId);
                    file.setCreate_date(new Date());
                    file.setFile_path(path);
                    file.setGoods_id(goodsId);
                    file.setStatus(Constant.ENABLE);
                    integralFileMapper.insert(file);
                } catch (IOException e) {
                    log.error("主图上传失败{}", e.getMessage());
                    throw new RuntimeException("主图上传失败");
                }

            });

            IntegralGoods goods = new IntegralGoods();
            BeanUtils.copyProperties(goods,goodsShelvesEntity);
//            goods.setCount(goodsShelvesEntity.getCount());
//            goods.setGoods_name(goodsShelvesEntity.getGood_name());
//            goods.setPay_points(goodsShelvesEntity.getPay_point());
//            goods.setPrice(goodsShelvesEntity.getPrice());
//            goods.setRemark(goodsShelvesEntity.getRemark());
//            goods.setIntegral_supplier_id(goodsShelvesEntity.getIntegral_supplier_id());
//            goods.setType_name(goodsShelvesEntity.getType_name());
//            goods.setPostage(goodsShelvesEntity.getPostage());
            goods.setCreate_date(new Date());
            goods.setUpdate_date(new Date());
            goods.setStatus(Constant.ENABLE);
            goods.setId(goodsId);

            integralGoodsMapper.insert(goods);
            return Result.ok(Result.build(), "", "");
        } catch (
                Exception e) {
            log.error("com.integral.service.impl.DoGoodServiceImpl.goodsShelves商品上架失败{}", e.getMessage());
            return Result.error(Result.build(), "", "");
        }

    }

    /**
     * 商品编辑
     *
     * @param goodsShelvesEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result editorGoods(GoodsShelvesEntity goodsShelvesEntity) {
        /*if (goodsShelvesEntity != null && goodsShelvesEntity.getIntegralGoods() != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goodsShelvesEntity.getIntegralGoods().getId());
            List<AppGoodsPage> goodsList = integralGoodsMapper.findGoods(map);
            if (goodsList != null && !goodsList.isEmpty()) {
                log.info("商品编辑{}", goodsShelvesEntity.toString());
                goodsService.updateGoods(goodsShelvesEntity.getIntegralGoods());
                return Result.ok(Result.build(), "", "");
            }
        }*/
        return Result.error(Result.build(), "为获取到商品信息,请刷新页面重试", "");
    }

    /**
     * 商品删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteGoods(Integer id) {
       /* Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        List<AppGoodsPage> goodsList = integralGoodsMapper.findGoods(param);
        if (goodsList != null && !goodsList.isEmpty()) {
            log.info("删除商品{}", goodsList.toString());
            IntegralFile fileEntity = new IntegralFile();
            fileEntity.setId(goodsList.get(0).getId());
            List<IntegralFile> integralFile = integralFileMapper.findByParam(fileEntity);
            if (integralFile != null && !integralFile.isEmpty()) {
                //删除主图片
                integralFileMapper.delete(integralFile.get(0).getId());
                fileEntity = new IntegralFile();
                fileEntity.setId(goodsList.get(0).getId());
                fileEntity.setPatent_id(integralFile.get(0).getId());
                //删除副图片
                List<IntegralFile> integralFileList = integralFileMapper.findByParam(fileEntity);
                if (integralFileList != null && !integralFileList.isEmpty()) {
                    List<Integer> ids = new ArrayList<>();
                    integralFileList.forEach(a -> {
                        ids.add(a.getId());
                    });
                    integralFileMapper.deleteBatch(ids);
                }
            }
            specificationsGoodsMapper.delete(goodsList.get(0).getId());
            integralGoodsMapper.delete(id);
            return Result.ok(Result.build(), "", "");
        }*/
        return Result.error(Result.build(), "未查询出该商品,请刷新页面重新删除", "");

    }

    /**
     * 商品兑换
     *
     * @param exchangeGoodsEntity
     * @param pay_type            商品支付类型
     * @param pay_postage_type    邮费支付类型
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result exchangeGoods(ExchangeGoodsEntity exchangeGoodsEntity, String pay_type, String pay_postage_type) {
        if (exchangeGoodsEntity == null) {
            return Result.error(Result.build(), "", "");
        }
        if (exchangeGoodsEntity.getCount() == 0 || exchangeGoodsEntity.getCount() < 0) {
            return Result.error(Result.build(), "", "");
        }
        if (exchangeGoodsEntity.getIntegralReceiptinfo() == null) {
            return Result.error(Result.build(), "地址不能为空", "");
        }
        if (exchangeGoodsEntity.getGoodsId() != null && exchangeGoodsEntity.getGoodsId() != 0) {
            return Result.error(Result.build(), "没有选择兑换商品", "");
        }
        IntegralMemberCcs memberCcs = new IntegralMemberCcs();
        memberCcs.setId(1);
        List<IntegralMemberCcs> memberCcsList = memberCcsMapper.findByParam(memberCcs);
        if (memberCcsList == null && memberCcsList.isEmpty()) {
            return Result.error(Result.build(), "该用户已经不存在，请重新登陆", "");
        }
        IntegralGoods goodsEntity = new IntegralGoods();
        goodsEntity.setId(exchangeGoodsEntity.getGoodsId());
        goodsEntity.setStatus(Constant.ENABLE);
        IntegralGoods goodsList = integralGoodsMapper.findOne(goodsEntity);
        if (goodsList == null) {
            return Result.error(Result.build(), "该商品已经下架，请刷新页面查看", "");
        }

        int count = Integer.valueOf(goodsList.getCount().toString()) - exchangeGoodsEntity.getCount();

        if (StringUtils.isBlank(goodsList.getCount()) || "0".equals(goodsList.getCount()) || count <= 0) {

            return Result.error(Result.build(), "商品数量不足，请选择其他商品", "");
        }
        log.info("商品兑换请求参数{}", exchangeGoodsEntity.toString());
        log.info("支付类型{}", pay_type);
        log.info("支付邮费类型", pay_postage_type);

        //s生成订单
        Integralitem item = new Integralitem();
        Integer itemId = integralitemMapper.querySEQ();
        item.setId(itemId);
        String itemCode = NumberUtil.createCode(itemId.toString());
        item.setItem_code(itemCode);
        item.setIntegral_goods_id(exchangeGoodsEntity.getGoodsId());
        Date date = new Date();
        item.setCreate_date(date);
        item.setStatus("");
        item.setUpdate_date(date);
        integralitemMapper.insert(item);

        IntegralReceiptinfo receiptinfo = exchangeGoodsEntity.getIntegralReceiptinfo();
        IntegralitemCcs itemCcs = new IntegralitemCcs();
        itemCcs.setId(integralitemCssMapper.querySEQ());
        itemCcs.setAddress(receiptinfo.getAddress());
        itemCcs.setAddressee(receiptinfo.getAddressee());
        itemCcs.setCount(Integer.valueOf(exchangeGoodsEntity.getCount()).toString());
        itemCcs.setCreate_date(new Date());
        itemCcs.setPhone(receiptinfo.getPhone());
        integralitemCssMapper.insert(itemCcs);

        Map<String, Object> result = new HashMap<>();

        switch (pay_type) {
            //积分支付
            case "0":
                //消费总积分
                int goodsPoints = Integer.valueOf(goodsList.getPay_points());
                Integer payCoutPoints = Integer.valueOf(goodsPoints * count);
                //可用积分
                Integer avaliable_points = memberCcsList.get(0).getEnable_points();
                if (avaliable_points < payCoutPoints) {
                    return Result.error(Result.build(), "用户积分不足", "");
                }
                result.put("payCoutPoints",payCoutPoints);
                //异步执行
                CompletableFuture future = new CompletableFuture().thenRunAsync(() -> {
                    payPoints(avaliable_points, payCoutPoints, count, goodsList, itemId);
                });
                futureList.add(future);
                break;
            //平台比兑换
            default:

                break;

        }

        futureList.forEach(a->{
            try {
                a.get();
            }catch (Exception e){
                throw new RuntimeException("积分兑换异常");
            }
        });
        result.put("itemCode", itemCode);
        result.put("goods_name",goodsList.getGoods_name());
        result.put("date",date);
        result.put("count",exchangeGoodsEntity.getCount());
        result.put("pay_type", PayEnum.valueOf(pay_type));
        result.put("pay_postage_type", PayEnum.valueOf(pay_postage_type));
        return Result.ok(Result.build(), "", result);


    }

    /**
     * 平台币支付商品
     */
    public void payCoin(String Coin, IntegralMemberCcs memberCcs) {

    }


    /**
     * 积分支付商品
     *
     * @param avaliable_points
     * @param payCoutPoints
     * @param count
     * @param goods
     * @param itemId
     */
    public void payPoints(Integer avaliable_points, Integer payCoutPoints, int count, IntegralGoods goods, Integer itemId) {


        goods.setCount(Integer.valueOf(count).toString());

        integralGoodsMapper.update(goods);
        //用户减积分
        Integer remaining_points = avaliable_points - payCoutPoints;
        IntegralMemberCcs userPoint = new IntegralMemberCcs();
        userPoint = null;
        userPoint.setId(1);
        userPoint.setEnable_points(remaining_points);
        memberCcsMapper.insert(userPoint);

        //生成记录
        IntegralHistory history = new IntegralHistory();
        history.setAction("");
        history.setCreate_date(new Date());
        history.setIntegral_item_id(itemId);
        history.setPoints(payCoutPoints.toString());
        history.setIntegral_member_ccs_id(1);
        integralHistoryMapper.insert(history);

        //供应商增加积分
        IntegralSupplier supplier = new IntegralSupplier();
        supplier.setId(goods.getIntegral_supplier_id());
        IntegralSupplier supplierEntity = supplierMapper.findOne(supplier);
        if (supplierEntity != null) {
            Integer points_count = Integer.valueOf(supplierEntity.getPoints_count()) + payCoutPoints;
            supplier.setPoints_count(points_count.toString());
            supplierMapper.update(supplier);
        }
    }
}

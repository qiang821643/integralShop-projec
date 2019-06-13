package com.integral.controller;


import com.github.pagehelper.PageInfo;
import com.integral.mapper.IntegralGoodsMapper;
import com.integral.model.ExchangeGoodsEntity;
import com.integral.model.GoodsShelvesEntity;
import com.integral.model.Result;
import com.integral.service.DoGoodService;
import com.integral.service.base.GoodsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.awt.font.MultipleMaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DoGoodService doGoodService;
    @Autowired
    private IntegralGoodsMapper goodsMapper;

    /**
     * 商品分页展示
     *
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ApiOperation(value = "商品分页展示",notes = "")
    @PostMapping("/goodsPage")
    public Result goodsPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize
                            /*@RequestBody Map<String, Object> param*/) {

        log.info("商品分页展示接口");
        Map<String,Object> param  = new HashMap<>();
        return goodsService.findGoodsPageBy(pageNum, pageSize, param);

    }

    @ApiOperation(value = "上架",notes = "{\"good_name\":\"商品名称\",\"count\":\"总数\",\"integral_supplier_id\":\"供应id\",\"pay_point\":\"支付积分\",\"price\":\"价格\",\"remark\":\"备注\",\"type_name\":\"种类\"}")
    @PostMapping("/shelves")
    public Result shelves(HttpServletRequest request, @RequestParam(name = "parentFile") List<MultipartFile> parentFile,
                                                                                                               @RequestParam(name = "sunFile") MultipartFile sunFile) {


        GoodsShelvesEntity shelvesEntity = new GoodsShelvesEntity();
        shelvesEntity.setGood_name( request.getParameter("good_name"));
        shelvesEntity.setCount(request.getParameter("count"));
        shelvesEntity.setIntegral_supplier_id(Integer.valueOf(request.getParameter("integral_supplier_id")));
        shelvesEntity.setPay_point(request.getParameter("pay_point"));
        shelvesEntity.setPrice(request.getParameter("price"));
        shelvesEntity.setRemark(request.getParameter("remark"));
        shelvesEntity.setType_name(request.getParameter("type_name"));
        shelvesEntity.setPostage(request.getParameter("postage"));
        log.info("商品上架接口{}",shelvesEntity);
        return doGoodService.goodsShelves(shelvesEntity,parentFile,sunFile);
    }

    @ApiOperation(value = "商品兑换",notes = "pay_type 支付类型 0积分、1平台币 ,pay_postage_type 邮费支付类型0积分、1平台币、3现金 ")
    @PostMapping("/exchange")
    public Result exchange(@RequestBody ExchangeGoodsEntity exchangeGoodsEntity,
                           @RequestParam("pay_type") @NotNull(message = "支付类型不能为空") String pay_type,
                           @RequestParam ("pay_postage_type")@NotNull(message = "邮费支付类型不可以空") String pay_postage_type) {

        log.info("商品兑换接口{}",exchangeGoodsEntity.toString());
        return doGoodService.exchangeGoods(exchangeGoodsEntity,pay_type,pay_postage_type);
    }

    @ApiOperation(value = "商品详情",notes = "")
    @GetMapping("/goodsDetals")
    public Result goodsDetals(@RequestParam(name = "id") Integer id){

        log.info("商品详情接口{}",id);
        return goodsService.goodsDetail(id);
    }

    @ApiOperation(value = "商品种类")
    @GetMapping("/getType")
    public Result getType(){
        log.info("商品种类接口");
        try {
            return Result.ok(Result.build(), "", goodsMapper.getGoodsType());
        }catch (Exception e){
            log.error("商品种类接口");
            return Result.error(Result.build(), "商品种类服务异常", "");
        }
    }

    /**
     * 商品编辑
     * @param goodsId
     * @param param
     * @return
     */
    @ApiOperation(value = "商品编辑")
    @PostMapping("/edit{id}")
    public Result editGoods(@PathVariable(name = "id") Integer goodsId,@RequestBody Map<String,Object> param){

        return Result.ok(Result.build(),"","");
    }

    /**
     * 商品上下架
     * @param goodsId
     * @param action
     * @return
     */
    @ApiOperation(value = "商品上下架",notes = "action 0上架,1下架")
    @GetMapping("/{id}")
    public Result upLoadOrDown(@PathVariable(name = "id") Integer goodsId,@RequestParam(name = "action") String action){

        return goodsService.upLoadOrDown(goodsId,action);
    }
}



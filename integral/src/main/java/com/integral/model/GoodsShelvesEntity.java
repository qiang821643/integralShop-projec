package com.integral.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.model
 * @date:2019/5/27
 **/
@Data
public class GoodsShelvesEntity implements Serializable {

    private String good_name;

    private String count;

    private String price;

    private String remark;

    private String pay_point;

    private Integer integral_supplier_id;

    private String type_name;

    private String postage;

    /**
     * 商品和规格关系
     */
    //private List<GoodsAndSpeci> specificationsList;

    /**
     * 主图
     */
    //private MultipartFile parentFile;

    /**
     * 副图
     */
   // private List<MultipartFile> sunFile;


}

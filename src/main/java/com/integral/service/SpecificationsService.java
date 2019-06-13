package com.integral.service;

import com.integral.model.AddSepci;
import com.integral.model.Result;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service
 * @date:2019/5/30
 **/

public interface SpecificationsService {

    /**
     * 添加商品规格
     * @param addSepci
     * @return
     */
    public Result addSpec(AddSepci addSepci);
}

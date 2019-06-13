package com.integral.service.base;

import com.integral.model.IntegralReceiptinfo;
import com.integral.model.Result;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service.base
 * @date:2019/5/29
 **/
public interface MemberCssService {

    /**
     * 账户同步
     * @param userName
     * @return
     */
    public Result synUserName(String member);

    /**
     *用户积分信息
     * @param id
     * @return
     */
    public Result pointsInfo(Integer id);

    /**
     * 平台币兑换积分
     * @param id
     * @param coin
     * @return
     */
    public Result exchangePoints(Integer id,Integer coin);

    /**
     * 收件信息
     * @param memberId
     * @return
     */
    public Result userAdress(Integer memberId);

    /**
     * 添加收件信息
     * @return receiptinfo
     */
    public Result addAddress(IntegralReceiptinfo receiptinfo);
}

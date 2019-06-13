package com.integral.service.base.impl;

import com.integral.constant.Constant;
import com.integral.mapper.IntegralHistoryMapper;
import com.integral.mapper.IntegralMemberCcsMapper;
import com.integral.mapper.IntegralReceiptinfoMapper;
import com.integral.model.IntegralHistory;
import com.integral.model.IntegralMemberCcs;
import com.integral.model.IntegralReceiptinfo;
import com.integral.model.Result;
import com.integral.security.JwtTokenUtil;
import com.integral.service.base.MemberCssService;
import com.integral.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.service.base.impl
 * @date:2019/5/29
 **/
@Service
@Slf4j
public class MemberCssServiceImpl implements MemberCssService {

    @Autowired
    private IntegralMemberCcsMapper memberCcsMapper;

    @Autowired
    private IntegralHistoryMapper historyMapper;
    @Autowired
    private IntegralReceiptinfoMapper receiptinfoMapper;
    /**
     * 账户同步
     *
     * @param member
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result synUserName(String member) {

        Map<String, Object> zMember = memberCcsMapper.queryZmemberTable(member);
        if(zMember==null || zMember.isEmpty()){
            return Result.error(Result.build(),"系统没有查询该账户","");
        }
        String zMemberId = member;
        IntegralMemberCcs memberCcs = new IntegralMemberCcs();
        memberCcs.setZ_member_id(zMemberId);
        memberCcs.setStatus(Constant.ENABLE);
        IntegralMemberCcs memberCcsOne = memberCcsMapper.findOne(memberCcs);
        //List<IntegralMemberCcs> memberCcsList = memberCcsMapper.findByParam(memberCcs);
        if (memberCcsOne!=null) {
            return Result.ok(Result.build(), "", memberCcsOne.getId());
        }
        Integer id = memberCcsMapper.queryQUE();
        memberCcs.setId(id);
        memberCcs.setCount_points(0);
        memberCcs.setEnable_points(0);
        memberCcs.setStatus(Constant.ENABLE);
        memberCcs.setCreate_date(new Date());
        log.info(memberCcs.toString());
        try {
            log.info("同步账号是{}",member);
            memberCcsMapper.insert(memberCcs);
            Map<String,Object> idMap = new HashMap<>();
            idMap.put("id",id);
            String jwt = JwtTokenUtil.generateToken("","",id);
            idMap.put("jwt",jwt);
            return Result.ok(Result.build(), "", idMap);
        } catch (Exception e) {
            log.error("com.integral.service.base.impl.MemberCssServiceImpl.synUserName", e.getMessage());
            throw new RuntimeException("账号同步异常");
        }
    }

    /**
     * 用户积分信息
     *
     * @param id
     * @return
     */
    @Override
    public Result pointsInfo(Integer id) {
        IntegralMemberCcs memberCcs = new IntegralMemberCcs();
        memberCcs.setId(id);
        memberCcs.setStatus(Constant.ENABLE);
        IntegralMemberCcs memberCcsPoints = memberCcsMapper.findOne(memberCcs);
        return Result.ok(Result.build(),"",memberCcsPoints);
    }

    /**
     * 平台币兑换积分
     *
     * @param id
     * @param coin
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result exchangePoints(Integer id, Integer coin) {
        IntegralMemberCcs memberCcs = memberCcsMapper.findMemberCcs(id);
        if(memberCcs==null){
            log.info("用户id{}",id);
            return  Result.error(Result.build(),"该用户不存在或已经停用","");
        }
        Map<String, Object> zMember = memberCcsMapper.queryZmemberTable(memberCcs.getZ_member_id());
        if(zMember==null || zMember.isEmpty()){
            return  Result.error(Result.build(),"该用户不存在或已经停用","");
        }
        if(coin==null || coin==0){
            return  Result.error(Result.build(),"平台币不可是0","");
        }
        //兑换平台币
        BigDecimal bigDecimalCoin = BigDecimal.valueOf(coin);
        //用户平台币
        BigDecimal memberCoin = BigDecimal.valueOf(Double.valueOf(String.valueOf(zMember.get("STOCKRIGHT"))));
        int coinV = coin;
        if(memberCoin.compareTo(bigDecimalCoin)<0){
            return  Result.error(Result.build(),"平台币不足","");
        }
        int cointInt =  Integer.valueOf(coin);
        int ponits = cointInt*10;
        int countPoints = memberCcs.getCount_points()+ponits;
        int enablePoints = memberCcs.getEnable_points()+ponits;
        memberCcs= new IntegralMemberCcs();
        memberCcs.setId(id);
        memberCcs.setEnable_points(enablePoints);
        memberCcs.setCount_points(countPoints);
        IntegralHistory history = new IntegralHistory();
        history.setCreate_date(new Date());
        history.setAction("0");
        history.setIntegral_member_ccs_id(id);
        history.setPoints(String.valueOf(ponits));

        try {
            memberCcsMapper.update(memberCcs);
            //用户平台币-兑换平台币
            BigDecimal newCoin = memberCoin.subtract(bigDecimalCoin);
            Map<String,Object> param = new HashMap<>();
            param.put("member",zMember.get("MNUMBER"));
            param.put("newCoin",newCoin);
            memberCcsMapper.minusCoin(param);
            historyMapper.insert(history);
            IntegralMemberCcs memberCcs1 = memberCcsMapper.findMemberCcs(id);
            return Result.ok(Result.build(),"",memberCcs1);
        }catch (Exception e){
            log.error("com.integral.service.base.impl.MemberCssServiceImpl.exchangePoints{}",e.getMessage());
            throw new RuntimeException("兑换积分服务异常,请稍后");
        }

    }

    /**
     * 收件信息
     *
     * @param memberId
     * @return
     */
    @Override
    public Result userAdress(Integer memberId) {
        if(memberId!=null){
            IntegralReceiptinfo receiptinfo = new IntegralReceiptinfo();
            receiptinfo.setIntegral_member_ccs_id(memberId);
            return Result.ok(Result.build(),"",receiptinfoMapper.findByParam(receiptinfo));
        }
        return Result.error(Result.build(),"系统没有收到该用户,请刷新重试","");
    }

    /**
     * 添加收件信息
     *
     * @param receiptinfo
     * @return receiptinfo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addAddress(IntegralReceiptinfo receiptinfo) {
        if(receiptinfo!=null &&  receiptinfo.getIntegral_member_ccs_id()!=null && receiptinfo.getIntegral_member_ccs_id()!=0) {
            IntegralMemberCcs memberCcs = new IntegralMemberCcs();
            memberCcs.setId(receiptinfo.getIntegral_member_ccs_id());
            memberCcs.setStatus(Constant.ENABLE);
            System.out.println(memberCcs.toString());
            IntegralMemberCcs memberCcs1 = memberCcsMapper.findOne(memberCcs);
            if(memberCcs1==null){
                return  Result.error(Result.build(),"系统未查询该用户请重新登陆","");
            }
            try {
                Integer id = receiptinfoMapper.querySEQ();
                receiptinfo.setCreate_date(TimeUtil.format(TimeUtil.YYYY_MM_DD_HH_MM_SS,new Date()));
                receiptinfo.setId(id);
                receiptinfo.setStatus(Constant.ENABLE);
                receiptinfoMapper.insert(receiptinfo);

                return Result.ok(Result.build(),"",receiptinfoMapper.findByParam(receiptinfo));
            } catch (Exception e) {
                log.error("com.integral.service.base.impl.MemberCssServiceImpl.addAddress{}", e.getMessage());
            }
        }
        return Result.error(Result.build(),"系统未查询该用户请重新登陆","");
    }


}

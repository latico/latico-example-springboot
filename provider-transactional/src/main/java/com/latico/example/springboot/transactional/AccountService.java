package com.latico.example.springboot.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <PRE>
 *  service业务层统一抛出异常
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-27 10:20:26
 * @Version: 1.0
 */
@Service
public class AccountService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    AccountMapper accountMapper;

    public Account queryAccount() {
        return accountMapper.queryAccount();
    }

    public List<Account> queryAllAccount() {
        return accountMapper.queryAllAccount();
    }

    @Transactional
    public void addMoneyFail() throws Exception {
        //先增加余额
        accountMapper.addMoney();
        //然后遇到故障
        throw new RuntimeException("发生异常了..");
    }

    @Transactional
    public void updateMoneyFail() throws Exception {
        //先增加余额
        accountMapper.updateMoney();
        //然后遇到故障
        throw new RuntimeException("发生异常了..");
    }

    @Transactional
    public void deleteMoneyFail() throws Exception {
        //先增加余额
        accountMapper.deleteMoney();
        //然后遇到故障
        throw new RuntimeException("发生异常了..");
    }
    @Transactional
    public void deleteMoneySucc() throws Exception {
        //先增加余额
        accountMapper.deleteMoney();
    }

    @Transactional
    public void addAndUpdateMoneyFail() throws Exception {
        //先增加余额
        accountMapper.addMoney();
        accountMapper.updateMoney();
        //然后遇到故障
        throw new RuntimeException("发生异常了..");
    }

    @Transactional
    public void addAndUpdateMoneySucc() throws Exception {
        //先增加余额
        accountMapper.addMoney();
        accountMapper.updateMoney();
    }
}
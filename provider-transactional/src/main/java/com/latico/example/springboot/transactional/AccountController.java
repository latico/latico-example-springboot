package com.latico.example.springboot.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 *  controller控制层统一控制异常捕获
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-27 10:19:59
 * @Version: 1.0
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 查询ID是1的
     * @return
     */
    @GetMapping("query")
    public Account queryAccount() {
        //查询账户
        return accountService.queryAccount();
    }

    /**
     * 查询ID是1的
     * @return
     */
    @GetMapping("queryAll")
    public List<Account> queryAllAccount() {
        //查询账户
        return accountService.queryAllAccount();
    }

    /**
     * 更新失败
     * @return
     */
    @GetMapping("updateMoneyFail")
    public Object updateMoneyFail() {
        try {
            accountService.updateMoneyFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryAccount();
    }

    /**
     * 新增失败
     * @return
     */
    @GetMapping("addMoneyFail")
    public List<Account> addMoneyFail() {
        try {
            accountService.addMoneyFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.queryAllAccount();
    }


    /**
     * 新增和更新，发生异常，全部回滚
     * @return
     */
    @GetMapping("addAndUpdateMoneyFail")
    public List<Account> addAndUpdateMoneyFail() {
        try {
            accountService.addAndUpdateMoneyFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.queryAllAccount();
    }


    /**
     * 新增和更新一起成功
     * @return
     */
    @GetMapping("addAndUpdateMoneySucc")
    public List<Account> addAndUpdateMoneySucc() {
        try {
            accountService.addAndUpdateMoneySucc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.queryAllAccount();
    }


    /**
     * 删除失败
     * @return
     */
    @GetMapping("deleteMoneyFail")
    public List<Account> deleteMoneyFail() {
        try {
            accountService.deleteMoneyFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.queryAllAccount();
    }

    /**
     * 删除成功
     * @return
     */
    @GetMapping("deleteMoneySucc")
    public List<Account> deleteMoneySucc() {
        try {
            accountService.deleteMoneySucc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.queryAllAccount();
    }
}
package com.latico.example.springboot.transactional;

import java.math.BigDecimal;

/**
 * <PRE>
 *  数据库javabean对象
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-27 10:21:14
 * @Version: 1.0
 */
public class Account {

    private String accountId;
    private String accountName;
    private BigDecimal balance;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
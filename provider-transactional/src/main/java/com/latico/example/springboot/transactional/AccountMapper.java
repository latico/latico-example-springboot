package com.latico.example.springboot.transactional;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <PRE>
 *  数据库操作不需要改变
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-27 10:20:59
 * @Version: 1.0
 */
@Mapper
public interface AccountMapper {

    @Select("select * from account where account_id=1")
    Account queryAccount();

    @Select("select * from account")
    List<Account> queryAllAccount();

    @Update("update account set balance = balance+100 where account_id=1")
    void updateMoney();

    @Insert("insert into account values ('2','admin2','2000.25')")
    void addMoney();


    @Delete("delete from account where account_id=2")
    void deleteMoney();
}
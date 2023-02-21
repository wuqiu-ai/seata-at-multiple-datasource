package com.fly.seata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.seata.common.pay.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author HelloWood
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {

    @Update("update account set balance = balance - #{price} where id = #{userId} and balance > 0")
    int reduce(@Param("userId") Long userId,@Param("price") Integer price);

}

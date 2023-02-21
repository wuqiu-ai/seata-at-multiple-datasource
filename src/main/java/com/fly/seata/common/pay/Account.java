package com.fly.seata.common.pay;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author HelloWood
 */
@Data
@Builder
public class Account {

    private Long id;

    private Integer balance;

    private Date lastUpdateTime;
}

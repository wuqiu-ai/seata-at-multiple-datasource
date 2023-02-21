package com.fly.seata.service.impl;

import com.fly.seata.common.OperationResponse;
import com.fly.seata.common.order.Order;
import com.fly.seata.common.order.PlaceOrderRequestVO;
import com.fly.seata.config.DataSourceKey;
import com.fly.seata.config.DynamicDataSourceContextHolder;
import com.fly.seata.dao.OrderDao;
import com.fly.seata.service.OrderService;
import com.fly.seata.service.PayService;
import com.fly.seata.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author HelloWoodes
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PayService payService;

    @Autowired
    private StorageService storageService;

    @Override
    public OperationResponse normalPlaceOrder(String type,PlaceOrderRequestVO placeOrderRequestVO)
        throws Exception {
        log.info("=============ORDER=================");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.STORAGE);
        log.info("当前 XID: {}", RootContext.getXID());
        Integer amount = 1;
        // 扣减库存
        boolean operationStorageResult = storageService.reduceStock(placeOrderRequestVO.getProductId(), amount);
        if( !operationStorageResult ){
            throw new RuntimeException("下单失败");
        }
        if(operationStorageResult){
            Integer price = placeOrderRequestVO.getPrice();
            Order order = Order.builder()
                .userId(placeOrderRequestVO.getUserId())
                .productId(placeOrderRequestVO.getProductId())
                .money(price)
                .count(1)
                .build();
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
            orderDao.insertOrder(order);
            return OperationResponse.builder().success(operationStorageResult).build();
        }
        return OperationResponse.builder().success(false).build();
    }

    @Override
    public OperationResponse seataPlaceOrder(String type,PlaceOrderRequestVO placeOrderRequestVO)
        throws Exception {
        log.info("=============ORDER=================");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.STORAGE);
        log.info("当前 XID: {}", RootContext.getXID());
        Integer amount = 1;
        // 扣减库存
        boolean operationStorageResult = storageService.reduceStock(placeOrderRequestVO.getProductId(), amount);
        if( !operationStorageResult ){
            throw new RuntimeException("下单失败");
        }
        if(operationStorageResult){
            Integer price = placeOrderRequestVO.getPrice();
            Order order = Order.builder()
                .userId(placeOrderRequestVO.getUserId())
                .productId(placeOrderRequestVO.getProductId())
                .money(price)
                .count(1)
                .build();
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
            orderDao.insertOrder(order);
            return OperationResponse.builder().success(operationStorageResult).build();
        }
        return OperationResponse.builder().success(false).build();
    }

    @Override
    public OperationResponse seataPlaceOrder2(String type, PlaceOrderRequestVO placeOrderRequestVO)
        throws Exception {
        log.info("=============ORDER=================");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.STORAGE);
        log.info("当前 XID: {}", RootContext.getXID());
        Integer amount = 1;

        Integer price = placeOrderRequestVO.getPrice();
        Order order = Order.builder()
            .userId(placeOrderRequestVO.getUserId())
            .productId(placeOrderRequestVO.getProductId())
            .money(price)
            .count(1)
            .build();
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
        orderDao.insertOrder(order);

        // 扣减库存
        boolean operationStorageResult = storageService.reduceStock(placeOrderRequestVO.getProductId(), amount);
        if( !operationStorageResult ){
            throw new RuntimeException("下单失败");
        }
        return OperationResponse.builder().success(true).build();
    }
}

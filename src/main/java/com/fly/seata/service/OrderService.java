package com.fly.seata.service;


import com.fly.seata.common.OperationResponse;
import com.fly.seata.common.order.PlaceOrderRequestVO;

/**
 * @author HelloWoodes
 */
public interface OrderService {

    /**
     * 下单
     *
     * @param placeOrderRequestVO 请求参数
     * @return 下单结果
     */
    OperationResponse normalPlaceOrder(String type,PlaceOrderRequestVO placeOrderRequestVO) throws Exception;

    OperationResponse seataPlaceOrder(String type,PlaceOrderRequestVO placeOrderRequestVO) throws Exception;

    OperationResponse seataPlaceOrder2(String type,PlaceOrderRequestVO placeOrderRequestVO) throws Exception;

}

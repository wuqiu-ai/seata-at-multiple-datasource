package com.fly.seata.controller;

import com.fly.seata.common.OperationResponse;
import com.fly.seata.common.order.PlaceOrderRequestVO;
import com.fly.seata.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HelloWoodes
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${service.disableGlobalTransaction}")
    private Boolean disableGlobalTransaction;


    @PostMapping("/normal/placeOrder")
    @ResponseBody
    public String normalPlaceOrder(HttpServletRequest request,@RequestBody PlaceOrderRequestVO placeOrderRequestVO) throws Exception {
        log.info("收到下单请求,用户:{}, 商品:{}", placeOrderRequestVO.getUserId(), placeOrderRequestVO.getProductId());
        String type = request.getHeader("type");
        OperationResponse operationResponse =  orderService.seataPlaceOrder2(type,placeOrderRequestVO);
        if(operationResponse.isSuccess()){
            return "ok";
        }else{
            return "fail";
        }
    }

    @GlobalTransactional
    @PostMapping("/seata/placeOrder")
    @ResponseBody
    public String seataPlaceOrder(HttpServletRequest request,@RequestBody PlaceOrderRequestVO placeOrderRequestVO) throws Exception {
        log.info("收到下单请求,用户:{}, 商品:{} disableGlobalTransaction:{}", placeOrderRequestVO.getUserId(), placeOrderRequestVO.getProductId(),disableGlobalTransaction);
        String type = request.getHeader("type");
        OperationResponse operationResponse = orderService.seataPlaceOrder2(type,placeOrderRequestVO);
        if(operationResponse.isSuccess()){
            return "ok";
        }else{
            return "fail";
        }
    }

    @GlobalTransactional
    @PostMapping("/seata/placeOrder2")
    @ResponseBody
    public String seataPlaceOrder2(HttpServletRequest request,@RequestBody PlaceOrderRequestVO placeOrderRequestVO) throws Exception {
        log.info("收到下单请求,用户:{}, 商品:{}", placeOrderRequestVO.getUserId(), placeOrderRequestVO.getProductId());
        String type = request.getHeader("type");
        OperationResponse operationResponse = orderService.seataPlaceOrder2(type,placeOrderRequestVO);
        if(operationResponse.isSuccess()){
            return "ok";
        }else{
            return "fail";
        }
    }
}

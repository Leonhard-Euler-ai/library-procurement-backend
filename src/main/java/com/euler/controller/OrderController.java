package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.bo.CartBookBO;
import com.euler.domain.Order;
import com.euler.domain.OrderDetail;
import com.euler.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/16
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @ApiOperation("获取订单列表")
    @GetMapping("/orderList")
    BaseResponse<List<Order>> getOrderList(HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, orderService.getOrderList(userId));
    }

    @ApiOperation("获取订单详情列表")
    @GetMapping("/orderDetailList")
    BaseResponse<List<OrderDetail>> getOrderList(Integer orderId) {
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, orderService.getOrderDetailList(orderId));
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    BaseResponse<Order> generateOrder(@RequestBody List<CartBookBO> orderBookList, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, orderService.addOrder(orderBookList, userId));
    }

    @ApiOperation("统计购买的每类图书数量")
    @GetMapping("/bookCountPerClass")
    BaseResponse<Map<String, Integer>> getStatisticsBooksOfClass(HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, orderService.getBookCountPerClass(userId));
    }

    @ApiOperation("付款成功")
    @PostMapping("/paySuccess")
    BaseResponse<Void> orderPaySuccess(Integer orderId) {
        orderService.paySuccess(orderId);
        return new BaseResponse<>(HttpServletResponse.SC_OK, null);
    }
}

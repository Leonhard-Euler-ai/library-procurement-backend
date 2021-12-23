package com.euler.service;

import com.euler.bo.CartBookBO;
import com.euler.domain.Order;
import com.euler.domain.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/16
 */
public interface OrderService {
    List<Order> getOrderList(Integer userId);

    List<OrderDetail> getOrderDetailList(Integer orderId);

    Order addOrder(List<CartBookBO> orderBookList, Integer userId);

    Map<String, Integer> getBookCountPerClass(Integer userId);

    void paySuccess(Integer orderId);
}

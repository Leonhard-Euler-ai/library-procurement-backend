package com.euler.service;

import com.euler.bo.CartBookBO;
import com.euler.domain.Order;
import com.euler.domain.OrderDetail;
import com.euler.dto.OrderBookDTO;
import com.euler.exception.IllegalRequestParamException;
import com.euler.repository.BookRepository;
import com.euler.repository.CartRepository;
import com.euler.repository.OrderDetailRepository;
import com.euler.repository.OrderRepository;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/16
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    BookRepository bookRepository;

    @Resource
    CartRepository cartRepository;

    @Resource
    OrderRepository orderRepository;

    @Resource
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getOrderList(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderDetail> getOrderDetailList(Integer orderId){
        return orderDetailRepository.findAllByOrderId(orderId);
    }

    @Override
    public Order addOrder(List<CartBookBO> orderBookList, Integer userId) {
        List<OrderBookDTO> orderBookDTOList = orderBookList.stream().map(cartBookBO ->
                OrderBookDTO.fromOrderAndBook(bookRepository.findByBookId(cartBookBO.getBookId()), cartBookBO.getBookCount())
        ).collect(Collectors.toList());

        float totalPrice = orderBookDTOList.stream().map(orderBookDTO ->
                orderBookDTO.getBook().getBookPrice() * orderBookDTO.getBookCount()).reduce(Float::sum).get();

        Order order = orderRepository.saveAndFlush(new Order(null, userId, null, totalPrice));

        Integer orderId = order.getOrderId();
        List<OrderDetail> orderDetailList = orderBookDTOList.stream().map(item ->
                orderDetailRepository.saveAndFlush(new OrderDetail(null, orderId, item.getBookCount(),
                        item.getBook().getBookPrice(), item.getBook().getBookClass(), item.getBook().getBookIsbn(),
                        item.getBook().getBookName(), item.getBook().getBookPicUrl()))
        ).collect(Collectors.toList());

        orderBookList.stream().forEach(cartBookBO-> cartRepository.deleteByUserIdAndBookId(userId,cartBookBO.getBookId()));

        return order;
    }

    @Override
    public Map<String, Integer> getBookCountPerClass(Integer userId) {
        List<OrderDetail> orderDetailList = orderRepository.findAllByUserId(userId).stream()
                .map(Order::getOrderId)
                .flatMap(orderId -> orderDetailRepository.findAllByOrderId(orderId).stream())  //获取该用户的所有详细订单信息
                .collect(Collectors.toList());

        Map<String, Integer> map=new HashMap<>();
        orderDetailList.stream().forEach(orderDetail -> {
            String bookClass=orderDetail.getBookClass();
            if(map.containsKey(bookClass)){
                map.put(bookClass, map.get(bookClass)+orderDetail.getCount());
            }else {
                map.put(bookClass,orderDetail.getCount());
            }
        });

        return map;
    }

    @Override
    public void paySuccess(Integer orderId) {
        Order orderInDB=orderRepository.findById(orderId).orElse(null);
        if(null==orderInDB){
            throw new IllegalRequestParamException("订单不存在");
        }else {
            SimpleDateFormat orderPayTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String payTime = orderPayTimeFormat.format((new Date()).getTime());
            orderInDB.setPayTime(payTime);
            orderRepository.saveAndFlush(orderInDB);
        }
    }
}

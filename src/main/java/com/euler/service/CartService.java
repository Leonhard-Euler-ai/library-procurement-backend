package com.euler.service;

import com.euler.VO.CartBookVO;
import com.euler.domain.Cart;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/15
 */
public interface CartService {
    Cart addCartBook(Cart cart);

    List<Cart> addBatchBooks(List<Cart> cartList);

    void reduceCartBook(Cart cart);

    void resetCartBookCount(Cart cart);

    void deleteCartBook(Integer userId, Integer bookId);

    List<CartBookVO> getCartBooksList(Integer userId);

    void clear(Integer userId);
}

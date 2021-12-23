package com.euler.repository;

import com.euler.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/15
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndBookId(Integer userId, Integer bookId);
    List<Cart> findAllByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);

    @Transactional
    void deleteByUserIdAndBookId(Integer userId, Integer bookId);
}

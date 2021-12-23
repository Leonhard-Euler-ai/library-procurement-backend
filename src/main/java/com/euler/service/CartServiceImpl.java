package com.euler.service;

import com.euler.VO.CartBookVO;
import com.euler.domain.Book;
import com.euler.domain.Cart;
import com.euler.repository.BookRepository;
import com.euler.repository.CartRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/15
 */
@Service
public class CartServiceImpl implements CartService {
    @Resource
    BookRepository bookRepository;

    @Resource
    CartRepository cartRepository;

    @Override
    public Cart addCartBook(Cart cart) {
        Book bookInDB = bookRepository.findByBookId(cart.getBookId());
        if (null == bookInDB) {
            throw new IllegalArgumentException("对应书籍不存在," + "bookId:" + cart.getBookId());
        }
        Cart cartBook = cartRepository.findByUserIdAndBookId(cart.getUserId(), cart.getBookId());
        if (cartBook == null) {
            cartBook = cartRepository.saveAndFlush(cart);
        } else {
            cartBook.setCount(cartBook.getCount() + cart.getCount());
            cartBook = cartRepository.saveAndFlush(cartBook);
        }
        return cartBook;
    }

    @Override
    public List<Cart> addBatchBooks(List<Cart> cartList) {
        return cartList.stream().map(this::addCartBook).collect(Collectors.toList());
    }


    @Override
    public void deleteCartBook(Integer userId, Integer bookId) {
        Cart cartBook = cartRepository.findByUserIdAndBookId(userId, bookId);
        if (null == cartBook) {
            throw new IllegalArgumentException("已添加的对应书籍不存在," + "bookId:" + bookId);
        }
        cartRepository.delete(cartBook);
    }

    @Override
    public void reduceCartBook(Cart cart) {
        Cart cartBook = cartRepository.findByUserIdAndBookId(cart.getUserId(), cart.getBookId());
        if (null == cartBook) {
            throw new IllegalArgumentException("已添加的对应书籍不存在," + "bookId:" + cart.getBookId());
        }
        if (cartBook.getCount() > cart.getCount()) {
            cartBook.setCount(cartBook.getCount() - cart.getCount());
            cartRepository.saveAndFlush(cartBook);
        } else if (cartBook.getCount() == cart.getCount()) {
            cartRepository.delete(cartBook);
        } else {
            throw new IllegalArgumentException("减少数量超过原数量");
        }
    }

    @Override
    public void resetCartBookCount(Cart cart) {
        Cart cartBook = cartRepository.findByUserIdAndBookId(cart.getUserId(), cart.getBookId());
        if (null == cartBook) {
            throw new IllegalArgumentException("已添加的对应书籍不存在," + "bookId:" + cart.getBookId());
        }
        if (cart.getCount() > 0) {
            cartBook.setCount(cart.getCount());
            cartRepository.saveAndFlush(cartBook);
        } else if (cart.getCount() == 0) {
            cartRepository.delete(cartBook);
        } else {
            throw new IllegalArgumentException("参数不合法");
        }
    }

    @Override
    public List<CartBookVO> getCartBooksList(Integer userId) {
        return cartRepository.findAllByUserId(userId).stream()
                .map(cart -> CartBookVO.fromBook(bookRepository.findByBookId(cart.getBookId()), cart.getCount()))
                .collect(Collectors.toList());
    }

    @Override
    public void clear(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
}

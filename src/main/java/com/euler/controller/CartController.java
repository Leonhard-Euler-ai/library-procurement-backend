package com.euler.controller;

import com.euler.VO.CartBookVO;
import com.euler.bo.BaseResponse;
import com.euler.bo.CartBookBO;
import com.euler.domain.Cart;
import com.euler.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/15
 */
@Api(tags = "购物车管理")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartService cartService;

    @ApiOperation("获取购物车图书列表")
    @GetMapping("/bookList")
    BaseResponse<List<CartBookVO>> getBookList(HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, cartService.getCartBooksList(userId));
    }

    @ApiOperation("添加图书到购物车")
    @PostMapping("/addBook")
    BaseResponse<Cart> addCartBook(CartBookBO cartBookBO, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        Cart cart = new Cart(userId, cartBookBO.getBookId(), cartBookBO.getBookCount());
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, cartService.addCartBook(cart));
    }

    @ApiOperation("批量添加图书到购物车")
    @PostMapping("/addBatchBooks")
    BaseResponse<List<Cart>> addBatchCartBooks(@RequestBody List<CartBookBO> cartBookBOList, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        List<Cart> cartList = cartBookBOList.stream().map(cartBookBO ->
                        new Cart(userId, cartBookBO.getBookId(), cartBookBO.getBookCount()))
                .collect(Collectors.toList());
        return new BaseResponse<>(HttpServletResponse.SC_OK, null, cartService.addBatchBooks(cartList));
    }

    @ApiOperation("减少购物车图书数量")
    @PostMapping("/reduceBook")
    BaseResponse<Void> reduceCartBook(CartBookBO cartBookBO, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        Cart cart = new Cart(userId, cartBookBO.getBookId(), cartBookBO.getBookCount());
        cartService.reduceCartBook(cart);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("重设购物车图书数量")
    @PostMapping("/resetBookCount")
    BaseResponse<Void> resetCartBookCount(CartBookBO cartBookBO, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        Cart cart = new Cart(userId, cartBookBO.getBookId(), cartBookBO.getBookCount());
        cartService.resetCartBookCount(cart);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("删除购物车图书")
    @PostMapping("/deleteBook")
    BaseResponse<Void> deleteCartBook(Integer bookId, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        cartService.deleteCartBook(userId, bookId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("清空购物车")
    @DeleteMapping("/clear")
    BaseResponse<Void> clearCart(HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        cartService.clear(userId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }
}

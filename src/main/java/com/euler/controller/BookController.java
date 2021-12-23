package com.euler.controller;

import com.euler.VO.BookVO;
import com.euler.bo.BaseResponse;
import com.euler.bo.PageParam;
import com.euler.domain.Book;
import com.euler.exception.IllegalRequestParamException;
import com.euler.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    BookService bookService;

    @ApiOperation("根据id查询图书")
    @GetMapping("/bookId")
    BaseResponse<List<Book>> getBookById(Integer bookId) {
        return new BaseResponse(HttpServletResponse.SC_OK, null, bookService.getBookByBookId(bookId));
    }

    @ApiOperation("分页查询图书")
    @GetMapping("/pageList")
    BaseResponse<List<BookVO>> getBookList(PageParam pageParam, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse(HttpServletResponse.SC_OK, null, bookService.transferBookWithFlag(bookService.getBookList(pageParam), userId));
    }

    @ApiOperation("获取所有图书")
    @GetMapping("/all")
    BaseResponse<List<BookVO>> getAllBooks(HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse(HttpServletResponse.SC_OK, null, bookService.transferBookWithFlag(bookService.getAllBooks(), userId));
    }

    @ApiOperation("获取图书总数【前端分页可能要用到！】")
    @GetMapping("/total")
    BaseResponse<Long> getBookSum() {
        return new BaseResponse(HttpServletResponse.SC_OK, null, bookService.getBookSum());
    }

    @ApiOperation("根据关键字和查询类型模糊查询【'allTypes','bookName','bookAuthor','bookClass','bookPublisher'】")
    @GetMapping("/like/bookName")
    BaseResponse<List<BookVO>> getBookListByBookKeyword(String keyword, String searchType, HttpServletRequest httpServletRequest) {
        if (Optional.ofNullable(keyword).orElse("").isEmpty() || Optional.ofNullable(searchType).orElse("").isEmpty()) {
            throw new IllegalRequestParamException("查询参数缺失");
        }
        String[] types = new String[]{"allTypes", "bookName", "bookAuthor", "bookClass", "bookPublisher"};
        if (!Arrays.asList(types).contains(searchType)) {
            throw new IllegalRequestParamException("不支持的查询参数");
        }
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        return new BaseResponse(HttpServletResponse.SC_OK, null, bookService.transferBookWithFlag(bookService.getBooksBySearchType(keyword, searchType), userId));
    }
}

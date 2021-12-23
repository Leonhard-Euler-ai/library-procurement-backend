package com.euler.service;

import com.euler.VO.BookVO;
import com.euler.bo.PageParam;
import com.euler.domain.Book;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
public interface BookService {
    List<Book> getBookList(PageParam pageParam);

    List<Book> getAllBooks();

    long getBookSum();

    List<Book> getBooksBySearchType(String keyword, String searchType);

    Book getBookByBookId(Integer bookId);

    List<BookVO> transferBookWithFlag(List<Book> bookList, Integer userId);
}

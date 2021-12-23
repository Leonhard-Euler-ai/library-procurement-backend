package com.euler.service;

import com.euler.VO.BookVO;
import com.euler.bo.PageParam;
import com.euler.domain.Book;
import com.euler.repository.BookRepository;
import com.euler.repository.CartRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookRepository bookRepository;

    @Resource
    CartRepository cartRepository;

    @Override
    public List<Book> getBookList(PageParam pageParam) {
        return bookRepository.findAll(PageParam.getPageAble(pageParam)).getContent();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public long getBookSum() {
        return bookRepository.count();
    }

    @Override
    public List<Book> getBooksBySearchType(String keyword, String searchType) {
        String likeKeyword = "%" + keyword + "%";
        if (searchType.equalsIgnoreCase("allTypes")) {
            return bookRepository.findAllByBookTypesAll(likeKeyword);
        } else if (searchType.equalsIgnoreCase("bookName")) {
            return bookRepository.findAllByBookNameLike(likeKeyword);
        } else if (searchType.equalsIgnoreCase("bookAuthor")) {
            return bookRepository.findAllByBookAuthorLike(likeKeyword);
        } else if (searchType.equalsIgnoreCase("bookClass")) {
            return bookRepository.findAllByBookClassLike(likeKeyword);
        } else if (searchType.equalsIgnoreCase("bookPublisher")) {
            return bookRepository.findAllByBookPublisherLike(likeKeyword);
        }
        return new ArrayList<>();
    }

    @Override
    public Book getBookByBookId(Integer bookId) {
        Book bookInDB = bookRepository.findByBookId(bookId);
        if (bookInDB == null) {
            throw new IllegalArgumentException("对应书籍不存在");
        }
        return bookInDB;
    }

    @Override
    public List<BookVO> transferBookWithFlag(List<Book> bookList, Integer userId) {
        return bookList.stream().map(book ->
                BookVO.fromBook(book, cartRepository.findByUserIdAndBookId(userId, book.getBookId()) == null ? 0 : 1)
        ).collect(Collectors.toList());
    }
}

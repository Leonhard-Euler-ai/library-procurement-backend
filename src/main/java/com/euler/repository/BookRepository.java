package com.euler.repository;

import com.euler.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from book where book_name like :keyword or book_author like :keyword " +
            "or book_class like :keyword or book_publisher like :keyword", nativeQuery = true)
    List<Book> findAllByBookTypesAll(@Param("keyword") String keyword);

    List<Book> findAllByBookNameLike(String bookName);

    List<Book> findAllByBookAuthorLike(String bookAuthor);

    List<Book> findAllByBookClassLike(String bookClass);

    List<Book> findAllByBookPublisherLike(String bookPublisher);

    Book findByBookId(Integer bookId);
}

package com.euler.VO;

import com.euler.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    private Integer bookId;
    private String bookIsbn;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String bookDate;
    private String bookIntro;
    private String bookClass;
    private Float bookPrice;
    private String bookPicUrl;
    private Integer flag;


    public static BookVO fromBook(Book book, Integer flag) {
        return BookVO.builder()
                .bookId(book.getBookId())
                .bookIsbn(book.getBookIsbn())
                .bookName(book.getBookName())
                .bookAuthor(book.getBookAuthor())
                .bookPublisher(book.getBookPublisher())
                .bookDate(book.getBookDate())
                .bookIntro(book.getBookIntro())
                .bookClass(book.getBookClass())
                .bookPrice(book.getBookPrice())
                .bookPicUrl(book.getBookPicUrl())
                .flag(flag)
                .build();
    }
}
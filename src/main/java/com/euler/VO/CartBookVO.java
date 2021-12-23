package com.euler.VO;

import com.euler.domain.Book;
import com.euler.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.PrinterGraphics;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartBookVO {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Integer bookCount;
    private Float bookPrice;
    private String bookPicUrl;


    public static CartBookVO fromBook(Book book, Integer bookCount) {
        return CartBookVO.builder()
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .bookAuthor(book.getBookAuthor())
                .bookCount(bookCount)
                .bookPrice(book.getBookPrice())
                .bookPicUrl(book.getBookPicUrl())
                .build();
    }
}
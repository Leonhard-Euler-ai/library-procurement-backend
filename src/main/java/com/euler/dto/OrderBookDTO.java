package com.euler.dto;

import com.euler.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDTO {
    private Book book;
    private Integer bookCount;

    public static OrderBookDTO fromOrderAndBook(Book book,Integer bookCount){
        return OrderBookDTO.builder()
                .book(book)
                .bookCount(bookCount)
                .build();
    }
}

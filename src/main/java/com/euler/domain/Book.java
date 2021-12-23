package com.euler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
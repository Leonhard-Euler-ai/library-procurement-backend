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
@Table(name = "cart")
@IdClass(CartJointPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private Integer userId;
    @Id
    private Integer bookId;
    private Integer count;
}

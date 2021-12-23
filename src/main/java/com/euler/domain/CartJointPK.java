package com.euler.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/16
 */
@NoArgsConstructor
@AllArgsConstructor
public class CartJointPK implements Serializable {
    private Integer userId;
    private Integer bookId;
}

package com.test.pub.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author LGCSpadger
 * @since 2021-05-14 20:07:20
 */
@Data
public class Article implements Serializable {

    private Long id;

    private String name;

    private String author;

    private String title;

    private String content;

    private String occurTime;

    private Integer rdl;

}
package com.example.greendaotest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by rhm on 2017/6/3.
 */


@Entity
public class User {
    @org.greenrobot.greendao.annotation.Id //主键，默认会自增
    private Long Id;

    private String name;

    @Transient//表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    private int tempUsageCount; // not persisted

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    @Generated(hash = 690585871)
    public User(Long Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Override
    public String toString() {
        return getId()+getName();
    }
}
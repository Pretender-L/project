package com.demo.jpa.service;


import com.demo.excetion.BadException;
import com.demo.pojo.PageInfo;
import com.demo.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void add(User user);

    void addAll(List<User> userList);

    void del(String userId);

    void delAll(String[] userIds);

    void update(User user);

    List<User> findAll() throws BadException;

    User find(String id) throws BadException;

    List<User> batchQuery(List<String> ids) throws BadException;

    List<User> findAllSortAsc() throws BadException;

    Page<User> findAllPageSortDesc(PageInfo pageInfo) throws BadException;

    Page<User> findListByConditions(PageInfo pageInfo, User user);

    Page<User> findListByCondition(PageInfo pageInfo, String condition);

    User findOne(User userInfo) throws BadException;

    void delDB();

    Page search(User user, PageInfo pageInfo);
}
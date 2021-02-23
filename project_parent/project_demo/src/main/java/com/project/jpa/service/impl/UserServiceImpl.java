package com.project.jpa.service.impl;

import com.project.excetion.BadException;
import com.project.jpa.repository.UserRepository;
import com.project.jpa.service.UserService;
import com.project.pojo.PageInfo;
import com.project.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void addAll(List<User> userList) {
        userRepository.saveAll(userList);
    }

    @Override
    public void del(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void delAll(String[] userIds) {
        userRepository.deleteByIds(userIds);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() throws BadException {
        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            throw new BadException("暂无数据!");
        }
        //60秒过期
        redisTemplate.opsForValue().set("findAll", userList, 60, TimeUnit.SECONDS);
        return userList;
    }

    @Override
    public User find(String id) throws BadException {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadException("暂无数据!");
    }

    @Override
    public List<User> batchQuery(List<String> ids) throws BadException {
        List<User> userList = userRepository.findAllById(ids);
        if (userList.size() == 0) {
            throw new BadException("暂无数据!");
        }
        return userList;
    }

    @Override
    public List<User> findAllSortAsc() throws BadException {
        Sort sort = Sort.by(Sort.Direction.ASC, "birthday");
        List<User> userList = userRepository.findAll(sort);
        if (userList.size() == 0) {
            throw new BadException("暂无数据!");
        }
        return userList;
    }

    @Override
    public Page<User> findAllPageSortDesc(PageInfo pageInfo) throws BadException {
        Sort sort = Sort.by(Sort.Direction.DESC, "birthday");
        PageRequest pageable = PageRequest.of((int) pageInfo.getCurrentpage() - 1, (int) pageInfo.getSize(), sort);
        Page<User> page = userRepository.findAll(pageable);
        if (page.getContent().size() == 0) {
            throw new BadException("暂无数据!");
        }
        return page;
    }

    @Override
    public Page<User> findListByConditions(PageInfo pageInfo, User user) {
        PageRequest pageable = PageRequest.of((int) pageInfo.getCurrentpage() - 1, (int) pageInfo.getSize());
        //前端传空串 数据库该字段如果有数据可以查询到，没有数据查询不到（设置null值后可查询）
        if (StringUtils.isEmpty(user.getSex())) {
            user.setSex(null);
        }
        if (StringUtils.isEmpty(user.getNickname())) {
            user.setNickname(null);
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            user.setEmail(null);
        }
        //条件值对象（定义一个查询实体，给相对应的属性赋值表示要查询的条件， 对象属性为空时代表全部查询）
        //表示创建一个空的条件匹配器（初始化）（进行模糊查询或等其它查询配置）
        //查询“sex”属性中包含的
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());
        //忽略查询字段
        //.withIgnorePaths("id");
        //ExampleMatcher.GenericPropertyMatchers.contains() 包含关键字
        //ExampleMatcher.GenericPropertyMatchers.startsWith() 前缀匹配
        //ExampleMatcher.GenericPropertyMatchers.ignoreCase() 忽略大小写

        //第一个参数是查询实体的查询条件， 第二个参数是条件匹配器（模糊查询等）
        //根据user对象有的属性查询
        Example<User> example = Example.of(user, exampleMatcher);
        Page<User> page = userRepository.findAll(example, pageable);
        return page;
    }

    @Override
    public Page<User> findListByCondition(PageInfo pageInfo, String condition) {
        PageRequest pageable = PageRequest.of((int) pageInfo.getCurrentpage() - 1, (int) pageInfo.getSize());
        Page page = userRepository.findCondition(condition, pageable);
        return page;
    }

    @Override
    public User findOne(User userInfo) throws BadException {
        Example<User> example = Example.of(userInfo);
        Optional<User> optional = userRepository.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BadException("暂无数据!");
    }

    @Override
    public void delDB() {
        userRepository.deleteAll();
    }

    @Override
    public Page search(User user, PageInfo pageInfo) {
        PageRequest pageable = PageRequest.of((int) pageInfo.getCurrentpage() - 1, (int) pageInfo.getSize());
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                //增加筛选的条件
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(user.getSex())) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("sex"), user.getSex()));
                }
                //起始日期
                if (user.getStartDate() != null) {
                    predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), user.getStartDate()));
                }
                //结束日期
                if (user.getEndDate() != null) {
                    predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), user.getEndDate()));
                }
                return predicate;
            }
        };
        return userRepository.findAll(specification, pageable);
    }

}

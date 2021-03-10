package com.project.demo.cache.service.impl;

import com.project.demo.cache.repository.AdminCacheRepository;
import com.project.demo.cache.service.AdminCacheService;
import com.project.demo.pojo.Admin;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Spring-Cache的不足之处：
 * 1）、读模式
 * 缓存穿透：查询一个null数据。
 * 解决方案：缓存空数据，spring-cache可以解决cache-null-values: true
 * 缓存击穿：大量并发进来同时查询一个正好过期的数据。
 * 解决方案：加锁 ? 默认是无加锁的;使用sync = true来解决击穿问题，这个加的是本地锁，
 * 高并发下，可能同一个微服务的每一个集群节点，都会去查一次数据库，有缓存之后，才都从缓存里获取数据
 * 不过无所谓了，对数据库几乎没啥影响@Cacheable(value = "category",key = "#root.method.name",sync =true)
 * 如果要用分布式锁Spring-Cache，做不到，得自己写
 * 缓存雪崩：大量的key同时过期。
 * 解决：加随机时间。spring-cache可以解决加上过期时间time-to-live: 100000
 * 2)、写模式：（缓存与数据库一致）
 * 1）、读写加锁。
 * 2）、引入Canal,感知到MySQL的更新去更新Redis
 * 3）、读多写多，直接去数据库查询就行
 * <p>
 * 总结：
 * 常规数据（读多写少，即时性，一致性要求不高的数据，完全可以使用Spring-Cache）：写模式(只要缓存的数据有过期时间就足够了)
 * 特殊数据：特殊设计
 */
@Service
public class AdminCacheServiceImpl implements AdminCacheService {
    @Resource
    private AdminCacheRepository adminRepository;

    /***
     * Cacheable:在调用方法之前，首先应该在缓存中查找方法的返回值，如果这个值能够找到，就会返回缓存的值。否则，这个方法就会被调用，返回值会放到缓存之中
     * value:用于指定缓存存储的集合名
     * key：缓存对象存储在Map集合中的key值，非必需，缺省按照函数的所有参数组合作为key值，若自己配置需使用SpEL表达式，比如：@Cacheable(key = "#p0")：使用函数第一个参数作为缓存的key值，更多关于SpEL表达式的详细内容可参考官方文档
     */
    @Cacheable(value = "admin", key = "#adminId")
    @Override
    public Admin findById(String adminId) {
        Optional<Admin> optional = adminRepository.findById(adminId);
        //对象不为空时orElse()方法传入方法会执行。与之相反，orElseGet() 方法不会执行
        return optional.orElse(null);
    }

    /**
     * CachePut注解的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用 。
     * 简单来说就是用户更新缓存数据。但需要注意的是该注解的value 和 key 必须与要更新的缓存相同，也就是与@Cacheable 相同
     *
     * CacheEvict的使用:数据库数据修改，删除缓存
     */
    @CacheEvict(value = "admin", key = "#admin.id")
    @Override
    public void update(Admin admin) {
        adminRepository.save(admin);
    }
}

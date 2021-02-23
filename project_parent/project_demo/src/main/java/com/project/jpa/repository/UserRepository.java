package com.project.jpa.repository;

import com.project.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * nativeQuery = true:开启本地化sql
     * Modifying = 声明删除或更新操作
     *
     * @param userIds
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_user WHERE id IN (:userIds)", nativeQuery = true)
    void deleteByIds(String[] userIds);

    /**
     * CONCAT:返回值得字符串拼接
     *
     * @param condition
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_user WHERE nickname LIKE CONCAT('%',:condition,'%') or sex=:condition", nativeQuery = true)
    Page<User> findCondition(@Param("condition") String condition, Pageable pageable);
}

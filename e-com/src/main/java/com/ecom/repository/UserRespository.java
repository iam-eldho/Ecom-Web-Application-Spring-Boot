/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecom.repository;

import com.ecom.entity.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eldho
 */
@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    @Query(value = "delete from user where user_id = ?1", nativeQuery = true)
    public void deleteByUserId(Long user_id);
    
    @Query(value = "select * from user where email = ?1", nativeQuery = true)
    public User findByEmails(String email);

    @Transactional
    @Modifying
    @Query(value = "update user set name=?2,email=?3,password=?4 where user_id = ?1", nativeQuery = true)
    public void update(Long user_id, String name, String email, String password);

}

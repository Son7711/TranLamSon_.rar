package com.example.baitapbuoi2.repository;

import com.example.baitapbuoi2.dto.UserPostCountDTO;
import com.example.baitapbuoi2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
    List<User> findByNameContaining(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u JOIN u.address a WHERE a.city LIKE %:city%")
    List<User> findByCityContaining(@Param("city") String city);

    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    List<User> findAllOrderByName();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.posts WHERE u.id = :userId")
    User findUserWithPosts(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > 5")
    List<User> findUsersWithMoreThan5Posts();

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) = 0")
    List<User> findUsersWithNoPosts();

    @Query("SELECT NEW com.example.baitapbuoi2.dto.UserPostCountDTO(u.id, SIZE(u.posts)) FROM User u")
    List<UserPostCountDTO> getUsersWithPostCount();
}
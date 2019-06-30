package com.latico.example.springboot.dao.mapper;
 
import com.latico.example.springboot.dao.entity.Demo2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface Demo2Repository extends JpaRepository<Demo2, Integer>{

//    public Demo2 findByName(String name);

    public Demo2 save(Demo2 user);
    
//    @Query(value = "SELECT u FROM Demo2 u WHERE username=:username")
    public Demo2 findByUsername(@Param("username") String username);
 
}
package com.latico.example.springboot.dao.primary.mapper;
 
import com.latico.example.springboot.dao.primary.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer>{
    
//    public Demo findByName(String name);

    public Demo save(Demo user);
    
//    @Query(value = "SELECT u FROM Demo u WHERE username=:username")
    public Demo findByUsername(@Param("username") String username);

    public Demo username(@Param("username") String username);


}
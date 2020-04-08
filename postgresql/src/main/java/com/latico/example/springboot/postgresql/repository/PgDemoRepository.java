package com.latico.example.springboot.postgresql.repository;

import com.latico.example.springboot.jpa.BaseRepository;
import com.latico.example.springboot.postgresql.entity.PgDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgDemoRepository extends BaseRepository<PgDemo, Integer> {
    
}

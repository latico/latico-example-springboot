package com.latico.example.springboot.dao.primary.mapper;

import com.latico.example.springboot.dao.primary.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <PRE>
 *
 *
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-06-20 10:13:25
 * @Version: 1.0
 */
public interface JobEntityRepository extends JpaRepository<JobEntity, Long> {

    JobEntity getById(Integer id);

}

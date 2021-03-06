package com.latico.example.springboot.cache.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

import java.util.List;

/**
 * <PRE>
 * 进行了一些常用的实现，但是这些方法不能直接使用，实现类必须复写，但是可以直接调用过来，请考察Demo的实现示例
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-31 9:20
 * @version: 1.0
 */
@Slf4j
public abstract class AbstractSpringCacheDao<T, K> implements SpringCacheDao<T, K> {

    @Override
    public Cache getCache() {
        return null;
    }

    /**
     * 目前只支持ehcache
     * @return
     */
    @Override
    public List<K> getCacheKeys() {
        return null;
    }

    @Override
    public T put(K id) {
        log.info("强制更新缓存,id:{},数据为空，实现类可以在这里查出数据", id);
        // 更新的时候，不是真的从数据库更新，也可以同时在这里从数据库中更新
        // demoRepository.save(data);
        return null;
    }

    @Override
    public T put(K id, T data) {
        log.info("强制更新缓存,id:{},数据:{}", id, data);
        // 更新的时候，不是真的从数据库更新，也可以同时在这里从数据库中更新
        // demoRepository.save(data);
        return data;
    }

    @Override
    public void evict(K id) {
        log.info("删除缓存数据id:{}", id);
        // 删除的时候，不是真的从数据库删除，也可以同时在这里从数据库中删除
        // demoRepository.deleteByTaskId(id);
    }

    @Override
    public void evict(K id, T data) {
        log.info("删除缓存数据id:{},数据:{}", id, data);
        // 删除的时候，不是真的从数据库删除，也可以同时在这里从数据库中删除
        // demoRepository.deleteByTaskId(id);
    }

    @Override
    public void clean() {
        log.info("清空缓存:{} 所有数据", getCacheName());
        //删除的时候，不是真的从数据库删除，也可以同时在这里从数据库中删除
        // demoRepository.deleteAll();
    }
}

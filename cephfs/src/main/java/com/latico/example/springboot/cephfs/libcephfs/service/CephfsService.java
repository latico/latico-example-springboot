package com.latico.example.springboot.cephfs.libcephfs.service;

import com.ceph.fs.CephStat;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-09 15:05
 * @version: 1.0
 */
public interface CephfsService {

    Boolean mountCephfsByRoot();

    String[] createDirByPath(String path);

    String[] deleteDirByPath(String path);

    CephStat getFileStatusByPath(String path);

    String readFileByPath(String path);

    Boolean uploadFileByPath(String localFilePath, String cephFsFilePath);

    Boolean downloadFileByPath(String localFilePath, String cephFsFilePath);
}

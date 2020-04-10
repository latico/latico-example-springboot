package com.latico.example.springboot.cephfs.libcephfs.service.impl;

import com.ceph.fs.CephMount;
import com.ceph.fs.CephStat;
import com.latico.commons.cephfs.libcephfs.LibCephFsUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.example.springboot.cephfs.libcephfs.service.CephfsService;
import org.springframework.stereotype.Service;

@Service
public class CephfsServiceImpl implements CephfsService {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CephfsServiceImpl.class);

    private CephMount mount = null;

    @Override
    public Boolean mountCephfsByRoot() {

        try {
            this.mount = LibCephFsUtils.createCephMount("admin", "AQCxGlBbNBRBGBAAAYW6tv/Z8x2Dz1mnCxwW9w==", "/", "192.168.101.103");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String[] createDirByPath(String path) {
        String[] dirList = null;
        try {
            if (this.mount == null) {
                return null;
            }
            LibCephFsUtils.mkdirs(mount, path);
            dirList = LibCephFsUtils.listdir(mount, "/");
            return dirList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] deleteDirByPath(String path) {
        String[] dirList = null;


        try {
            LibCephFsUtils.rmdir(mount, path);
            dirList = LibCephFsUtils.listdir(mount, "/");
            return dirList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CephStat getFileStatusByPath(String path) {
        try {
            if (this.mount == null) {
                return null;
            }
            CephStat stat = LibCephFsUtils.getFileStatus(mount, path);
            return stat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String readFileByPath(String path) {
        String context = null;
        try {
            if (this.mount == null) {
                return null;
            }
            byte[] buffer = LibCephFsUtils.readFile(mount, path);
            context = new String(buffer);
            return context;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean uploadFileByPath(String localFilePath, String cephFsFilePath) {

        // exit with null if not mount
        if (this.mount == null) {
            LOG.info("Ceph fs not mount!");
            return null;
        }

        return LibCephFsUtils.uploadFile(mount, localFilePath, cephFsFilePath);
    }

    @Override
    public Boolean downloadFileByPath(String localFilePath, String cephFsFilePath) {

        // exit with null if not mount
        if (this.mount == null) {
            LOG.info("Ceph fs not mount!");
            return null;
        }

        return LibCephFsUtils.downloadFile(mount, localFilePath, cephFsFilePath);
    }

}
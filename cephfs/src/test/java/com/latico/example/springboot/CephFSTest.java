package com.latico.example.springboot;

import com.ceph.fs.CephMount;
 
import java.io.IOException;
 
public class CephFSTest {
    public static void main(String[] args){
        //admin是ceph的admin用户　
        CephMount mount = new CephMount("admin");
        //10.112.101.141;10.112.101.142;10.112.101.143是ceph集群的mon节点，有多少个写多少个
        mount.conf_set("mon_host", "10.112.101.141;10.112.101.142;10.112.101.143");
        System.out.println(mount.conf_get("mon_host"));
        //以下的key来自于ceph环境的/etc/ceph/ceph.client.admin.keyring里面的key
        mount.conf_set("key","AQAfN2dZzp19OhAAKlQz2rKKoBATIuGdBkXD0A==");
        //在创建目录之前必须先mount到根目录
        mount.mount("/");
        //在根目录下面创建子目录　mysqlDB,0777是对目录的权限控制，这个可以改成别的，不过最好要让目录具有读写权限
        try {
            mount.mkdirs("/mongoDB/",0777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建完后断掉mount
        mount.unmount();
        System.out.println("success");
    }
}
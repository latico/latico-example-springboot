package com.latico.example.springboot.controller;

import com.ceph.fs.CephStat;
import com.latico.example.springboot.cephfs.libcephfs.service.CephfsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping(value = "demo/cephfs")
public class DemoCephfsController {

    @Autowired
    private CephfsService cephfsService;
 
    @ApiOperation(value = "Mount", notes = "Mount")
    @RequestMapping(value = "/mount", method = RequestMethod.GET)
    public Boolean mountCephFsByRoot(){
        return cephfsService.mountCephfsByRoot();
    }
 
    @ApiOperation(value = "CreateDir", notes = "CreateDir")
    @RequestMapping(value = "/createdir", method = RequestMethod.POST)
    public String[] createDirByPath(@RequestParam(value = "DirPath") String path){
        return cephfsService.createDirByPath(path);
    }
 
    @ApiOperation(value = "DeleteDir", notes = "DeleteDir")
    @RequestMapping(value = "/deletedir", method = RequestMethod.DELETE)
    public String[] deleteDirByPath(@RequestParam(value = "DirPath") String path){
        return cephfsService.deleteDirByPath(path);
    }
 
    @ApiOperation(value = "FileStatus", notes = "FileStatus")
    @RequestMapping(value = "/getfilestatus", method = RequestMethod.GET)
    public CephStat getFileStatusByPath(@RequestParam(value = "DirPath") String path){
        return cephfsService.getFileStatusByPath(path);
    }
 
    @ApiOperation(value = "FileContext", notes = "FileContext")
    @RequestMapping(value = "/getfilecontext", method = RequestMethod.GET)
    public String readFileByPath(@RequestParam(value = "DirPath") String path){
        return cephfsService.readFileByPath(path);
    }
 
}
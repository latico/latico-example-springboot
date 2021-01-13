package com.latico.example.springboot.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.latico.commons.common.util.io.FileUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.fastdfs.starter.FastDfsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);
    @Value("${server.port}")
    String serverPort;

    @Autowired
    private FastDfsClientService fastDfsClientService;

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "hello")
    public String hello() {
        //返回字符串，需要包一层JSON
        return "端口" + serverPort + ":服务生产者数据:" + "你好";
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestBody String content) {

        StorePath storePath = fastDfsClientService.uploadFile("abc.txt", content, "UTF-8");
        return storePath.getFullPath();
    }

    @RequestMapping(value = "downloadFileToStr", method = RequestMethod.GET)
    public String downloadFileToStr(String fileUrl) {
        try {
            return fastDfsClientService.downloadFileToStr(fileUrl, "UTF-8");
        } catch (IOException e) {
            LOG.error("", e);
        }
        return "失败";
    }

    @RequestMapping(value = "deleteFile", method = RequestMethod.GET)
    public String deleteFile(String fileUrl) {
        fastDfsClientService.deleteFile(fileUrl);
        return "成功";
    }
}

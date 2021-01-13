package com.latico.example.springboot.invoker.feign.eureka.fastdfs;

import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-fastdfs")
@RequestMapping("/service-fastdfs/fastdfs/service/fastdfs")
public interface FastdfsControllerClient {

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content 文件内容
     * @return
     */
    @ApiOperation(value = "将一段字符串生成一个文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "文本内容", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fileName", value = "文件名称ctg-dfs生效 当时ctg-dfs为必填", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "overwrite", value = "重名是否覆盖,默认false", required = false, dataType = "String", paramType = "query")
    })
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "返回fastdfs上传成功后的文件地址")})
    @PostMapping(value = "uploadText")
    @ResponseBody
    public ResponseData<String> uploadText(@RequestParam(value = "content") String content, @RequestParam(value = "fileName") String fileName, @RequestParam(value = "overwrite", defaultValue = "false") String overwrite) ;


    @PostMapping(value = "uploadData")
    @ResponseBody
    public ResponseData<String> uploadData(@RequestBody UploadDataParam uploadDataParam);

}

package com.latico.example.springboot.invoker.feign.eureka.fastdfs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 方法统一返回对象
 * {
 *     code: 200,
 *     message: 状态描述
 *     data： 实际业务返回值
 * }
 *
 */
@Data
@ApiModel(value = "方法统一返回对象")
public class ResponseData<T> implements Serializable {
	
	private static final long serialVersionUID = -8782333365744933352L;
	
	/**
	 * 状态码  ==200（ResponseRecode.SUCCESS_CODE.getRecode()） 时 成功
     */
	@ApiModelProperty(value = "状态码")
	private int code = 200;
	/**
	 * 状态描述信息
	 */
	@ApiModelProperty(value = "状态描述信息")
	private String message = "";
	
	@ApiModelProperty(value = "返回数据体")
	private T data;
	
}

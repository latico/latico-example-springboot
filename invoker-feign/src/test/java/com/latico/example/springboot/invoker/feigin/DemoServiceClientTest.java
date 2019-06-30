package com.latico.example.springboot.invoker.feigin;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemoServiceClientTest {

    @Test
    public void testRequestParam() {
        DemoServiceClient serviceClient = createProxyByJAXRSContractJson("http://127.0.0.1:9000", DemoServiceClient.class);
        System.out.println(serviceClient.testRequestParam("abc"));
    }

    @Test
    public void testPathVariable() {
        DemoServiceClient serviceClient = createProxyByJAXRSContractJson("http://127.0.0.1:9000", DemoServiceClient.class);
        System.out.println(serviceClient.testPathVariable("路径参数"));
    }

    @Test
    public void testMultiPathVariable() {
        DemoServiceClient serviceClient = createProxyByJAXRSContractJson("http://127.0.0.1:9000", DemoServiceClient.class);
        System.out.println(serviceClient.testMultiPathVariable("路径参数key", "路径参数value"));
    }

    /**
     * 使用JAXRSContract，JSON传输格式
     方法上面的注解格式：
     @POST
     @Path("/serverTime")
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})

      * @param serverUrl http服务器URL
     * @param clazz 要被代理的类
     * @param <T>
     * @return
     */
    public static <T> T createProxyByJAXRSContractJson(String serverUrl, Class<T> clazz) {
//        建造者模式创建对象
        return Feign.builder()
                .contract(new JAXRSContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(10000, 60000))
                .retryer(new Retryer.Default(3000, 5000, 3))
                .target(clazz, serverUrl);
    }
}
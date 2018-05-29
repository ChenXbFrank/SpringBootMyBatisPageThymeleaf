package com.cxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
//没有连接数据库的时候报错  需要加上这一句
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafApplication.class, args);
	}
	
	/**
	 * 这里是为了使用阿里的fastjson   方式一
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonConverters() {
		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
		FastJsonConfig fastConfig=new FastJsonConfig();
		fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastConfig);
		HttpMessageConverter<?> converter=fastConverter;
		return new HttpMessageConverters(converter);
	}
}

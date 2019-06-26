package nj.zj.study.utils;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


/**  

* <p>Description: 使用@RestController 或者@ResponseBody
* json里面存在null就会转成空字符串
* </p>  

* @author ZTY  

* @date 2019年6月26日  

*/

@Configuration
public class JacksonConfig {
	 @Bean
	    @Primary
	    @ConditionalOnMissingBean(ObjectMapper.class)
	    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
	        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
	        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
	            @Override
	            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
	                jsonGenerator.writeString("");
	            }
	        });
	        return objectMapper;
	    }

	
}

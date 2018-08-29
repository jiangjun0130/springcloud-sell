package jj.fly.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 转换为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object fromJson(String message, Class classType){
		try{
			return objectMapper.readValue(message, classType);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Object fromJson(String message, TypeReference typeReference){
		try{
			return objectMapper.readValue(message, typeReference);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
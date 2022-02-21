package com.example.multimodule.service;

import com.example.multimodule.service.utils.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyService {

	public String formatMessage(BindingResult result){
		List<Map<String,String>> errors = result.getFieldErrors().stream()
				.map(err -> {
					Map<String,String> error = new HashMap<>();
					error.put(err.getField(),err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());

		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01")
				.messages(errors)
				.build();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try{
			json = objectMapper.writeValueAsString(errorMessage);
		} catch (IOException e){
			e.printStackTrace();
		}
		return json;
	}
}

package com.view;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JsonView extends MappingJacksonJsonView {
	
	protected Object filterModel(Map<String, Object> model) {
		Map result = (Map)model.get("Json");
		if (!result.containsKey("ReturnCode")) {
			result.put("ReturnCode", "000000");
		}
		
		return result;
	}

}

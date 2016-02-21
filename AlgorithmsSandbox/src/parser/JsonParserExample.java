package parser;

import java.util.Map;
import java.util.HashMap;

public class JsonParserExample {
	public static void main(String[] args) {
		String input = "{name:joe,"
				+      "gender:male,"
				+      "address:{"
				+      "city:shanghai,"
				+      "street:hanghua,"
				+      "number:285}"
				+      "}";
		
		
		JsonParserExample example = new JsonParserExample();
		Map<String, Object> result = example.parseJson(input);
		StringBuilder builder = new StringBuilder();
		example.printJson(result, builder);
		System.out.println(builder.toString());
	}
	
	public void printJsonEntry(Map.Entry<String, Object> jsonEntry, StringBuilder sb) {
		sb.append(jsonEntry.getKey());
		sb.append(":");
		Map<String, Object> jsonSubObject = null;
		String value = "";
		Object jsonEntryValue = jsonEntry.getValue();
		if (jsonEntryValue instanceof Map<?,?>) {
			jsonSubObject = (Map<String, Object>)jsonEntryValue;
			printJson(jsonSubObject, sb);
		} else {
			value = (String)jsonEntry.getValue();
			sb.append(value);
		}
	}
	
	public void printJson(Map<String, Object> json, StringBuilder sb) {
		
		sb.append("{");
		for (Map.Entry<String, Object> jsonEntry : json.entrySet()) { 
			printJsonEntry(jsonEntry, sb);
		}
		sb.append("}");
	}
	
	int index;
	public Map<String, Object> parseJson(String input) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isKey = true;
		String key = "";
		String value_str = "";
		Map<String, Object> value = null;
		boolean isValueString = true;
		
		while (index < input.length()) {
			char c = input.charAt(index);
			index++;
			switch (c) {
			case '{':
				value = parseJson(input);
				isValueString = false;
				break;
			case '}':
				if (!isValueString) {
					if (!"".equals(key)) {
						map.put(key, value);
					} else {
						return value;
					}
				} else {
					if (!"".equals(key)) { 
						map.put(key, value_str);
					}
					
					return map;
				}
				
				isKey = true;
				key = "";
				value_str = "";
				value = null;
				isValueString = true;
				break;
			case ':':
				isKey = false;
				break;
			case ',':
				if (isValueString) {
					map.put(key, value_str);
				} else { 
					map.put(key, value);
				}
				
				isKey = true;
				key = "";
				value_str = "";
				value =null;
				break;
			default:
				if (isKey) { 
					key += c; 
				} else {
					value_str += c;
				}
				
				break;
			}
			
		}
		
		// value != null stands for top-level situation.
		if (value != null) return value;
		return map;
	}
}

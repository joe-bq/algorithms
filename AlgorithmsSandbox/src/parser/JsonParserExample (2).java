package parser;
import java.util.HashMap;
import java.util.Map;

public class JsonParserExample {
	public static void main(String[] args) {
		String input = "{"
					 + "name:joe,"
					 + "phone:1234,"
					 + "address:{"
					 + "city:shanghai,"
					 + "street:handong,"
					 + "no:285"
					 + "}"
					 +"}";
		
		JsonParserExample example = new JsonParserExample();
		Map<String, Object> result = example.parseJson(input);
		
		StringBuilder sb = new StringBuilder();
		
		example.printJson(result, sb);
		
		System.out.println(sb.toString());
	}
	
	public void printJson(Map.Entry<String, Object> entry, StringBuilder sb) {
		
		if (entry.getValue() instanceof HashMap<?, ?>) {
			sb.append(entry.getKey());
			sb.append(":");
			Map<String, Object> map = (HashMap<String, Object>) entry.getValue();
			printJson(map, sb);
		} else {
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());		
		}
	}
	
	public void printJson(Map<String, Object> map, StringBuilder sb) {
		sb.append("{");
		for (Map.Entry<String, Object> entry : map.entrySet()) { 
			printJson(entry, sb);
			sb.append(",");
		}
		sb.append("}");
	}
	
	int index = 0;

	public Map<String, Object> parseJson(String input) {
	
		boolean isKey = true;
		Object value = null;
		String key = "";
		String value_str = "";
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isValueObject = false;
		while (index < input.length()) { 
			char c = input.charAt(index);
			index++;
			switch (c) {
			case '}':
				if (isValueObject) { 
					map.put(key, value);
				} else { 
					map.put(key, value_str);
				}
				
				isValueObject = false;
				key = "";
				value_str = "";
				value  = null;
				isKey = true;
				return map;
			case '{':
				isValueObject = true;
				isKey = true;
				value = parseJson(input);
				break;
			case ',':
				if (isValueObject) {
					map.put(key, value);
				} else { 
					map.put(key, value_str);
				}
				isKey = true;
				value_str = "";
				key = "";
				break;
			case ':':
				isKey = false;
				break;
			default:
				if (isKey) { 
					key += c;
				} else {
					value_str += c;
				}
			}
		}
		
		if (value != null) return (Map<String, Object>)value;
		return map;
	}

}
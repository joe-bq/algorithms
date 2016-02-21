package parser;
import java.util.Map;
import java.util.HashMap;

public class JsonParserExample2 {
	
	
	public static void main(String[] args) {
		JsonParserExample2 example2 = new JsonParserExample2();
		String input = "{name:joe,"
				+      "gender:male,"
				+      "address:{"
				+      "city:shanghai,"
				+      "street:hanghua,"
				+      "number:285}"
				+      "}";
		StringBuilder builder = new StringBuilder();
		example2.input = input.toCharArray();
		example2.index = 0;
		Map<String, Object> result = example2.parse();
		example2.printJson(result, builder);
		System.out.println(builder.toString());
	}
	
	/** inner class Tuple */
	class Tuple<X, Y> {
		public final X x;
		public final Y y;
		
		public Tuple(X x, Y y) { 
			this.x = x;
			this.y = y;
		}
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
			sb.append(",");
		}
		sb.append("}");
	}
	
	
	/**
	 * var: input character arrays
	 */
	private char[] input;
	/**
	 * reading pointer
	 */
	private int index;
	
	public Map<String, Object> parse() { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		eatSpaces();
		while (index < input.length){
			char c = input[index];
			switch (c) {
				case '{':
					index++;
					return parseObject();
			}
		}
		
		return map;
	}
	
	public Map<String, Object> parseObject() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> value = null;
		String value_str = "";
		String key = "";
		while (index < input.length){ 
			char c = input[index];
			
			switch (c)  {
				case '{':
					index++;
					value = parseObject();
					break;
				case '}':
					index++;
					if (!"".equals(key)) {
						if (value != null) {
							map.put(key,  value);
						} else { 
							map.put(key, value_str);
						}
					}
					return map;
				case ',':
					index++;
					key = getNextToken();
					break;
				case ':':
					index++;
					char nextSymbol = lookAhead();
					switch (nextSymbol) {
					case '{':
						continue;
					default:
						value_str = getNextToken();
						map.put(key, value_str);
					}
					break;
				default:
					key = getNextToken();
					break;
			}
		}

		if (value != null) { return value; } 
		return map;
	}
	
	public String getNexKey() { 
		return getNextToken();
		
	}
	
	public String getNextValue() { 
		return getNextToken();
	}
	
	
	/**
	 * Look ahead to next non-empty
	 * this can renamed to skip blanks.
	 * @return
	 */
	public char lookAhead() {
		while (index < input.length) {
			char c = input[index];
			switch (c) {
				case ' ':
				case '\t':
					index++;
					continue;
				default:
					return c;
			}
		}
		
		return 0;
	}
		
	/**
	 * Get next available token.
	 * @return
	 */
	public String getNextToken() { 
		String token = "";
		outer:
		while (index < input.length) {
			char c = input[index];
			switch (c) {
				case ',':
				case '{':
				case '}':
				case ':':
					break outer;
				case ' ':
				case '\t':
					eatSpaces();
			}
			token += input[index];
			index++;
		}
		
		return token;
	}
	
	public void eatSpaces() { 
		while (index < input.length) {
			char c = input[index];
			switch (c) { 
				case '\t':
				case ' ':
					index++;
					break;
				default:
					return;
			}
		}
	}
	

}

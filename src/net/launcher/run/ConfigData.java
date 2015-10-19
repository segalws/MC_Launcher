package net.launcher.run;

import java.util.HashMap;
import java.util.Map;

public class ConfigData {
	Map<String, String> data;
	
	public ConfigData() {
		data = new HashMap<String, String>();
	}
	
	public void put(String key, String value) {
		data.put(key, value);
	}

	public String getString(String key) {
		return data.get(key);
	}
	public Boolean getBoolean(String key) {
		if(data.get(key).equals("true"))
			return true;
		return false;
	}
}

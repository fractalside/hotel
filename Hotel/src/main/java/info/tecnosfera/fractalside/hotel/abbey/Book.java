/*
-------------------------------------------------------------------------
fractalside's Hotel - Alpha 0.0.2 
(Don't use yet. There's work left)
-------------------------------------------------------------------------
http://fractalside.tecnosfera.info , https://github.com/fractalside
"The miracle is this: the more we share the more we have" 
                                           Leonard Nimoy 1931 - 2015
-------------------------------------------------------------------------
Copyright 2018 fractalside (Gonzalo Virgos Revilla)
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package info.tecnosfera.fractalside.hotel.abbey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class Book {
	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	/**
	 * 
	 * @param key
	 * @return can be null
	 */
	public List<String> get(String key) {
		return map.get(key);
	}
	
	/**
	 * 
	 * @param key
	 * @param list
	 */
	public void put(String key, List<String> list) {
		map.put(key, list);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, List<String>> getMap() {
		return map;
	}
	
	/**
	 * 
	 * @param map
	 */
	public void setMap(Map<String,List<String>> map) {
		this.map = map;
	}
}



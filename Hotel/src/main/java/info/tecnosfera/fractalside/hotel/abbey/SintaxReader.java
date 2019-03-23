/*
-------------------------------------------------------------------------
fractalside's Hotel - Alpha 0.0.2 [2018117] 
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class SintaxReader {
	
	
	public List<String> splitFound(String pauta, String str) {
		List<String> list = new ArrayList<String>();
		if ((pauta == null)||(str == null)) {
			return list;
		}
		Matcher m = Pattern.compile(pauta).matcher(str);
		while (m.find()) {
		    list.add(m.group(1));
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param texto
	 * @return
	 */
	public List<String> splitWords(String text) {
		List<String> list = new ArrayList<String>();
		if (text == null) {
			return list;
		}
		Pattern pauta = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher buscador = pauta.matcher(text);
		while (buscador.find()) {
		    if (buscador.group(1) != null) { // Add double-quoted string without the quotes
		    	list.add(buscador.group(1));
		    } else if (buscador.group(2) != null) { // Add single-quoted string without the quotes
		    	list.add(buscador.group(2));
		    } else { // Add single-quoted string without the quotes
		    	list.add(buscador.group());
		    }
		} 
		return list;
	}
	/**
	 * 
	 * @param param
	 * @return option as key, condition as [0]
	 */
	public Book readCondicionalSet(String param) {
		Monk monk = new Monk(new Book());
		String[] items = param.split("\\s*,\\s*");
		for (String item: items) {
			int[] condIndex = {item.indexOf("("), item.lastIndexOf(")")};
			if (condIndex[0] > -1 && condIndex[1] > -1) {
				monk.put(item.substring(0,condIndex[0]), item.substring(condIndex[0], condIndex[1]));
			} else {
				monk.put(item,"");
			}
		}
		return monk.book;
	}
	
}

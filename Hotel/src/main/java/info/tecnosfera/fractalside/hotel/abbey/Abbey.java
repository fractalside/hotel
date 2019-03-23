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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class Abbey {
	public final static String RN = "\r\n";
	public final static String URN = "&#13;&#10;";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYYMMDD_HHMM = "yyyyMMdd_HHmm";
	public final static String HHMM = "HHmm";
	public final static String LIBRARIAN_LOG = "librarian.log";

	private final static String FOUNDATION_RSC = "foundation.xml";
//	public static final String OPTION_PATTERN  = "([^\"]\\S*|\".+?\")\\s*";
//	public final static String WORD_PATTERN = "(\\s*([,\\(\\)]{1})\\s*)|([\\+-]?[A-Za-z0-9 _]+)";
	
	private static Book cfgBook = null;
	
	static {
		cfgBook = new Founder().takeFnd(FOUNDATION_RSC, null);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String fnd(String key) {
		return new BookWorm(cfgBook).item(key, 0);
	}
	
	/**
	 * 
	 * @return
	 */
	public static Book fnd() {
		return cfgBook;
	}

	
	/**
	 * 7 hasn't join
	 * @param joiner
	 */
	public static String join(String joiner, List<String> list) {
		if (list == null || list.size()==0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(list.get(0));
		for (int i = 1; i<list.size(); i++) {
			builder.append(joiner);
			builder.append(nvl("",list.get(i)));
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * @param prefix
	 * @param strings
	 * @return
	 */
	public static String join(String joiner, String... strings) {
		return join(joiner, list(strings));
	}
	
	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static String glue(String first, String... lasts) {
		StringBuilder builder = new StringBuilder(first);
		for (int i = 0; i < lasts.length; i++) {
			builder.append(nvl("", lasts[i]));
		}
		return builder.toString();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return (value == null) || ("".equals(value.trim()));
	}
	
	/**
	 * 
	 * @param defaultValue
	 * @param value
	 * @return
	 */
	public static String nvl(String defaultValue, String value) {
		return (isEmpty(value)) ? defaultValue : value;
	}
	
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	public static List<String> list(String...values) {
		return new ArrayList<String>(Arrays.asList(values));
	}

	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String[] array(List<String> list) {
		return list.toArray(new String[0]);	
	}
	
	/**
	 * 
	 * @param index
	 * @param list
	 * @return
	 */
	public static String item(int index, List<String> list) {
		return (list.size() > index) ? list.get(index) : "";
	}
	
	/**
	 * 
	 * @author fractal
	 *
	 */
	public enum PAGETYPE{
		STD, 
		/** $ **/
		META,
		/** @ **/
		TYPED,
		/** ^ **/
		ROOT,
		/** % **/
		VAR
	}

	
}

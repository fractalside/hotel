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

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class Monk extends BookWorm {
	/**
	 * 
	 * @param book
	 */
	public Monk(Book book) {
		super(book);
		if (this.book.get(ROOT_KEY_LIST) == null) {
			this.book.put(ROOT_KEY_LIST, Abbey.list());
		}
		if (this.book.get(CONTENT_KEY_LIST) == null) {
			this.book.put(CONTENT_KEY_LIST, Abbey.list());
		}
	}
	
	
	public void setBook(Book book) {
		super.book = book;
	}

	/**
	 * 
	 * @param key
	 * @param values
	 */
	public void put(Abbey.PAGETYPE pageType, String key, String...values) {
		String prefix = prefix(pageType);
		String keyListKey = null;
		if (Abbey.PAGETYPE.STD.equals(pageType)) {
			keyListKey = CONTENT_KEY_LIST;
		} 
		
		book.put(prefix + key, Abbey.list(values));
		putKey(key, ROOT_KEY_LIST);
		putKey(key, keyListKey);
	}
	
	
	/**
	 * 
	 * @param key
	 * @param size
	 */
	public void dim(String key, int size) {
		List<String> list = list(key);
		while (list.size() < size) {
			list.add("");
		}
		put(key, Abbey.array(list));
	}
	
	
	/**
	 * 
	 * @param key
	 * @param index
	 * @param value each element glued
	 */
	public void put(String key, int index, String... text) {
		dim(key, index + 1);
		List<String> list = list(key);
		list.set(index, Abbey.glue("", text));
		put(key, Abbey.array(list));
	}

	public void add(String key, int index, String... text) {
		put(key,index,Abbey.glue(item(key, index),text));
	}

	/**
	 * 
	 * @param key
	 * @param values each value in a list position
	 */
	public void put(String key, String... values) {
		put(Abbey.PAGETYPE.STD, key, values);
	}
	
	/**
	 * 
	 * @param key
	 * @param values
	 */
	public void putMeta(String key, String... values) {
		put(Abbey.PAGETYPE.META, key, values);
	}

	/**
	 * 
	 * @param key
	 * @param values
	 */
	public void add(String key, String...values) {
		if (key != null) {
			List<String> list = book.get(key);
			list = (list == null) ? new ArrayList<String>() : list;
			list.addAll(Abbey.list(values));
			book.put(key, list);
		}
		
	}
	
	
	/**
	 * 
	 * @param index
	 * @param newKey
	 */
	private void putKey(String newKey, String keyListKey) {
		if (keyListKey != null) {
			List<String> keyList = book.get(keyListKey);
			if (!keyList.contains(newKey)) {
				keyList.add(newKey);
				book.put(keyListKey, keyList);
			}
		}
	}
}

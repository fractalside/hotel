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
import java.util.List;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class BookWorm {
	
	protected final String ROOT_KEY_LIST 	= "^.root.*";
	protected final String CONTENT_KEY_LIST = "^.content.*";
	protected Book book;
	
	
	public Book getBook() {
		return this.book;
	}

	/**
	 * If null, create a new empty Book: isEmpty will return true.
	 * @param book
	 */
	public BookWorm(Book book) {
		this.book = (book == null) ? new Book() : book;
	}
	
	
	
	public boolean isEmpty() {
		return (list(CONTENT_KEY_LIST).size() == 0);
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean contains(String item) {
		return list(CONTENT_KEY_LIST).contains(item);
	}
	
	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String item(String key, Abbey.PAGETYPE type, int index) {
		List<String> list = list(prefix(type) + key);
		return (list.size() > index) ? list.get(index) : "";
	}
	
	
	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String item(String key, int index) {
		return item(key, Abbey.PAGETYPE.STD, index);
	}
	
	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String meta(String key, int index) {
		return item(key, Abbey.PAGETYPE.META, index);
	}
	
	
	/**
	 * 
	 * @param key
	 * @return if null, empty list
	 */
	public List<String> list (String key) {
		List<String> list = book.get(key);
		return (list == null) ? new ArrayList<String>() : list;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	protected String prefix(Abbey.PAGETYPE type) {
		if (Abbey.PAGETYPE.ROOT.equals(type)) {
			return "^."; 
		} else if (Abbey.PAGETYPE.TYPED.equals(type)) {
			return "@."; 
		} else if (Abbey.PAGETYPE.META.equals(type)) {
			return "$."; 
		} else if (Abbey.PAGETYPE.VAR.equals(type)) {
			return "%."; 
		} else {
			return ""; 
		}
	}
	
	public List<String> getRootKeyList() {
		return list(ROOT_KEY_LIST);
	}
	
	public List<String> getKeyList() {
		return list(CONTENT_KEY_LIST);
	}

	/**
	 * 
	 */
	public String toString() {
		List<String> keyList = getRootKeyList();
		if (keyList == null) {
			return "<book status='null'></book>";
		}
		XmlWriter builder = new XmlWriter();
		List<String> page;
		builder.open("book");
		for (String key : keyList) {
			page = book.get(key);
			builder.open("pg");
			for (String p : page) {
				builder.put("<p>", p, "</p>");
			}
			builder.close(1);
		}
		builder.close(1);
		return builder.toString();
	}
	

}

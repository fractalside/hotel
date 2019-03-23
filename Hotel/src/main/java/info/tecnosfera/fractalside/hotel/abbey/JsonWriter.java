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

import java.util.Stack;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class JsonWriter extends Writer {
	
	private Stack<String> boxStack = new Stack<String>();
	private Stack<Boolean> glueStack = new Stack<Boolean>();
	private String quote = "\"";
	
	public JsonWriter() {
		super("{");
		boxStack.push("}");
		glueStack.push(false);
	}

	/**
	 * 
	 * @param tag
	 */
	public void openItem(String tag) {
		glueNotNullTag(tag,"{");
		boxStack.push("}");
		glueStack.push(false);
	}
	

	/**
	 * 
	 * @param tag
	 */
	public void openList(String tag) {
		glueNotNullTag(tag,"[");
		boxStack.push("]");
		glueStack.push(false);
	}
	
	public void putList(String tag, String... pares ) {
		openList(tag);
		assign(pares);
		close(1);
	}

	
	public void putBoolean(String tag, boolean value) {
		glueNotNullTag(tag, (value)?"true":"false");
	}
	
	public void putItem(String tag, String... pares ) {
		openItem(tag);
		assign(pares);
		close(1);
	}

	
	public void assign(String... pares) {
		for(int i = 1; i < pares.length; i = i + 2) {
			glueNotNullTag(pares[i - 1], quote, pares[i], quote);
		}
	}
	
	
	/**
	 * 
	 * @param n
	 */
	public void close(int n) {
		for(int i = 0; i < n;i++) {
			put(boxStack.pop());
			glueStack.pop();
		}
	}
	

	
	@Override
	public String dump() {
		close(1);
		return super.dump();
	}
	
	/**
	 * 
	 * @param tag
	 */
	private void glueNotNullTag(String tag, String... suffix) {
		if (glueStack.peek()) { //glue if required
			put(",");
		} else {
			glueStack.pop();
			glueStack.push(true);
		}
		if (!Abbey.isEmpty(tag)) {
			put(quote, tag, quote, ":");
		}
		put(suffix);
	}
	

}

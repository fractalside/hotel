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

import java.util.List;
import java.util.Stack;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class XmlWriter extends Writer {
	private Stack<String> stack = new Stack<String>();
	
	public XmlWriter() {
		super("");
	}

	
	/**
	 * 
	 * @param tag
	 * @param atts odd is name, even is value
	 */
	public void open(String tag, String... atts) {
		put("<", tag);
		putAtts(atts);
		put(">");
		stack.push(tag);
	}
	
	/**
	 * $$ content
	 * @param tag
	 * @param atts
	 */
	public void putItem(String tag, String... atts) {
		put("<", tag);
		List<String> list = Abbey.list(atts);
		int iContent = list.indexOf("$$");
		if (iContent != -1) {
			String content = list.get(iContent + 1);
			list.remove(iContent);
			list.remove(iContent);
			putAtts(Abbey.array(list));
			put(">", content, "</",tag,">");
		} else {
			putAtts(atts);
			put("/>");
		}
	}

	/**
	 * 
	 * @param n
	 */
	public void close(int n) {
		for(int i=0;i<n;i++) {
			put("</", stack.pop(), ">");
		}
	}
	
	/**
	 * close all
	 * @return xml complete
	 */
	public String dump() {
		close(stack.size());
		return super.dump();
	}
	
	/**
	 * 
	 * @param atts
	 */
	public void putAtts(String... atts) {
		if (atts.length > 1) {
			put(" ");
		}
		for (int i = 1; i < atts.length; i = i + 2) {
			if ("$Y".equals(atts[i])) {
				put(atts[i - 1]);
			} else if ("$N".equals(atts[i])) {
				continue;
			} else {
				put(atts[i - 1], "='", atts[i], "' ");
			}
		}
	}
	
	 
}

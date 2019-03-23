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

public class Writer {
	private StringBuilder builder;
	
	public Writer(String init) {
		builder = new StringBuilder(init);
	}

	/**
	 * 
	 */
	public String toString() {
		return builder.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String dump() {
		String value = builder.toString();
		builder = new StringBuilder("");
		return value;
	}
	
	/**
	 * 
	 * @param items
	 */
	public void put(String... items) {
		for(String item: items) {
			builder.append(item);
		}
	}

	
}

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
package info.tecnosfera.fractalside.hotel.tools;

import java.math.BigInteger;

import info.tecnosfera.fractalside.hotel.abbey.Book;
import info.tecnosfera.fractalside.hotel.abbey.Monk;

public class IdCoder {
	
	private Book def;

	public IdCoder(Book def) {
		this.def = def;
	}
	
	/**
	 * 
	 * @param source
	 * @return Book(binary,source)
	 */
	public Book decode(String source) {
		Monk monk = new Monk(new Book());
		BigInteger big = new BigInteger(source, 16);
		if (big.bitLength() > 0) {
			String binary = big.toString(2);
			monk.put("source", source);
			monk.put("binary", binary);
		}
		return monk.getBook();
	}
	
}

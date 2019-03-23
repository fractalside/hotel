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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbbeyTime {
	/**
	 * 
	 * @param source
	 * @return
	 */
	public Date getDate(String source) {
		return parseString(Abbey.YYYYMMDD, source);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public String formatDate(Date date) { 
		return formatString(Abbey.YYYYMMDD, date);
	}

	/**
	 * 
	 * @param 
	 * @param date
	 * @return
	 */
	public String formatString (String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 
	 * @param pattern
	 * @param string
	 * @return
	 */
	public Date parseString (String pattern, String string) {
		try {
			return new SimpleDateFormat(pattern).parse(string);
		} catch (ParseException e) {
			return null;
		}
	}
	

}
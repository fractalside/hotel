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
package info.tecnosfera.fractalside.hotel.pitia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import info.tecnosfera.fractalside.hotel.abbey.Abbey;
import info.tecnosfera.fractalside.hotel.abbey.AbbeyTime;

/**
 * 
 * @author fractal
 *
 */
public class WorkCalendar {
	private List<String> holidays = new ArrayList<String>();
	private int[] weekdayMM = {0, 9 * 60, 8 * 60, 9 * 60, 8 * 60, 7 * 60, 0};
	private int dayMM = 0;
	private Date dateCursor;
	private String logText = "";
	
	/* Constructors --------------------------------------------------------------------------------------------------*/

	/**
	 * 
	 * @param date
	 */
	public WorkCalendar(Date date) {
		this.holidays = new ArrayList<String>();
		this.dateCursor = date;
	}

	/**
	 * 
	 * @param workDate
	 */
	public WorkCalendar(String workDate) {
		this.holidays = new ArrayList<String>();
		String[] tokens = workDate.split("\\:");
		dateCursor = new AbbeyTime().getDate(tokens[0]);
		if (dateCursor == null) {
			dateCursor = new Date();
		}
		dayMM = Integer.parseInt(tokens[1]) * 60;
	}
	
	/* Publics -------------------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * @param date
	 * @param dayOffset
	 * @return
	 */
	public String parse(Date date, int dayOffset) {
		return Abbey.join(new AbbeyTime().formatDate(date), ":", String.valueOf(dayOffset));
	}


	/**
	 * 
	 * @param holidays
	 */
	public void setHolidays(List<String> holidays) {
		this.holidays = holidays;
	}
	
	/**
	 * 
	 * @param source
	 * @param hours
	 * @return
	 */
	public String addHours(int hours) {
		int mm = dayMM + hours * 60, cursorDayMM = weekdayMM[getWeekDay()];
		while (mm > cursorDayMM) {
			mm = mm - cursorDayMM;
			log("Workday: ",String.valueOf(cursorDayMM/8)," en ", new AbbeyTime().formatDate(dateCursor));
			addDays(1);
			skipHolidays();
		}
		skipHolidays();
		dayMM = mm;
		return parse(dateCursor, dayMM / 60);
	}
	
	/**
	 * 
	 */
	private void skipHolidays() {
		String dayText = new AbbeyTime().formatDate(dateCursor);
		while (holidays.contains(dayText)) {
			log("Skip holiday: ",dayText);
			addDays(1);
			dayText = new AbbeyTime().formatDate(dateCursor);
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getWeekDay() {
		return new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
	}
	

	/**
	 * 
	 * @param days
	 * @return
	 */
	public Date addDays(int days) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(dateCursor); 
		calendar.add(Calendar.DATE, days);
		dateCursor = calendar.getTime();	
		return dateCursor;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLog() {
		return logText;
	}
	
	/* Privates ------------------------------------------------------------------------------------------------------*/

	/**
	 * 
	 * @param logs
	 */
	private void log(String... logs) {
		if ("".equals(logText)) {
			Abbey.join(logText, ", ");
		}
		logText =  Abbey.join(logText, logs);
	}
	
	
}

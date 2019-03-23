/*
-------------------------------------------------------------------------
fractalside Hotel - Alpha 0.0.2
(Don't use yet. There's work left)
-------------------------------------------------------------------------
http://fratalside.tecnosfera.info , https://github.com/fractalside
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class Librarian extends LibrarianHelper {
	
	/**
	 * 
	 * @param path
	 * @param book
	 * @return
	 */
	public Book takeFile(String path, Book book) {
		 try {
			return take(new FileInputStream(path), book);
		} catch (FileNotFoundException e) {
			trap(e);
			return new Book();
		}
	}
	
	/**
	 * 
	 * @param book
	 * @param pathText
	 * @return
	 */
	public boolean keep(Book book, String pathText, boolean append) {
		return keep(xmlList(book), pathText, append);
	}
	
	/**
	 * 
	 * @param book
	 * @return
	 */
	private List<String> xmlList(Book book) {
		BookWorm worm = new BookWorm(book);
		List<String> keys = worm.getRootKeyList();
		XmlWriter writer = new XmlWriter();
		writer.open("book");
		for(String key : keys) {
			if ("librarian.log".equals(key)) {
				continue;
			}
			writer.open("rg", "key", key);
			for (String item :  worm.list(key)) {
				writer.putItem("it", "$$", item);
			}
			writer.close(1);
		}
		writer.close(1);

		List<String> textLines = new ArrayList<String>();
		textLines.add(writer.dump());
		return textLines;
	}
	
	
	/**
	 * 
	 * @param pathText
	 * @param lines
	 */
	public void tryToKeep(List<String> lines, String pathText, boolean append) throws Rat {
		Path path = Paths.get(pathText);
		if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
		    try {
				Files.createFile(path);
			} catch (IOException e) {
				throw new Rat(e);
			}
		}
		try {
			if (append) {
				Files.write(path, lines, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
			} else {
				Files.write(path, lines, Charset.forName("UTF-8"));
			}
		} catch (IOException e) {
			throw new Rat(e);
		}
	}
	

	/**
	 * 
	 * @param lines
	 * @param pathText
	 * @return
	 */
	public boolean keep(List<String> lines, String pathText, boolean append) {
		try {
			tryToKeep(lines, pathText, append);
			return true;
		} catch(Rat e) {
			trap(e);
			return false;
		}
	}

}

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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class LibrarianHelper extends XmlReader {
	private List<Rat> ratTrap = new ArrayList<Rat>();

	/**
	 * 
	 * @return
	 */
	public List<Rat> dumpRatTrap() {
		List<Rat> dump = ratTrap;
		ratTrap = null;
		return dump;
	}
	
	protected void trap(Exception e) {
		ratTrap.add(new Rat(e));
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	protected Book take(InputStream stream, Book book) {
		Monk monk = new Monk(book);
		//Transform into xml doc
		Document doc;
		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			fabrica.setNamespaceAware(false);
			DocumentBuilder constructorXml = fabrica.newDocumentBuilder();
			doc = constructorXml.parse(stream);
		} catch (Exception e) {
			trap(e);
			return monk.book;
		}
		
		//Parse doc
		BookWorm tagWorm;
		NodeList nodes = doc.getChildNodes();
		nodes = nodes.item(0).getChildNodes(); //Igmora la raiz que es book.
		for (int i = 0; i < nodes.getLength(); i++) {
			tagWorm = new BookWorm(read(nodes.item(i), false, "rg"));
			if (!tagWorm.isEmpty()) {
				String key = tagWorm.item("key", 0);
				monk.put(key, Abbey.array(readItList(nodes.item(i))));
			}
		}
		return monk.book;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private List<String> readItList(Node node) {
		List<String> list = new ArrayList<String>();
		NodeList nodes = node.getChildNodes();
		String content;
		for (int i = 0; i < nodes.getLength(); i++) {
			content = readContent(nodes.item(i), "it");
			if (content != null) {
				list.add(content);
			}
		}
		return list;
		
	}
	


}

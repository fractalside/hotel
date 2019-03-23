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

import java.io.InputStream;

//Transform into xml doc
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
public class Founder extends XmlReader{
	private static final String FND_SUFFIX = ".fnd.xml";
	private String domainPrefix = "";
	private XmlWriter libLog = new XmlWriter();
	
	/**
	 * 
	 * @param resource
	 * @param book
	 * @return
	 */
	public Book takeFnd(String resource, Book book) {
		//Resource load
		Thread hilo = Thread.currentThread();
		ClassLoader cargador  = hilo.getContextClassLoader();
		InputStream stream = cargador.getResourceAsStream(resource);
		return takeFnd(stream, book);
	}
	
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	protected Book takeFnd(InputStream stream, Book book) {
		libLog.open("read");
		
		//Transform into xml doc
		Document doc;
		try {
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			fabrica.setNamespaceAware(false);
			DocumentBuilder constructorXml = fabrica.newDocumentBuilder();
			doc = constructorXml.parse(stream);
		} catch (Exception e) {
			Monk monk = new Monk(book);
			libLog.put(e.getMessage());
			monk.put(Abbey.LIBRARIAN_LOG, libLog.dump());
			return monk.book;
		}
		
		//Parse doc
		NodeList nodes = doc.getChildNodes();
//		nodes = nodes.item(0).getChildNodes(); //Igmora la raiz que es book.
		Monk monk = new Monk(parse(nodes.item(0), book));
		monk.put(Abbey.LIBRARIAN_LOG, libLog.dump());
		return monk.book;
	}
	
	/**
	 * 
	 * @param node
	 * @param book
	 * @return
	 */
	private Book parse(Node node, Book book) {
		Book tagBook;
		BookWorm tagWorm;
		Monk monk = new Monk(book);
		NodeList nodes = node.getChildNodes();
		String tag;
		for (int i = 0; i < nodes.getLength(); i++) {
			tagBook = read(nodes.item(i), true, "df", "include");
			if (tagBook == null) {
				libLog.close(1);
				continue;
			}
			tagWorm = new BookWorm(book);
			tag = tagWorm.meta("tag",  0);
			libLog.open("read", "tag", tag);
			if ("df".equals(tag)) {
				String[] df = splitFirst(":",tagWorm.meta("$$", 0));
				monk.put(domain(df[0]), df[1]);
			} else if ("csdf".equals(tag)) {
				String[] df = splitFirst(":",tagWorm.meta("$$", 0));
				monk.put(domain(df[0]), df[1].split("\\,"));
			} if ("index".equals(tag)) {
				String indexTag = readAtt("tag", node, "");
				libLog.open("index", "tag", indexTag);
				domainPrefix = Abbey.glue(domainPrefix,".",indexTag);
				parse(nodes.item(i), book);
				domainPrefix = domainPrefix.substring(0, domainPrefix.length() - indexTag.length());
				libLog.close(1);
			} else if ("include".equals(tag)) {
				String rsc = tagWorm.item("rsc", 0);
				if(!"".equals(rsc)) {
					monk.book = takeFnd(rsc + FND_SUFFIX, book);
					libLog.open("include", "rsc", rsc);
					libLog.put(monk.item(Abbey.LIBRARIAN_LOG, 0));
					libLog.close(1);
				}
			}
		}
		return monk.book;
	}
	
	/**
	 * 
	 * @param leaf
	 * @return
	 */
	private String domain(String leaf) {
		return (Abbey.glue(domainPrefix, ".", leaf));
	}
	
	/**
	 * 
	 * @param separator
	 * @param source
	 * @return
	 */
	private String[] splitFirst(String separator, String source) {
		int pivot = source.indexOf(separator);
		return new String[] {
			source.substring(0, pivot).trim(),
			source.substring(pivot + separator.length()).trim()
		};
	}


}

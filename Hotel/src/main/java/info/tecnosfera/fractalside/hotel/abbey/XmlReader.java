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

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * 
 * @author fractalside (Gonzalo Virgos Revilla)
 *
 */
public class XmlReader {
	
	/**
	 * 
	 * @param node
	 * @param content
	 * @param tagNames
	 * @return null, if not in tagNames
	 */
	public Book read(Node node, boolean content, String... tagNames ) {
		Monk monk = new Monk(new Book());
		monk.put(Abbey.PAGETYPE.ROOT, "type", "xmlItem");
		String name = node.getNodeName();
		if ((Node.ELEMENT_NODE != node.getNodeType()) || (!Abbey.list(tagNames).contains(node.getNodeName()))) {
			// ::::---- RETURN ----::::
			return null;
		}
		monk.put(Abbey.PAGETYPE.META, "tag", name);
		NamedNodeMap map = node.getAttributes();
		Node att;
		for (int i = 0; i < map.getLength(); i++) {
			att = map.item(i);
			monk.put(att.getNodeName(), att.getNodeValue());
		}
		if (content) {
			monk.put(Abbey.PAGETYPE.META, "$$", node.getTextContent());
		}
		// ::::---- RETURN ----::::
		return monk.book;
	}
	
	/**
	 * 
	 * @param attName
	 * @param node
	 * @param defaultValue
	 * @return
	 */
	public String readAtt(String attName, Node node, String defaultValue) {
		NamedNodeMap map = node.getAttributes();
		Node att = map.getNamedItem(attName);
		return (att == null) ? defaultValue : att.getNodeValue();
	}
	
	
	
	public String readContent(Node node, String tagName) {
		String value = null;
		if ((Node.ELEMENT_NODE == node.getNodeType()) && (tagName.equals(node.getNodeName()))) {
			value = node.getTextContent();
		}
		return value;
	}

}

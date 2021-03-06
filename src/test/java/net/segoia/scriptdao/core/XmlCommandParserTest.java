/**
 * script-dao - Library to support dynamic command generation based on Apache Velocity templates
 * Copyright (C) 2009  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.segoia.scriptdao.core;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;
import net.segoia.scriptdao.core.CommandTemplate;
import net.segoia.scriptdao.core.XmlCommandParser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlCommandParserTest extends TestCase {
	
	public void testParseCommand()  throws Exception{
		XmlCommandParser parser = new XmlCommandParser();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("command-test.xml");
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
//		NodeList nodes = document.getChildNodes();
		
		CommandTemplate template = parser.parseCommand(document.getLastChild());
		assertNotNull(template);
		assertNotNull(template.getName());
		assertNotNull(template.getParameterNames());
		assertNotNull(template.getScript());	
		
	}
}

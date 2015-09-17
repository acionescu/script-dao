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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.segoia.scriptdao.core.exceptions.ParseCommandException;
import net.segoia.util.xml.resources.ClasspathEntityResolver;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlCommandLoader implements CommandLoader {
    Logger logger = Logger.getLogger(XmlCommandLoader.class.getName());

    private InputSource inputSource;

    public XmlCommandLoader(InputStream inputStream) {
	inputSource = new InputSource(inputStream);
    }

    public CommandTemplate[] loadCommands() throws Exception {
	DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	return load(documentBuilder);
    }

    public CommandTemplate[] loadCommands(ClassLoader classLoader) throws Exception {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setIgnoringComments(true);
	dbf.setIgnoringElementContentWhitespace(true);
	DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	documentBuilder.setEntityResolver(new ClasspathEntityResolver(classLoader));
	return load(documentBuilder);
    }
    
    private CommandTemplate[] load(DocumentBuilder documentBuilder) throws Exception{
	List<CommandTemplate> commandTemplates = new ArrayList<CommandTemplate>();
	Document document;
	document = documentBuilder.parse(inputSource);
	XmlCommandParser commandParser = new XmlCommandParser();
	Node node = document.getLastChild();
//	NodeList nodes = document.getChildNodes();
//	for (int i = 0; i < nodes.getLength(); i++) {
//	    Node node = nodes.item(i);
	    try {
		commandTemplates.add(commandParser.parseCommand(node));
	    } catch (ParseCommandException e) {
		logger.warn("Error parsing command for node " + node,e);
	    }
//	}
	return (CommandTemplate[]) commandTemplates.toArray(new CommandTemplate[0]);
    }

}

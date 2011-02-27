/*******************************************************************************
 * Copyright 2011 Adrian Cristian Ionescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.zg.scriptdao.core;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ro.zg.scriptdao.core.exceptions.ParseCommandException;

public class XmlCommandParser implements CommandParser{
	private static String COMMAND = "command";
	private static String NAME = "name";
	private static String VERSION = "version";
	private static String PARAMETERS = "parameters";
	private static String PARAMETER = "parameter";
	private static String SCRIPT = "script";
	private static String TYPE = "type";
	
	public CommandTemplate parseCommand(Node node) throws ParseCommandException{
		CommandTemplate commandTemplate = new CommandTemplate();
		
		if(node == null){
			throw new ParseCommandException("The specified node cannot be null!");
		}
		
		if(node.getNodeName().equals(COMMAND)){
			NamedNodeMap attributes = node.getAttributes();
			if(attributes == null){
				throw new ParseCommandException("The attribute '"+NAME+"' must be specified for tag '"+COMMAND+"'.");
			}
			//get the command name
			Node nameAttribute = attributes.getNamedItem(NAME);			
			if(nameAttribute == null){
				throw new ParseCommandException("The attribute '"+NAME+"' must be specified for tag '"+COMMAND+"'.");
			}
			else{
				commandTemplate.setName(nameAttribute.getNodeValue());
			}
			//get the command type
			Node typeAttribute = attributes.getNamedItem(TYPE);
			if(typeAttribute != null){
				commandTemplate.setType(typeAttribute.getNodeValue());
			}			
			//get the command version
			Node versionAttribute = attributes.getNamedItem(VERSION);
			if(versionAttribute != null){
				commandTemplate.setVersion(versionAttribute.getNodeValue());
			}
			
			NodeList commandChildNodes = node.getChildNodes();
			
			for(int i=0;i<commandChildNodes.getLength();i++){
				Node childNode = commandChildNodes.item(i);
				
				if(PARAMETERS.equals(childNode.getNodeName())){
					NodeList parameterNodes = childNode.getChildNodes();
					List<String> parameters = new ArrayList<String>();
					
					for(int j=0;j<parameterNodes.getLength();j++){
						Node parameter = parameterNodes.item(j);
						if(PARAMETER.equals(parameter.getNodeName())){
							parameters.add(parameter.getAttributes().getNamedItem(NAME).getNodeValue());
						}
					}
					commandTemplate.setParameterNames((String[])parameters.toArray(new String[0]));
				}
				else if(SCRIPT.equals(childNode.getNodeName())){
					commandTemplate.setScript(childNode.getFirstChild().getNodeValue().trim());
				}
			}
		}
		else{
			throw new ParseCommandException("The node must start with the tag '"+COMMAND+"' but found "+node.getNodeName());
		}

		return commandTemplate;
	}

}

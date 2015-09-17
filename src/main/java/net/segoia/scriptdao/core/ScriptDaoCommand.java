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

import java.util.Map;

public class ScriptDaoCommand {
	private String name;
	private String type;
	private String content;
	private Map<String,Object> arguments;
	
	public ScriptDaoCommand(String name, String content){
		this.content = content;
		this.name = name;
	}
	
	public ScriptDaoCommand(String name, String content, String type){
		this(name,content);
		this.type = type;
	}
	
	public ScriptDaoCommand(String name, String content, String type, Map<String,Object> args){
		this(name,content,type);
		this.arguments = args;
	}
	
	public Object getArgument(String name){
	    if(arguments == null){
		return null;
	    }
	    return arguments.get(name);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer(255);
		sb.append("Command[");
		sb.append("type = ").append(type).append("; ");
		sb.append("content = ").append(content).append(";");
		sb.append("arguments = ").append(arguments).append("]");
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the arguments
	 */
	public Map<String, Object> getArguments() {
	    return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(Map<String, Object> arguments) {
	    this.arguments = arguments;
	}
	
	
}

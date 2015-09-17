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



public class CommandTemplate {
	/**
	 * Command name
	 */
	private String name;
	private String version;
	/**
	 * Command parameter names
	 */
	private String[] parameterNames = new String[0];
	/**
	 * The command body
	 */
	private String script;
	/**
	 * The name of the file from were the command was loaded
	 */
	private String source;
	/**
	 * Command type
	 */
	private String type;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getParameterNames() {
		return parameterNames.clone();
	}
	public void setParameterNames(String[] parameterNames) {
	    /* we cannot allow null values because bad things happen*/
	    if(parameterNames != null) {
		this.parameterNames = parameterNames;
	    }
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;

public class VelocityCommandBuilder implements CommandBuilder {
	private static Logger logger = Logger.getLogger(VelocityCommandBuilder.class.getName());
	private static VelocityEngine velocityEngine;
	private static  ToolManager velocityToolManager;
	private String builderIdentifier = "VelocityCommandBuilder";
	static{
//		Properties p = new Properties();
//		p.setProperty(Velocity.RESOURCE_LOADER, "ro.zg.velocity.ext.StringResourceLoader");
//		try {
//			Velocity.init(p);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		velocityEngine = new VelocityEngine();
		
		EasyFactoryConfiguration toolboxConfig = new EasyFactoryConfiguration();
		toolboxConfig.toolbox(Scope.APPLICATION).tool(DisplayTool.class);
		toolboxConfig.toolbox(Scope.APPLICATION).tool(DateTool.class);
		
		velocityToolManager = new ToolManager(); 
		velocityToolManager.configure(toolboxConfig);
		
		try {
			velocityEngine.init();
			logger.info("Velocity Engine for Script Dao   successfully initialized");
		} catch (Exception e) {
			logger.error("Error initializing the velocity engine",e);
		}
	}
	

	public String buildCommand(String script, Map arguments) {
		StringWriter sw = new StringWriter();		
		Context vc = velocityToolManager.createContext();
		for(Object key : arguments.keySet()){
			vc.put(key.toString(),arguments.get(key));
		}
		
		try {
			//construct the command
			
			velocityEngine.evaluate(vc, sw, builderIdentifier, script);
		} catch (ParseErrorException e) {
			logger.error("Error merging the command '"+script+"' with context "+arguments,e);
		} catch (MethodInvocationException e) {
			logger.error("Error merging the command '"+script+"' with context "+arguments,e);
		} catch (ResourceNotFoundException e) {
			logger.error("Error merging the command '"+script+"' with context "+arguments,e);
		} catch (IOException e) {
			logger.error("Error merging the command '"+script+"' with context "+arguments,e);
		}		
		
		return sw.toString();
	}

}

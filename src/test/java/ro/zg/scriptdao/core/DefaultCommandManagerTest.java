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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;


public class DefaultCommandManagerTest extends TestCase{
	
	public void testGetCommandArguments() throws Exception{
		DefaultCommandManager commandManager = new DefaultCommandManager("TestCommandManager");
				
		commandManager.setCommandLoader(new ClasspathCommandLoader(""));
		commandManager.setCommandExecutor(new SoCommandExecutor());
		commandManager.setCommandBuilder(new VelocityCommandBuilder());
		commandManager.load();
		System.out.println(commandManager.getAvailableCommands().length);
		
		System.out.println("Found "+commandManager.getAvailableCommands().length+" commands.");
		
		System.out.println("Reloading..");;
		commandManager.load();
		System.out.println("After reload "+commandManager.getAvailableCommands().length);
		
		Map args = new HashMap();
		args.put("prm1", "-ltr");
		args.put("prm2", "/");
		Object resp = commandManager.executeCommand("pwd", args);
		Process p = (Process)resp;
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String line = null;
		while( (line = reader.readLine()) != null){
			System.out.println(line);
		}
		
	}

}

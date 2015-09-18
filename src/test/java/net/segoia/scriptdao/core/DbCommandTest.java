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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.segoia.scriptdao.core.ClasspathCommandLoader;
import net.segoia.scriptdao.core.DefaultCommandManager;
import net.segoia.scriptdao.core.VelocityCommandBuilder;
import net.segoia.scriptdao.core.db.DbCommandExecutor;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbCommandTest extends TestCase{
	
	public void testDbCommand() throws Exception{
//		DefaultCommandManager commandManager = new DefaultCommandManager("TestCommandManager");
//		
//		BasicDataSource bds = new BasicDataSource();
//		bds.setDriverClassName("com.mysql.jdbc.Driver");
//		bds.setUrl("jdbc:mysql://127.0.0.1:3306/test");
//		bds.setUsername("test");
//		bds.setPassword("test");
//		
//		commandManager.setCommandLoader(new ClasspathCommandLoader(""));
//		commandManager.setCommandExecutor(new DbCommandExecutor(bds));
//		commandManager.setCommandBuilder(new VelocityCommandBuilder());
//		commandManager.load();
//		System.out.println(commandManager.getAvailableCommands().length);
//		
//		System.out.println("Found "+commandManager.getAvailableCommands().length+" commands.");
//		
//		System.out.println("Reloading..");
//		commandManager.load();
//		System.out.println("After reload "+commandManager.getAvailableCommands().length);
//		
//		Map args = new HashMap();
//		args.put("service", "PREPAID.EO310");
//		args.put("exit_point","Success");
//		args.put("input_channel","SMS");
//		Object resp = commandManager.executeCommand("GetMessagesForService", args);
//		System.out.println(resp);
	}

}

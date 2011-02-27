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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class VelocityCommandBuilderTest extends TestCase{
	
	
	public void testBuildCommand(){
		CommandBuilder commandBuilder = new VelocityCommandBuilder();
		String script = "$name said that human $what is endless";
		Map<String,String> context = new HashMap<String,String>();
		context.put("name", "Albert Einstein");
		context.put("what", "stupidity");
		
		String command = commandBuilder.buildCommand(script, context);
		System.out.println(command);
		assertEquals("Albert Einstein said that human stupidity is endless", command);
	}
}

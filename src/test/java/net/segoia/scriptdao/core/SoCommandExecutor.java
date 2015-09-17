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

import net.segoia.scriptdao.core.CommandContext;
import net.segoia.scriptdao.core.CommandExecutor;
import net.segoia.scriptdao.core.ScriptDaoCommand;

public class SoCommandExecutor implements CommandExecutor{

	public Object executeCommand(ScriptDaoCommand command) {
		try {
			return Runtime.getRuntime().exec(command.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object[] executeAsTransaction(ScriptDaoCommand[] commands) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] executeAsTransaction(CommandContext commandContext) throws Exception {
	    // TODO Auto-generated method stub
	    return null;
	}

	public Object executeCommand(CommandContext commandContext) throws Exception {
	    // TODO Auto-generated method stub
	    return null;
	}

}

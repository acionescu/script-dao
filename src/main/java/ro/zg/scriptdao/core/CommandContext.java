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

public class CommandContext {
    private ScriptDaoCommand command;
    
    private ScriptDaoCommand[] commands;
    
    private ConnectionManager<?> connectionManager;
    
    public CommandContext(){
	
    }
    
    public CommandContext(ScriptDaoCommand command, ConnectionManager<?> connectionManager){
	this.command = command;
	this.connectionManager = connectionManager;
    }
    
    public CommandContext(ScriptDaoCommand[] commands, ConnectionManager<?> connectionManager){
	this.commands = commands;
	this.connectionManager = connectionManager;
    }
    
    
    /**
     * @return the command
     */
    public ScriptDaoCommand getCommand() {
        return command;
    }
    /**
     * @return the connectionManager
     */
    public ConnectionManager<?> getConnectionManager() {
        return connectionManager;
    }
    /**
     * @param command the command to set
     */
    public void setCommand(ScriptDaoCommand command) {
        this.command = command;
    }
    /**
     * @param connectionManager the connectionManager to set
     */
    public void setConnectionManager(ConnectionManager<?> connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return the commands
     */
    public ScriptDaoCommand[] getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(ScriptDaoCommand[] commands) {
        this.commands = commands;
    }
    
    
}

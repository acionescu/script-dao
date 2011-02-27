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

import ro.zg.db.sql.SqlUtil;

public class DefaultCommandManager<R> implements CommandManager<R> {
    private String name;
    private CommandLoader commandLoader;
    private CommandBuilder commandBuilder;
    private CommandExecutor<?> commandExecutor;
    private ConnectionManager<?> connectionManager;
    private ResponseTranslator<Object, R> translator;
    private ClassLoader resourcesLoader;
    private Map<String, CommandTemplate> commandTemplates = new HashMap<String, CommandTemplate>();

    public DefaultCommandManager() {

    }

    public DefaultCommandManager(String name) {
	this.name = name;
    }

    public void init() throws Exception {
	load();
    }

    public void load() throws Exception {
	if (commandLoader != null) {
	    commandTemplates.clear();
	    CommandTemplate[] templates = commandLoader.loadCommands(resourcesLoader);
	    for (CommandTemplate template : templates) {
		if (!commandTemplates.containsKey(template.getName())) {
		    commandTemplates.put(template.getName(), template);
		} else {
		    CommandTemplate prevTemplate = commandTemplates.get(template.getName());
		    throw new RuntimeException("Could not add the command with name '" + template.getName()
			    + "' from '" + template.getSource() + "."
			    + "\nA command with this name was loaded previously from '" + prevTemplate.getSource()
			    + "'");
		}
	    }
	}
    }

    private CommandContext getCommandContext(ScriptDaoCommand command) {
	return new CommandContext(command, connectionManager);
    }

    private CommandContext getCommandContext(ScriptDaoCommand[] commands) {
	return new CommandContext(commands, connectionManager);
    }

    public String[] getCommandArguments(String commandName) {
	CommandTemplate template = commandTemplates.get(commandName);
	return template.getParameterNames();
    }

    public Object executeCommand(String commandName, Map<String, Object> arguments) throws Exception {
	ScriptDaoCommand command = getCommand(commandName, arguments);
	try {
	    return executeCommand(command);
	} catch (Exception e) {
	    throw new Exception("Error executing :\n " + command.getContent(), e);
	}
    }

    public String[] getAvailableCommands() {
	return commandTemplates.keySet().toArray(new String[0]);
    }

    public CommandBuilder getCommandBuilder() {
	return commandBuilder;
    }

    public void setCommandBuilder(CommandBuilder commandBuilder) {
	this.commandBuilder = commandBuilder;
    }

    public CommandExecutor<?> getCommandExecutor() {
	return commandExecutor;
    }

    public void setCommandExecutor(CommandExecutor<R> commandExecutor) {
	this.commandExecutor = commandExecutor;
    }

    public CommandLoader getCommandLoader() {
	return commandLoader;
    }

    public void setCommandLoader(CommandLoader commandLoader) {
	this.commandLoader = commandLoader;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    /**
     * @return the connectionManager
     */
    public ConnectionManager<?> getConnectionManager() {
	return connectionManager;
    }

    /**
     * @param connectionManager
     *            the connectionManager to set
     */
    public void setConnectionManager(ConnectionManager<?> connectionManager) {
	this.connectionManager = connectionManager;
    }

    /**
     * @return the translator
     */
    public ResponseTranslator<?, R> getTranslator() {
	return translator;
    }

    /**
     * @param translator
     *            the translator to set
     */
    public void setTranslator(ResponseTranslator<Object, R> translator) {
	this.translator = translator;
    }

    public ScriptDaoCommand getCommand(String commandName, Map<String, Object> arguments) throws Exception {
	CommandTemplate template = commandTemplates.get(commandName);
	String script = template.getScript();
	/* sanitize string arguments */
	sanitizeStringArguments(arguments);
	String mergedScript = commandBuilder.buildCommand(script, arguments);
	ScriptDaoCommand command = new ScriptDaoCommand(commandName, mergedScript, template.getType(), arguments);
	return command;
    }

    private void sanitizeStringArguments(Map<String, Object> arguments) {
	for (Map.Entry<String, Object> e : arguments.entrySet()) {
	    if (e.getValue() instanceof String) {
		String value = (String) e.getValue();
		e.setValue(SqlUtil.sanitizeStringValues(value));
	    }
	}
    }

    public Object executeAsTransaction(ScriptDaoCommand[] commands) throws Exception {
	Object[] responses = null;
	if (connectionManager != null) {
	    responses = commandExecutor.executeAsTransaction(getCommandContext(commands));
	} else {
	    responses = commandExecutor.executeAsTransaction(commands);
	}
	return translate(responses);
    }

    public R executeCommand(ScriptDaoCommand command) throws Exception {
	Object r = null;
	if (connectionManager != null) {
	    r = commandExecutor.executeCommand(getCommandContext(command));
	} else {
	    r = commandExecutor.executeCommand(command);
	}
	return translate(r);
    }

    private R translate(Object response) {
	if (translator != null) {
	    return translator.translate(response);
	}
	return (R) response;
    }

    private Object translate(Object[] responses) {
	if (translator != null) {
	    return translator.translate(responses);
	}
	return responses;
    }

    public Map<String, CommandTemplate> getCommandTemplates() {
	return commandTemplates;
    }

    /**
     * @return the resourcesLoader
     */
    public ClassLoader getResourcesLoader() {
	return resourcesLoader;
    }

    /**
     * @param resourcesLoader
     *            the resourcesLoader to set
     */
    public void setResourcesLoader(ClassLoader resourcesLoader) {
	this.resourcesLoader = resourcesLoader;
    }

}

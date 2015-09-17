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

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.segoia.util.resources.ResourcesUtil;

import org.apache.log4j.Logger;

/**
 * Implementation of {@link CommandLoader} which loads the commands from a local path or an url
 * 
 * @author adi
 * 
 */
public class UrlCommandLoader implements CommandLoader {
    Logger logger = Logger.getLogger(UrlCommandLoader.class.getName());

    private URL sourceDirectory;
    private String regex;

    public UrlCommandLoader(String sourceDirectoryPath, String filter) throws Exception{
	this(new URL(sourceDirectoryPath));
	regex = filter;
    }
    
    public UrlCommandLoader(String sourceDirectoryPath) throws Exception{
	this(new URL(sourceDirectoryPath));
    }

    public UrlCommandLoader(URL sourceUrl) {
	if (sourceUrl == null) {
	    throw new IllegalArgumentException("The path to the source directory cannot be null!");
	}
	sourceDirectory = sourceUrl;
//	if (!sourceDirectory.exists()) {
//	    throw new IllegalArgumentException("The specified source path: '" + sourceDirectoryPath
//		    + "' does not exist!");
//	}
//	if (!sourceDirectory.isDirectory()) {
//	    throw new IllegalArgumentException("The specified source path: '" + sourceDirectoryPath
//		    + "' is not a directory!");
//	}
    }

    public CommandTemplate[] loadCommands() throws Exception {
	return loadCommands(ClassLoader.getSystemClassLoader());
    }

    public CommandTemplate[] loadCommands(ClassLoader classLoader) throws Exception {
	List<CommandTemplate> commandTemplates = new ArrayList<CommandTemplate>();
	List<String> files = ResourcesUtil.listFiles(sourceDirectory);

	for (String file : files) {
	    try {
		String filePath = sourceDirectory.toString()+file;
//		String sep = File.separator;
//		filePath = filePath.replace(sep+sep, sep);
		URL currentFileUrl =new URL(filePath);
		CommandLoader xmlCommandLoader = new XmlCommandLoader(currentFileUrl.openStream());
		CommandTemplate[] templates = xmlCommandLoader.loadCommands(classLoader);
		// setting the name of the file
		for (CommandTemplate tmp : templates) {
		    tmp.setSource(currentFileUrl.getPath());
		}

		commandTemplates.addAll(Arrays.asList(templates));
	    } catch (Exception e) {
		logger.error("Error loading " + file, e);
	    }
	}
	return (CommandTemplate[]) commandTemplates.toArray(new CommandTemplate[0]);
    }

}

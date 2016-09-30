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

import java.net.URL;

import org.apache.velocity.exception.ResourceNotFoundException;

public class ClasspathCommandLoader implements CommandLoader {
    private String path;

    public ClasspathCommandLoader() {

    }

    public ClasspathCommandLoader(String path) {
	this.path = path;
    }

    /**
     * @return the path
     */
    public String getPath() {
	return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
	this.path = path;
    }

    public CommandTemplate[] loadCommands() throws Exception {
	URL url = ClassLoader.getSystemResource(path);
	return new UrlCommandLoader(url).loadCommands();
    }

    public CommandTemplate[] loadCommands(ClassLoader classLoader) throws Exception {
	URL url = classLoader.getResource(path);
	try {
	    return new UrlCommandLoader(url).loadCommands(classLoader);
	} catch (Exception e) {
	    throw new ResourceNotFoundException("No resource found for path " + path, e);
	}
    }

}

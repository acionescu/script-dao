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
package net.segoia.scriptdao.sql;

import java.util.Map;

import net.segoia.db.sql.SqlUtil;
import net.segoia.scriptdao.core.VelocityCommandBuilder;
import net.segoia.util.data.GenericNameValueContext;
import net.segoia.util.data.NameValue;

public class SqlCommandBuilder extends VelocityCommandBuilder{

    /* (non-Javadoc)
     * @see net.segoia.scriptdao.core.VelocityCommandBuilder#buildCommand(java.lang.String, java.util.Map)
     */
    @Override
    public String buildCommand(String script, Map arguments) {
	
	sanitizeStringArguments(arguments);
	
	return super.buildCommand(script, arguments);
    }
    
    private void sanitizeStringArguments(Map<String, Object> arguments) {
	for (Map.Entry<String, Object> e : arguments.entrySet()) {
	    Object rawValue = e.getValue();
	    if (rawValue instanceof String) {
		e.setValue(SqlUtil.sanitizeStringValues((String) rawValue));
	    }
	    else if(rawValue instanceof GenericNameValueContext) {
		sanitizeStringArgumens((GenericNameValueContext)rawValue);
	    }
	}
    }
    
    private void sanitizeStringArgumens(GenericNameValueContext nvc) {
	for( Map.Entry<String, NameValue<Object>> e : nvc.getParameters().entrySet() ) {
	    Object rawValue = e.getValue().getValue();
	    if( rawValue instanceof String ) {
		e.getValue().setValue(SqlUtil.sanitizeStringValues((String)rawValue));
	    }
	    else if(rawValue instanceof GenericNameValueContext) {
		sanitizeStringArgumens((GenericNameValueContext)rawValue);
	    }
	}
    }

}

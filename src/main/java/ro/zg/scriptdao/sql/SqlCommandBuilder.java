package ro.zg.scriptdao.sql;

import java.util.Map;

import ro.zg.db.sql.SqlUtil;
import ro.zg.scriptdao.core.VelocityCommandBuilder;
import ro.zg.util.data.GenericNameValueContext;
import ro.zg.util.data.NameValue;

public class SqlCommandBuilder extends VelocityCommandBuilder{

    /* (non-Javadoc)
     * @see ro.zg.scriptdao.core.VelocityCommandBuilder#buildCommand(java.lang.String, java.util.Map)
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

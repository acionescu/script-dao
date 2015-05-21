package ro.zg.scriptdao.sql;

import java.util.Map;

import ro.zg.db.sql.SqlUtil;
import ro.zg.scriptdao.core.VelocityCommandBuilder;

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
	    if (e.getValue() instanceof String) {
		String value = (String) e.getValue();
		e.setValue(SqlUtil.sanitizeStringValues(value));
	    }
	}
    }

}

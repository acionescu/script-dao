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
package ro.zg.scriptdao.core.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import ro.zg.scriptdao.core.ScriptDaoCommand;
import ro.zg.scriptdao.core.CommandContext;
import ro.zg.scriptdao.core.CommandExecutor;

public class DbCommandExecutor implements CommandExecutor{
	private DataSource dataSource;
	
	public DbCommandExecutor(){
		
	}
	
	public DbCommandExecutor(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public Object executeCommand(ScriptDaoCommand command) {
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			if("SQL.QUERY".equals(command.getType())){
				return getResultsAsList(stmt.executeQuery(command.getContent()));
			}
			else if("SQL.UPDATE".equals(command.getType())){
				return stmt.executeUpdate(command.getContent());
			}
			else{
				//TODO handle this case
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List getResultsAsList(ResultSet rs) throws SQLException {
		List results = new ArrayList();
		ResultSetMetaData meta = rs.getMetaData();
		int count = rs.getMetaData().getColumnCount();
		List colNames = new ArrayList();
		
		for(int i=1; i<=count; i++) {
			colNames.add(meta.getColumnName(i).toLowerCase());
		}
		while(rs.next()) {
			Map result = new HashMap();
			for(int i=1; i<=count; i++) {
				int colType = meta.getColumnType(i);
				if(colType == Types.DATE || colType == Types.TIMESTAMP) {
					result.put(colNames.get(i-1), rs.getTimestamp(i));
				}
				else {
					result.put(colNames.get(i-1), rs.getObject(i));
				}
			}
			results.add(result);
		}
		
		return results;
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

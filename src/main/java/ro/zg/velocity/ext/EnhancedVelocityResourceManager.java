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
package ro.zg.velocity.ext;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.ResourceManager;
import org.apache.velocity.runtime.resource.ResourceManagerImpl;

public class EnhancedVelocityResourceManager extends ResourceManagerImpl{
	
	protected Resource getResource(String resourceName,String resourceContent, int resourceType, String encoding )
    throws ResourceNotFoundException, ParseErrorException, Exception
    {
		
		return null;
    }
	
	public Resource getResource(String resourceName,String resourceContent )
    throws ResourceNotFoundException, ParseErrorException, Exception
    {
		
		return getResource(resourceName,resourceContent,ResourceManager.RESOURCE_TEMPLATE, RuntimeConstants.ENCODING_DEFAULT);
    }

}

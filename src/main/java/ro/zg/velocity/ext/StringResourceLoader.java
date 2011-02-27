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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class StringResourceLoader extends ResourceLoader{

	@Override
	public long getLastModified(Resource resource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getResourceStream(String source) throws ResourceNotFoundException {
		ByteArrayInputStream is = new ByteArrayInputStream(source.getBytes());
		return is;
	}

	@Override
	public void init(ExtendedProperties configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		// TODO Auto-generated method stub
		return false;
	}

}

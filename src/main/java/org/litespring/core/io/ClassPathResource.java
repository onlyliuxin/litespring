package org.litespring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.util.ClassUtils;

public class ClassPathResource implements Resource {

	private String path;
	private ClassLoader classLoader;

	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}
	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	public InputStream getInputStream() throws IOException {
		InputStream is = this.classLoader.getResourceAsStream(this.path);
		
		if (is == null) {
			throw new FileNotFoundException(path + " cannot be opened");
		}
		return is;
		
	}
	public String getDescription(){
		return this.path;
	}

}

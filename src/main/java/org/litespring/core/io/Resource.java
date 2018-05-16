package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
	public InputStream getInputStream() throws IOException;
	public String getDescription();
}

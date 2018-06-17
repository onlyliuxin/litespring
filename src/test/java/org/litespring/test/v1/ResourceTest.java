package org.litespring.test.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class ResourceTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void testClassPathResource() throws Exception {

		Resource r = new ClassPathResource("petstore-v1.xml");

		InputStream is = null;

		try {
			is = r.getInputStream();
			// 注意：这个测试其实并不充分！！
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

	@Test
	public void testFileSystemResource() throws Exception {
		temporaryFolder.newFolder("test");
	  File file = temporaryFolder.newFile("test/petstore-v1.xml");
		copyResourceToFile("petstore-v1.xml", file);
		Resource r = new FileSystemResource(file.getAbsolutePath());

		InputStream is = null;

		try {
			is = r.getInputStream();
			// 注意：这个测试其实并不充分！！
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private void copyResourceToFile(String resource, File file) throws IOException
	{
		InputStream initialStream = ResourceTest.class.getClassLoader().getResourceAsStream(resource);
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);
		OutputStream outStream = new FileOutputStream(file);
		outStream.write(buffer);
	}

}

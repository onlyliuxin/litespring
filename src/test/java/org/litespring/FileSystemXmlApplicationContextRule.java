package org.litespring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.rules.ExternalResource;
import org.litespring.test.v1.ResourceTest;

public class FileSystemXmlApplicationContextRule extends ExternalResource
{
  private String resourceToCopy;
  private File file;
  public FileSystemXmlApplicationContextRule(String resourceToCopy)
  {
    this.resourceToCopy = resourceToCopy;
  }

  @Override
  public void before() throws IOException
  {
    String[] name = resourceToCopy.split("\\.");
    String prefix = name[0];
    String suffix = name[1];
    file = File.createTempFile(prefix, "." + suffix);
    copyResourceToFile(resourceToCopy, file);
  }

  @Override
  public void after()
  {
    file.delete();
  }

  public String getPath()
  {
    return file.getAbsolutePath();
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

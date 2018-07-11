package org.litespring.core.io.support;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

public class PackageResourceLoader  {

	private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

	private final ClassLoader classLoader;

	public PackageResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}

	public PackageResourceLoader(ClassLoader classLoader) {
		Assert.notNull(classLoader, "ResourceLoader must not be null");
		this.classLoader = classLoader;
	}



	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	public Resource[] getResources(String basePackage) throws IOException {
		Assert.notNull(basePackage, "basePackage  must not be null");
		String location = ClassUtils.convertClassNameToResourcePath(basePackage);
		ClassLoader cl = getClassLoader();
		URL url = cl.getResource(location);
		File rootDir = new File(url.getFile());
		
		Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
		Resource[] result = new Resource[matchingFiles.size()];
		int i=0;
		for (File file : matchingFiles) {
			result[i++]=new FileSystemResource(file);
		}
		return result;
		
	}
	protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
		if (!rootDir.exists()) {
			// Silently skip non-existing directories.
			if (logger.isDebugEnabled()) {
				logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
			}
			return Collections.emptySet();
		}
		if (!rootDir.isDirectory()) {
			// Complain louder if it exists but is no directory.
			if (logger.isWarnEnabled()) {
				logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
			}
			return Collections.emptySet();
		}
		if (!rootDir.canRead()) {
			if (logger.isWarnEnabled()) {
				logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
						"] because the application is not allowed to read the directory");
			}
			return Collections.emptySet();
		}
		/*String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
		if (!pattern.startsWith("/")) {
			fullPattern += "/";
		}
		fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
		*/
		Set<File> result = new LinkedHashSet<File>(8);
		doRetrieveMatchingFiles(rootDir, result);
		return result;
	}

	
	protected void doRetrieveMatchingFiles( File dir, Set<File> result) throws IOException {
		
		File[] dirContents = dir.listFiles();
		if (dirContents == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
			}
			return;
		}
		for (File content : dirContents) {
			
			if (content.isDirectory() ) {
				if (!content.canRead()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() +
								"] because the application is not allowed to read the directory");
					}
				}
				else {
					doRetrieveMatchingFiles(content, result);
				}
			} else{
				result.add(content);
			}
			
		}
	}

}

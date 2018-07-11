package org.litespring.core.type.classreading;



import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;


public class SimpleMetadataReader implements MetadataReader {

	private final Resource resource;

	private final ClassMetadata classMetadata;

	private final AnnotationMetadata annotationMetadata;


	public SimpleMetadataReader(Resource resource) throws IOException {
		InputStream is = new BufferedInputStream(resource.getInputStream());
		ClassReader classReader;
		
		try {
			classReader = new ClassReader(is);
		}
		finally {
			is.close();
		}

		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
		classReader.accept(visitor, ClassReader.SKIP_DEBUG);

		this.annotationMetadata = visitor;
		this.classMetadata = visitor;
		this.resource = resource;
	}


	public Resource getResource() {
		return this.resource;
	}

	public ClassMetadata getClassMetadata() {
		return this.classMetadata;
	}

	public AnnotationMetadata getAnnotationMetadata() {
		return this.annotationMetadata;
	}

}

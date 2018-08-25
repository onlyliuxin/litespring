首先，利用ASM的visitor去解决怎么读取注解的问题，有```ClassVisitor```、```ClassMetadataReadingVisitor```、
```AnnotationVisitor```；
由于 visitor用起来很麻烦，所以我们做了一个封装，利用```MetaDataReader```对于暴露一个非常简单清晰的接口，
该接口中有两个重要的方法：```getClassMetaData()```和```getAnnotationMetaData()```,有了这个类以后，我们创建了
一个新的类```ScannerGenericBeanDefinition```来表达从类中提取的```BeanDefinition```。

```ClassPathBeanDefinitionScanner```：根据packageName去扫描包，并创建```ScannerGenericBeanDefinition```，
同时注册到```BeanFactory```。

```BeanNameGenerator```：根据一套策略去创建```BeanName```。
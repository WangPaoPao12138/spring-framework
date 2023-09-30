package mytest;

import mytest.obj.MyApplicationAware;
import mytest.obj.MyBeanPostProcessor;
import org.junit.Test;
import org.springframework.aop.framework.autoproxy.AdvisorAutoProxyCreatorIntegrationTests;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

/**
 * @author Gin
 * @description
 * @date 2023/9/30 22:41
 */
public class MyTest {
//	private static final Class<?> CLASS = MyTest.class;
//	private static final String CLASSNAME = CLASS.getSimpleName();

	private static final String DEFAULT_CONTEXT = "spring.xml";
	protected BeanFactory getBeanFactory() throws IOException {
		ClassPathResource resource = new ClassPathResource(DEFAULT_CONTEXT,MyTest.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		return factory;
	}

	@Test
	public void testAware01() throws Exception {
		BeanFactory factory = getBeanFactory();
		MyApplicationAware applicationAware = (MyApplicationAware) factory.getBean("myApplicationAware");
		applicationAware.display();
	}
	@Test
	public void testAware02() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml",MyTest.class);
		MyApplicationAware applicationAware = (MyApplicationAware) applicationContext.getBean("myApplicationAware");
		applicationAware.display();
	}
	@Test
	public void testBeanPostProcessor01(){
		ClassPathResource resource = new ClassPathResource("spring.xml",MyTest.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		//手动添加才执行postProcessor方法
		MyBeanPostProcessor beanPostProcessorTest = new MyBeanPostProcessor();
		factory.addBeanPostProcessor(beanPostProcessorTest);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		MyBeanPostProcessor test = (MyBeanPostProcessor) factory.getBean("myBeanPostProcessor");
		test.display();
	}
	@Test
	public void testBeanPostProcessor02(){
		// ApplicationContext 实例对象的时候会调用 #registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) 方法
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml",MyTest.class);
		MyBeanPostProcessor test = (MyBeanPostProcessor) applicationContext.getBean("myBeanPostProcessor");
		test.display();
	}

}

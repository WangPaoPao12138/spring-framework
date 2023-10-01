package mytest.obj;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author Gin
 * @description
 * @date 2023/10/1 10:12
 */
public class MyInitializingBean1 implements InitializingBean {
	private String name;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBeanTest initializing...");
		this.name = "Gin 2 号";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

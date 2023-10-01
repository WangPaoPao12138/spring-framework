package mytest.obj;

/**
 * @author Gin
 * @description
 * @date 2023/10/1 10:24
 */
public class MyInitializingBean2 {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOtherName(){
		System.out.println("InitializingBeanTest setOtherName...");
		this.name = "Gin 3 号";
	}
}

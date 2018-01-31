import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext  a = new ClassPathXmlApplicationContext("classpath:application-dubbo-consumer.xml");
	}
}

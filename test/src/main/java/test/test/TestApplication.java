package test.test;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
////import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import test.dto.Hotel;
//import test.service.IhotelService;




@SpringBootApplication
@ComponentScan({"test.service","test.rest"})
@EntityScan("test.dto")
@EnableJpaRepositories("test.dao")

public class TestApplication /*implements CommandLineRunner*/{
 	 
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

	}  
	
}


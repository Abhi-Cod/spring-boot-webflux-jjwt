package abhi.code.springbootwebfluxjjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "abhi.code.springbootwebfluxjjwt")
public class SpringBootWebfluxJjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxJjwtApplication.class, args);
    }
}

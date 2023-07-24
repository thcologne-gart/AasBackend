package RestApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Executable extends BackendGART{
	
	public static void main(String[] args) {
		SpringApplication.run(Executable.class, args);
	}
}
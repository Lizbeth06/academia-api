package academiaapi.ipd.gob.pe.academiaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AcademiaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaapiApplication.class, args);
		System.out.println("Corriendo Academia api");
	}
}

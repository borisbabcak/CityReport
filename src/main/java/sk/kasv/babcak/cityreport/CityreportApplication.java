package sk.kasv.babcak.cityreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityreportApplication.class, args);
		System.out.println("Connection to DB");
	}

}

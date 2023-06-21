package ru.learnUp.LearnUp22_DB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUp.LearnUp22_DB.dao.User;
import ru.learnUp.LearnUp22_DB.dao.UserDao;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class LearnUp22DbApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnUp22DbApplication.class, args);
		UserDao userDao = context.getBean((UserDao.class));
		User user = userDao.findById(1);
		log.info("{}",  user);

		User forUpdateUser = User.builder()
				.name("Petr")
				.surname("Petrov")
				.address("USA")
				.birth_date(LocalDate.of(1999, 5, 12))
				.build();
		userDao.save(forUpdateUser);
		log.info("{}", userDao.findById(2));
	}
}

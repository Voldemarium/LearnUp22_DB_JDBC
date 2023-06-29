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

    //Создание схемы learnup, таблицы myUser с последующим их удалением
		userDao.queryDDL("CREATE SCHEMA IF NOT EXISTS learnup");
		userDao.queryDDL("CREATE TABLE IF NOT EXISTS learnup.myUser (\n" +
				"id SERIAL PRIMARY KEY UNIQUE NOT NULL,\n" +
				"\"name\" text not null,\n" +
				"surname text not null,\n" +
				"birth_date date, \n" +
				"address text\n" +
				")");
		userDao.queryDDL("DROP TABLE IF EXISTS learnup.myUser");
		userDao.queryDDL("DROP SCHEMA IF EXISTS learnup CASCADE");

    // Создание таблицы user в схеме public
		userDao.queryDDL("CREATE TABLE IF NOT EXISTS public.user (\n" +
				"id SERIAL PRIMARY KEY UNIQUE NOT NULL,\n" +
				"\"name\" text not null,\n" +
				"surname text not null,\n" +
				"birth_date date, \n" +
				"address text\n" +
				")");

	// Создание user1
		User user1 = User.builder()
				.name("Petr")
				.surname("Petrov")
				.address("USA")
				.birth_date(LocalDate.of(1999, 5, 12))
				.build();
		// Добавление строки в таблицу с user1
		userDao.save(user1);
		// Поиск по id=1 записи в таблице и возвращение объекта User
		User user = userDao.findById(1);
		log.info("{}",  user);

    // Создание user2
		User user2 = User.builder()
				.name("Vlad")
				.surname("Ivanov")
				.address("Russia")
				.birth_date(LocalDate.of(2001, 2, 4))
				.build();
		// Поиск по id=2 записи в таблице и возвращение объекта User
		userDao.save(user2);
		log.info("{}", userDao.findById(2));
	}
}

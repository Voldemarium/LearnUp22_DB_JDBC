package ru.learnUp.LearnUp22_DB.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
	private long id;
	private String name;
	private String surname;
	private String address;
	private LocalDate birth_date;

}

package ru.learnUp.LearnUp22_DB.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

import java.time.ZoneOffset;

@Repository
public class UserDao {

	private static final String FIND_BY_ID = "SELECT * FROM public.user WHERE id = :id";
	private static final String SAVE = "INSERT INTO public.user (name, surname, address, birth_date)" +
			" values (:name, :surname, :address, :birth_date)";

	private final NamedParameterJdbcTemplate template;

	public UserDao(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public void save(User user) {
		template.update(
				SAVE,
				new MapSqlParameterSource()
						.addValue("name", user.getName())
						.addValue("surname", user.getSurname())
						.addValue("address", user.getAddress())
						.addValue("birth_date", new Date(user.getBirth_date()
								.atStartOfDay().toInstant(ZoneOffset.UTC)
								.toEpochMilli()))
		);
	}

	public User findById(long id) {
		return template.query(
						FIND_BY_ID,
						new MapSqlParameterSource("id", id),
						(rs, rn) -> User.builder()
								.id(rs.getLong("id"))
								.name(rs.getString("name"))
								.surname(rs.getString("surname"))
								.address(rs.getString("address"))
								.birth_date(rs.getDate("birth_date").toLocalDate())
								.build()
				).stream()
				.findAny()
				.orElseThrow(() -> new RuntimeException("user with id = " + id + "is not found"));
	}
}
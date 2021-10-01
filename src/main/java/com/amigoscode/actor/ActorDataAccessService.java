package com.amigoscode.actor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDataAccessService  implements ActorDao {

    private final JdbcTemplate jdbcTemplate;

    public ActorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> selectActors() {
        var sql = """
                SELECT id, name
                FROM actor
                LIMIT 100;
                 """;
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public int insertActor(Actor actor) {
        var sql = """
                INSERT INTO actor(name)
                VALUES (?);
                 """;
        return jdbcTemplate.update(
                sql,
                actor.name()
        );
    }

    @Override
    public int deleteActor(int id) {
        var sql = """
                DELETE FROM actor   
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Actor> selectActorById(int id) {
        var sql = """
                SELECT id, name
                FROM actor
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, new ActorRowMapper(), id)
                .stream()
                .findFirst();
    }
}

package com.amigoscode.actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {
    List<Actor> selectActors();
    int insertActor(Actor actor);
    int deleteActor(int id);
    Optional<Actor> selectActorById(int id);
    // TODO: Update
}

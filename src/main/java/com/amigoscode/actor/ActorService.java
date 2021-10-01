package com.amigoscode.actor;

import com.amigoscode.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getActors() {
        return actorDao.selectActors();
    }

    public void addNewActor(Actor actor) {
        // TODO: check if actor exists
        int result = actorDao.insertActor(actor);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteActor(Integer id) {
        Optional<Actor> actors = actorDao.selectActorById(id);
        actors.ifPresentOrElse(actor -> {
            int result = actorDao.deleteActor(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete actor");
            }
        }, () -> {
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        });
    }

    public Actor getActor(int id) {
        return actorDao.selectActorById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", id)));
    }

}

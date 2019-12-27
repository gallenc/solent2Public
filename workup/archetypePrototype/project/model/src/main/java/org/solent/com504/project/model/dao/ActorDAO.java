package org.solent.com504.project.model.dao;

import java.util.List;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;

public interface ActorDAO {

    public Actor findById(Long id);

    public Actor save(Actor actor);

    public List<Actor> findAll();

    public void deleteById(long id);

    public Actor delete(Actor actor);

    public void deleteAll();

    public List<Actor> findByRole(Role role);

    public List<Actor> findByName(String firstName, String secondName);
}

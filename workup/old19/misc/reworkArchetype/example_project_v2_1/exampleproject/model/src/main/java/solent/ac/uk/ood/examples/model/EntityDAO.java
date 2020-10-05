package solent.ac.uk.ood.examples.model;

import java.util.List;

public interface EntityDAO {

    public Entity createEntity(Entity entity);

    public boolean deleteEntity(Integer id);

    public void deleteAllEntities();

    public Entity retrieveEntity(Integer id);

    public List<Entity> retrieveAllEntities();

    public List<Entity> retrieveMatchingEntities(Entity entityTempate);

    public Entity updateEntity(Entity entity);
}

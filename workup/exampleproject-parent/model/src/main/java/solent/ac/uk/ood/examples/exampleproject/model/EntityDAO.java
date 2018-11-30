package solent.ac.uk.ood.examples.exampleproject.model;

import java.util.List;

public interface EntityDAO {

    public Entity createEntity(Entity entity);

    public boolean deleteEntity(Integer id);

    public Entity retrieveEntity(Integer id);

    public List<Entity> retrieveAllEntities();

    public Entity retrieveMatchingEntites(Entity entityTempate);

    public Entity updateEntity(Entity entity);
}

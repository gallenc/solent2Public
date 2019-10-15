package org.solent.com504.factoryandfacade.model.dao;

public interface AnimalDao {

    public Long retrieve(Long id);

    public List<Animal> update(Animal animal);

    public Long delete(Long id);

    public List<Animal> retrieve(Animal animalTemplate);

    public AnimalType create(AnimalType animalType);

    public Animal save(Animal animal);
}

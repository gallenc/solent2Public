package org.solent.com504.oodd.user.model.dao;

import java.util.List;
import java.util.Optional;
import org.solent.com504.oodd.user.model.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,Long> {
    
    long count();
    
    void delete(User t);
    
    void deleteAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    List<User> findAll();

    Optional<User> findById(Long id);

    User getOne(Long id);
    
    <S extends User> S save(S s);
    
}

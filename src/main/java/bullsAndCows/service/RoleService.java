package bullsAndCows.service;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.Role;

import java.util.List;

public interface RoleService {

    Role add(Role role);
    void deleteById(Long id);
    Role update(Role role);
    List<Role> findAll();
}

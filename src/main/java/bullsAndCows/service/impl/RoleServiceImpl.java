package bullsAndCows.service.impl;

import bullsAndCows.model.Role;
import bullsAndCows.repository.RoleRepository;
import bullsAndCows.service.RoleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> findAll() {
        return null;
    }
}

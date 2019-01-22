package casrest.support.service;

import java.util.Set;

public interface RoleService {
    Set<String> findRoleByUserId(Long id);
    Set<String> findRoleNames();
}

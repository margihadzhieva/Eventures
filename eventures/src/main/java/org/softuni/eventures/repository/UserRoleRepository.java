package org.softuni.eventures.repository;

import org.softuni.eventures.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole getByAuthority(String authority);
}

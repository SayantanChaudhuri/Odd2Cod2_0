package com.citi.sayantan.report.userentitlement.repository;

import com.citi.sayantan.report.userentitlement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findFirstBySoeId(String soeId);
}

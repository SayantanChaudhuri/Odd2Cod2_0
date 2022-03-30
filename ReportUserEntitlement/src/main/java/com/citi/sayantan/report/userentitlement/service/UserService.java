package com.citi.sayantan.report.userentitlement.service;

import com.citi.sayantan.report.userentitlement.domain.QueryDetails;
import com.citi.sayantan.report.userentitlement.domain.User;
import com.citi.sayantan.report.userentitlement.repository.QueryDetailsRepository;
import com.citi.sayantan.report.userentitlement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueryDetailsRepository queryDetailsRepository;

    public User getUserDetails(String soeId) {

        return this.userRepository.findFirstBySoeId(soeId);
    }

    public User addUserDetails(User user) {
        return this.userRepository.save(user);
    }

    public List<QueryDetails> getAllQueryBySoeId(String soeid) {
        return this.queryDetailsRepository.findBySoeId(soeid);
    }

    public QueryDetails upsertQuery(QueryDetails queryDetails) {
        return this.queryDetailsRepository.save(queryDetails);
    }

    public List<String> getUserBlockedTables(String soeId) {
        User user = this.getUserDetails(soeId);
        return user.getBlockedTables();
    }
}

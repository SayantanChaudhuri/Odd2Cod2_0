package com.citi.sayantan.report.userentitlement.controller;

import com.citi.sayantan.report.userentitlement.domain.QueryDetails;
import com.citi.sayantan.report.userentitlement.domain.User;
import com.citi.sayantan.report.userentitlement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{soeId}")
    public User getUserDetails(@PathVariable("soeId") String soeId) {

        return userService.getUserDetails(soeId);
    }

    @PostMapping("/user")
    public User addUserDetails(@RequestBody User user) {
        return this.userService.addUserDetails(user);
    }

    @GetMapping("/user/{soeId}/queries")
    public List<QueryDetails> getAllQueryBySoeId(@PathVariable String soeId) {
       return this.userService.getAllQueryBySoeId(soeId);
    }

    @PostMapping("/user/query")
    public QueryDetails upsertQuery(@RequestBody QueryDetails queryDetails) {
       return this.userService.upsertQuery(queryDetails);
    }

    @GetMapping("/user/{soeId}/blockedtables")
    public List<String> getUserBlockedTables(@PathVariable String soeId) {
        return this.userService.getUserBlockedTables(soeId);
    }
}

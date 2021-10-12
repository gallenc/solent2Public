package org.solent.com504.project.model.user.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}

package com.pfe.service;


import com.pfe.dto.UserUpdateRequest;
import com.pfe.model.Role;
import com.pfe.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(Integer id, UserUpdateRequest request);

    User getById(Integer id);

    List<User> getAll();

    void delete(Integer id);

    List<User> findByRole(Role role);
}
package casrest.support.service;


import casrest.support.model.UUser;

public interface UserService {

  UUser findUserByEmail(String email);
}

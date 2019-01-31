package server.sso.cas.service;


import server.sso.cas.model.UUser;

public interface UserService {

  UUser login(String email, String psw);
  UUser findUserByEmail(String email);
}

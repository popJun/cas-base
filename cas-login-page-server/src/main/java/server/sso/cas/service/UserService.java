package server.sso.cas.service;


import server.sso.cas.model.UUser;

public interface UserService {

public UUser login(String email, String psw);
}

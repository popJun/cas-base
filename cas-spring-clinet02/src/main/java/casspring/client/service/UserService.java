package casspring.client.service;


import casspring.client.model.UUser;

public interface UserService {

  UUser login(String email, String psw);
}

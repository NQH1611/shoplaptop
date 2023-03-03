package vn.edu.stu.shop_laptop.service;

import vn.edu.stu.shop_laptop.model.Token;

public interface TokenService {

    Token createToken(Token token);

    Token findByToken(String token);
}

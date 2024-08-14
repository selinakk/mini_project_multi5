package com.example.mini_05_marketmulti.cart.model;

import java.util.List;

public interface CartDAO {
    public int insert(CartVO vo);
    public int update(CartVO vo);
    public int delete(CartVO vo);
    public List<CartVO> selectAll(String user_id);
}

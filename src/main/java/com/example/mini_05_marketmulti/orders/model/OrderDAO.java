package com.example.mini_05_marketmulti.orders.model;

import java.util.List;

public interface OrderDAO {
    public int insert(OrderVO vo);
    public int update(OrderVO vo);
    public int delete(OrderVO vo);
    public OrderVO selectOne(OrderVO vo);
    public List<OrderVO> selectAll(String user_id);
    public List<OrderVO> searchList(String searchKey, String searchWord);
}

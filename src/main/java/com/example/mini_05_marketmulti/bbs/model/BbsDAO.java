package com.example.mini_05_marketmulti.bbs.model;

import java.util.List;

public interface BbsDAO {
    public int insert(BbsVO vo);
    public int update(BbsVO vo);
    public int delete(BbsVO vo);
    public BbsVO selectOne(BbsVO vo);
    public List<BbsVO> selectAll();
    public List<BbsVO> searchList(String searchKey,String searchWord);
}

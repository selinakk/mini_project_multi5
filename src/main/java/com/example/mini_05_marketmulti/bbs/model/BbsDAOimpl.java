package com.example.mini_05_marketmulti.bbs.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BbsDAOimpl implements BbsDAO {
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BbsDAOimpl(){
        System.out.println("BbsDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(BbsVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into bbs(bbs_no, title, content, writer) values(seq_bbs.nextval,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getTitle());
            pstmt.setString(2,vo.getContent());
            pstmt.setString(3,vo.getWriter());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return flag;
    }

    @Override
    public int update(BbsVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "update bbs set title=?,content=?,writer=?,bdate=sysdate where bbs_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getTitle());
            pstmt.setString(2,vo.getContent());
            pstmt.setString(3,vo.getWriter());
            pstmt.setInt(4,vo.getBbs_no());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return flag;
    }

    @Override
    public int delete(BbsVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete from bbs where bbs_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getBbs_no());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return flag;
    }

    @Override
    public BbsVO selectOne(BbsVO vo) {
        System.out.println("selectOne()..");
        BbsVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from bbs where bbs_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getBbs_no());

            rs = pstmt.executeQuery();
            if(rs.next()){
                vo2 = new BbsVO();
                vo2.setBbs_no(rs.getInt("bbs_no"));
                vo2.setTitle(rs.getString("title"));
                vo2.setContent(rs.getString("content"));
                vo2.setWriter(rs.getString("writer"));
                vo2.setBdate(rs.getDate("bdate"));
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {if(rs!=null) {try {rs.close();}
        catch (SQLException e) {throw new RuntimeException(e);}}
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return vo2;
    }

    @Override
    public List<BbsVO> selectAll() {
        System.out.println("selectAll()..");
        List<BbsVO> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from bbs order by bbs_no desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BbsVO vo = new BbsVO();
                vo.setBbs_no(rs.getInt("bbs_no"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setWriter(rs.getString("writer"));
                vo.setBdate(rs.getDate("bdate"));
                list.add(vo);
            }
            System.out.println("전체 게시글 "+list.size()+"개");
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {if(rs!=null) {try {rs.close();}
        catch (SQLException e) {throw new RuntimeException(e);}}
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return list;
    }

    @Override
    public List<BbsVO> searchList(String searchKey, String searchWord) {
        System.out.println("searchList()..");
        List<BbsVO> vos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "";
            if(searchKey.equals("title")){
                sql = "select * from bbs where title like ? order by bdate desc";
            }else if(searchKey.equals("content")){
                sql = "select * from bbs where content like ? order by bdate desc";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");

            rs = pstmt.executeQuery();
            while(rs.next()){
                BbsVO vo = new BbsVO();
                vo.setBbs_no(rs.getInt("bbs_no"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setWriter(rs.getString("writer"));
                vo.setBdate(rs.getDate("bdate"));
                vos.add(vo);
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {if(rs!=null) {try {rs.close();}
        catch (SQLException e) {throw new RuntimeException(e);}}
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return vos;
    }
}

package com.example.mini_05_marketmulti.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOimpl implements MemberDAO{
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public MemberDAOimpl(){
        System.out.println("MemberDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(MemberVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into member(user_id,pw,name,tel,addr) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());
            pstmt.setString(2,vo.getPw());
            pstmt.setString(3,vo.getName());
            pstmt.setString(4,vo.getTel());
            pstmt.setString(5,vo.getAddr());

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
    public int update(MemberVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "update member set pw=?,name=?,tel=?,addr=? where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getPw());
            pstmt.setString(2,vo.getName());
            pstmt.setString(3,vo.getTel());
            pstmt.setString(4,vo.getAddr());
            pstmt.setString(5,vo.getUser_id());

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
    public int delete(MemberVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete from member where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());

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
    public MemberVO selectOne(MemberVO vo) {
        System.out.println("selectOne()..");
        MemberVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from member where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());

            rs = pstmt.executeQuery();
            if (rs.next()){
                vo2 = new MemberVO();
                vo2.setUser_id(rs.getString("user_id"));
                vo2.setPw(rs.getString("pw"));
                vo2.setName(rs.getString("name"));
                vo2.setTel(rs.getString("tel"));
                vo2.setAddr(rs.getString("addr"));
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
    public List<MemberVO> selectAll() {
        System.out.println("selectAll()..");
        List<MemberVO> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from member order by user_id asc";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while(rs.next()){
                MemberVO vo = new MemberVO();
                vo.setUser_id(rs.getString("user_id"));
                vo.setPw(rs.getString("pw"));
                vo.setName(rs.getString("name"));
                vo.setTel(rs.getString("tel"));
                vo.setAddr(rs.getString("addr"));
                list.add(vo);
            }
            System.out.println("전체 회원 "+list.size()+"명");
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
    public List<MemberVO> searchList(String searchKey, String searchWord) {
        System.out.println("searchList()..");
        List<MemberVO> list  = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");
            String sql = "";
            if(searchKey.equals("name")){
                sql = "select * from member where name like ? order by name asc";
            }else if(searchKey.equals("user_id")){
                sql = "select * from member where user_id like ? order by user_id asc";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");

            rs = pstmt.executeQuery();
            while(rs.next()){
                MemberVO vo = new MemberVO();
                vo.setUser_id(rs.getString("user_id"));
                vo.setPw(rs.getString("pw"));
                vo.setName(rs.getString("name"));
                vo.setTel(rs.getString("tel"));
                vo.setAddr(rs.getString("addr"));
                list.add(vo);
            }
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
    public MemberVO login(MemberVO vo) {
        System.out.println("login()..");
        MemberVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from member where user_id=? and pw=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());
            pstmt.setString(2,vo.getPw());

            rs = pstmt.executeQuery();
            while (rs.next()){
                vo2 = new MemberVO();
                vo2.setName(rs.getString("name"));
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
    public MemberVO checkId(MemberVO vo) {
        System.out.println("checkId()..");
        MemberVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from member where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());

            rs = pstmt.executeQuery();
            while (rs.next()){
                vo2 = new MemberVO();
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
}


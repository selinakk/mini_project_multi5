package com.example.mini_05_marketmulti.wish.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishDAOimpl implements WishDAO{
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public WishDAOimpl(){
        System.out.println("WishDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(WishVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into wish(wish_no, product_id, user_id) values(seq_wish.nextval,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getProduct_id());
            pstmt.setString(2,vo.getUser_id());

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
    public int delete(WishVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete wish where wish_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getWish_no());

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException(e);}
        finally {if(rs!=null) {try {rs.close();}
        catch (SQLException e) {throw new RuntimeException(e);}}
            if(pstmt!=null) {try {pstmt.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
            if(conn!=null) {try {conn.close();}
            catch (SQLException e) {throw new RuntimeException(e);}}
        }
        return flag;
    }
    @Override
    public List<WishVO> selectAll(String user_id) {
        System.out.println("searchList()..");
        List<WishVO> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select wish.*,product.thumbnail,product.product_name from wish join product on wish.product_id=product.product_id where wish.user_id=? order by product.product_name";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user_id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                WishVO vo = new WishVO();
                vo.setWish_no(rs.getInt("wish_no"));
                vo.setProduct_id(rs.getString("product_id"));
                vo.setUser_id(rs.getString("user_id"));
                vo.setThumbnail(rs.getString("thumbnail"));
                vo.setProduct_name(rs.getString("product_name"));
                list.add(vo);
            }
            System.out.println("찜에 총 "+list.size()+"개의 상품");
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
}

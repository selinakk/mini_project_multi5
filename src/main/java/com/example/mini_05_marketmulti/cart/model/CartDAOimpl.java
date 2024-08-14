package com.example.mini_05_marketmulti.cart.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOimpl implements CartDAO{
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public CartDAOimpl(){
        System.out.println("CartDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(CartVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into cart(cart_no, user_id, product_id, qty, price) values(seq_cart.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());
            pstmt.setString(2,vo.getProduct_id());
            pstmt.setInt(3,vo.getQty());
            pstmt.setInt(4,vo.getPrice());

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
    public int update(CartVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
        conn = DriverManager.getConnection(URL,USER,PW);
        System.out.println("DB connected.");

        String sql = "update cart set qty=? where cart_no=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,vo.getQty());
        pstmt.setInt(2,vo.getCart_no());

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
    public int delete(CartVO vo) {
        System.out.println("update()..");
        int flag = 0;

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete cart where cart_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getCart_no());

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
    public List<CartVO> selectAll(String user_id) {
        System.out.println("searchList()..");
        List<CartVO> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select cart.*,product.thumbnail,product.product_name from cart join product on cart.product_id=product.product_id where cart.user_id=? order by product.product_name";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user_id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                CartVO vo = new CartVO();
                vo.setCart_no(rs.getInt("cart_no"));
                vo.setProduct_id(rs.getString("product_id"));
                vo.setUser_id(rs.getString("user_id"));
                vo.setQty(rs.getInt("qty"));
                vo.setPrice(rs.getInt("price"));
                vo.setThumbnail(rs.getString("thumbnail"));
                vo.setProduct_name(rs.getString("product_name"));
                list.add(vo);
            }
            System.out.println("장바구니에 "+list.size()+"개의 상품");
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

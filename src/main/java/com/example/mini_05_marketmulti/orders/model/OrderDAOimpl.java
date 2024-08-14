package com.example.mini_05_marketmulti.orders.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOimpl implements OrderDAO {
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public OrderDAOimpl() {
        System.out.println("OrderDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(OrderVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into orders(order_no,user_id,product_id,shipping_addr,status,qty,price) values(TO_CHAR(current_timestamp, 'YYMMDDHH24MISS'),?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUser_id());
            pstmt.setString(2,vo.getProduct_id());
            pstmt.setString(3,vo.getShipping_addr());
            pstmt.setString(4,vo.getStatus());
            pstmt.setInt(5,vo.getQty());
            pstmt.setInt(6,vo.getPrice());
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
    public int update(OrderVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "update orders set status=? where order_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getStatus());
            pstmt.setLong(2,vo.getOrder_no());
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
    public int delete(OrderVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete from orders where order_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,vo.getOrder_no());

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
    public OrderVO selectOne(OrderVO vo) {
        System.out.println("selectOne()..");
        OrderVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select orders.*,product.product_name from orders join product on orders.product_id=product.product_id where orders.order_no=? order by order_no";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,vo.getOrder_no());
            rs = pstmt.executeQuery();
            if(rs.next()){
                vo2 = new OrderVO();
                vo2.setOrder_no(rs.getLong("order_no"));
                vo2.setUser_id(rs.getString("user_id"));
                vo2.setProduct_id(rs.getString("product_id"));
                vo2.setProduct_name(rs.getString("product_name"));
                vo2.setShipping_addr(rs.getString("shipping_addr"));
                vo2.setOdate(rs.getDate("odate"));
                vo2.setStatus(rs.getString("status"));
                vo2.setQty(rs.getInt("qty"));
                vo2.setPrice(rs.getInt("price"));
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
    public List<OrderVO> selectAll(String user_id) {
        System.out.println("selectAll()..");
        List<OrderVO> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select orders.*,product.product_name from orders join product on orders.product_id=product.product_id where orders.user_id=? order by order_no";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user_id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                OrderVO vo = new OrderVO();
                vo.setOrder_no(rs.getLong("order_no"));
                vo.setUser_id(rs.getString("user_id"));
                vo.setProduct_id(rs.getString("product_id"));
                vo.setProduct_name(rs.getString("product_name"));
                vo.setShipping_addr(rs.getString("shipping_addr"));
                vo.setOdate(rs.getDate("odate"));
                vo.setStatus(rs.getString("status"));
                vo.setQty(rs.getInt("qty"));
                vo.setPrice(rs.getInt("price"));
                list.add(vo);
            }
            System.out.println("주문내역 "+list.size()+"건");
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
    public List<OrderVO> searchList(String searchKey, String searchWord) {
        System.out.println("searchList()..");
        List<OrderVO> vos = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "";
            if(searchKey.equals("order_no")){
                sql = "select orders.*,product.product_name from orders join product on orders.product_id=product.product_id where order_no like ? order by order_no";
            } else if(searchKey.equals("user_id")){
                sql = "select orders.*,product.product_name from orders join product on orders.product_id=product.product_id where user_id like ? order by user_id";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");

            rs = pstmt.executeQuery();
            while(rs.next()){
                OrderVO vo = new OrderVO();
                vo.setOrder_no(rs.getLong("order_no"));
                vo.setUser_id(rs.getString("user_id"));
                vo.setProduct_id(rs.getString("product_id"));
                vo.setProduct_name(rs.getString("product_name"));
                vo.setShipping_addr(rs.getString("shipping_addr"));
                vo.setOdate(rs.getDate("odate"));
                vo.setStatus(rs.getString("status"));
                vo.setQty(rs.getInt("qty"));
                vo.setPrice(rs.getInt("price"));
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
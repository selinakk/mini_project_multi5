package com.example.mini_05_marketmulti.product.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOimpl implements ProductDAO{
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ProductDAOimpl(){
        System.out.println("ProductDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(ProductVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into product(product_id,product_name,mfr,thumbnail,price) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getProduct_id());
            pstmt.setString(2,vo.getProduct_name());
            pstmt.setString(3,vo.getMfr());
            pstmt.setString(4,vo.getThumbnail());
            pstmt.setInt(5,vo.getPrice());

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
    public int update(ProductVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "update product set product_name=?,mfr=?,thumbnail=?,price=? where product_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getProduct_name());
            pstmt.setString(2,vo.getMfr());
            pstmt.setString(3,vo.getThumbnail());
            pstmt.setInt(4,vo.getPrice());
            pstmt.setString(5,vo.getProduct_id());

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
    public int delete(ProductVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete from Product where product_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getProduct_id());

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
    public ProductVO selectOne(ProductVO vo) {
        System.out.println("selectOne()..");
        ProductVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from product where product_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getProduct_id());
            rs = pstmt.executeQuery();
            if (rs.next()){
                vo2 = new ProductVO();
                vo2.setProduct_id(rs.getString("product_id"));
                vo2.setProduct_name(rs.getString("product_name"));
                vo2.setMfr(rs.getString("mfr"));
                vo2.setThumbnail(rs.getString("thumbnail"));
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
    public List<ProductVO> selectAll() {
        System.out.println("selectAll()..");
        List<ProductVO> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from product order by product_id asc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ProductVO vo = new ProductVO();
                vo.setProduct_id(rs.getString("product_id"));
                vo.setProduct_name(rs.getString("product_name"));
                vo.setMfr(rs.getString("mfr"));
                vo.setThumbnail(rs.getString("thumbnail"));
                vo.setPrice(rs.getInt("price"));
                list.add(vo);
            }
            System.out.println("전체 상품 "+list.size()+"건");
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
    public List<ProductVO> searchList(String searchKey, String searchWord) {
        System.out.println("searchList()..");
        List<ProductVO> list  = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");
            String sql = "";
            if(searchKey.equals("product_name")){
                sql = "select * from product where product_name like ? order by product_name asc";
            }else if(searchKey.equals("mfr")){
                sql = "select * from product where mfr like ? order by mfr asc";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");

            rs = pstmt.executeQuery();
            while(rs.next()){
                ProductVO vo = new ProductVO();
                vo.setProduct_id(rs.getString("product_id"));
                vo.setProduct_name(rs.getString("product_name"));
                vo.setMfr(rs.getString("mfr"));
                vo.setThumbnail(rs.getString("thumbnail"));
                vo.setPrice(rs.getInt("price"));
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
}
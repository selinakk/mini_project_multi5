package com.example.mini_05_marketmulti.review.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOimpl implements ReviewDAO{
    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "mini5";
    private static final String PW = "minipw";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ReviewDAOimpl() {
        System.out.println("ReviewDAOimpl()..");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        try {
            Class.forName(DRIVER_NAME);
            //System.out.println("Driver successful...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(ReviewVO vo) {
        System.out.println("insert()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "insert into review(review_no,product_id,user_id,content,rating) values(seq_review.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getProduct_id());
            pstmt.setString(2,vo.getuser_id());
            pstmt.setString(3,vo.getContent());
            pstmt.setInt(4,vo.getRating());

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
    public int update(ReviewVO vo) {
        System.out.println("update()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "update review set content=?,rdate=sysdate, rating=? where review_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getContent());
            pstmt.setInt(2,vo.getRating());
            pstmt.setInt(3,vo.getReview_no());

            flag = pstmt.executeUpdate();
            System.out.println("flag: "+flag);

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
    public int delete(ReviewVO vo) {
        System.out.println("delete()..");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "delete from review where review_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getReview_no());

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
    public ReviewVO selectOne(ReviewVO vo) {
        System.out.println("selectOne()..");
        ReviewVO vo2 = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from review where review_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vo.getReview_no());

            rs = pstmt.executeQuery();
            while(rs.next()){
                vo2 = new ReviewVO();
                vo2.setReview_no(rs.getInt("review_no"));
                vo2.setProduct_id(rs.getString("product_id"));
                vo2.setuser_id(rs.getString("user_id"));
                vo2.setContent(rs.getString("content"));
                vo2.setRdate(rs.getDate("rdate"));
                vo2.setRating(rs.getInt("rating"));
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
    public List<ReviewVO> selectAll(String product_id) {
        System.out.println("selectAll().. 상품 "+product_id+"의 리뷰");
        List<ReviewVO> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL,USER,PW);
            System.out.println("DB connected.");

            String sql = "select * from review where product_id=? order by review_no";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product_id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                ReviewVO vo = new ReviewVO();
                vo.setReview_no(rs.getInt("review_no"));
                vo.setProduct_id(rs.getString("product_id"));
                vo.setuser_id(rs.getString("user_id"));
                vo.setContent(rs.getString("content"));
                vo.setRdate(rs.getDate("rdate"));
                vo.setRating(rs.getInt("rating"));
                list.add(vo);
            }
            System.out.println(product_id+"의 리뷰: "+list.size()+"건");
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

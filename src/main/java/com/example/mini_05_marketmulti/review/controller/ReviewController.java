package com.example.mini_05_marketmulti.review.controller;

import java.io.*;

import com.example.mini_05_marketmulti.review.model.ReviewDAO;
import com.example.mini_05_marketmulti.review.model.ReviewDAOimpl;
import com.example.mini_05_marketmulti.review.model.ReviewVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/r_insert.do","/r_insertOK.do","/r_update.do","/r_updateOK.do","/r_deleteOK.do","/r_view.do"})
public class ReviewController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        ReviewDAO dao = new ReviewDAOimpl();
        if (sPath.equals("/r_insert.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("review/insert.jsp");
            rd.forward(request,response);
        } if (sPath.equals("/r_insertOK.do")) {
            String product_id = request.getParameter("product_id");
            String user_id = request.getParameter("user_id");
            String content = request.getParameter("content");
            String rating = request.getParameter("rating");
            System.out.println("상품코드: "+product_id+" 회원아이디: "+user_id+" 내용: "+content+" 별점: "+rating);

            ReviewVO vo = new ReviewVO();
            vo.setProduct_id(product_id);
            vo.setuser_id(user_id);
            vo.setContent(content);
            vo.setRating(Integer.parseInt(rating));
            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted..");
                response.sendRedirect("p_view.do?product_id="+product_id);
            }else{
                System.out.println("insert failed..");
            }
        } else if (sPath.equals("/r_update.do")) {
            String review_no = request.getParameter("review_no");
            System.out.println("리뷰번호: "+review_no);

            ReviewVO vo = new ReviewVO();
            vo.setReview_no(Integer.parseInt(review_no));
            ReviewVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("review/update.jsp");
            rd.forward(request,response);
        }  else if (sPath.equals("/r_updateOK.do")) {
            String review_no = request.getParameter("review_no");
            String product_id = request.getParameter("product_id");
            String user_id = request.getParameter("user_id");
            String content = request.getParameter("content");
            String rating = request.getParameter("rating");
            System.out.println("리뷰번호: "+review_no+" 상품코드: "+product_id+" 회원아이디: "+user_id+" 별점: "+rating+" 내용: "+content);

            ReviewVO vo = new ReviewVO();
            vo.setReview_no(Integer.parseInt(review_no));
            vo.setProduct_id(product_id);
            vo.setuser_id(user_id);
            vo.setContent(content);
            vo.setRating(Integer.parseInt(rating));

            int result = dao.update(vo);
            if(result == 1){
                System.out.println("updated...");
                response.sendRedirect("r_view.do?review_no="+review_no);
            }else{
                System.out.println("update failed...");
                response.sendRedirect("r_update.do?review_no="+review_no);
            }
        }  else if (sPath.equals("/r_deleteOK.do")) {
            String review_no = request.getParameter("review_no");
            System.out.println(review_no);
            String product_id = request.getParameter("product_id");
            System.out.println(product_id);

            ReviewVO vo = new ReviewVO();
            vo.setReview_no(Integer.parseInt(review_no));

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("p_view.do?product_id="+product_id);
            } else{
                System.out.println("delete failed...");
                response.sendRedirect("r_delete.do?review_no="+review_no);
            }
        }  else if (sPath.equals("/r_view.do")) {
            String review_no = request.getParameter("review_no");
            System.out.println(review_no);

            ReviewVO vo = new ReviewVO();
            vo.setReview_no(Integer.parseInt(review_no));
            ReviewVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("review/selectOne.jsp");
            rd.forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
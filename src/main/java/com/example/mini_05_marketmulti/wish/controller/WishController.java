package com.example.mini_05_marketmulti.wish.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.wish.model.WishDAO;
import com.example.mini_05_marketmulti.wish.model.WishDAOimpl;
import com.example.mini_05_marketmulti.wish.model.WishVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/w_insertOK.do","/w_deleteOK.do","/w_list.do"})
public class WishController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        WishDAO dao = new WishDAOimpl();

        if (sPath.equals("/w_insertOK.do")) {
            String user_id = request.getParameter("user_id");
            String product_id = request.getParameter("product_id");
            System.out.println("회원 아이디: "+user_id+" 상품코드: "+product_id);

            WishVO vo = new WishVO();
            vo.setUser_id(user_id);
            vo.setProduct_id(product_id);

            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted..");
                response.sendRedirect("w_list.do?user_id="+user_id);
            }else{
                System.out.println("insert failed..");
            }
        }else if (sPath.equals("/w_deleteOK.do")) {
            String wish_no = request.getParameter("wish_no");
            String product_id = request.getParameter("product_id");
            HttpSession session = request.getSession();
            String user_id = (String)session.getAttribute("user_id");
            System.out.println("찜 번호: "+wish_no+" 상품코드: "+product_id+" 회원아이디: "+user_id);

            WishVO vo = new WishVO();
            vo.setWish_no(Integer.parseInt(wish_no));

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("w_list.do?user_id="+user_id);
            } else{
                System.out.println("delete failed...");
                response.sendRedirect("w_delete.do?wish_no="+wish_no);
            }
        } else if (sPath.equals("/w_list.do")) {
            String user_id = request.getParameter("user_id");
            List<WishVO> list = dao.selectAll(user_id);
            System.out.println(user_id+"님의 찜 목록");

            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("wish/selectAll.jsp");
            rd.forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
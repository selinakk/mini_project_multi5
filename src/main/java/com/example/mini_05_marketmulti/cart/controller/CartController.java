package com.example.mini_05_marketmulti.cart.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.cart.model.CartDAO;
import com.example.mini_05_marketmulti.cart.model.CartDAOimpl;
import com.example.mini_05_marketmulti.cart.model.CartVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/c_insert.do","/c_insertOK.do","/c_update.do","/c_updateOK.do","/c_delete.do","/c_deleteOK.do","/c_list.do"})
public class CartController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        CartDAO dao = new CartDAOimpl();
        if(sPath.equals("/c_insert.do")){
            RequestDispatcher rd = request.getRequestDispatcher("cart/insert.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/c_insertOK.do")) {
            String user_id = request.getParameter("user_id");
            String product_id = request.getParameter("product_id");
            String qty = request.getParameter("qty");
            String price = request.getParameter("price");
            System.out.println("회원아이디: "+user_id+" 상품코드: "+product_id+" 수량: "+qty+"개 가격: "+price);

            CartVO vo = new CartVO();
            vo.setUser_id(user_id);
            vo.setProduct_id(product_id);
            vo.setQty(Integer.parseInt(qty));
            vo.setPrice(Integer.parseInt(price));

            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted..");
                response.sendRedirect("c_list.do?user_id="+user_id);
            }else{
                System.out.println("insert failed..");
                response.sendRedirect("c_insert.do?product_id"+product_id);
            }
        } else if (sPath.equals("/c_deleteOK.do")) {
            String cart_no = request.getParameter("cart_no");
            HttpSession session = request.getSession();
            String user_id = (String)session.getAttribute("user_id");
            System.out.println(user_id+"님의 장바구니 "+cart_no+"번");

            CartVO vo = new CartVO();
            vo.setCart_no(Integer.parseInt(cart_no));

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("c_list.do?user_id="+user_id);
            }else{
                System.out.println("delete failed...");
                response.sendRedirect("c_list.do?user_id="+user_id);
            }
        } else if (sPath.equals("/c_list.do")) {
            String user_id = request.getParameter("user_id");
            System.out.println(user_id+"님의 장바구니");

            List<CartVO> list = dao.selectAll(user_id);
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("cart/selectAll.jsp");
            rd.forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
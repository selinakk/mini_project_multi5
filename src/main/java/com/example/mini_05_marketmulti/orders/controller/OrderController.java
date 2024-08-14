package com.example.mini_05_marketmulti.orders.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.orders.model.OrderDAO;
import com.example.mini_05_marketmulti.orders.model.OrderDAOimpl;
import com.example.mini_05_marketmulti.orders.model.OrderVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/o_insert.do", "/o_insertOK.do", "/o_update.do", "/o_updateOK.do", "/o_delete.do", "/o_deleteOK.do", "/o_view.do", "/o_list.do", "/o_searchList.do"})
public class OrderController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: " + sPath);
        OrderDAO dao = new OrderDAOimpl();

        if (sPath.equals("/o_insert.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("orders/insert.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_insertOK.do")) {
            String user_id = request.getParameter("user_id");
            String product_id = request.getParameter("product_id");
            String shipping_addr = request.getParameter("shipping_addr");
            String status = request.getParameter("status");
            String qty = request.getParameter("qty");
            String price = request.getParameter("price");
            System.out.println("["+product_id+"]"+"상품 ("+price+"원) "+qty+"개 "+status);
            System.out.println("배송지: "+shipping_addr);

            OrderVO vo = new OrderVO();
            vo.setUser_id(user_id);
            vo.setProduct_id(product_id);
            vo.setShipping_addr(shipping_addr);
            vo.setStatus(status);
            vo.setQty(Integer.parseInt(qty));
            vo.setPrice(Integer.parseInt(price));

            int result = dao.insert(vo);
            if (result == 1) {
                System.out.println("inserted..");
                response.sendRedirect("o_list.do?user_id=" + user_id);
            } else {
                System.out.println("insert failed..");
                response.sendRedirect("o_insert.do?product_id=" + product_id);
            }
        } else if (sPath.equals("/o_update.do")) {
            String order_no = request.getParameter("order_no");
            System.out.println("주문번호: "+order_no);

            OrderVO vo = new OrderVO();
            vo.setOrder_no(Long.parseLong(order_no));
            OrderVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2", vo2);

            RequestDispatcher rd = request.getRequestDispatcher("orders/update.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_updateOK.do")) {
            String order_no = request.getParameter("order_no");
            String status = request.getParameter("status");
            System.out.println("주문번호: "+order_no+" 주문상태: "+status);

            OrderVO vo = new OrderVO();
            vo.setOrder_no(Long.parseLong(order_no));
            vo.setStatus(status);
            int result = dao.update(vo);
            if (result == 1) {
                System.out.println("updated...");
                response.sendRedirect("o_view.do?order_no=" + order_no);
            } else {
                System.out.println("update failed...");
                response.sendRedirect("o_update.do?order_no=" + order_no);
            }
        } else if (sPath.equals("/o_delete.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("orders/delete.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_deleteOK.do")) {
            String order_no = request.getParameter("order_no");
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            System.out.println(user_id + "님의 주문[" + order_no + "]내역");

            OrderVO vo = new OrderVO();
            vo.setOrder_no(Long.parseLong(order_no));
            int result = dao.delete(vo);
            if (result == 1) {
                System.out.println("deleted...");
                response.sendRedirect("o_list.do?user_id=" + user_id);
            } else {
                System.out.println("delete failed...");
                response.sendRedirect("o_delete.do?order_no=" + order_no);
            }
        } else if (sPath.equals("/o_view.do")) {
            String order_no = request.getParameter("order_no");
            System.out.println("주문번호: "+order_no);

            OrderVO vo = new OrderVO();
            vo.setOrder_no(Long.parseLong(order_no));
            OrderVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2", vo2);

            RequestDispatcher rd = request.getRequestDispatcher("orders/selectOne.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_list.do")) {
            String user_id = request.getParameter("user_id");
            System.out.println(user_id + "님의 주문내역");

            List<OrderVO> list = dao.selectAll(user_id);
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("orders/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_searchList.do")) {
            String searchKey = request.getParameter("searchKey");
            String searchWord = request.getParameter("searchWord");
            System.out.println(searchKey+"에서 "+searchWord+" 검색");

            List<OrderVO> list = dao.searchList(searchKey, searchWord);
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("orders/selectAll.jsp");
            rd.forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
    public void destroy() {}
}
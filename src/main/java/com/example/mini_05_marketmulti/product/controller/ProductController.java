package com.example.mini_05_marketmulti.product.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.product.model.ProductDAO;
import com.example.mini_05_marketmulti.product.model.ProductDAOimpl;
import com.example.mini_05_marketmulti.product.model.ProductVO;
import com.example.mini_05_marketmulti.review.model.ReviewDAO;
import com.example.mini_05_marketmulti.review.model.ReviewDAOimpl;
import com.example.mini_05_marketmulti.review.model.ReviewVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/p_insert.do","/p_insertOK.do","/p_update.do","/p_updateOK.do","/p_delete.do","/p_deleteOK.do","/p_view.do","/p_list.do","/p_searchList.do"})
public class ProductController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        ProductDAO dao = new ProductDAOimpl();
        ReviewDAO rdao = new ReviewDAOimpl();

        if(sPath.equals("/p_insert.do")){
            RequestDispatcher rd = request.getRequestDispatcher("product/insert.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/p_insertOK.do")) {
            String product_id = request.getParameter("product_id");
            String product_name = request.getParameter("product_name");
            String mfr = request.getParameter("mfr");
            String thumbnail = request.getParameter("thumbnail");
            String price = request.getParameter("price");
            System.out.println("상품코드: "+product_id+" 상품명: "+product_name+" 제조사: "+mfr+" 이미지경로: "+thumbnail+" 가격: "+price);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(product_id);
            vo.setProduct_name(product_name);
            vo.setMfr(mfr);
            vo.setThumbnail(thumbnail);
            vo.setPrice(Integer.parseInt(price));

            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted...");
                response.sendRedirect("p_list.do");
            }else{
                System.out.println("insert failed...");
                response.sendRedirect("p_insert.do");
            }
        } else if (sPath.equals("/p_update.do")) {
            String product_id = request.getParameter("product_id");
            System.out.println(product_id);
            
            ProductVO vo = new ProductVO();
            vo.setProduct_id(product_id);
            ProductVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);
            
            RequestDispatcher rd = request.getRequestDispatcher("product/update.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/p_updateOK.do")) {
            String product_id = request.getParameter("product_id");
            String product_name = request.getParameter("product_name");
            String mfr = request.getParameter("mfr");
            String thumbnail = request.getParameter("thumbnail");
            String price = request.getParameter("price");
            System.out.println("상품코드: "+product_id+" 상품명: "+product_name+" 제조사: "+mfr+" 이미지경로: "+thumbnail+" 가격: "+price);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(product_id);
            vo.setProduct_name(product_name);
            vo.setMfr(mfr);
            vo.setThumbnail(thumbnail);
            vo.setPrice(Integer.parseInt(price));
            
            int result = dao.update(vo);
            if(result == 1){
                System.out.println("updated...");
                response.sendRedirect("p_view.do?product_id="+product_id);
            }else{
                System.out.println("update failed...");
                response.sendRedirect("p_update.do?product_id="+product_id);
            }
        } else if (sPath.equals("/p_delete.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("product/delete.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/p_deleteOK.do")) {
            String product_id = request.getParameter("product_id");
            System.out.println("상품코드: "+product_id);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(product_id);

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("p_list.do");
            } else{
                System.out.println("delete failed...");
                response.sendRedirect("p_delete.do?product_id="+product_id);
            }
        } else if (sPath.equals("/p_view.do")) {
            String product_id = request.getParameter("product_id");
            System.out.println("상품코드: "+product_id);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(product_id);
            ProductVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            List<ReviewVO> rlist = rdao.selectAll(product_id);
            request.setAttribute("rlist",rlist);
            
            RequestDispatcher rd = request.getRequestDispatcher("product/selectOne.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/p_list.do")) {
            List<ProductVO> list = dao.selectAll();
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/p_searchList.do")) {
            String searchKey = request.getParameter("searchKey");
            String searchWord = request.getParameter("searchWord");
            System.out.println(searchKey+"에서 "+searchWord+" 검색");

            List<ProductVO> list = dao.searchList(searchKey,searchWord);
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
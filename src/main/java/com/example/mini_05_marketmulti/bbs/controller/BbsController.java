package com.example.mini_05_marketmulti.bbs.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.bbs.model.BbsDAO;
import com.example.mini_05_marketmulti.bbs.model.BbsDAOimpl;
import com.example.mini_05_marketmulti.bbs.model.BbsVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/b_insert.do","/b_insertOK.do","/b_update.do","/b_updateOK.do","/b_delete.do","/b_deleteOK.do","/b_view.do","/b_list.do","/b_searchList.do"})
public class BbsController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        BbsDAO dao = new BbsDAOimpl();

        if(sPath.equals("/b_insert.do")){
            RequestDispatcher rd = request.getRequestDispatcher("bbs/insert.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/b_insertOK.do")) {
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");
            System.out.println("글 제목: "+title+" 작성자: "+writer);
            System.out.println("내용: "+content);

            BbsVO vo = new BbsVO();
            vo.setTitle(title);
            vo.setContent(content);
            vo.setWriter(writer);

            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted..");
                response.sendRedirect("b_list.do");
            }else{
                System.out.println("insert failed..");
                response.sendRedirect("b_insert.do");
            }
        } else if (sPath.equals("/b_update.do")) {
            String bbs_no = request.getParameter("bbs_no");
            System.out.println(bbs_no+"번 게시글 보기");

            BbsVO vo = new BbsVO();
            vo.setBbs_no(Integer.parseInt(bbs_no));
            BbsVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("bbs/update.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/b_updateOK.do")) {
            String bbs_no = request.getParameter("bbs_no");
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");
            System.out.println("글번호: "+bbs_no+" 글 제목: "+title+" 작성자: "+writer);
            System.out.println("내용: "+content);

            BbsVO vo = new BbsVO();
            vo.setBbs_no(Integer.parseInt(bbs_no));
            vo.setTitle(title);
            vo.setContent(content);
            vo.setWriter(writer);

            int result = dao.update(vo);
            if(result == 1){
                System.out.println("updated...");
                response.sendRedirect("b_view.do?bbs_no="+bbs_no);
            }else{
                System.out.println("update failed...");
                response.sendRedirect("b_update.do?bbs_no="+bbs_no);
            }
        } else if (sPath.equals("/b_delete.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("bbs/delete.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/b_deleteOK.do")) {
            String bbs_no = request.getParameter("bbs_no");
            System.out.println("글번호: "+bbs_no);

            BbsVO vo = new BbsVO();
            vo.setBbs_no(Integer.parseInt(bbs_no));

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("b_list.do");
            }else{
                System.out.println("delete failed...");
                response.sendRedirect("b_delete.do?bbs_no="+bbs_no);
            }
        } else if (sPath.equals("/b_view.do")) {
            int bbs_no = Integer.parseInt(request.getParameter("bbs_no"));
            System.out.println(bbs_no+"번 게시글 보기");

            BbsVO vo = new BbsVO();
            vo.setBbs_no(bbs_no);
            BbsVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("bbs/selectOne.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/b_list.do")) {
            List<BbsVO> list = dao.selectAll();
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("bbs/selectAll.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/b_searchList.do")) {
            String searchKey = request.getParameter("searchKey");
            String searchWord = request.getParameter("searchWord");
            System.out.println(searchKey+"에서 "+searchWord+" 검색");

            List<BbsVO> list = dao.searchList(searchKey,searchWord);
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("bbs/selectAll.jsp");
            rd.forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
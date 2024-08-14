package com.example.mini_05_marketmulti.member.controller;

import java.io.*;
import java.util.List;

import com.example.mini_05_marketmulti.member.model.MemberDAO;
import com.example.mini_05_marketmulti.member.model.MemberDAOimpl;
import com.example.mini_05_marketmulti.member.model.MemberVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/m_insert.do","/m_insertOK.do","/m_update.do","/m_updateOK.do","/m_delete.do","/m_deleteOK.do","/m_view.do","/m_list.do","/m_searchList.do","/login.do","/loginOK.do","/logout.do"})
public class MemberController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: "+sPath);
        MemberDAO dao = new MemberDAOimpl();

        if(sPath.equals("/m_insert.do")){
            RequestDispatcher rd = request.getRequestDispatcher("member/insert.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/m_insertOK.do")) {
            String user_id = request.getParameter("user_id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String addr = request.getParameter("addr");
            System.out.println("회원 아이디: "+user_id+" 회원 비밀번호: "+pw+" 이름: "+name+" 전화번호: "+tel+" 주소: "+addr);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            vo.setPw(pw);
            vo.setName(name);
            vo.setTel(tel);
            vo.setAddr(addr);

            int result = dao.insert(vo);
            if(result == 1){
                System.out.println("inserted...");
                response.sendRedirect("m_list.do");
            }else{
                System.out.println("insert failed...");
                response.sendRedirect("m_insert.do");
            }
        } else if (sPath.equals("/m_update.do")) {
            String user_id = request.getParameter("user_id");
            System.out.println("회원 아이디: "+user_id);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            MemberVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("member/update.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/m_updateOK.do")) {
            String user_id = request.getParameter("user_id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String addr = request.getParameter("addr");
            System.out.println("회원 아이디: "+user_id+" 회원 비밀번호: "+pw+" 이름:"+name+" 전화번호: "+tel+" 주소: "+addr);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            vo.setPw(pw);
            vo.setName(name);
            vo.setTel(tel);
            vo.setAddr(addr);
            int result = dao.update(vo);
            if(result == 1){
                System.out.println("updated...");
                response.sendRedirect("m_view.do?user_id="+user_id);
            }else{
                System.out.println("update failed...");
                response.sendRedirect("m_update.do?user_id="+user_id);
            }
        } else if (sPath.equals("/m_delete.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("member/delete.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/m_deleteOK.do")) {
            String user_id = request.getParameter("user_id");
            System.out.println("회원 아이디: "+user_id);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);

            int result = dao.delete(vo);
            if(result == 1){
                System.out.println("deleted...");
                response.sendRedirect("m_list.do");
            }else{
                System.out.println("delete failed...");
                response.sendRedirect("m_delete.do?user_id="+user_id);
            }
        } else if (sPath.equals("/m_view.do")) {
            String user_id = request.getParameter("user_id");
            System.out.println("회원 아이디: "+user_id);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            MemberVO vo2 = dao.selectOne(vo);
            request.setAttribute("vo2",vo2);

            RequestDispatcher rd = request.getRequestDispatcher("member/selectOne.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/m_list.do")) {
            List<MemberVO> list = dao.selectAll();
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("member/selectAll.jsp");
            rd.forward(request,response);
        } else if (sPath.equals("/m_searchList.do")) {
            String searchKey = request.getParameter("searchKey");
            String searchWord = request.getParameter("searchWord");
            System.out.println(searchKey+"에서 "+searchWord+" 검색");

            List<MemberVO> list = dao.searchList(searchKey,searchWord);
            request.setAttribute("list",list);

            RequestDispatcher rd = request.getRequestDispatcher("member/selectAll.jsp");
            rd.forward(request,response);
        } else if(sPath.equals("/login.do")){
            RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
            rd.forward(request,response);
        }else if(sPath.equals("/loginOK.do")){
            String user_id = request.getParameter("user_id");
            String pw = request.getParameter("pw");
            System.out.println("회원 아이디: "+user_id+" 회원 비밀번호: "+pw);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            vo.setPw(pw);
            MemberVO vo2 = dao.login(vo);

            if(vo2 != null){
                HttpSession session = request.getSession();
                session.setAttribute("user_id",user_id);
                session.setAttribute("name",vo2.getName());
                session.setMaxInactiveInterval(60*60);
                response.sendRedirect("p_list.do");
            }else{
                response.sendRedirect("login.do");
            }
        }else if(sPath.equals("/logout.do")){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("home.do");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {}
}
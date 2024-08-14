package com.example.mini_05_marketmulti.member.controller;

import com.example.mini_05_marketmulti.member.model.MemberDAO;
import com.example.mini_05_marketmulti.member.model.MemberDAOimpl;
import com.example.mini_05_marketmulti.member.model.MemberVO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/m_checkId.do")
public class MemberRestController extends HttpServlet {
    MemberDAO dao = new MemberDAOimpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        String sPath = request.getServletPath();
        System.out.println("현재 서블릿패스: " + sPath);

        if(sPath.equals("/m_checkId.do")){
            String user_id = request.getParameter("user_id");
            System.out.println("회원 아이디: "+user_id);

            MemberVO vo = new MemberVO();
            vo.setUser_id(user_id);
            MemberVO vo2 = dao.checkId(vo);
            Map<String, String> map = new HashMap<>();

            if(vo2 == null){
                map.put("result","none");
            } else{
                map.put("result","found");
            }
            PrintWriter out = response.getWriter();
            out.println(new Gson().toJson(map));
        }
    }
    public void destroy() {}
}

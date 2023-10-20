package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        System.out.println("imowkring");
        System.out.println(session.getAttributeNames());

        resp.sendRedirect("/");
    }
}

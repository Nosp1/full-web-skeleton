package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserModel;
import tools.repository.UserRepository;

@WebServlet(name = "ValidatePost", urlPatterns = { "/ValidatePost" })
public class ValidatePost extends AbstractAppServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        writeResponse(request, response, "test");

    }

    @Override
    protected void writeBody(HttpServletRequest request, PrintWriter out) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        System.out.println(firstName + lastName + email + password + dob);

        UserModel insertNewUser = new UserModel(firstName, lastName, email, password, dob);
         UserRepository.addUser(insertNewUser, out);

        out.format("<h1> %s has been added to the database with id: %s</h1>",email);

    }
}

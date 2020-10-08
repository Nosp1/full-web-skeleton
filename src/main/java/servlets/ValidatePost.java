package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Gender;
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
        String password =request.getParameter("password");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        //check gender
        Gender gen = (gender.matches("MALE")) ?  Gender.MALE : Gender.FEMALE;
        System.out.println(gen);

        System.out.println(firstName + lastName + email + password + dob);

        UserModel insertNewUser = new UserModel(firstName, lastName, email, password, dob,gen);
       Integer id =  UserRepository.addUserJdbc(insertNewUser, out);
        out.print("<a href='RequestUserNameFromDb'>Hent en bruker</a>");
        out.format("<h1> %s has been added to the database with %d</h1>",email, id);


    }
}

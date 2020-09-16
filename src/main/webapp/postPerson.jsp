<%--
  Created by IntelliJ IDEA.
  User: tryme
  Date: 16/09/2020
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="ValidatePost" method="post">
    <h1>Register</h1>
    <div>
        <label for="firstName">First name</label>
        <input id="firstName" type="text" name="firstName" placeholder="John" required>
    </div>

    <div>
        <label for="lastName">Last name</label>
        <input id="lastName" type="text" name="lastName" placeholder="Smith" required>
    </div>

    <div>
        <label for="email">Email</label>
        <input id="email" type="email" name="email" required placeholder="JohnS@example.com">
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" name="password" placeholder="password; minimum 8 characters" pattern=".{8,0}"
               id="password"
               required title="must atleast contain 8 symbols">
    </div>

    <div>
        <label for="dob">Date Of birth</label>
        <input id="dob" type="date" name="dob" required>
    </div>

    <div>
        <label for="terms">I agree the Terms of Use
            <input type="checkbox" name="checkbox" id="terms" required>
        </label>
    </div>
    <div>
        <input type="submit" name="action" value="Register">
    </div>
</form>

</body>
</html>

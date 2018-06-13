<form method="post" action="/signin" modelAttribute="customer">
    <label>Email</label>
    <input type="email" name="emailAddress" placeholder="john@test.com"/>
    <button type="submit">Sign In</button><br>
    ${(errorMessage)!""}

</form>
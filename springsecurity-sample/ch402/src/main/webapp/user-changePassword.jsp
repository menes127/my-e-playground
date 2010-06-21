<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@include file="/includes/meta.jsp"%>
    <title>create</title>
    <script type="text/javascript">
$(document).ready(function() {
    $("#userForm").validate();
});
    </script>
  </head>
  <body>
    <%@include file="/includes/header.jsp"%>
    <%@include file="/includes/error.jsp"%>
    <fieldset>
      <legend>Change User's Password</legend>
      <form id="userForm" method="post" action="user.do?action=changePassword">
        <table>
          <tr>
            <td><label for="oldPassword">Old Password:</label></td>
            <td><input id="oldPassword" type="password" name="oldPassword" value="" class="required" minlength="1"></td>
          </tr>
          <tr>
            <td><label for="newPassword">New Password:</label></td>
            <td><input id="newPassword" type="password" name="newPassword" value="" class="required" minlength="1"></td>
          </tr>
          <tr>
            <td><label for="confirmPassword">Confirm Password:</label></td>
            <td><input id="confirmPassword" type="password" name="confirmPassword" value="" class="required" equalTo="#newPassword"></td>
          </tr>
          <tr>
            <td colspan="2">
              <input type="submit">
              <input type="reset">
            </td>
          </tr>
        </table>
      </form>
    </fieldset>
  </body>
</html>
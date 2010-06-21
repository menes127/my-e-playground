<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@include file="/includes/meta.jsp"%>
    <title>create</title>
    <script type="text/javascript">
$(document).ready(function() {
    $("#groupForm").validate();
});
    </script>
  </head>
  <body>
    <%@include file="/includes/header-group.jsp"%>
    <%@include file="/includes/error.jsp"%>
    <fieldset>
      <legend>Create Group</legend>
      <form id="userForm" method="post" action="group.do?action=save">
        <table>
          <tr>
            <td><label for="username">Group Name:</label></td>
            <td><input id="username" type="text" name="name" value="" class="required"></td>
          </tr>
          <tr>
            <td><label for="authority">Authorities:</label></td>
            <td><input id="authority" type="text" name="authority" value=""></td>
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
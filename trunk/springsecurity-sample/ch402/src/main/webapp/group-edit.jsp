<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@ include file="/includes/meta.jsp"%>
    <title>edit</title>
  </head>
  <body>
    <%@include file="/includes/header-group.jsp"%>
    <%@include file="/includes/error.jsp"%>
    <fieldset>
      <legend>Edit Group</legend>
      <form id="userForm" method="post" action="group.do?action=update">
        <input type="hidden" name="oldName" value="${group.name}">
        <table>
          <tr>
            <td><label for="name">Group Name:</label></td>
            <td><input id="name" type="text" name="name" value="${group.name}">&nbsp;</td>
          </tr>
          <tr>
            <td><label for="member">Members:</label></td>
            <td><input id="member" type="text" name="member" value="${group.member}"></td>
          </tr>
          <tr>
            <td><label for="authority">Authorities:</label></td>
            <td><input id="authority" type="text" name="authority" value="${group.authority}"></td>
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
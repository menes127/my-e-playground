<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@ include file="/includes/meta.jsp"%>
    <title>view</title>
  </head>
  <body>
    <%@include file="/includes/header.jsp"%>
    <fieldset>
      <legend>User Info</legend>
      <table>
        <tr>
          <td>Username:</td>
          <td>${user.username}</td>
        </tr>
        <tr>
          <td>Password:</td>
          <td>[PROTECTED]</td>
        </tr>
        <tr>
          <td>Enabled:</td>
          <td>${user.enabled}</td>
        </tr>
        <tr>
          <td>Authorities:</td>
          <td>${user.authority}</td>
        </tr>
      </table>
    </fieldset>
  </body>
</html>
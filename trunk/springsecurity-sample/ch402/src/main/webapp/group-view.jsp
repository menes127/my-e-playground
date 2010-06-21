<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@ include file="/includes/meta.jsp"%>
    <title>view</title>
  </head>
  <body>
    <%@include file="/includes/header-group.jsp"%>
    <fieldset>
      <legend>Group Info</legend>
      <table>
        <tr>
          <td>Group Name:</td>
          <td>${group.name}</td>
        </tr>
        <tr>
          <td>Members:</td>
          <td>${group.member}</td>
        </tr>
        <tr>
          <td>Authorities:</td>
          <td>${group.authority}</td>
        </tr>
      </table>
    </fieldset>
  </body>
</html>
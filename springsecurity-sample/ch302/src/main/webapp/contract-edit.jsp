<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>edit</title>
  </head>
  <body>
    <table width="100%">
      <tr>
        <td><a href="message.do?action=list">Back</a></td>
        <td align="right">
          username: <sec:authentication property="name"/>
          |
          <a href="j_spring_security_logout">logout</a>
        </td>
      </tr>
    </table>
    <hr>
    <fieldset>
      <legend>Contract Info</legend>
      <form method="post" action="contract.do?action=${param.action == 'create' ? 'save' : 'update'}">
        <input type="hidden" name="id" value="${contract.id}">
        <table>
          <tr>
            <td>Content:</td>
            <td><input type="text" name="content" value="${contract.content}"></td>
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
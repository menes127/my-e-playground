<table width="100%">
  <tr>
<c:if test="${param.action == 'list'}">
    <td><a href="user.do?action=create">Create User</a></td>
</c:if>
<c:if test="${param.action != 'list'}">
    <td><a href="user.do?action=list">Back</a></td>
</c:if>
    <td align="right">
      username: <sec:authentication property="name"/>
      |
      <a href="user.do?action=preChangePassword">Change Password</a>
      |
      <a href="j_spring_security_logout">Logout</a>
    </td>
  </tr>
</table>
<hr>
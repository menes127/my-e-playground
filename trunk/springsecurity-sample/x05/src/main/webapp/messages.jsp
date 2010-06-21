<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Message Index</title>
    <script type="text/javascript" src="../ajax.js"></script>
    <script type="text/javascript">
function view() {
    Ajax.request({
        method: 'GET',
        url: 'messages/1',
        success: function(response) {
            alert(response.responseText);
        },
        failure: function(response) {
            alert('访问被拒绝！');
        }
    });
}

function create() {
    Ajax.request({
        method: 'POST',
        url: 'messages',
        params: 'id=1&title=message',
        success: function(response) {
            location.reload();
        },
        failure: function(failure) {
            alert('访问被拒绝！');
        }
    });
}

function update() {
    Ajax.request({
        method: 'PUT',
        url: 'messages/1',
        params: 'id=1&title=new-message',
        success: function(response) {
            location.reload();
        },
        failure: function(failure) {
            alert('访问被拒绝！');
        }
    });
}

function remove() {
    Ajax.request({
        method: 'DELETE',
        url: 'messages/1',
        success: function(response) {
            location.reload();
        },
        failure: function(failure) {
            alert('访问被拒绝！');
        }
    });
}
    </script>
  </head>
  <body>
    <h1>Message Index</h1>
    <button onclick="create();">create</button>
    <table border="1" width="100%">
      <thead>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Time</th>
          <th>Operation</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>welcome</td>
          <td>2009-08-02</td>
          <td>
            <a href="javascript:view();void(0);">View</a>
            &nbsp;|&nbsp;
            <a href="javascript:update();void(0);">Update</a>
            &nbsp;|&nbsp;
            <a href="javascript:remove();void(0);">Remove</a>
          </td>
        </tr>
      </tbody>
    </table>
  </body>
</html>

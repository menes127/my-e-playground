<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Ajax</title>
    <script type="text/javascript">
function createAjax() {
    var http_request;
    if (window.XMLHttpRequest) {
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType) {
            http_request.overrideMimeType("text/html");
        }
    } else if (window.ActiveXObject) {
        try {
            http_request = new ActiveXObject("Xsxml2.XMLHTTP");
        } catch(e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e) {
            }
        }
    }
    return http_request;
}

function login() {
    var oRequest = createAjax();

    oRequest.open("get", "index.jsp", false, "user", "user");
    oRequest.setRequestHeader("Content-type", "text/xml; charset=utf-8");
    oRequest.send("");

    alert(oRequest.responseText);

    if (oRequest.status != 200) {
        alert("Error: " + oRequest.status);
        return;
    } else {
        alert(oRequest.getAllResponseHeaders());
        var newWin = window.open("_blank", "testWin");
        newWin.document.location = "index.jsp";
    }
}
    </script>
  </head>
  <body>
    <h1>Ajax Login</h1>
    <button onclick="login()">login</button>
  </body>
</html>

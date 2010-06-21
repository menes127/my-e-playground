<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
    String username = "admin";
    String password = "admin";
    byte[] token = (username + ":" + password).getBytes("utf-8");
    String authorization = "Basic " + new String(Base64.encodeBase64(token), "utf-8");

    URL url = new URL("http://localhost:8080/ch10/admin.jsp");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setUseCaches(false);
    conn.setRequestProperty("Authorization", authorization);
    out.println("返回码: " + conn.getResponseCode());

    java.io.InputStream is = conn.getInputStream();
    java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
    byte[] b = new byte[1024];
    int len = -1;
    while ((len = is.read(b, 0, 1024)) != -1) {
        os.write(b, 0, len);
    }
    os.flush();
    os.close();
    String content = new String(os.toByteArray());
    out.println(content);
%>

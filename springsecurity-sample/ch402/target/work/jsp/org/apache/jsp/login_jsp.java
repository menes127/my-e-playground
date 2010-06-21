package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>\r\n");
      out.write("    <title>登陆</title>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("div.error {\r\n");
      out.write("    width: 260px;\r\n");
      out.write("    border: 2px solid red;\r\n");
      out.write("    background-color: yellow;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("div.hide {\r\n");
      out.write("    display: none;\r\n");
      out.write("}\r\n");
      out.write("    </style>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <div class=\"error ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.error == true ? '' : 'hide'}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("      登陆失败<br>\r\n");
      out.write("      ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/j_spring_security_check\" style=\"width:260px;text-align:center;\">\r\n");
      out.write("      <fieldset>\r\n");
      out.write("        <legend>登陆</legend>\r\n");
      out.write("        用户： <input type=\"text\" name=\"j_username\" style=\"width:150px;\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope['SPRING_SECURITY_LAST_USERNAME']}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/><br />\r\n");
      out.write("        密码： <input type=\"password\" name=\"j_password\" style=\"width:150px;\" /><br />\r\n");
      out.write("        <input type=\"checkbox\" name=\"_spring_security_remember_me\" />两周之内不必登陆<br />\r\n");
      out.write("        <input type=\"submit\" value=\"登陆\"/>\r\n");
      out.write("        <input type=\"reset\" value=\"重置\"/>\r\n");
      out.write("      </fieldset>\r\n");
      out.write("    </form>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

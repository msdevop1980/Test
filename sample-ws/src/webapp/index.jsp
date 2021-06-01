<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1">
<jsp:scriptlet>
    try( java.io.InputStream is= getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")) {
        if(is !=null){
        java.util.jar.Manifest mf=new java.util.jar.Manifest(is);
        pageContext.setAttribute("manifestEntries",mf.getMainAttributes().entrySet())
        }
    }
 </jsp:scriptlet>
    <html>
    <body>
    <h2> Sample System</h2>
    <ul>
    <li><a href="api"> API</a></li>
     <li><a href="activator"> </a></li>
      <li><a href="webjars/swagger-ui/index.html?url=/sample/v1/api-v1-yaml"> API Document</a></li>
    </ul>
    </body>

    </html>

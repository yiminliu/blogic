<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Item Management System</title>
</head>
<body>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<div class="container">
<form:form method="POST" modelAttribute="item">
   <div class="container" style="color:GREEN"> <h3>Deactivate An Item</h3></div>
   <table class="category">
      <tr>
         <td>Item Code: <form:input path="itemcode"></form:input></td>
      </tr>
   </table>
   <table></table>
   <table> 
      <tr> 
        <td>
            <input type="submit" value="Submit"/>
        </td>
      </tr>
    </table>  
</form:form>
<%@ include file="/WEB-INF/includes/footer.jsp"%>
</div><!-- container -->
</body>
</html>

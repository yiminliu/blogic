<%@ include file="/WEB-INF/includes/taglibs.jsp"%>
<%@ include file="/WEB-INF/includes/doctype.jsp"%>
<%@ include file="/WEB-INF/includes/styles.jsp"%>
<%@ include file="/WEB-INF/includes/popups.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Show Item</title>
</head>
<body>

<div class="container">

<div id="main-content">
<div class="span-18 colborder">
<h3 style="margin-bottom: 10px; padding-bottom: 0px; color: GREEN">Enter Item Search Criteria</h3>

<spring:url var="action" value="/ims/getItems" />
<form:form method="POST" action="${action}" modelAttribute="item">

  <table>
     <tr>
       <td><label>Item Code: </label><form:input path="itemcode"></form:input></td>
     </tr>
     <tr>
        <td><label>Item Description:</label> <form:input path="itemdesc.itemdesc1"></form:input></td>
     </tr>
     <tr>
        <td><label>Category:</label> <form:input path="itemcategory"></form:input></td>
     </tr>
     <tr>
        <td><label>Series Name:</label> <form:input path="series.seriesname"></form:input></td>
     </tr>
     <tr>  
         <td><label for="materialType">Material Type:</label>
            <form:select id="materialType" path="material.materialtype" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:150px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialType" items="${materialTypeList}" varStatus="status">
                 <form:option value="${materialType}">${materialType.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td> 
     </tr>
     <tr>    
         <td><label for="materialCategory">Material Category:</label>
            <form:select id="materialCategory" path="material.materialcategory" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:150px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialCategory" items="${materialCategoryList}" varStatus="status">
                 <form:option value="${materialCategory}">${materialCategory.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>   
     </tr>
     <tr>  
         <td><label for="materialClass">Material Class:</label>
            <form:select id="materialClass" path="material.materialclass" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:150px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialClass" items="${materialClassList}" varStatus="status">
                 <form:option value="${materialClass}">${materialClass.getDescription()}</form:option>
              </c:forEach>
            </form:select>
         </td>
      </tr>
      <tr>   
          <td><label for="materialStyle">Material Style:</label>
            <form:select id="materialStyle" path="material.materialstyle" cssClass="span-8" cssErrorClass="span-8 validationFailed" cssStyle="width:150px;">
               <form:option value="" selected="selected">Select one</form:option>
               <c:forEach var="materialStyle" items="${materialStyleList}" varStatus="status">
                 <form:option value="${materialStyle}">${materialStyle.getDescription()}</form:option>
              </c:forEach>
            </form:select>
          </td> 
      </tr>
      <tr>
       <td>Length: <form:input path="dimensions.nominallength" cssStyle="width:90px;"></form:input></td>
     </tr>
      <tr>
       <td>Width : <form:input path="dimensions.nominalwidth" cssStyle="width:90px;"></form:input></td>
     </tr>
     <tr>  
        <td><label for="countryOptions">Origin: </label>
            <c:forEach var="countryName" items="${countryList}" varStatus="status">
                 <form:radiobutton path="countryorigin" value="${countryName}" />${countryName}
              </c:forEach>
        </td> 
      </tr>
      <tr> 
        <td><label for="itemStatusOptions">Status: </label>
              <c:forEach var="itemStatus" items="${statusList}" varStatus="status">
                 <form:radiobutton path="newFeature.status" value="${itemStatus}"/>${itemStatus}
              </c:forEach>
        </td>
     </tr>
     <tr>
         <td><label for="gradeOptions">Grade: </label>
              <c:forEach var="grade" items="${gradeList}" varStatus="status">
                 <form:radiobutton path="newFeature.grade" value="${grade}"/>${grade}
              </c:forEach>
         </td> 
     </tr>
     <tr>   
        <td><label for="MPSOptions">MPS: </label>
              <c:forEach var="mps" items="${mpsList}" varStatus="status">
                 <form:radiobutton path="newFeature.mpsCode" value="${mps}"/>${mps}
              </c:forEach>
        </td>
     </tr>
     <tr>
          <td><label for="edgeOptions">Edge: </label>
              <c:forEach var="edge" items="${edgeList}" varStatus="status">
                 <form:radiobutton path="newFeature.edge" value="${edge}"/>${edge.getDescription()}
              </c:forEach>
         </td>                  
       </tr>
       <tr>
         <td><label for="bodyOptions">Body: </label>
              <c:forEach var="body" items="${bodyList}" varStatus="status">
                 <form:radiobutton path="newFeature.body" value="${body}"/>${body.getDescription()}
              </c:forEach>
         </td>
       </tr>
       <tr>
         <td><label for="designLookOptions">Design Look: </label>
              <c:forEach var="designLook" items="${designLookList}" varStatus="status">
                 <form:radiobutton path="newFeature.designLook" value="${designLook}" />${designLook.getDescription()}
              </c:forEach>
          </td>  
       </tr>
       <tr>
        <td><label for="designStyleOptions">Design Styles: </label>
              <c:forEach var="designStyle" items="${designStyleList}" varStatus="status">
                 <form:radiobutton  path="newFeature.designStyle" value="${designStyle}"/>${designStyle.getDescription()}
              </c:forEach>
         </td>  
      </tr>
      <tr>
        <td><label for="surfaceTypeOptions">Surface Type: </label>
              <c:forEach var="surfaceType" items="${surfaceTypeList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceType" value="${surfaceType}"/>${surfaceType.getDescription()}
              </c:forEach>
         </td>  
       </tr>
       <tr>
          <td><label for="surfaceFinishOptions">Surface Finish: </label>
              <c:forEach var="surfaceFinish" items="${surfaceFinishList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceFinish" value="${surfaceFinish}"/>${surfaceFinish.getDescription()}
              </c:forEach>
          </td>         
       </tr>
       <tr>
         <td><label for="surfaceApplicationOptions">Surface Application: </label>
              <c:forEach var="surfaceApplication" items="${surfaceApplicationList}" varStatus="status">
                 <form:radiobutton path="newFeature.surfaceApplication" value="${surfaceApplication}" />${surfaceApplication.getDescription()}
              </c:forEach>
         </td>
       </tr>
     <tr>  
          <td><label for="colorOptions">Colors Category: </label>
              <span style="padding-left: 5px;padding-bottom:3px; font-size: 12px;">
                 <c:forEach var="colorHue" items="${colorList}" varStatus="loop">
                   <form:radiobutton path="colorhues" value="${colorHue}" />${colorHue}
                    <!--<form:checkbox path="colorhues[${loop.index}].colorHue" value="${colorHue}" />${colorHue}-->
                 </c:forEach>
              </span>                  
          </td> 
     </tr>
     <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</div><!-- border -->
</div><!-- content -->
</div><!-- container -->
</body>
</html>

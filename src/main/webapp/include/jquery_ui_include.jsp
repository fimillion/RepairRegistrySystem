<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 04.08.2015
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<c:url value="/resources/script/jquery/jquery-ui.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/script/jquery/jquery-ui.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/script/jquery/jquery-ui.theme.min.css"/>">
<%--
<link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.structure.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.theme.min.css"/>">


<link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui-1.10.3.custom.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.1.10.3.theme.css"/>">
--%>


<script>
  $.widget.bridge('uitooltip', $.ui.tooltip);
  $.widget.bridge('uibutton', $.ui.button);
</script>

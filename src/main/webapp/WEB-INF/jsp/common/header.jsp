<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
<spring:message code="ccms.title" text="default" />
${param.titleAppend}
--%>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title><spring:message code="ccms.title" text="CCMS" /> ${param.titleAppend}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="icon" href="/favicon.png">
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/aos@3.0.0-beta.6/dist/aos.css" />
    <link rel="stylesheet" href="/css/default.min.css" />
    <link rel="stylesheet" href="/css/ccms.css" />
</head>

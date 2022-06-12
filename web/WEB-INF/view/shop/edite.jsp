<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<page:template>
    <jsp:attribute name="title">Update address human</jsp:attribute>
    <jsp:body>
        <c:url value="/update-shop" var="update"/>

        <!-- Page Content -->
        <form action="${update}" method="post" id="dataform">
            <div class="container">
                <input type="text" hidden name="id" value="${shop.id}">
                <div class="mb-3 row p-1">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name"  value="${shop.name}">
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="address" class="col-sm-2 col-form-label">Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address" name="address" value="${shop.address}">
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone" name="phone" value="${shop.phone}">
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" value="${shop.email}">
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="link" class="col-sm-2 col-form-label">Link</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="link" name="link" value="${shop.link}">
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="description" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="description" name="description">${shop.description}</textarea>
                    </div>
                </div>
                <div class="mb-3 row p-1">
                    <label for="category" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="category" name="category.id">
                            <c:forEach var="c" items="${categories}">
                                <option value="${c.id}"<c:if test="${c.id==shop.category.id}"> selected</c:if>>${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="mb-3 row text-center p-1">
                    <button type="submit" class="btn btn-outline-primary p-1" id="btnsubmit">Submit</button>
                </div>
            </div>
        </form>
    </jsp:body>
</page:template>


<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<page:template>
    <jsp:attribute name="title">Create address human</jsp:attribute>
    <jsp:body>
        <!-- Page Content -->
        <form action="" method="post" id="dataform">
        <div class="container">
            <input type="text" hidden name="id" value="0">
            <div class="mb-3 row p-1">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name">
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="address" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="address" name="address">
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone">
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email">
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="link" class="col-sm-2 col-form-label">Link</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="link" name="link">
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="description" name="description"></textarea>
                </div>
            </div>
            <div class="mb-3 row p-1">
                <label for="category" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                    <select class="form-control" id="category" name="category.id">
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}">${c.name}</option>
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


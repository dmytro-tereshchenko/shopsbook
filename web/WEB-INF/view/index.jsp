<%@ page import="mvc.mvc.bean.Shop" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
  <jsp:body>
    <c:url value="/get-shop" var="get" />
    <c:url value="/delete-shop" var="delete" />
    <c:url value="/search" var="search" />

    <!-- Page Content -->
    <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Phone</th>
          <th scope="col">Address</th>
          <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
          <c:forEach var="h" items="${resultObject}">
            <tr>
                <th scope="row">${h.id}</th>
                <td>${h.name}</td>
                <td>${h.phone}</td>
                <td>${h.address}</td>
                <td>
                  <a href="${get}/${h.id}" class="btn btn-sm btn-success p-1 m-1">Edit</a>
                  <a href="${delete}/${h.id}" class="btn btn-sm btn-danger p-1 m-1">Delete</a>
                </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <form action="${search}" method="get">
      <div class="container">
        <div class="mb-2 row p-1">
          <label for="shopName" class="col-sm-2 col-form-label">Shop name</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="shopName" name="shopName">
          </div>
        </div>
        <div class="mb-3 row p-1">
          <label for="category" class="col-sm-2 col-form-label">Description</label>
          <div class="col-sm-10">
            <select class="form-control" id="category" name="categoryId">
              <option value="0">Unselected</option>
              <c:forEach var="c" items="${categories}">
                <option value="${c.id}">${c.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="mb-3 row p-1">
          <label for="address" class="col-sm-2 col-form-label">Address</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="address" name="address">
          </div>
        </div>
        <div class="mb-3 row text-center p-1">
          <button type="submit" class="btn btn-outline-primary p-1">Search</button>
        </div>
      </div>
      </form>
    </div>
    <!-- /.container -->
  </jsp:body>
</page:template>

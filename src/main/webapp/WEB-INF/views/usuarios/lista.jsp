<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/resources" var="resPath" />

<tags:pageTemplate titulo="Usuários Cadastrados">
    <div class="container">
      <h2><a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a></h2>
      <h1>Usuários cadastrados:</h1>
      <table class="table table-bordered table-striped table-hover">
        <tr>
          <th>Nome</th>
          <th>Email</th>
          <th>Roles</th>
        </tr>
        <c:forEach items="${usuarios}" var="usuario">
          <tr>
            <td>${usuario.nome}</td>
            <td>${usuario.email}</td>
            <td>${usuario.roles}</td>
            <td>
              <form:form action="${s:mvcUrl('UC#editarForm').build()}" method="GET" modelAttribute="usuario" >
                <input type="hidden" name="email" value="${usuario.email}"  />
                <input type="image" src="${resPath}/imagens/editar.png" alt="Editar" title="Editar"/>
              </form:form>
            </td>
          </tr>
        </c:forEach>
      </table>
      <h3>${sucesso}</h3>
    </div>
</tags:pageTemplate>
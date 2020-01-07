<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Edição das Permissões de ${u.nome}">
    <div class="container">
        <form:form servletRelativeAction="${s:mvcUrl('UC#atualizar').arg(0, u.email).arg(1, u.roles).build()}"
          method="POST" modelAttribute="u">
          <h1>Cadastro de Permissões para ${u.nome}</h1>
          <form:checkboxes items="${listaRoles}" path="roles"/>
          <br>
          <button type="submit">Atualizar</button>
        </form:form>
    </div>
</tags:pageTemplate>
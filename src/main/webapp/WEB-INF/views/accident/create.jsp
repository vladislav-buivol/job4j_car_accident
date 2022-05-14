<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card">
            <div class="card-header">
                Добавить инцидент
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method='POST'>
                    <table>
                        <tr>
                            <td>Название:</td>
                            <td><input type='text' name='name'></td>
                        </tr>
                        <tr>
                            <td>Описание:</td>
                            <td><input type='text' name='text'></td>
                        </tr>
                        <tr>
                            <td>Адрес:</td>
                            <td><input type='text' name='address'></td>
                        </tr>
                        <tr>
                            <td>Тип:</td>
                            <td>
                                <select name="type.id">
                                    <c:forEach var="type" items="${types}">
                                        <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td>Статьи:</td>
                            <td>
                                <select name="rIds" multiple>
                                    <c:forEach var="rule" items="${rules}">
                                        <option value="${rule.id}">${rule.name}</option>
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
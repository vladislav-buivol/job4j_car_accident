<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <a href="<c:url value='/create'/>">
                            <span class='btn btn-primary'>
                                Добавить инцидент
                            </span>
        </a>
        <div class="fw-bold">
            Заявления о несчастных случаях
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Название</th>
                <th scope="col">Описание</th>
                <th scope="col">Адрес</th>
                <th scope="col">Тип</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accidents}" var="accident">
                <tr>
                    <td>
                        <c:out value="${accident.id}"/>
                    </td>
                    <td>
                        <c:out value="${accident.name}"/>
                    </td>
                    <td>
                        <c:out value="${accident.text}"/>
                    </td>
                    <td>
                        <c:out value="${accident.address}"/>
                    </td>
                    <td>
                        <c:out value="${accident.type.name}"/>
                    </td>
                    <td>
                        <span>
                            <a href="<c:url value='/update?id=${accident.id}'/>">Редактировать</a>
                        </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
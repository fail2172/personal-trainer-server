<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit-no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://bootstrap-4.ru/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Главная страница</title>
</head>
<body class=" bd-example row-centered container mx-auto bg-light">
<nav class="navbar navbar-dark bg-dark">

    <a class="navbar-brand container pl-2 align-center">
        <img src="../styles/images/logoCut.png" width="200" height="80" class="d-inline-block align-left" alt="">
        Welcome to personal coach</a>
</nav>
<h1>Добро пожаловать на главную страницу</h1>
<div class="container m-2 p-2 border-1">
    <div class="row justify-content-start">
        <div class="col-coach">
            <h2>Рассписание тренировок на 3 дня
                <div class="accordion" id="accordionCoach">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne"
                                    aria-expanded="true" aria-controls="collapseOne">
                                Тренировка для первого дня
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">A</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Тренировка для второго дня
                            </button>
                        </h2>
                        <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">a</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Тренировка для третьего дня
                            </button>
                        </h2>
                        <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body"></div>
                        </div>
                    </div>
                </div>
            </h2>
        </div>
        <div class="col-diet">
            <h2>Диета на день
                <div class="accordion" id="accordionDiet">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="DietHeadingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOneDiet"
                                    aria-expanded="true" aria-controls="collapseOneDiet">
                                Список продуктов для завтрака
                            </button>
                        </h2>
                        <div id="collapseOneDiet" class="accordion-collapse collapse" aria-labelledby="DietHeadingOne"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">A</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="DietHeadingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseTwoDiet" aria-expanded="false"
                                    aria-controls="collapseTwoDiet">
                                Список продуктов для обеда
                            </button>
                        </h2>
                        <div id="collapseTwoDiet" class="accordion-collapse collapse" aria-labelledby="DietHeadingTwo"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body">a</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="DietHeadingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseThreeDiet" aria-expanded="false"
                                    aria-controls="collapseThreeDiet">
                                Список продуктов для ужина
                            </button>
                        </h2>
                        <div id="collapseThreeDiet" class="accordion-collapse collapse"
                             aria-labelledby="DietHeadingThree"
                             data-bs-parent="#accordionExample">
                            <div class="accordion-body"></div>
                        </div>
                    </div>
                </div>
            </h2>
        </div>
    </div>
</div>
</body>
</html>

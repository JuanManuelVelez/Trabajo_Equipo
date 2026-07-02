<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<div class="row g-4">
    <div class="col-12">
        <h1 class="mb-4">Bienvenidos a los alumnos </h1>
    </div>

    <div class="col-md-7">
        <div class="row">
            <h4 class="text-secondary col-6">Aquí están todas los alumnos</h4>
            <a href="mascota" class="btn btn-primary col-6 align-content-center text-center carga"><i class="bi bi-arrow-clockwise"></i> Cargar mascotas</a>
        </div>

        <c:choose>
            <%-- Condición 1: Si la lista es nula o está vacía --%>
            <c:when test="${empty listaAlumnos}">
                <div class="alert alert-info text-center mt-4" role="alert">
                    <i class="bi bi-info-circle-fill"></i> No hay alumnos registradas en este momento.
                </div>
            </c:when>

            <%-- Condición por defecto: Si la lista SÍ tiene datos --%>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover mt-4 align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Edad</th>
                            <th>Matricula </th>
                            <th>Correo Electronico</th>
                            <th>Sexo</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listaAlumnos}" var="alumno">
                            <tr>
                                <td><strong>${alumno.id}</strong></td>
                                <td>${alumno.nombre}</td>
                                <td><span class="badge bg-secondary">${alumno.apellidos}</span></td>
                                <td>${alumno.edad} años</td>
                                <td>${alumno.matricula}</td>
                                <td>${alumno.correoElectronico}</td>
                                <td>${alumno.sexo}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="col-md-5">
        <div class="card shadow-sm">
            <div class="card-body">
                <h4 class="card-title text-primary mb-4"><i class="bi bi-plus-circle-fill"></i> ¡Registra a tus alumnos!</h4>

                <form action="alumno" method="POST">
                    <input type="hidden" name="action" value="create">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre de del alumno</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ej: emy" required>
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos de del alumno</label>
                        <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Ej: castaneda" required>
                    </div>

                    <div class="mb-3">
                        <label for="edad" class="form-label">Edad (Años)</label>
                        <input type="number" class="form-control" id="edad" name="edad" placeholder="Ej: 3" required min="0" max="30">
                    </div>

                    <div class="mb-3">
                        <label for="matricula" class="form-label">Matricula / Descripción</label>
                        <input type="text" class="form-control" id="matricula" name="matricula" placeholder="20253ds093" required>
                    </div>
                    <div class="mb-3">
                        <label for="correoElectronico" class="form-label">Matricula / Descripción</label>
                        <input type="text" class="form-control" id="correoElectronico" name="correoElectronico" placeholder="20253ds093@gmail.com" required>
                    </div>
                    <div class="mb-3">
                        <label for="sexo" class="form-label">Sexo / Descripción</label>
                        <input type="text" class="form-control" id="sexo" name="sexo" placeholder="mujer" required>
                    </div>



                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary carga"><i class="bi bi-save"></i> Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="layout/footer.jsp" %>
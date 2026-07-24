<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Panel de Administración - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:Arial, Helvetica, sans-serif;
        }

        body{
            background:#eef5ff;
        }

        .navbar{
            width:100%;
            background:#0d47a1;
            color:white;
            display:flex;
            justify-content:space-between;
            align-items:center;
            padding:18px 50px;
        }

        .navbar h2{
            font-size:30px;
        }

        .navbar nav a{
            color:white;
            text-decoration:none;
            margin-left:25px;
            font-weight:bold;
        }

        .contenedor{
            width:95%;
            margin:30px auto;
        }

        .titulo{
            margin-bottom:25px;
        }

        .titulo h1{
            color:#0d47a1;
        }

        .cards{
            display:flex;
            gap:20px;
            margin-bottom:30px;
        }

        .card{
            flex:1;
            background:white;
            border-radius:15px;
            padding:25px;
            text-align:center;
            box-shadow:0 8px 20px rgba(0,0,0,.1);
        }

        .card h2{
            color:#1565c0;
            font-size:40px;
        }

        .card p{
            color:#666;
            margin-top:10px;
        }

        table{
            width:100%;
            border-collapse:collapse;
            background:white;
            border-radius:12px;
            overflow:hidden;
            box-shadow:0 8px 20px rgba(0,0,0,.1);
        }

        th{
            background:#1565c0;
            color:white;
            padding:15px;
        }

        td{
            padding:14px;
            border-bottom:1px solid #ddd;
        }

        tr:hover{
            background:#f4f8ff;
        }

        .btn{
            padding:8px 15px;
            text-decoration:none;
            border-radius:8px;
            color:white;
            font-weight:bold;
            margin-right:8px;
        }

        .editar{
            background:#ff9800;
        }

        .eliminar{
            background:#e53935;
        }

        .nuevo{
            display:inline-block;
            margin:25px 0;
            background:#1565c0;
            color:white;
            text-decoration:none;
            padding:12px 25px;
            border-radius:10px;
            font-weight:bold;
        }

    </style>

</head>

<body>

<header class="navbar">

    <h2>SELECTO</h2>

    <nav>

        <a href="index.jsp">Inicio</a>
        <a href="#">Candidatos</a>
        <a href="#">Vacantes</a>
        <a href="#">Reportes</a>

    </nav>

</header>

<div class="contenedor">

    <div class="titulo">

        <h1>Panel de Administración</h1>

        <p>Gestión de candidatos registrados</p>

    </div>

    <div class="cards">

        <div class="card">
            <h2><%= request.getAttribute("totalCandidatos") %></h2>
            <p>Candidatos</p>
        </div>

        <div class="card">
            <h2>0</h2>
            <p>Vacantes</p>
        </div>

        <div class="card">
            <h2><%= request.getAttribute("totalEmpresas") %></h2>
            <p>Empresas</p>
        </div>

    </div>

        <a href="index.jsp" class="nuevo">+ Registrar nuevo candidato</a>

        <table>

            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Acciones</th>
            </tr>

            <%
                List<String[]> candidatos =
                        (List<String[]>) request.getAttribute("candidatos");

                if (candidatos != null) {

                    for (String[] candidato : candidatos) {
            %>

            <tr>

                <td><%= candidato[0] %></td>

                <td><%= candidato[1] %></td>

                <td><%= candidato[2] %></td>

                <td>

                    <a class="btn editar"
                       href="editar.jsp?id=<%= candidato[0] %>">
                        Editar
                    </a>

                    <a class="btn eliminar"
                       href="EliminarServlet?id=<%= candidato[0] %>"
                       onclick="return confirm('¿Deseas eliminar este candidato?');">
                        Eliminar
                    </a>

                </td>

            </tr>

            <%
                    }
                }
            %>

        </table>

        </div>

        <footer>

            © 2026 SELECTO | Sistema de Selección de Personal

        </footer>

        </body>

        </html>
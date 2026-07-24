<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<title>Registro exitoso | Selecto</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, Helvetica, sans-serif;
}

body{
    background:linear-gradient(135deg,#2563eb,#60a5fa);
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
}

.card{
    background:#fff;
    width:500px;
    padding:40px;
    border-radius:20px;
    text-align:center;
    box-shadow:0 15px 35px rgba(0,0,0,.25);
}

.check{
    font-size:70px;
}

h1{
    color:#2563eb;
    margin:20px 0;
}

p{
    color:#555;
    font-size:18px;
    margin-bottom:10px;
}

.info{
    background:#f5f5f5;
    padding:20px;
    border-radius:10px;
    margin:25px 0;
    text-align:left;
}

a{
    display:inline-block;
    margin:10px;
    padding:14px 28px;
    text-decoration:none;
    color:white;
    border-radius:10px;
    font-weight:bold;
}

.lista{
    background:#2563eb;
}

.nuevo{
    background:#22c55e;
}

a:hover{
    opacity:.9;
}

</style>

</head>

<body>

<div class="card">

<div class="check">✅</div>

<h1>¡Registro exitoso!</h1>

<p>El candidato fue registrado correctamente.</p>

<div class="info">
<strong>Nombre:</strong> <%= request.getAttribute("nombre") %><br><br>

<strong>Correo:</strong> <%= request.getAttribute("correo") %>
</div>

<a class="lista" href="ListarCandidatos">Ver candidatos</a>

<a class="nuevo" href="index.jsp">Registrar otro candidato</a>

</div>

</body>
</html>
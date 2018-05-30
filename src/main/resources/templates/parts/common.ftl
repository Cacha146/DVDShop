<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DVD</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/./webjars/bootstrap/4.1.0/css/bootstrap.min.css" >
</head>
<body>
<#include "navbar.ftl">
<div class="container mt-5">
<#nested>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/./webjars/jquery/3.2.1/jquery.js" ></script>
<script src="/./webjars/popper.js/1.14.1/popper.min.js" ></script>
<script src="/./webjars/bootstrap/4.1.0/js/bootstrap.min.js" ></script>
</body>
</html>
</#macro>

<%-- 
    Document   : gallery
    Created on : 30 mars 2014, 16:56:18
    Author     : Bastien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>MediaStore</title>

        <!-- Include Bootstrap & FA -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

        <!-- Include DataTables -->
        <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Include Style -->
        <link href="css/media.css" rel="stylesheet">

        <!-- Include Jstree -->
        <link href="dist/themes/default/style.css" rel="stylesheet" />

        <!-- Include Maps -->
        <style type="text/css">
            #map img {
                max-width: none;
            }
            div#infoBulle{
                width:200px;
                height:350px;
            }
            #cadre{position:relative;width:800px;margin:auto;}
            #cadre #map{width:100%;height:500px;margin:auto;}
        </style>
    </head>
    <body>
        <c:import url="/WEB-INF/header.jsp"></c:import>
        <c:import url="/WEB-INF/menu.jsp"></c:import>

        <!-- /.navbar-static-side -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">

                    <br>

                    <div id="includedContent"></div>
                </div>
            </div>
        </div>


        <!-- Script JQuery & Bootstrap -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script>
            $(function() {
                $("#includedContent").load("galleryload.html");
            });
        </script>

    </body>
</html>


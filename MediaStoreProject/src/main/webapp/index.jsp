<%-- 
    Document   : index
    Created on : 30 mars 2014, 16:29:39
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

        <!-- Include Drag&Drop -->
        <style>
            #filedrag {
                display: block;
                font-weight: bold;
                text-align: center;
                padding: 1em 0;
                margin: 1em 0;
                color: #555;
                border: 1px dashed #555;
                border-radius: 7px;
                cursor: default;
            }

            #filedrag:hover {
                color: #f00;
                border-color: #f00;
                border-style: solid;
                box-shadow: inset 0 3px 4px #888;
            }
        </style>
    </head>
    <body>
        <c:import url="/WEB-INF/header.jsp"></c:import>
        <c:import url="/WEB-INF/menu.jsp"></c:import>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Home</h1>
                </div>

            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-camera-retro fa-fw"></i> Images
                        </div>

                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width:135px" >Miniatures</th>
                                            <th>Nom</th>
                                            <th>Taille</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td style="text-align:center"><img src="../img/1.jpg"/></td>
                                            <td>Photo17</td>
                                            <td>23 Mo</td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center"><img src="../img/2.jpeg"/></td>
                                            <td>Photo653</td>
                                            <td>980 Ko</td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center"><img src="../img/3.jpg"/></td>
                                            <td>Photo34</td>
                                            <td>6.57 Mo</td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center"><img src="../img/4.jpg"/></td>
                                            <td>Photo6</td>
                                            <td>10 Mo</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    <i class="fa fa-info fa-fw"></i> Infos
                                </a>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    Nom : Photo1.jpg <br>
                                    Taille : 67 Ko <br>
                                    Dimension : 335 x 720 px <br>
                                </div>
                            </div>
                        </div>

                        <div id="mapGlobal" class="panel panel-default">
                            <div class="panel-heading">

                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                    <i class="fa fa-location-arrow fa-fw"></i> GPS
                                </a>

                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">

                                    Coordonnée Lat : 48.785152
                                    <br><br>
                                    Coordonnée Long : 1.893768
                                    <br><br>
                                    <div id="cadre">
                                        <div id="map"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Script JQuery & Bootstrap -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <!-- Script Maps -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAgfQp0OgUsZoCHHq5as2jCEWBDsJOYHWU&sensor=false"></script>
        <script type="text/javascript" src="js/maps.js"></script>

        <!-- Script Drag&Drop -->
        <script src="js/jquery.fileupload.js"></script>
        <script src="js/jquery.fileupload-fp.js"></script>
        <script src="js/jquery.fileupload-ui.js"></script>


        <script src="js/drag.js"></script>

        <!-- Script DataTables -->
        <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
        <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

        <script>
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>

        <!-- Script Jstree -->
        <script src="dist/jstree.min.js"></script>
        <script>
            $(function() {
                $('#jstree').jstree({'core': {
                        'data': [
                            'Simple root node',
                            {
                                'text': 'Root node 2',
                                'state': {
                                    'opened': true,
                                    'selected': true
                                },
                                'children': [
                                    {'text': 'Child 1'},
                                    'Child 2'
                                ]
                            }
                        ]
                    }});
            });
        </script>

    </body>
</html>

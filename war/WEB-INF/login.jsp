<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In</title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
#button{
clear:both;
}
 .cssbtn{
        position: relative;
        top: 9px;
        right: 40px;
        float: right;
        }
        body{
	background-image: url("resources/bicycle.jpg");
    width: 100%;
    margin: 0px;
    //background-color: rgba(255, 255, 255, .3);	
        color: black;
    
    background-size: cover;
    background-attachment: fixed;
}
#content{
    width: 1365px;
    background-color: rgba(0, 0, 0, .5);
    height: 667px;
    position: relative;
    left: 0;
    top: 0;
}
#first{
font-size: 34px;
    font-family: sans-serif;
    font-weight: 600;
    color: white;
}
#second{
color: whitesmoke;
    font-family: sans-serif;
    font-size: 65px;
}
#button{
//https://d3ptyyxy2at9ui.cloudfront.net/3777e588e8f2f1080a490d9068ab047a.jpg
    color: white;
    background-color: tomato;
    border: 0;
    padding: 6px;
    border-radius: 6px;
    position: absolute;
    left: 0;
    text-align: center;
    top: 213px;
    padding: 7px 20px 7px 20px;
    color: white;
    font-size: large;
}
#box{
width: 608px;
    float: right;
    position: relative;
    right: 59px;
    top: 157px;
}
#btn{
}
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
     <a href="#" class="navbar-brand">ToDoo List</a>
     <a href="glogin"><button class="btn btn-sm btn-info cssbtn">Log in Google</button></a>
</div>
<div id="content">
<div id="box">
<div id="second">Get started today </div>
<div id="first">Every one know what they should be doing</div>

<div id="btn"> <a href="glogin"><button id="button">Sign in with Google+</button></a>
</div></div></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add List</title>
<style type="text/css">
 body{
	background-image: url("../resources/new-york.jpg");
    width: 100%;
    margin: 0px;
        color: black;
    
    background-size: cover;
    background-attachment: fixed;
}
#hdr{
            margin: -8px;
    border: 0px;
    padding: 0px;
    width: 1374px;
    height: 50px;
    background-color: black;
    background-image: linear-gradient(to bottom, #000000, #808080);
        }
         #todolist{
        width: 116px;
   display: block;
    color: white;
    font-size: 25px;
    position: relative;
    top: 3px;
    left: 86px;
        }
        #todoul{
        float: left;
        }
        #li3{
        color: white;
	    font-style: oblique;
	    font-size: medium;
	    position: relative;
	    top: 27px;
	    right: 49px;
	    width: 159px;
        }
        #li2{
    position: relative;
    right: 19px;
        }
         .btn {
	background: #3498db;
    background-image: linear-gradient(to bottom, #3498db, #0783d1);
    border: 20px;
    border-radius: 10px;
    font-family: Arial;
    color: #ffffff;
    font-size: 19px;
    padding: 5px 17px 5px 17px;
    text-decoration: none;
}
#btnul{
    position: relative;
    display: inline;
    right: 49px;
    top: -16px;
    display: block;
    float: right;
        }
        .btnl{
        display: block;
        position: relative;
        top: 12px;
        float: right;
        width:108px;
        }
        .hdrlink{
        color:white;
        text-decoration: none;
        }
        #back{
        position:absolute;
        top:41px;
        width: 1366px;
    background-color: rgba(0, 0, 0, .3);
    height: 625px;
      //  background-color: rgba(255, 255, 255, .3);
        }
#addlistid{
position: relative;
    width: 445px;
    left: 430px;
    top: 233px;
    background-color: aqua;
    height: 188px;
    background-color: rgba(255, 255, 255, .8);
}
#addlistformid{
position: relative;
    top: 27px;
}
input[type="text"] {
      display: block;
    margin: 0;
    position: relative;
    left: 37px;
    top: 3px;
    width: 82%;
    height: 37px;
    font-family: sans-serif;
    font-size: 18px;
    box-shadow: none;
}
input[type="text"]:focus {
  outline: none;
  border: solid 1px #707070;
  box-shadow: 0 0 5px 1px #969696;
}
#listname{

}
#save{
position: relative;
    left: 180px;
    top: 29px;
    background: #3498db;
    background-image: linear-gradient(to bottom, #3498db, #0783d1);
    border: 20px;
    border-radius: 10px;
    font-family: Arial;
    color: #ffffff;
    font-size: 19px;
    padding: 5px 17px 5px 17px;
    text-decoration: none;
}
span{

}
#todo{
font-size: 33px;
    font-family: sans-serif;
}
</style>
</head>
<body>
<%
if(session.getAttribute("email")==null || session.getAttribute("name")==null)
response.sendRedirect("../");

String name= (String)session.getAttribute("name");
String email= (String)session.getAttribute("email");
%>

<div id="hdr">
        <ul id="todoul">
        <li id="todolist">ToDoo List</li>
    </ul>
    <ul id="btnul" >
        
        <li id="li1" class="btnl"><button id="addlist" class="btn"><a  class="hdrlink" href="../first">Go Back</a></button></li>
        <li id="li2" class="btnl"><button class="btn"><a class="hdrlink" href="../logout">Log Out</a></button></li>
        <li id="li3" class="btnl">Hi... <%=name %></li>
        </ul>
    </div>

<div id="back">
<div id="addlistid" class='addlistclass' >
<div id="todo">T<span>o</span>D<span>oo</span> List</div>
 <form  id="addlistformid" action="listname">
<input type="text" id="listname" name="listname"  placeholder="Add List Here" required/>
<input type=submit  id="save" value="Save"/>
</form>
</div>

</div>
<script>

</script>
</body>
</html>
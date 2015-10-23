<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script  src="resources/js/jquery-ui.js"></script>
  <script src="resources/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <style type="text/css">
#container{
width: 100%;
    position: absolute;
    top: 126px;
    //background-color: black;
}
.listdiv{
margin: 24px;
    float: left;
    display: inline;
    background-color: aqua;
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 10px;
    width: 400px;
}
ul{
margin:5px;
padding-left: 5px;
}
li{
   display: block;
   margin: 5px;
   width: 380px;
   }

.listname{
background-color: whitesmoke;
    text-align: center;
    font-family: sans-serif;
    font-size: 24px;
    border-radius: 13px;
    border-bottom-right-radius: initial;
    border-bottom-left-radius: -40px;
    border-bottom-left-radius: initial;
}
.addbuttondiv{
text-align: center;
//background-color:whitesmoke;
}
a{
color: white;
text-decoration: none !important;
}
</style>
<style type="text/css">
body{
	background-image: url("resources/new-york.jpg");
    width: 100%;
    margin: 0px;
    background-size: 1349px;
    background-attachment: fixed;
}
 #hdr{
            margin: -8px;
    border: 0px;
    padding: 0px;
    width: 1374px;
    height: 56px;
    background-color: black;
    background-image: linear-gradient(to bottom, #000000, #808080);
        }
         #todolist{
        width: 122px;
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
    font-style: initial;
    font-size: medium;
    position: relative;
    top: 27px;
    right: 49px;
    width: 159px;
    font-family: sans-serif;
        }
        .content{
        font-size: initial;
    font-family: sans-serif;
    flood-color: initial;
    font-weight: 500;
    background-color: white;
    border-radius: 5px;
    padding: 7px;
        }
        .mytodo{
        padding: 1px 0px 1px 0px;
        
        }
        #li2{
    position: relative;
    right: 19px;
        }
         .btn {
	//background: #3498db;
    //background-image: linear-gradient(to bottom, #3498db, #0783d1);
   // border: 20px;
   // border-radius: 10px;
   // font-family: Arial;
    //color: #ffffff;
    font-size: 19px;
    //padding: 5px 17px 5px 17px;
    //text-decoration: none;
}
#btnul{
    position: relative;
    display: inline;
    right: 49px;
    top: -11px;
    display: block;
    float: right;
        }
        .btnl{
        display: block;
        position: relative;
        top: 12px;
        float: right;
        width:105px;
        }
        .hdrlink{
        color:white;
        }
        .hdrlink:focus{
        color:white;
        }
        #sechdr{
        margin: 0px;
    border: 0px;
    border-color: white;
    width: 1373;
    width: 1365px;
    height: 51px;
    //background-color: aqua;
    clear: both;
        }
         .style-1{
        float: left;
    position: relative;
    left: -15px;
    top: -9px;
            
        }
.style-1 input[type="text"]:focus,
.style-1 input[type="text"].focus {
    padding: 14px;
        margin-top: 2px;
  border: solid 1px #dcdcdc;
  border: solid 1px #707070;
  box-shadow: 0 0 5px 1px #ffffff;
}
#datediv{
width: 863px;
    //border: 2px;
    //background-color: black;
    padding: 0px;
    position: relative;
    float: right;
    display: inline;
}
.datebutton{
background: #3498db;
    background-image: linear-gradient(to bottom, #3498db, #0783d1);
    border: 0px;
    font-family: Arial;
    font-size: 18px;
    padding: 10px 20px 10px 20px;
    text-decoration: none;
    border-radius: 8px;
}
.buttonlink{
color: white;
}
.active_class{
background: #83848A;
    /* border: 19px; */
    padding: 14px;
    /* border-color: black; */
    /* color: black; */
}
.prenxt{
background: #3498db;
    background-image: linear-gradient(to bottom, #3498db, #0783d1);
    border: 20px;
    font-family: Arial;
    font-size: 19px;
    /* padding: 10px 20px 10px 20px; */
    /* text-decoration: none; */
    /* background: #3498db; */
    /* background-image: linear-gradient(to bottom, #3498db, #0783d1); */
    /* border: 0px; */
    /* font-family: Arial; */
    /* font-size: 18px; */
    padding: 10px 20px 10px 20px;
    text-decoration: none;
    border-radius: 8px;
}
.mybtn{
border-bottom-left-radius: 12px;
    padding: 0px 189px 0px 189px;
    background: rgba(255,255,255,.6);
    font-size: 35px;
    font-weight: bold;
    border-bottom-right-radius: 12px;
}
.buttn{
   background: transparent url(resources/delete2.png) no-repeat;
    padding: 11px;
    float: right;
    clear: both;
    border: 0;
}
#back{
height: 675px;
    background-color: rgba(0, 0, 0, .5);
}
#img{
position: absolute;
    left: 39px;
    top: 3px;
}
</style>
  
<script >
//*********************default call to print list and then data in the list******************************************//
$(document).ready(function(){
	$.ajax({
        url: "data/get",
        type: "GET",
         
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(a) {
             console.log(a);
             console.log(a.length); 
             for(var i=0;i<a.length;i++){
                 var $container = $("#container");
                 $container.append('<div id="list'+ $container.children().length +'" class="listdiv"><div class="listname">'+a[i].listname+'</div><ul id="'+a[i].id+'" class="mytodo"></ul> <div class="addbuttondiv"> <button id="button'+i+'" type="button" class="btn btn-default mybtn" data-toggle="modal" data-target="#exampleModal"> + </button> </div> </div>');
             }
        }
    });
	
	$.ajax({
		url:"data/getdata",
		type:"GET",
		 beforeSend: function(xhr) {
	         xhr.setRequestHeader("Accept", "application/json");
	         xhr.setRequestHeader("Content-Type", "application/json");
	     },
		success: function(a) {
			console.log(a);
			for(var i=0;i<a.length;i++){
				console.log("loop running");
				console.log(a[i].listid);
				$("#"+a[i].listid).append("<li class='content "+a[i].id+"'>"+a[i].todo+"<button id='"+a[i].id+"' class='buttn'></button></li>");
			}
		}
		});
	
});

//*********************called when date button clicked******************************************//
$(document).ready(function(){
	$(".datebutton").click(function(){
		$(".datebutton").removeClass("active_class");
		 $(this).addClass('active_class');
		var prev=$(this).children('a')[0].text;
		console.log("got the date "+prev);
		$.ajax({
			url:"data/date/"+prev,
			type:"GET",
			 beforeSend: function(xhr) {
		         xhr.setRequestHeader("Accept", "application/json");
		         xhr.setRequestHeader("Content-Type", "application/json");
		     },
			success: function(a) {
				console.log(a);
				$('ul .content').remove();
				for(var i=0;i<a.length;i++){
					console.log("loop running");
					console.log(a[i].listid);
					$("#"+a[i].listid).append("<li class='content "+a[i].id+"'>"+a[i].todo+"<button id='"+a[i].id+"' class='buttn'></button></li>");
				}
				 
			}
			
		});
		
	});
	
});

//*********************called when previous button called******************************************//

$(document).ready(function(){
	
	
	 function reload_cart() {
	        console.log("method called");
	        var prev=$(".active_class").children('a')[0].text;
	        console.log(prev);
	        $.ajax({
				url:"data/date/"+prev,
				type:"GET",
				 beforeSend: function(xhr) {
			         xhr.setRequestHeader("Accept", "application/json");
			         xhr.setRequestHeader("Content-Type", "application/json");
			     },
				success: function(a) {
					console.log(a);
					$('ul .content').remove();
					for(var i=0;i<a.length;i++){
						console.log("loop running");
						console.log(a[i].listid);
						$("#"+a[i].listid).append("<li class='content "+a[i].id+"'>"+a[i].todo+"<button id='"+a[i].id+"' class='buttn'></button></li>");
					}
					 
				}
				
			});
	    }
	
	$("#prev").click(function(){
		var prev=$("#button1").children('a')[0].text;
		console.log(prev);
		$.ajax({
			url:"data/date/prev/"+prev,
			type: "GET",
			beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(a) {
	             console.log(a);
	             var d = new Date(a);
	             console.log(d);
	             $( ".datebutton a" ).remove();
	             for (i = 0; i < 6; i++) { 
	            		var tmr=new Date(a);
	            		
	            		tmr.setDate(tmr.getDate() + i);
	            		console.log("today"+tmr+""+i);
	            		var checkfrmt=$.datepicker.formatDate( "yy-mm-dd", tmr);
	            		console.log("check frmt"+checkfrmt);
	            		var j=i+1;
	            		$("#button"+j).append("<a id='lnk"+i+"' href='#' class='buttonlink'>"+checkfrmt+"</a>");	
	            	}
	             reload_cart();
	        }
		});
	});
	
});

//*********************called when next button clicked******************************************//
$(document).ready(function(){
	function reload_cart() {
        console.log("method called");
        var prev=$(".active_class").children('a')[0].text;
        console.log(prev);
        $.ajax({
			url:"data/date/"+prev,
			type:"GET",
			 beforeSend: function(xhr) {
		         xhr.setRequestHeader("Accept", "application/json");
		         xhr.setRequestHeader("Content-Type", "application/json");
		     },
			success: function(a) {
				console.log(a);
				$('ul .content').remove();
				for(var i=0;i<a.length;i++){
					console.log("loop running");
					console.log(a[i].listid);
					$("#"+a[i].listid).append("<li class='content "+a[i].id+"'>"+a[i].todo+"<button id='"+a[i].id+"' class='buttn'></button></li>");
				}
				
			}
			
		});
    }
	
	$("#next").click(function(){
		var prev=$("#button1").children('a')[0].text;
		console.log(prev);
		$.ajax({
			url:"data/date/next/"+prev,
			type: "GET",
			beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(a) {
	             console.log(a);
	             var d = new Date(a);
	             console.log(d);
	             $( ".datebutton a" ).remove();
	             for (i = 0; i < 6; i++) { 
	            		var tmr=new Date(a);
	            		
	            		tmr.setDate(tmr.getDate() + i);
	            		console.log("today"+tmr+""+i);
	            		var checkfrmt=$.datepicker.formatDate( "yy-mm-dd", tmr);
	            		console.log("check frmt"+checkfrmt);
	            		var j=i+1;
	            		$("#button"+j).append("<a id='lnk"+i+"' href='#' class='buttonlink'>"+checkfrmt+"</a>");	
	            		
	            	}
	             reload_cart();
	        }
		});
	});
	
});

//*********************called when add(+) button clicked******************************************//

$(document).ready(function(){
	$(document).on('click', '.mybtn', function(){ 
		 console.log("button clicked");
		 
		 var $btnprnt=$( this ).parent().get( 0 ).tagName;
		 
		 var prev=$(this).parent().prev('ul').attr('id');
		 var $namlist=$(this).parent().prev('div');
		 var namlistval=$namlist.text();
		 console.log("list name is here "+namlistval);
		 
		 var todotxt=$("#recipient-name").val(prev);
		 
		 var date=$(".active_class").children('a')[0].text;
		 $("#recipient-date").val(date);
		 console.log(todotxt);
		 console.log(prev);

		 
	
	
	
	}); 
	$("#addid").click(function(){
	
		var listid=$("#recipient-name").val();
		var dateid=$("#recipient-date").val();
		var todoid=$("#recipient-todo").val();
		console.log(listid);
		console.log(dateid);
		console.log(todoid);
		$("#recipient-todo").val("");
		
		var json = { "todotxt" : todoid, "date" : dateid };
		$.ajax({
	        url: "data/addtodo/"+listid,
	        data: JSON.stringify(json),
	        type: "POST",
	         
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(a) {
	             console.log(a);
	             $("#todotxt").val("");
	            console.log(a.listid);
	            $("#"+a.listid).append("<li class='content "+a.id+"'>"+a.todo+"<button id='"+a.id+"' class='buttn'></button></li>");
	        }
	    });
		
		
		
		
	});
	});
//**********************************called when addlist button clicked***********************************************//

$(document).ready(function(){
	$("#addlistname").click(function(){
		var listname=$("#recipient-listname").val();
		console.log(listname);
		
		
		$.ajax({
	        url: "data/addlistname/"+listname,
	        type: "GET",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(a) {
	        console.log(a);
	        var $container = $("#container");
            $container.append('<div id="list'+ $container.children().length +'" class="listdiv"><div class="listname">'+a.listname+'</div><ul id="'+a.id+'" class="mytodo"></ul> <div class="addbuttondiv"> <button id="button'+ $container.children().length+'" type="button" class="btn btn-default mybtn" data-toggle="modal" data-target="#exampleModal"> + </button> </div> </div>');
	        }
	    });
	});
	
});

//*********************called when delete button clicked******************************************//
$(document).ready(function(){
	$(document).on('click','.buttn',function(){
		console.log("delete button clicked");
		var id=$(this).attr("id");
		console.log(id);
		
		 $.ajax({
		        url: "data/deletetodo/"+id,
		        type:"POST",
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
		        success: function(a) {
		             console.log("success "+a);
		             $('.'+a).remove();
		        }
		        });
		        
		
		        
	});
	
});
//*********************default call to print date******************************************//
$(document).ready(function(){
	var today = new Date();
	console.log("today"+today);
	
for (i = 0; i < 6; i++) { 
	var tmr=new Date();
	
	tmr.setDate(tmr.getDate() + i);
	console.log("today"+tmr+""+i);
	var checkfrmt=$.datepicker.formatDate( "yy-mm-dd", tmr);
	console.log("check frmt"+checkfrmt);
	var j=i+1;
	$("#button"+j).append("<a id='link"+i+"' class='hdrlink' href='#'>"+checkfrmt+"</a>");	
}var prev=$("#button3").children('a')[0].text;
console.log("got the date "+prev);

});
</script>
<title>mainpage</title>
</head>
<body>
<%
if(session.getAttribute("email")==null || session.getAttribute("name")==null)
response.sendRedirect("../");

String name= (String)session.getAttribute("name");
String email= (String)session.getAttribute("email");
String pic=(String)session.getAttribute("picture");
%><div id="back">
<div id="hdr">
        <div id="img"><img src="<%=pic %>" alt="" class="img-circle" width="42" height="42"></div>
        <ul id="todoul">
        
        <li id="todolist">ToDoo List</li>
    </ul>
    <ul id="btnul" >
        
        <li id="li1" class="btnl"><button id="addlist"  type="button" class="btn btn-default btn" data-toggle="modal" data-target="#add-popdown" >Add List</button></li>
        <li id="li2" class="btnl"><a class="hdrlink" href="../logout"><button type="button" class="btn btn-default btn">Log Out</button></a></li>
        <li id="li3" class="btnl">Hi... <%=name %></li>
        </ul>
    </div>
    <div id="sechdr">
    <ul class="input-list style-1 clearfix">
    
    <li>
      <input type="text"  id="todotxt" placeholder="Add your todo here" class="focus" size="50">
    </li>
  	</ul >
  	<div id="datediv">
<button id="prev" class="prenxt"><a>prev</a></button>
<button id="button1" class="datebutton active_class"></button>
<button id="button2" class="datebutton"></button>
<button id="button3" class="datebutton"></button>
<button id="button4" class="datebutton"></button>
<button id="button5" class="datebutton"></button>
<button id="next" class="prenxt"><a>next</a></button>
</div>
  	
    </div>
 <div id="container">

</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">Add data </h4>
      </div>
      <div class="modal-body">
       <form>
         <div class="form-group">
            <input type="hidden"  class="form-control" id="recipient-name">
          </div>
          
          <div class="form-group">
            <input type="hidden" class="form-control" id="recipient-date">
          </div>

          <div class="form-group">
          <label for="recipient-todo" class="control-label">Todo:</label>
            <input type="text" class="form-control" id="recipient-todo">
          </div>  
          
        </form>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="addid" class="btn btn-primary" data-dismiss="modal">ADD</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="add-popdown" tabindex="-1" role="dialog" aria-labelledby="exampleModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModal">Add List </h4>
      </div>
      <div class="modal-body">
       <form>

          <div class="form-group">
          <label for="recipient-todo" class="control-label">add list here:</label>
            <input type="text" class="form-control" id="recipient-listname">
          </div>  
          
        </form>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="addlistname" class="btn btn-primary" data-dismiss="modal">ADD</button>
      </div>
    </div>
  </div>
</div>
  </div>
</body>
</html>
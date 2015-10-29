package todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import java.util.Properties;
import java.util.logging.Logger;
import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;






@Controller
@RequestMapping("/data")
public class DataController {

	@RequestMapping("/addlist")  
    public ModelAndView helloWorld() {  
      
    	return new ModelAndView("addlist");  
    
    }
	//*****************************please dont see this method just done for testing **************************************//
	@RequestMapping(value="/listname", method=RequestMethod.GET) 
    public ModelAndView getlistname(HttpServletRequest req) {  
		
	   System.out.println("got in");
	   
	   Properties prop = new Properties();
	    Session sessio = Session.getDefaultInstance(prop,null);
	    try{    
	        Message mimeMessage = new MimeMessage(sessio);
	        mimeMessage.setFrom(new InternetAddress("sriram.umapathy@a-cti.com"));
	        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sriram.umapathy@a-cti.com", "Mr./Ms. sri"));
	        mimeMessage.setSubject("ToDo List reminder");
	        mimeMessage.setText("");
	        
	    
       PersistenceManager pm = PMF.get().getPersistenceManager();
       Query q = pm.newQuery(User.class);
       List<User> userlist = null;
	
       try {
			userlist = (List<User>) q.execute();

		} finally {
			q.closeAll();
		}
       
       Iterator iter = userlist.iterator();
	   //******************first while loop***************//
       while (iter.hasNext())
	    {
	      User getlist = (User)iter.next();
	      String name=  getlist.getEmail();
	      String userid=  getlist.getId();
	      String username=  getlist.getUsername();
	    mimeMessage.setText("hi "+username+"\n\n");
	      
	      Query q2 = pm.newQuery(ListClass.class, "userid == value");
			q2.declareParameters("String value");
	        
	      List<ListClass> listname = null;
	  	
	       try {
				listname = (List<ListClass>) q2.execute(userid);

			} finally {
				q2.closeAll();
			}
	        
	       
	       Iterator iter2 = listname.iterator();
	       //**************second while loop********************//
	       while (iter2.hasNext())
		    {
	    	  ListClass getlistname=(ListClass)iter2.next();
	    	  Long longid=getlistname.getId();
	    	  String namelist= getlistname.getListname();
	    	  String listuserid= getlistname.getUserid();
	    	  Query q3 = pm.newQuery(Todo.class, "listid == value");  
	    	  q3.declareParameters("Long value");
	    	  
	    	  List<Todo> todo = null;
	  	  	
		       try {
					todo = (List<Todo>) q3.execute(longid);

				} finally {
					q3.closeAll();
				}
	    	  
	    	  
	    	  
		    }
	       
	    }
	    
       Transport.send(mimeMessage);
       System.out.println("Successfull Delivery.");
	    }catch (AddressException e) {
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
       
       
		
		ModelAndView view=new ModelAndView("redirect:login");
		return view;
    
	}
	
	
	
	//*************************adding the list name here*******************************//
	
	@RequestMapping(value="/addlistname/{listname}", method=RequestMethod.GET )
	@ResponseBody
	public String addinglistnamehere(@PathVariable String listname,HttpSession session){
		String email = (String) session.getAttribute("email");
		String userid = (String) session.getAttribute("id");
		System.out.println("document  "+email);
		System.out.println("document  "+userid);
		System.out.println("id"+listname);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ListClass listobj=new ListClass();
		listobj.setUserid(userid);
		listobj.setListname(listname);
		try {
			pm.makePersistent(listobj);	
		} finally {
			System.out.println("successfully added");
			pm.close();
		}
	
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(listobj);
		System.out.println(json);
		return json;
		}
	
	
	//*****************this is the default call to return the number of list for the user logged in ************//
	@RequestMapping(value="/get", method=RequestMethod.GET )
	@ResponseBody
	public String defaultcall(HttpSession session){
		String email = (String) session.getAttribute("email");
		String userid = (String) session.getAttribute("id");
		System.out.println("document  "+email);
		System.out.println("document  "+userid);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(ListClass.class, "userid == value");
		q.declareParameters("String value");
	    List<User> results = (List<User>) q.execute(userid);
	    Iterator iter = results.iterator();
	    ListClass getlist = null;
	    while (iter.hasNext())
	    {
	         getlist = (ListClass)iter.next();
	    }
	    
	    
	    Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(results);
		System.out.println(json);
		return json;
		}
	
	//********************this is the default call to return the data for the current date****************//
	
	@RequestMapping(value="/getdata", method=RequestMethod.GET )
	@ResponseBody
	public String defaultdatacall(HttpSession session){
		String email = (String) session.getAttribute("email");
		String userid = (String) session.getAttribute("id");
		System.out.println("document loaded "+email);
		System.out.println("document loaded "+userid);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    
		   // Date dateobj = formatter.parse(date);
		    Date obj=new Date();
		    String getdatestr=formatter.format(obj);
		    
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Todo.class, "userid == value");
		q.declareParameters("String value");
	    List<Todo> results = (List<Todo>) q.execute(userid);
		
	    Iterator iter = results.iterator();
	    Todo currentUser = null;
	    List<Todo> filter=new ArrayList<Todo>();
	    while (iter.hasNext())
	    {
	    	currentUser = (Todo)iter.next();
	         System.out.println(currentUser.getDate());
	         Date getdate=currentUser.getDate();
	         if(obj.compareTo(getdate)==0){
	         filter.add(currentUser);
	         }
	         String getdatestring=formatter.format(getdate);
	         if(getdatestring.equals(getdatestr)){
	        	 filter.add(currentUser);
	         }
	    }
		
	    pm.close();
	    
	    
	    
	    System.out.println("filtered data"+filter);
		
	    //Iterator iter = results.iterator();
	    //Todo currentUser = null;
	   // List<Todo> filter=new ArrayList<Todo>();
	   /* while (iter.hasNext())
	    {
	         currentUser = (Todo)iter.next();
	         System.out.println(currentUser.getDate());
	         Date getdate=currentUser.getDate();
	         if(dateobj.compareTo(getdate)==0){
	         filter.add(currentUser);
	         }
	         String getdatestr=formatter.format(getdate);
	         if(getdatestr.equals(date)){
	        	 filter.add(currentUser);
	         }
	    }*/
	    
	     
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(filter);
		System.out.println(json);
		return json;
		}
	
	//***********this method will delete the todo in the list using the list id******************//
	@RequestMapping(value="/deletetodo/{id}", method=RequestMethod.POST )
	@ResponseBody
	public String deletetodo(@PathVariable String id,HttpSession session){
		System.out.println("to delete"+id);
		long longid = Long.parseLong(id);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
            System.out.println("hi1");
			Todo obj = pm.getObjectById(Todo.class, longid);
			System.out.println("hi2");
			System.out.println(obj.getId());
			System.out.println(obj.getTodo());
			pm.deletePersistent(obj);
		} finally {
			pm.close();
		}
			
		
		return id;
	}
	
	//*******************this method will add the todo in the corresponding list***************@PathVariable("app") String app//
	@RequestMapping(value="/addtodo/{listname}/{date}", method=RequestMethod.POST )
	@ResponseBody
	public String addtodo(@PathVariable("listname") String listname,@PathVariable("date") Date date,@RequestBody String todo,HttpSession session) throws java.text.ParseException{
		System.out.println("susscess fully got in addtodo"+todo);
		System.out.println(listname);
		Long longid=Long.parseLong(listname);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ListClass obj = pm.getObjectById(ListClass.class, longid);
		String listnamegot=obj.getListname();
		System.out.println("list name===="+listnamegot);
		String email = (String) session.getAttribute("email");
	    String userid = (String) session.getAttribute("id");
	    String name=(String)session.getAttribute("name");
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(todo);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateformatted=formatter.format(date);
		Date dateobj = formatter.parse(dateformatted);
		System.out.println(""+dateobj);
		System.out.println(email +" document loaded "+date);
		String content = (String) jsonObject.get("todotxt");	
		String datestr = (String) jsonObject.get("date");
		System.out.println("date string check"+datestr);
		//Date dateobj = formatter.parse(datestr);
		System.out.println(content);
		Todo addtodo=new Todo();
		Long listid=Long.parseLong(listname);
		Date newdate=new Date();
		System.out.println(listid);
		
		addtodo.setListid(listid);
		addtodo.setUserid(userid);
		addtodo.setTodo(content);
		addtodo.setDate(dateobj);
		addtodo.setCreateddate(newdate);
		addtodo.setListname(listnamegot);
		addtodo.setUseremail(email);
		addtodo.setUsername(name);
		
		
		try {
			
			pm.makePersistent(addtodo);	
			System.out.println("run make persistent");
		} finally {
			System.out.println("successfully added");
			pm.close();
		}
		
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(addtodo);
		System.out.println(json);
		return json;
	}
	//***********this method returns the data for the incoming date*****************//
	@RequestMapping(value="/date/{date}", method=RequestMethod.GET )
	@ResponseBody
	public String datebuttoncall(@PathVariable Date date,HttpSession session) throws java.text.ParseException{
		String email = (String) session.getAttribute("email");
		String userid = (String) session.getAttribute("id");
		//Tue Oct 2015
		//Tue Dec 30 00:00:00 IST 2014
		//Tue Dec 2015
		//"EEE M/dd"
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateformatted=formatter1.format(date);
		Date dateobj = formatter1.parse(dateformatted);
		System.out.println(""+dateobj);
		System.out.println(email +" document loaded "+date);
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM yyyy");
		
		//Date dateobj = formatter.parse(date);
		//System.out.println(dateobj);
		//System.out.println(formatter.format(dateobj));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Todo.class, "userid == value");
		q.declareParameters("String value");
	    List<Todo> results = (List<Todo>) q.execute(userid);
		
	    Iterator iter = results.iterator();
	    Todo currentUser = null;
	    List<Todo> filter=new ArrayList<Todo>();
	    while (iter.hasNext())
	    {
	         currentUser = (Todo)iter.next();
	         System.out.println(currentUser.getDate());
	         Date getdate=currentUser.getDate();
	         String getdatestr=formatter1.format(getdate);
	         System.out.println(getdatestr+"   "+dateformatted);
	         if(getdatestr.equals(dateformatted)){
	        	 filter.add(currentUser);
	         }
	    }
		
	    pm.close();
		System.out.println("filtered value"+filter);
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(filter);
		System.out.println(json);
		return json;
	}
	
	//***************this method returns the date by sub 1 to the incoming date ***************//
	@RequestMapping(value="/date/prev/{date}", method=RequestMethod.GET )
	@ResponseBody
	public String prevdatebuttoncall(@PathVariable Date date,HttpSession session) throws java.text.ParseException{
		System.out.println("came into the prev call method"+date	);
		//Tue Oct 2015
		//Tue Dec 30 00:00:00 IST 2014
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM yyyy");
		//Date dateobj = formatter.parse(date);
		//System.out.println(dateobj);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR,-2);
		Date oneDayBefore= cal.getTime();
		
		ArrayList<String> adddate=new ArrayList<String>();
		for(int i=0;i<5;i++){
			Date oneDayBefo= cal.getTime();
			
			cal.setTime(oneDayBefo);
			cal.add(Calendar.DAY_OF_YEAR,-1);
			oneDayBefo= cal.getTime();
			String datebefore=formatter.format(oneDayBefo);
			
			
			adddate.add(datebefore);
			System.out.println(datebefore);
		}
		
		
		System.out.println("yest =="+oneDayBefore);
		String datebefore=formatter.format(oneDayBefore);
		System.out.println(formatter.format(oneDayBefore));
		
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(oneDayBefore);
		System.out.println(json);
		System.out.println("end of the prev call method");
		return json;
	}
	//***************this method returns the date by adding 1 to the incoming date ***************//
	@RequestMapping(value="/date/next/{date}", method=RequestMethod.GET )
	@ResponseBody
	public String nextdatebuttoncall(@PathVariable Date date,HttpSession session) throws java.text.ParseException{
		System.out.println(date	);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//Date dateobj = formatter.parse(date);
		//System.out.println(dateobj);
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.DAY_OF_YEAR,+1);
		 Date oneDayAfter= cal.getTime();
		System.out.println("yest =="+oneDayAfter);
		
		
		String datebefore=formatter.format(oneDayAfter);
		
		System.out.println(formatter.format(oneDayAfter));
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(oneDayAfter);
		System.out.println(json);
		return json;
	}

	@RequestMapping(value="/deletelist/{id}", method=RequestMethod.POST )
	@ResponseBody
	public String deletelist(@PathVariable Long id,HttpSession session){
		System.out.println("susscess fully got in deletelist "+id);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
            System.out.println("hi1");
			ListClass obj = pm.getObjectById(ListClass.class, id);
			System.out.println("hi2");
			System.out.println(obj.getId());
			System.out.println(obj.getListname());
			pm.deletePersistent(obj);
		} finally {
			pm.close();
		}
		
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(id);
		System.out.println(json);
		return json;
	}
	
}

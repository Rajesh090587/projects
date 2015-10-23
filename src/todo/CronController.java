package todo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import javax.servlet.http.*;
//import org.slf4j.LoggerFactory;
@Controller
public class CronController {
	private static final Logger log = Logger.getLogger(CronController.class.getName());
	//private static final Logger logger = LoggerFactory.getLogger(CronController.class);
	@RequestMapping(value="/sendmail", method=RequestMethod.GET) 
	public  void sendmail(){
		
		System.out.println("cron method");
		 PersistenceManager pm = PMF.get().getPersistenceManager();
	       Query q = pm.newQuery(User.class);
	       List<User> userlist = null;
		
	       try {
				userlist = (List<User>) q.execute();

			} finally {
				q.closeAll();
			}
	     Queue queue = QueueFactory.getQueue("subscription-queue");
	      // queue.purge();
	       Iterator iter = userlist.iterator();
		   //******************first while loop***************//
	       while (iter.hasNext())
		    {
	    	   User getuserlist = (User)iter.next();
	 	      String useremail=  getuserlist.getEmail();
	 	      String userid=  getuserlist.getId();
	 	      String username=  getuserlist.getUsername();
		   
		    
	 	     
	 	  queue.add(TaskOptions.Builder.withUrl("/taskcall").param("emailid", userid));

		    
		    
		    }
	 	  /*   Query q2 = pm.newQuery(Todo.class, "userid == value");
				q2.declareParameters("String value");
		        
		      List<Todo> todolist = null;
		      String dataadd ="";
		       try {
					todolist = (List<Todo>) q2.execute(userid);

				} finally {
					q2.closeAll();
				}
		        */
		       
		    //   Iterator iter2 = todolist.iterator();
		       //**************second while loop********************//
		  /*     while (iter2.hasNext())
			    {
		    	  Todo getlistname=(Todo)iter2.next();
		    String todousername=getlistname.getUsername();
		    String todouseremail=getlistname.getUseremail();
		    String todolistname=getlistname.getListname();
		    String todolisttodo=getlistname.getTodo();
		    Date tododate=getlistname.getDate();
			    
			    
			    
			   String data = "date :"+tododate+"\t"+ todolistname+"\t"+todolisttodo+"\n"; 
			    
			    dataadd +=data;
			    }
		
		
		*/
		//*****************mail properties***************
	   /* Properties prop = new Properties();
	    Session sessio = Session.getDefaultInstance(prop,null);
	    try{    
	        Message mimeMessage = new MimeMessage(sessio);
	        mimeMessage.setFrom(new InternetAddress("sriram.umapathy@a-cti.com"));
	        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sriram.umapathy@a-cti.com", "Mr./Ms. sri"));
	        mimeMessage.setSubject("ToDo List successfully added note");
	        mimeMessage.setText("Name :: sri\nEmail-id :: sriram.umapathy@a-cti.com\n\n\n"+dataadd);
	        Transport.send(mimeMessage);
	        System.out.println("Successfull Delivery.");
	    } catch (AddressException e) {
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }*/
	
	
	
}
	
	@RequestMapping(value="/taskcall", method=RequestMethod.POST) 
	public @ResponseBody void taskcall(@RequestParam("emailid") String userid ){	
		log.info("task executed");
		
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		
		 Query q2 = pm.newQuery(Todo.class, "userid == value");
			q2.declareParameters("String value");
	        
	      List<Todo> todolist = null;
	      String dataadd ="";
	      String email="";
	      String name="";
	       try {
				todolist = (List<Todo>) q2.execute(userid);

			} finally {
				q2.closeAll();
			}
	       Iterator iter2 = todolist.iterator();
	       while (iter2.hasNext())
		    {
	    	  Todo getlistname=(Todo)iter2.next();
	    String todousername=getlistname.getUsername();
	    name=todousername;
	    String todouseremail=getlistname.getUseremail();
	    email=todouseremail;
	    String todolistname=getlistname.getListname();
	    String todolisttodo=getlistname.getTodo();
	    Date tododate=getlistname.getDate();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String gotdate=formatter.format(tododate);
		    Date date=new Date();
		    String newdate=formatter.format(date);
		    if(newdate.equals(gotdate)){
		    String data = "date :"+tododate+"\t"+ todolistname+"\t"+todolisttodo+"\n"; 
		    dataadd +=data;
		    }
		    
		    }
	       if(dataadd != ""){
	       Properties prop = new Properties();
		    Session sessio = Session.getDefaultInstance(prop,null);
		    try{    
		        Message mimeMessage = new MimeMessage(sessio);
		        mimeMessage.setFrom(new InternetAddress("sriram.umapathy@a-cti.com"));
		        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "Mr./Ms."+name));
		        mimeMessage.setSubject("ToDo List Reminder for added note");
		        mimeMessage.setText("Name :: "+name+"\nEmail-id :: "+email+"\n\n\n"+dataadd);
		        Transport.send(mimeMessage);
		        System.out.println("Successfull Delivery.");
		    } catch (AddressException e) {
		        e.printStackTrace();
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    } catch (UnsupportedEncodingException e) {
		        e.printStackTrace();
		    }
	       }
	}
	
}

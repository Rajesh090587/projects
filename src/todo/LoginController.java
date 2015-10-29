package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gson.Gson;

import java.util.logging.Logger;

@Controller
public class LoginController {
	public static List<User> logstatus=new ArrayList<User>();
	@RequestMapping("/")  
    public ModelAndView helloWorld() {  
      
    	return new ModelAndView("login");  
    
    }
	
	
	@RequestMapping("/glogin")  
    public ModelAndView authen() {  
      
		return new ModelAndView("redirect:https://accounts.google.com/o/oauth2/auth?redirect_uri=http://localhost:8888/get&response_type=code&client_id=21895785036-j4gre0d0thmqn9e3ac13ab5bq4l5akt5.apps.googleusercontent.com&approval_prompt=force&scope=email&access_type=online");
    
	}  
	
	@RequestMapping("/get")  
    public ModelAndView getValue(@RequestParam("code") String note,HttpSession session) throws IOException {  
	
			String clientId="21895785036-j4gre0d0thmqn9e3ac13ab5bq4l5akt5.apps.googleusercontent.com";
			String clientSecret="XbW_r8rTTQRvR0nzbTaZfSYJ";
			String redirect_url="http://localhost:8888/get";
			System.out.println(""+note);
			String grant_type="authorization_code";
			URL obj = new URL("https://www.googleapis.com/oauth2/v3/token?client_id="+clientId+"&client_secret=" + clientSecret+ "&redirect_uri=" + redirect_url + "&grant_type="+ grant_type + "&code=" + note);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		    con.setDoOutput(true);
	    	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String response = "";
			while ((inputLine = in.readLine()) != null) {
				response+=inputLine;
			}
			in.close();
			System.out.println(response.toString());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			try {
				jsonObject = (JSONObject) parser.parse(response);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			String access_token = (String) jsonObject.get("access_token");
			System.out.println("got it ="+access_token);
			URL obj1 = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+access_token);
			HttpURLConnection conn = (HttpURLConnection) obj1.openConnection();
		    BufferedReader in1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine1;
			String responsee = "";
			while ((inputLine1 = in1.readLine()) != null) {
				responsee+=inputLine1;
			}
			in1.close();
			System.out.println(responsee.toString());
			JSONObject jsonObject2 = null;
			try {
				jsonObject2 = (JSONObject) parser.parse(responsee);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			String email = (String) jsonObject2.get("email");
		    String name=(String)  jsonObject2.get("name");
		    String id=(String)  jsonObject2.get("id");
		    
		    session.setAttribute("name", jsonObject2.get("name"));
			session.setAttribute("email", jsonObject2.get("email"));
			session.setAttribute("picture", jsonObject2.get("picture"));
			session.setAttribute("id",jsonObject2.get("id"));
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<User> results = null;
			Query q = pm.newQuery(User.class);
			q.setFilter("email =='"+email+"'");
			try {
				results = (List<User>) q.execute();
				if (results.isEmpty()) {
					User user = new User();
					user.setUsername(name);
					user.setEmail(email);
					user.setId(id);
					
					pm.makePersistent(user);
				}
				else{
					System.out.println("User exist already:");
				}
				
				Query q2 = pm.newQuery(User.class, "id == value");
				q2.declareParameters("String value");
			    List<User> result = (List<User>) q.execute(id);
			    Iterator iter = result.iterator();
			    User getlist = null;
			    while (iter.hasNext())
			    {
			         getlist = (User)iter.next();
			       logstatus.add(getlist);
			    }
					
			} finally {
				q.closeAll();
				pm.close();
			}
			System.out.println(logstatus);
			
			
			
			//logstatus.add();
			Gson gson = new Gson();
		    //convert java object to JSON format,
			//and returned as JSON formatted string
			String json = gson.toJson(logstatus);
			ChannelService channelService = ChannelServiceFactory.getChannelService();
			channelService.sendMessage(new ChannelMessage("logger", json));
			ModelAndView view=new ModelAndView("redirect:first");
			
			return view;	
	}
	
	
	@RequestMapping("first")  
	public String toHellopage(ModelMap model,HttpSession session){
		
		return "mainpage";
	} 
	
	
	@RequestMapping(value="/triggerchannel", method=RequestMethod.GET )
	@ResponseBody
	public String datebuttoncall(HttpSession session) throws java.text.ParseException{
		
		
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(logstatus);
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		channelService.sendMessage(new ChannelMessage("logger", json));
	return "triggered";
	}
	
	
	@RequestMapping("/logout")  
    public ModelAndView logout(HttpSession session) {  
		
		String email = (String) session.getAttribute("email");
		String userid = (String) session.getAttribute("id");
		
		String logout="logged out "+email+" "+userid;
		Iterator iter = logstatus.iterator();
		while (iter.hasNext())
	    {
		User	loggingoutuser = (User)iter.next();
			String id=loggingoutuser.getId();
			if(id.equals(userid)){
				iter.remove();
				
			}
	    }
		
		Gson gson = new Gson();
	    //convert java object to JSON format,
		//and returned as JSON formatted string
		String json = gson.toJson(logstatus);
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		channelService.sendMessage(new ChannelMessage("logger", json));
    	
		if(session != null){
			session.invalidate();
			
		}
    	return new ModelAndView("login");  
    
    }
	
}



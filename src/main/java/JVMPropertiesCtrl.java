import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.util.*;

/**
 *	Home controller
 * 
 * 	@author nicolatassini
 */
public class JVMPropertiesCtrl extends HttpServlet {
	
	private final String TITLE = "Heroku on Java: JVM properties";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	//Init body
		String h = "<html><head>"
			+ getMeta()
			+ "<title>" + TITLE + "</title><body>"
			+ getStyle()
			+ "</head>"
			+ "<body>"
			+ "<h1>" + TITLE + "</h1>"
			+ "<h4><a class=\"linkme\" href=\"http://about.me/nicola.tassini\">Contact Me</a></h4>" 
			+ "<br/>";
		
		//Print properties
		h += "<table>";
		Properties properties = System.getProperties();
		for(String parameterName : Collections.list((Enumeration<String>)properties.propertyNames())) {
			h += "<tr><td>"+parameterName+"</td><td>"+properties.getProperty(parameterName)+"</td></tr>";
		}
		h += "</table>";
		
		h += "</body></html>";
		
		//Render
        resp.getWriter().print(h);
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();   
    }
    
	private String getMeta() {
		return "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8;\" />"
		+"<meta http-equiv=\"Cache Control\" content=\"no-cache\"/>"
		+"<meta name=\"content-language\" content=\"en\"/>"
		+"<meta name=\"author\" content=\"Nicola Tassini\"/>"
		+"<meta name=\"keywords\" content=\"heroku, java, system, properties, jvm, version, paas\"/>"
		+"<meta name=\"description\" content=\"Display all the system properties of Java JVM on Heroku platform\"/>"
		+"<meta name=\"ROBOTS\" content=\"all\"/>";
	}
	
	private String getStyle() {
		return "<style type=\"text/css\">"
			+"body { color: #FF6C00; }"
			+"#tquilalink { position: absolute; top: 10px; right: 10px; }"
			+"#linkme { color: #FF6C00; }"
			+"</style>";
	}
}
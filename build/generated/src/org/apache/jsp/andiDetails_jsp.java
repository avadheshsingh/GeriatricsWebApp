package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.StringTokenizer;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public final class andiDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    public String printName(String value,String delim){              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split(delim);
		  String  name="";
		  for(int i=0;i<v.length;i++){
                   name += v[i].trim()+" ";}
             return name;
               }
    
    
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');

     //jena code
     try{
        //String s="Hypertension";
         String s=request.getParameter("name");
        //request.getParameter("val");
       // session.setAttribute("disease", s.trim());
        
        String Diagnosis="";
        String Symptoms="";
		Model model=FileManager.get().loadModel("C:/Users/DELL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
		String k="bB:"+s.trim()+" ";
		String queryString=
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
		"PREFIX : <http://xmlns.com/foaf/0.1/>"+
						"SELECT * WHERE { " +
		k+"rdfs:"+
		"label ?label"+";"+"rdfs:comment ?com"+
						"}";
		
		
                //fetching symptoms
                String queryStringSymptoms=
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
	"PREFIX : <http://xmlns.com/foaf/0.1/>"+
	"SELECT * WHERE { " +
	"?x bB:IsSymptomOf bB:"+s.trim() +";"+
	"rdfs:label ?label"+
	"}";
             Query querySymp=QueryFactory.create(queryStringSymptoms);
	QueryExecution qexecSymp=QueryExecutionFactory.create(querySymp,model);
        
        try
	{
	ResultSet resultsSymp=qexecSymp.execSelect();
	while(resultsSymp.hasNext())
	{
	QuerySolution solnSymp=resultsSymp.nextSolution();
	Literal nameSymp=solnSymp.getLiteral("label");	
	String symp=nameSymp.toString();
	int quit_positionSymp = symp.indexOf("^");
	Symptoms+=printName(symp.substring(0, quit_positionSymp) , "_")+",";
         
                 
       	}
        
	}
	finally
	{
	qexecSymp.close();
	}
        
        
        
		
		Query query=QueryFactory.create(queryString);
		QueryExecution qexec=QueryExecutionFactory.create(query,model);
	try
	{
		ResultSet results=qexec.execSelect();
		while(results.hasNext())
		{
			QuerySolution soln=results.nextSolution();
			Literal name=soln.getLiteral("label");
			Literal nu=soln.getLiteral("com");
			String hu=name.toString();
			
			
			int quit_position = hu.indexOf("^^");
                        
			int quit_position1 = nu.toString().indexOf("^^");                    
                                                                              
			//out.print(hu.substring(0, quit_position));  //disease name      
    
                       Diagnosis= nu.toString().substring(0, quit_position1);  //Diagnosis
                       
			
		}
	}
        catch(Exception e){out.print("Somthing goes wrong..plz try again.");}
	finally
	{
            out.print(Symptoms+"AviDiagnosis"+Diagnosis);
		qexec.close();
	}
        
}catch(Exception e){out.print("Something is wrong..try Again.");}  
    
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

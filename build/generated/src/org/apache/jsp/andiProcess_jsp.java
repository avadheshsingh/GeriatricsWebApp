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

public final class andiProcess_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


     public String printName(String value){
              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.split("_");
		  String  name="";
		  for(int i=0;i<v.length;i++){
             name += v[i].trim()+" ";}
             return name;
               }

 public String removeSpace(String value){
              
               StringTokenizer st = new StringTokenizer(value);  
		  String[] v = value.trim().split("\\s+");
		  String  name="";
                  int i;
		  for(i=0;i<v.length-1;i++){
                  name += v[i].trim()+"_";}
                  name +=v[i];
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

    try{
        String t=request.getParameter("name");
      
         StringTokenizer stk = new StringTokenizer(t);  
        String[] symptoms = t.split(",");
 
         String squery="";
        // jena code
        // FileManager.get().addLocatorClassLoader(hi.class.getClassLoader());
     
		Model model=FileManager.get().loadModel("C:/Users/DELL/Documents/NetBeansProjects/GeriatricsWebApp/web/data/geriatrics.owl");
	/*	for(int i=1;i<symptoms.length;i++)
                  squery ="bB:HasSymptoms bB:"+symptoms[i].trim()+";";
		String queryString=
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						"PREFIX bB: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
						"SELECT * WHERE { " +
		"?x bB:HasSymptoms bB:"+symptoms[0]+";"+squery+
		"rdfs:label  ?label."+
						"}";
		*/
         for(int i=1;i<symptoms.length;i++)                    
                squery +="union {?s  OL:HasSymptoms  OL:"+removeSpace(symptoms[i]).trim()+"}";
                String  queryString=
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "PREFIX OL: <http://purl.obolibrary.org/obo/geriatrics.owl#>"+
                "PREFIX : <http://xmlns.com/foaf/0.1/>"+
                "SELECT * WHERE { " +
                "   {?s  OL:HasSymptoms  OL:"+removeSpace(symptoms[0]).trim()+"}"+squery+
                "?s rdfs:label ?label"+
                "}";

		
		Query query=QueryFactory.create(queryString);
		QueryExecution qexec=QueryExecutionFactory.create(query,model);
	try
	{
		ResultSet results=qexec.execSelect();
		while(results.hasNext())
		{
			QuerySolution soln=results.nextSolution();
			Literal name=soln.getLiteral("label");
                        String m=name.toString();                        
                         StringTokenizer st = new StringTokenizer(m,"^^");
                         String disease=st.nextToken();
                       
			out.println(printName(disease)+",");
                       
		          }
	                      }catch(Exception e){ out.print("Something is wrong ");}
	              finally
	{
		qexec.close();
	}
        
        
        
        
     }catch(Exception e){out.print("Something is wrong..try again.");}  
    
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

<%-- 
    Document   : andiProcess
    Created on : 8 Jun, 2016, 4:52:43 PM
    Author     : Avadhesh
--%>

<%@page import="java.util.*"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.hp.hpl.jena.rdf.model.Literal"%>
<%@page import="com.hp.hpl.jena.query.QuerySolution"%>
<%@page import="com.hp.hpl.jena.query.ResultSet"%>
<%@page import="com.hp.hpl.jena.query.QueryExecutionFactory"%>
<%@page import="com.hp.hpl.jena.query.QueryExecution"%>
<%@page import="com.hp.hpl.jena.query.QueryFactory"%>
<%@page import="com.hp.hpl.jena.query.Query"%>
<%@page import="com.hp.hpl.jena.rdf.model.Model"%>
<%@page import="com.hp.hpl.jena.util.FileManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
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
    %>

<%
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
    %>
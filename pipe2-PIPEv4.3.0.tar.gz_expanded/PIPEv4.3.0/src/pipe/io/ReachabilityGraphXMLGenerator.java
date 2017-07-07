package pipe.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class ReachabilityGraphXMLGenerator {	
	
public ReachabilityGraphXMLGenerator(int var_numstates,int var_numtrans ){	
		try {
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.newDocument();
	    // root element	    
	    
	   
	    Element rootElement = doc.createElement("ReachabilityGraph");	       
	    doc.appendChild(rootElement);
	    
	    //Add the <geninfo> tag
		Element geninfo = doc.createElement("geninfo");
	    rootElement.appendChild(geninfo);	       
	              
	    //Add the <numberofstates> tag
	     Element numberofstates = doc.createElement("numberofstates");		  
		 numberofstates.appendChild(doc.createTextNode(String.valueOf(var_numstates)));
		 geninfo.appendChild(numberofstates);
		    
		 //Add the <transitionfrom> tag	   	 
		 Element numberoftransitions = doc.createElement("numberoftransitions");			  
		 numberoftransitions.appendChild(doc.createTextNode(String.valueOf(var_numtrans)));
		 geninfo.appendChild(numberoftransitions);
	    
	    
		// write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer =  transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result =  new StreamResult(new File("ReachabilityGraph.xml"));
        transformer.transform(source, result);
           
		} catch (Exception e) {
	         e.printStackTrace();
	      }
		
	} 
	
public void addTransitiontoXML(String var_transid, int var_transfrom, int var_transto){
		   try {
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document doc = dBuilder.parse("ReachabilityGraph.xml");
		   
		   // root element
		   Node rootElement = doc.getFirstChild();
		   	   
		   //Add the <transitions> tag
		   Element transitions = doc.createElement("transitions");
		   /*Attr attrType = doc.createAttribute("label");
	       attrType.setValue("T"+String.valueOf(var_transid));
	       transitions.setAttributeNode(attrType);*/
	       rootElement.appendChild(transitions);	       
	              
	        //Add the <transitionlabel> tag
		    Element transitionlabel = doc.createElement("transitionlabel");		  
		    transitionlabel.appendChild(doc.createTextNode(var_transid));
		    transitions.appendChild(transitionlabel);
		    
		    //Add the <transitionfrom> tag	   	 
		    Element transitionfrom = doc.createElement("transitionfrom");			  
		    transitionfrom.appendChild(doc.createTextNode("S"+String.valueOf(var_transfrom)));
		    transitions.appendChild(transitionfrom);
		    
		   //Add the <transitionto> tag	   	 
		    Element transitionto = doc.createElement("transitionfrom");			  
		    transitionto.appendChild(doc.createTextNode("S"+String.valueOf(var_transto)));
		    transitions.appendChild(transitionto);
		   
		     // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer =  transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         DOMSource source = new DOMSource(doc);
	         FileOutputStream file = new FileOutputStream("ReachabilityGraph.xml");
	         StreamResult result =  new StreamResult(file);
	         transformer.transform(source, result);
	       
		   } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
  
public void addStatetoXML(int var_stateid, boolean var_istangible, String var_marking){
		   try {
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document doc = dBuilder.parse("ReachabilityGraph.xml");
		   
		   Node rootElement = doc.getFirstChild();
		   
		   //Add the <states> tag
		   Element states = doc.createElement("states");
	       rootElement.appendChild(states);	       
	              
	        //Add the <statelabel> tag
		    Element statelabel = doc.createElement("statelabel");		  
		    statelabel.appendChild(doc.createTextNode("S"+String.valueOf(var_stateid)));
		    states.appendChild(statelabel);
		    
		    //Add the <statestatus>  tag	
		    String var_statestatus;
		    if (var_istangible)
		    	var_statestatus = "Tangible";
		    else 
		    	var_statestatus = "Vanishing";
		    
		    Element statestatus = doc.createElement("statestatus");			  
		    statestatus.appendChild(doc.createTextNode(var_statestatus));
		    states.appendChild(statestatus);
		    
		    //Add the <statemarking>  tag		    
		    Element statemarking = doc.createElement("statemarking");			  
		    statemarking.appendChild(doc.createTextNode(var_marking));
		    states.appendChild(statemarking);
		   
		     // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer =  transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         DOMSource source = new DOMSource(doc);
	         FileOutputStream file = new FileOutputStream("ReachabilityGraph.xml");
	         StreamResult result =  new StreamResult(file);
	         transformer.transform(source, result);
	       
		   } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
	   
}

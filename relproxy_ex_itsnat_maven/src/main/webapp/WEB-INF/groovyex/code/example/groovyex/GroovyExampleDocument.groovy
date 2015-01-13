
package example.groovyex;

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener

class GroovyExampleDocument
{
    def itsNatDoc // ItsNatHTMLDocument
    def textInput // ItsNatHTMLInputText
    def resultsElem // Element   

    GroovyExampleDocument(itsNatDoc,db)
    {
        this.itsNatDoc = itsNatDoc

        if (db.getCityList().size() != 3) 
            throw new RuntimeException("Unexpected");

        def doc = itsNatDoc.getHTMLDocument()
   
        def compMgr = itsNatDoc.getItsNatComponentManager()
        this.textInput = compMgr.createItsNatComponentById("inputId")
        
        def buttonElem = doc.getElementById("buttonId")
        buttonElem.addEventListener("click", 
            { 
             	Event evt -> def text = textInput.getText();
             	def comment = " YES I SAID THIS"; 
             	resultsElem.setTextContent(text + comment); 
         	} as EventListener, false)

        
        this.resultsElem = doc.getElementById("resultsId")       
    }
}

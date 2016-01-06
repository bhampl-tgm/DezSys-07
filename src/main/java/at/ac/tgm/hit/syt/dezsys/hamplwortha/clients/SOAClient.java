package at.ac.tgm.hit.syt.dezsys.hamplwortha.clients;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ein einfacher SOAP Client
 *
 * @version 1.0
 * @author Simon Wortha [simon.wortha@student.tgm.ac.at]
 */
public class SOAClient {

    private static final String NAMESPACE = "http://at/ac/tgm/hit/syt/dezsys/hamplwortha/soa";

    /**
     * Hier wird der SOAP Client gestartet
     * @param args
     */
    public static void main(String args[]) {
        System.out.print("search for: ");
        String title = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            title = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://localhost:8000/knowledge/search";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(title), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    /**
     * Printet die Nachricht des Servers in die Konsole
     * @param soapResponse Die Message von welcher ein Response erhalten werden soll
     * @throws Exception
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.println("\nResponse SOAP Message:");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

    /**
     * Erstellt einen SOAP Request
     * @param title Der Titel nach welchem gesucht werden soll
     * @return Die SOAP Message fuer den Service
     * @throws Exception
     */
    private static SOAPMessage createSOAPRequest(String title) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("knowledge", NAMESPACE);


        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestElem = soapBody.addChildElement("getKnowledgeRequest", "knowledge");
        SOAPElement titleElem = requestElem.addChildElement("title", "knowledge");
        titleElem.addTextNode(title);

        // SOAP Headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", NAMESPACE  + "getKnowledgeRequest");

        soapMessage.saveChanges();

        // Print the request message
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.jnadeau.xmlrest.jaxbandjersey;

/**
 *
 * @author jnadeau
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class XMLService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEcho() {

        String echo = "ECHO!";
        return echo;

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Customer getCustomerInXML(@PathParam("id") int id) {

        Customer customer = new Customer();
        customer.setName("Mr. Smith");
        customer.setPin(1234);
        customer.setId(id);

        return customer;

    }

}

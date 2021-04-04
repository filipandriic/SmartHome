/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthFilter;
import entiteti.Users;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import com.mycompany.userservice.resources.JavaEE8Resource;

/**
 *
 * @author Stefan
 */
@Provider
public class Filter implements ContainerRequestFilter{

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    //izmeniti telo ove metode
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeaderValues = requestContext.getHeaders().get("Authorization");
        
        if(authHeaderValues != null && authHeaderValues.size() > 0){
            String authHeaderValue = authHeaderValues.get(0);
            String decodedAuthHeaderValue = new String(Base64.getDecoder().decode(authHeaderValue.replaceFirst("Basic ", "")),StandardCharsets.UTF_8);
            StringTokenizer stringTokenizer = new StringTokenizer(decodedAuthHeaderValue, ":");
            String username = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();
            
            TypedQuery<Users> q = em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class);
            q.setParameter("username", username);
            List<Users> users = q.getResultList();
            
            if (users.size() != 1) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Korisnicko ime ili sifra nisu validni.").build();
                requestContext.abortWith(response);
                return;
            }
            
            Users user = users.get(0);
            if (!user.getPassword().equals(password)) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Korisnicko ime ili sifra nisu validni.").build();
                requestContext.abortWith(response);
                return;
            }
            JavaEE8Resource.currentUser = user.getIdUser();
            
            return;
        }
        
        Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Posaljite kredencijale.").build();
        requestContext.abortWith(response);
        return;
    }
    
}
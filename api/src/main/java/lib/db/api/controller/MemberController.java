package lib.db.api.controller;

import java.net.http.HttpResponse;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lib.db.api.beans.MemberBean;
import lib.db.api.objects.Member;

@RestController
@Path("/api/members")
public class MemberController {

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMember(@RequestBody Member member){

        try {
            if (member == null){
                return Response.status(Status.BAD_REQUEST).entity("").build();
            }
            MemberBean memberBean = new MemberBean();
       
            memberBean.addMember(member);

            return Response.status(Status.OK).build();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity("An error has occured: " + e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginMember(@RequestBody Member member,@Context HttpServletResponse response) throws SQLException{
        
        if (member == null){
            return Response.status(Status.CONFLICT).entity("No data found").build();
        }

        MemberBean memberBean = new MemberBean();
    
        Member results = memberBean.loginMember(member);
        String token = results.getToken();

        if (results != null){
            Gson gson = new Gson();
            String result = gson.toJson(results);

            Cookie cookie = new Cookie("authToken", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(900);
            response.addCookie(cookie);
            
            return Response.status(Status.OK).entity(result).build();

        } else{
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/auth/add")
    @Produces(MediaType.TEXT_PLAIN)
    public void getMember(){
        System.out.println("Allowed!");
    }

}

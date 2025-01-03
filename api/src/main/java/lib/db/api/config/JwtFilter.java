package lib.db.api.config;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            header = header.substring(7);

            try{
                JwtUtil.verifyToken(header);
                filterChain.doFilter(request, response);
            }
            catch (Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token Expired");
            }
            //request.setAttribute("claims", claims);
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token Missing");
        }
        // filterChain.doFilter(request, response);
    }
}

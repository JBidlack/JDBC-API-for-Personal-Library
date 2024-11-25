package lib.db.api.config;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtil {
    
    private static String key = Variables.getKey();
    private static String vite = Variables.getViteKey();

    public static String generateToken(String username){
        
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    } 

    public static Claims verifyToken(String token){

        try{
            return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        }
        catch(SignatureException e){
            throw new RuntimeException("Invalid request: " + e.getLocalizedMessage());
        }
        catch (ExpiredJwtException jwt){
            throw new RuntimeException("Token Expired: " + jwt.getLocalizedMessage());
        }
        catch (MalformedJwtException jwt){
            throw new RuntimeException("Token Malformed: " + jwt.getLocalizedMessage());
        }
        catch (UnsupportedJwtException jwt){
            throw new RuntimeException("Token Unsupported: " + jwt.getLocalizedMessage());
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException("No token found: " + e.getLocalizedMessage());
        }
    }

    public static String decryptData(String data){

        try{
            byte[] decodedBytes = Base64.getDecoder().decode(data);
            byte[] iv = new byte[16];
            System.arraycopy(decodedBytes, 0, iv, 0, iv.length);
            byte[] cipherText = new byte[decodedBytes.length-iv.length];
            System.arraycopy(decodedBytes, iv.length, cipherText, 0, cipherText.length);

            Cipher cypher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(vite.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cypher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            
            byte[] decryptedBytes = cypher.doFinal(cipherText);

            return new String(decryptedBytes, "UTF-8");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        return null;
    }


}

package lib.db.api.config;

import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtil {
    
    private static String key = Variables.getKey();
    private static String vite = Variables.getViteKey();

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
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
            throw new RuntimeException("Invalid token");
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

package lib.db.api.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import lib.db.api.config.ConfigDataSource;
import lib.db.api.config.JwtUtil;
import lib.db.api.objects.Book;
import lib.db.api.objects.Member;
import lib.db.api.objects.Role;

public class MemberDAO {
    
    @Autowired
    DataSource dataSource;
    Connection conn = null;
    PreparedStatement psmt = null;
    

    public MemberDAO(){}

    public void addMember(Member member) throws SQLException{
        try {
            boolean existingMember  = memberExists(member);

            
            conn = dataSource.getConnection();

            if(!existingMember && conn !=null){

                String insert = "INSERT INTO public.members(email, password, username, name, role) "+
                                "VALUES(?,?,?,?,?)";
                                psmt = conn.prepareStatement(insert);
                                psmt.setString(1, member.getEmail());
                                psmt.setString(2, member.getPassword());
                                psmt.setString(3, member.getUsername());
                                psmt.setString(4, member.getName());
                                psmt.setString(5, "BASE");
                                psmt.execute();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        // catch (PropertyVetoException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } 
        finally{
            if(!conn.isClosed() && conn != null){
                conn.close();
            }
        }
    }

    public Member loginMember(Member member) throws SQLException{
        String query =  "SELECT id, email, username, name, password " +
        "FROM public.members " +
        "WHERE username = ? ";

        try{
            
            conn = dataSource.getConnection();

            psmt = conn.prepareStatement(query);
            psmt.setString(1, member.getUsername());


            ResultSet rset = psmt.executeQuery();

            if(rset.next()){
                String storedPW = rset.getString("password");
                String decryptedStoredPW = JwtUtil.decryptData(storedPW);
                String inputPw = JwtUtil.decryptData(member.getPassword());

                if(decryptedStoredPW.equals(inputPw)){
                    Member exists = new Member();
                    exists.setId(rset.getInt("id"));
                    exists.setEmail(JwtUtil.decryptData(rset.getString("email")));
                    exists.setUsername(rset.getString("username"));
                    exists.setName(JwtUtil.decryptData(rset.getString("name")));
                    exists.setToken(JwtUtil.generateToken(exists.getUsername()));
                    

                    // Check to make sure multiple records don't exist which would indicate a problem in the DB
                    if(rset.next()){
                        throw new Exception("Multiple accounts found. Please contact an administrator");
                    }
                    conn.close();
                    return exists;
                }
            }            
        }
        catch (Exception e){
            System.err.println("An error has occurred: " + e.getMessage());
        }
        conn.close();
        return null;
    }

    public ArrayList<Book> getMemberBooks(Member member){
        String query =  "SELECT book.author, book.isbn, book.title, mb.rating " +
                        "FROM public.book book " +
                        "JOIN public.member_books mb " +
                        "ON mb.book_id =  book.id " +
                        "JOIN public.members m " +
                        "ON m.id = mb.member_id " +
                        "WHERE m.username = ?";
        ArrayList<Book> bookList = new ArrayList<>();

        try{
            
            conn = dataSource.getConnection();

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, member.getUsername());

            ResultSet rset = psmt.executeQuery();

            while (rset.next()){
                Book book = new Book();
                book.setAuthor(rset.getString("author"));
                book.setIsbn(rset.getString("isbn"));
                book.setTitle(rset.getString("title"));
                bookList.add(book);
            }

            return bookList;
        }
        catch(SQLException sql){
            System.err.println(sql.getMessage());
        } 
        // catch (PropertyVetoException e) {
        //             e.printStackTrace();
        //         }
        return null;
    }

    private boolean memberExists(Member member) throws SQLException{
        try{
           
            conn = dataSource.getConnection();

            String query =  "Select username from public.members "+
                            "where username = ?";
            
            PreparedStatement execute = conn.prepareStatement(query);
            execute.setString(1, member.getUsername());


            ResultSet rset = execute.executeQuery();
            if(rset.next()){
                return true;    
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            conn.close();
        }
        return false;
    }
}

// package lib.db.api.dao;

// import java.sql.Connection;
// import java.sql.SQLException;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// @Component
// public class Connect {

//     private DataSource datasource;

//     public Connect(DataSource datasource){
//         this.datasource = datasource;
//     }

//     @Autowired
//     public Connection openConnection() throws SQLException{
        
//         return datasource.getConnection();
//     } 

//     public void closeConnection(Connection conn) throws SQLException{
//         if(conn != null && !conn.isClosed()){
//             conn.close();
//         }
        
//     }
// }

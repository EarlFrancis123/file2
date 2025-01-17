package project.example.action;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import java.sql.Connection;                
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import project.example.model.Evacuation;

public class ListEvacuation extends ActionSupport {
    ArrayList<Evacuation> evacuations = new ArrayList<Evacuation>();
    public ArrayList listOfFirstNames = new ArrayList();
    public ArrayList<Evacuation> getEvacuations() {  
        return evacuations;  
    }  
    public void setList(ArrayList<Evacuation> evacuations) {  
        this.evacuations = evacuations;  
    }  

    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "Loveleycute30");

            if (connection != null) {
                String sql = "SELECT * FROM evacuation";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Evacuation evacuation=new Evacuation();
                    evacuation.setIdevacuation(rs.getInt(1));   
                    evacuation.setEvacuationName(rs.getString(2));   
                    evacuation.setEvacuationNumber(rs.getString(3)); 
                    evacuation.setEvacuationAddress(rs.getString(4));
                    evacuation.setEvacuationBarangay(rs.getString(5));
                    evacuation.setEvacuationCapacity(rs.getString(6));
                    evacuation.setEvacuationStatus(rs.getString(7));
                    evacuations.add(evacuation);  
                    listOfFirstNames.add(evacuation.getEvacuationBarangay());
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    public String displayUser() {   
        return SUCCESS;
    }
    public void setEvacuations(ArrayList<Evacuation> evacuations) {
        this.evacuations = evacuations;
    }
    public ArrayList getListOfFirstNames() {
        return listOfFirstNames;
    }
    public void setListOfFirstNames(ArrayList listOfFirstNames) {
        this.listOfFirstNames = listOfFirstNames;
    }

    
}

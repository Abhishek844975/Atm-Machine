import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/CheckAmount")
public class CheckAmount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String url = "jdbc:mysql://localhost:3306/atm_machine";
    public static final String user = "root";
    public static final String password = "Abhi@249408";

    public CheckAmount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Use POST method to submit data.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        double totalAmount = 500000;
        

        try {
           
            String user_name=request.getParameter("user_name");
             String  user_choice=request.getParameter("option");
             String withdraw_amount=request.getParameter("withdrawAmount");
             
             String deposite_amount=request.getParameter("depositeAmount");
             
             String password=request.getParameter("user_pin");
             
             double withdrawAmount = 0;
             double depositeAmount = 0;

             if ( withdraw_amount != null && ! withdraw_amount.isEmpty()) {
                 withdrawAmount = Double.parseDouble( withdraw_amount);
             }

             if (deposite_amount != null && !deposite_amount.isEmpty()) {
                 depositeAmount = Double.parseDouble(deposite_amount);
             }
             int user_pin = 0;
             

             if (password != null && !password.isEmpty()) {
                 
             }
            // 2. Database Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root","Abhi@249408");

            // 3. CORRECTED QUERY: 4 columns = 4 question marks
            String query = "INSERT INTO accounts(customer_name, withdraw_amount, deposit_amount, password) VALUES(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user_name);
            pst.setDouble(2, withdrawAmount);
            pst.setDouble(3, depositeAmount);
            pst.setString(4, password);

            int rows = pst.executeUpdate();
            System.out.println("Rows inserted into DB: " + rows);

            // 5. Business Logic & Output
            int choice = Integer.parseInt(user_choice);
            out.println("<html><body>");
            out.println("<h2>ATM Transaction Receipt</h2>");
            out.println("<p>Customer Name: " + user_name + "</p>");

            switch(choice) {
                case 1: // Check Balance
                    out.println("<h3>Your Current Balance: " + totalAmount + "</h3>");
                    break;
                case 2: // Withdraw
                    if(withdrawAmount <= totalAmount) {
                        double currentBalance = totalAmount - withdrawAmount;
                        out.println("<h3>Withdrawal Successful. New Balance: " + currentBalance + "</h3>");
                    } else {
                        out.println("<h3 style='color:red;'>Insufficient Balance!</h3>");
                    }
                    break;
                case 3: // Deposit
                    if(depositeAmount >= 100) {
                        double newBalance = totalAmount + depositeAmount;
                        out.println("<h3>Deposit Successful. New Balance: " + newBalance + "</h3>");
                    } else {
                        out.println("<h3 style='color:red;'>Minimum Deposit should be 100.</h3>");
                    }
                    break;
                default:
                    out.println("<h3>Invalid Option Selected</h3>");
            }
            
            out.println("<p>Status: Data saved to Database successfully!</p>");
            out.println("</body></html>");

            con.close();

        } catch(Exception e) {
            e.printStackTrace(); 
            out.println("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
        }
    }
}
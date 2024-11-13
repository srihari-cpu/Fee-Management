import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewEmployee {

	JFrame frame;
	JTable employee;
	
	public ArrayList<Employee> getEmployee(){
		ArrayList<Employee> arr=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery("select * from employee");
			while(rs.next()) {
				Employee e = new Employee();
				e.setFirstname(rs.getString(1));
				e.setLastname(rs.getString(2));
				e.setUsername(rs.getString(3));
				arr.add(e);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return arr;
	}
	
	public ViewEmployee() {
		frame=new JFrame("DevElet institute - Employee List");
		
		DefaultTableModel modal=new DefaultTableModel();
		String colNames[]= {"FirstName","LastName","UserName"};
		modal.setColumnIdentifiers(colNames);
		for(int i=0;i<getEmployee().size();i++) {
			Object row[]= {getEmployee().get(i).getFirstname(),getEmployee().get(i).getLastname(),getEmployee().get(i).getUsername()};
			modal.addRow(row);
		}
		JPanel pan=new JPanel();
		employee = new JTable();
		employee.setModel(modal);
		JScrollPane jsp=new JScrollPane(employee);
		pan.setLayout(new BorderLayout());
		pan.add(jsp,BorderLayout.CENTER);
	
		frame.add(pan);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		new ViewEmployee();
	}

}

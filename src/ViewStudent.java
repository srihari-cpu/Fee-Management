
import java.awt.BorderLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewStudent {
	
	JFrame frame;
	JTable student;
	
	public ArrayList<Student> getStudent() {
		ArrayList<Student> arr=new ArrayList<Student>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery("select * from student");
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setCourse(rs.getString(3));
				s.setTotalamount(rs.getInt(4));
				s.setAmountpaid(rs.getInt(5));
				s.setAmountpending(rs.getInt(6));
				arr.add(s);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return arr;
	}
	
	
	public ViewStudent() {
		frame=new JFrame("DevElet institute - Student List");
		
		DefaultTableModel modal= new DefaultTableModel();
		String columnNames[]= {"ID","Name","Course","Total Fee","Fee paid","Fee Balance"};
		modal.setColumnIdentifiers(columnNames);
		for(int i=0;i<getStudent().size();i++) {
			Object row[]= {getStudent().get(i).getId(),getStudent().get(i).getName(),getStudent().get(i).getCourse(),getStudent().get(i).getTotalamount(),getStudent().get(i).getAmountpaid(),getStudent().get(i).getAmountpending()};
			modal.addRow(row);
		}
		
		JPanel pan=new JPanel();
		student = new JTable();
		student.setModel(modal);
		JScrollPane jsp=new JScrollPane(student);
		pan.setLayout(new BorderLayout());
		pan.add(jsp,BorderLayout.CENTER);
	
		frame.add(pan);
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ViewStudent();
	}
}

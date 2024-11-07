import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchStudent {

	JFrame frame;
	JLabel header,segment,id;
	JTextField i;
	JButton logout,submit,clear;
	
	public SearchStudent() {
		frame=new JFrame("DevElet Institute - Search Student");
		segment = new JLabel("DevElet Institute");
		header=new JLabel("Search Student");
		logout=new JButton("Logout");
		
		id=new JLabel("Enter Student ID");
		i=new JTextField(20);
		submit=new JButton("Submit");
		clear=new JButton("Clear");
		
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		
		container.add(segment);
		container.add(logout);
		container.add(header);
		container.add(id);
		container.add(i);
		container.add(submit);
		container.add(clear);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmployeeLogin();	
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i.setText("");
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(i.getText());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					String sql="select * from student where id=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1, id);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
					
						String name=rs.getString(2);
						String course=rs.getString(3);
						int tf=rs.getInt(4);
						int pf=rs.getInt(5);
						int bp=rs.getInt(6);
						frame.dispose();
						new EditStudent(id,name,course,tf,pf,bp);
						
					}
				} catch (Exception e2) {
					System.err.println(e2.toString());
				}
			}
		});
	}

	public static void main(String[] args) {
		new SearchStudent();
	}
}

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteEmployee {

	JFrame frame;
	JLabel header,segment,username;
	JTextField user;
	JButton logout,submit;
	
	public DeleteEmployee() {
		frame=new JFrame("DevElet Institute - Delete Emmployee");
		segment = new JLabel("DevElet Institute");
		header = new JLabel("Delete Employee");
		
		username = new JLabel("Enter username");
		user = new JTextField(30);
		logout = new JButton("Logout");
		submit = new JButton("Delete Record");
		
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		
		container.add(header);
		container.add(segment);
		container.add(username);
		container.add(user);
		container.add(submit);
		container.add(logout);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminLogin();
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String u=user.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					String sql="delete from employee where username = ?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setString(1,u);
					pst.executeUpdate();
					
					JDialog d= new JDialog(frame,"Success");
					JLabel l= new JLabel("Employee : "+u +" is removed Successfully");
					JButton ok=new JButton("Ok");
					d.setLayout(new FlowLayout());
					d.add(l);
					d.add(ok);
					d.setVisible(true);
					d.setSize(500,100);
					
					ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
							frame.dispose();
							new AdminHome();
						}
					});
			
				}catch(Exception ex) {
					System.out.println(ex.toString());
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new DeleteEmployee();
	}
}

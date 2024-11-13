import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddEmployee {
	
	JFrame frame;
	JLabel fname,lname,segment,paswd,header;
	JTextField first,last;
	JPasswordField password;
	JButton submit,clear,logout;
	
	public AddEmployee() {
		frame=new JFrame("DevElet INSTITUTE - Add employee ");
		header=new JLabel("DevElet institute");
		logout = new JButton("Logout");
		segment = new JLabel("Add Employee");
		
		fname = new JLabel("First Name");
		first = new JTextField(30);
		lname = new JLabel("Last Name");
		last = new JTextField(30);
		paswd = new JLabel("password");
		password = new JPasswordField(30);
		submit =new JButton("Add Employee");
		clear = new JButton("Clear");
		
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		
		container.add(header);
		container.add(segment);
		container.add(fname);
		container.add(first);
		container.add(lname);
		container.add(last);
		container.add(paswd);
		container.add(password);
		container.add(submit);
		container.add(clear);
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
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				first.setText("");
				last.setText("");
				password.setText("");
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random rand = new Random();
				String fname=first.getText();
				String lname=last.getText();
				char[] ch=password.getPassword();
				String pass=new String(ch);
				
				String user;
				user = Character.toString(fname.charAt(0));
				if(lname.length()>5) {
					user+=lname.substring(0,5);
				}else {
					user+=lname;
				}
				user+=Integer.toString(rand.nextInt(99));
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					String sql="insert into employee values(?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1,fname);
					pst.setString(2,lname);
					pst.setString(3,user);
					pst.setString(4,pass);
					pst.executeUpdate();
					//dialog box for display success msg and username
					JDialog d = new JDialog(frame,"Success");
					JLabel l= new JLabel("Employee Added Successfully with username : "+user );
					JButton ok = new JButton("Ok");
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
					
					pst.close();
					conn.close();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new AddEmployee();
	}
}

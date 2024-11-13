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

public class DeleteStudent {

	JFrame frame;
	JLabel header,segment,id;
	JTextField i;
	JButton logout,submit,clear;
	
	public DeleteStudent() {
		frame=new JFrame("DevElet Institute - Delete Student");
		segment = new JLabel("DevElet Institute");
		header=new JLabel("Delete Student");
		
		logout=new JButton("Logout");
		id=new JLabel("Enter Student ID");
		i=new JTextField(20);
		submit=new JButton("Delete Student");
		clear=new JButton("Clear");
		
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		
		container.add(segment);
		container.add(header);
		container.add(id);
		container.add(i);
		container.add(submit);
		container.add(clear);
		container.add(logout);
		
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
					String sql="delete from student where id=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1, id);
					pst.executeUpdate();
					
					JDialog d=new JDialog();
					JLabel l = new JLabel("Student ID "+id +" is Deleted Successfully");
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
							new EmployeeHome();
						}
					});
				}catch(Exception ex) {
					System.err.println(ex.toString());
				}
			}
		});
	}

	public static void main(String[] args) {
		new DeleteStudent();
	}
}

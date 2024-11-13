import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminHome {

	JFrame frame;
	JLabel header;
	JButton add,delete,view,empView,logout;
	
	public AdminHome() {
		frame= new JFrame("DevElet intstitute - Admin Home");
		header = new JLabel("DevElet institute ");
		logout = new JButton("Log out here");
		
		add=new JButton("Add Employee");
		delete = new JButton("Delete Employee");
		empView = new JButton("View Employees Details");
		view = new JButton("View Student Details");
		
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(add);
		container.add(delete);
		container.add(empView);
		container.add(view);
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
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddEmployee();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new DeleteEmployee();
			}
		});
		
		empView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ViewEmployee();				
			}
		});
		
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ViewStudent();
			}
		});
	}
	
	public static void main(String[] args) {
		new AdminHome();
	}
}

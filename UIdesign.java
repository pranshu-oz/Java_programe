import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
public class UIdesign implements ActionListener{
JFrame fr=new JFrame("Chat Box");
JPanel p1,p2,p3,p4,p5;
JLabel l1;
JTextField msgbox;
static JTextArea msgcontainer;
JButton logout,send;
static JList user=new JList();
DataOutputStream dos;
DataInputStream dis;
Socket socket;
String msg=null;
int inc=0;
SocketAddress sa;

	public void splash() {
		JFrame fr;
		JLabel l1,l2;
		JProgressBar pb;
		JPanel p1,p2,p3;
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		fr=new JFrame("Splash Window");
		fr.setVisible(true);
		fr.setLocation(300,50);
		fr.setLayout(new FlowLayout());
		fr.setSize(550,300);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    l2=new JLabel(new ImageIcon("talk.jpg"));
		
		p1=new JPanel(new FlowLayout());
		p2=new JPanel(new FlowLayout());
		p3=new JPanel(new FlowLayout());
		
		l1=new JLabel("CHAT BOX");
		l1.setFont(new Font("Modern No. 20",Font.BOLD,34));
		l1.setForeground(new Color(255,0,0));
		pb=new JProgressBar(0,100);
		pb.setValue(0);
		pb.setBackground(new Color(255,0,0));
		pb.setForeground(new Color(0,0,0,225));
		pb.setPreferredSize(new Dimension(400,30));
		l2.setLayout(new BorderLayout());
		l2.add(l1,BorderLayout.NORTH);
		l2.add(pb,BorderLayout.SOUTH);
		fr.add(l2);
		fr.pack();
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				inc++;
				pb.setString("please wait : "+inc+"%");
				pb.setValue(inc);
				pb.setStringPainted(true);
				if(inc==100) {
					fr.dispose();
					new Login();
				}
			}
		};
		new Timer(40,al).start();
	}
	
	public void uiFrame() {
		//define frame
		Dimension d=new Dimension(900,600);
		fr.setVisible(true);
		fr.setLocation(300,50);
		fr.setLayout(new FlowLayout());
		fr.setPreferredSize(d);
		fr.getContentPane().setBackground(Color.DARK_GRAY);
		fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//define label
		l1=new JLabel("Welcome To ChatBox");
		l1.setForeground(Color.RED);
		l1.setFont(new Font("Comic Sans MS",Font.BOLD,34));
		//define TextBoxes
		msgbox=new JTextField(20);
		msgbox.setPreferredSize(new Dimension(50,40));
		msgbox.setFont(new Font("calibri",Font.PLAIN,22));
		msgbox.setForeground(Color.BLACK);
		msgcontainer=new JTextArea(23,40);
		msgcontainer.setEditable(false);
		msgcontainer.setBackground(Color.BLACK);
		msgcontainer.setForeground(Color.WHITE);
		msgcontainer.setFont(new Font("Courier New",Font.BOLD,16));
		
		//define Button
		logout=new JButton("Log Out");
		logout.setBackground(Color.CYAN);
		send=new JButton("Send");
		send.setBackground(Color.CYAN);
		
		//define List 
		user.setBackground(Color.WHITE);
		user.setForeground(Color.RED);
		user.setPreferredSize(new Dimension(300,200));
		
		//define panel
		p1=new JPanel();
		p1.add(l1,"NORTH");
		p1.setBackground(Color.DARK_GRAY);
		p2=new JPanel(new GridLayout(1,2));
		p2.setBackground(Color.DARK_GRAY);
		p3=new JPanel(new FlowLayout());
		p4=new JPanel(new FlowLayout());
		p5=new JPanel();
		p3.setBackground(Color.DARK_GRAY);
		p4.setBackground(Color.DARK_GRAY);
		p5.setBackground(Color.DARK_GRAY);
		p2.add(new JScrollPane(msgcontainer));
		p2.add(new JScrollPane(user));
		p3.add(msgbox);
		p4.add(send);
		p4.add(logout);
		
		fr.add(p1);
		fr.add(p2);
		fr.add(p3);
		fr.add(p4);
		fr.add(p5);
		fr.pack();
		setConnection();
		Thread t=new Thread(new MyClient(dis,dos,socket));
		t.start();
		send.addActionListener(this);
		logout.addActionListener(this);
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				logoutSession();
			}
		});
	}

	public static void main(String[] args) {
		
		new UIdesign().splash();
	}

	@Override
	public void actionPerformed(ActionEvent ex) {
		// TODO Auto-generated method stub
		if(ex.getSource()==send) {
			try {
				String msg=msgbox.getText();
				dos.writeUTF(msg);
				dos.flush();

			} catch (IOException e) {e.printStackTrace();}
		}else if(ex.getSource()==logout) {
			logoutSession();
		}
	}
	
	public void setConnection() {
		
		try {
			socket=new Socket();
			sa=new InetSocketAddress(Login.ipAddress,3333);
			socket.connect(sa,30000);
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(Login.user);
			dos.flush();
		} catch (IOException e) {msgcontainer.append(e.getMessage());}
	}
	public void logoutSession() {
		if(socket==null) {
			sa=null;
			fr.dispose();
			new Login();
		}
		try {
			dos.writeUTF("LogoutTrue");
			dos.flush();
			Thread.sleep(2000);
			socket=null;
			fr.dispose();
			new Login();
		}catch(Exception e) {msgcontainer.append(e.getMessage());}
	}
		
}

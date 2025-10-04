import java.net.*;
import java.util.*;
import java.io.*;
public class MyServer {
ServerSocket ss;
Socket s;
DataOutputStream dos;
DataInputStream dis;
ArrayList user=new ArrayList();
ArrayList al=new ArrayList();

	public MyServer() {
		try {
			ss=new ServerSocket(3333);
			System.out.print("Waiting for connection...");
			while(true) {
				s=ss.accept();
				System.out.println("server is started...");	
				Thread st=new Thread(new ServerThread(al,s,user));
				st.start();
				System.out.println("conection established-->"+ss);
		} 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyServer();
	}
}

class ServerThread implements Runnable{
ArrayList al,user;
Socket s;
String username;
	public ServerThread(ArrayList al,Socket s,ArrayList user) {
		this.al=al;
		this.s=s;
		this.user=user;
		String send;
		System.out.println("constructor call..");
		
		try {
			DataInputStream dis=new DataInputStream(s.getInputStream());
			username=dis.readUTF();
			user.add(username);
			al.add(s);
			send="****Welcome "+username+" Login Successfull****";
			shareToAll(updateFileData()+"\n"+send);
			shareToAll("list "+user.toString());
			System.out.println("massege send for box : "+username);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	@Override
	public void run() {
		String message;
		try {
			DataInputStream dis=new DataInputStream(s.getInputStream());
			while(true) {
				message=dis.readUTF();
				if(message.equals("LogoutTrue")) {
					System.out.println("Server break for logout");
					break;
				}
				shareToAll(username+" said :- "+message);
				System.out.println("massege sent to method");
			}
			DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			dos.writeUTF("LogoutTrue");
			dos.flush();
			user.remove(username);
			System.out.print(user.toString());
			shareToAll("### "+username+" Logout!!! ###");
			shareToAll("list "+user.toString());
			s.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public void shareToAll(String str) {
		Iterator it=al.iterator();
		while(it.hasNext()) {
			Socket socket=(Socket)it.next();
			try {
				DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(str);
				if(!str.startsWith("list ")) {
					fileDataEntry(str+"\n");
				}
				dos.flush();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	//reading From file
	public String updateFileData() {
		String str="";
		try {
			int ch;
			FileReader fr=new FileReader("E:\\fileDataSave.txt");
			while((ch=fr.read())!=-1) {
				str+=Character.toString((char)ch);
			}
			fr.close();
			
		} catch (Exception e) {e.printStackTrace();}
		return str;
	}
	//writing to file
	public void fileDataEntry(String str) {
		try {
			String pretext="";
			int ch=0;
			File file=new File("E:\\fileDataSave.txt");
			FileReader fir=new FileReader(file);
			while((ch=fir.read())!=-1) {
				pretext+=Character.toString((char)ch);
			}
			fir.close();
			FileWriter fw=new FileWriter(file);
			fw.write(pretext+"\n"+str);
			fw.close();
		} catch (IOException e) {e.printStackTrace();}
	}

}
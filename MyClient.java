import java.io.*;
import java.net.*;
import java.util.*;
public class MyClient implements Runnable{
	DataInputStream dis;
	DataOutputStream dos;
	Socket s;
	String str;
	
	public MyClient(DataInputStream dis,DataOutputStream dos,Socket s) {
		this.dis=dis;
		this.dos=dos;
		this.s=s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(str!="stop") {
			try {
				str=dis.readUTF();
				if(str.startsWith("list")) {
					setSeprateData(str);
				}else if(str.startsWith("LogoutTrue")){
					break;
				}else {
				UIdesign.msgcontainer.append(str+"\n");
				}
			} catch (IOException e) {UIdesign.msgcontainer.append(e.getMessage()+"\n");}
		}
		
	}
	public void setSeprateData(String data) {
		Vector vec=new Vector();
		data=data.replace("list", "");
		data=data.replace("[", "");
		data=data.replace("]", "");
		
		StringTokenizer st=new StringTokenizer(data,",");
		while (st.hasMoreElements()) {
			 vec.add(st.nextElement());
		}
		UIdesign.user.setListData(vec);
	}
	
}
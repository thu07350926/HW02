import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class talkarea extends JTextArea
{
	final String line_ad = "txt/line.txt";//文本地址
	final String name_ad = "txt/name.txt";
	public talkarea() 
	{
		
	}
	void read_line()
	{		
		try
		{
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//取得角色名稱
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader(line_ad));//取得文本
			String line;
			
			while(txt.ready())//一條一條讀
			{
				line=txt.readLine();
				line=line.replaceAll("主角", player_name);
				this.append(line);
				this.append("\r\n");
			}
			
			txt.close();
			
		}catch (IOException e){};
	}
}

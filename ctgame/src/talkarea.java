import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class talkarea extends JTextArea
{
	final String line_ad = "txt/line.txt";//�奻�a�}
	final String name_ad = "txt/name.txt";
	public talkarea() 
	{
		
	}
	void read_line()
	{		
		try
		{
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//���o����W��
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader(line_ad));//���o�奻
			String line;
			
			while(txt.ready())//�@���@��Ū
			{
				line=txt.readLine();
				line=line.replaceAll("�D��", player_name);
				this.append(line);
				this.append("\r\n");
			}
			
			txt.close();
			
		}catch (IOException e){};
	}
}

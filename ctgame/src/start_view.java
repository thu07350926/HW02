import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class start_view extends JFrame implements ActionListener , KeyListener
{
	private static final long serialVersionUID = 1L;//我看上面的驚嘆號不順眼 順手點一下就出現了

	public static int bg_flag = 0;//判斷目前在哪個頁面(在前面宣告後面都可以用  0=開始畫面  1=主畫面  2=說明畫面
	public static int dodge_bg_flag = 0; //判斷迴避頁面有沒有被啟動
	
	static start_view frm = new start_view();//設置frame
	static JPanel S_pnl = new JPanel();//設置start_panel
	static JPanel M_pnl = new JPanel();//設置main_panel
	static JPanel E_pnl = new JPanel();//設置explain_panel									
	static JPanel img_pnl = new JPanel();//設置玄學panel
	
	static ImageIcon bgi=new ImageIcon("img/background.jpg");//將圖片導入
	static ImageIcon exp=new ImageIcon("img/explain.jpg");//將圖片導入
	static ImageIcon dodge=new ImageIcon("img/dodge.jpg");//將圖片導入
	static JLabel show_bgi=new JLabel(bgi);//給圖片一個家
	
	static JButton start=new JButton("開始回憶");//各種開始畫面按鈕
	static JButton explain=new JButton("操作方法");
	static JButton load=new JButton("追憶之旅");
	static JButton leave=new JButton("離開遊戲");
	static JButton back=new JButton("回標題");//主畫面按鈕
	static JButton save=new JButton("記憶");
	
	static JPanel name_pnl = new JPanel();//設置name_panel
	static JLabel find_name=new JLabel("請先輸入名子");//設定名子
	static JTextArea set_name=new JTextArea("");
	static JButton save_name=new JButton("確定");
	
	static talkarea talk=new talkarea();//本體 對話框!!!
	static JTextArea howtoplay=new JTextArea("");//說明文本
	
	static backgroundsound p=new backgroundsound();
	
	static int linelength = 0;
	static Timer tm;
	static int counter = 0;
	static int line_flag = 0;
	static int lineflag = 0;
	static int line_temp = 0;
	static char word[];
	static String line_ad = "txt/line.txt";//文本地址
	static String name_ad = "txt/name.txt";
	
	@SuppressWarnings("static-access")//這個也是看不爽之後就出來的
	public static void main(String[] args) 
	{
		start_page();
		
		p.playmusic();

		frm.setLayout(null);
		frm.setTitle("對話遊戲專題");
		frm.setSize(bgi.getIconWidth(), bgi.getIconHeight());
		
		frm.setDefaultCloseOperation(EXIT_ON_CLOSE);//從貓力奧射擊那邊抄過來的
		frm.setResizable(false);
		frm.setLocationRelativeTo(null);
		
		show_bgi.setBounds(0, 0, bgi.getIconWidth(), bgi.getIconHeight());
		frm.add(show_bgi);//加入圖片
		frm.getLayeredPane().add(show_bgi, new Integer(Integer.MIN_VALUE));//置底
		img_pnl = (JPanel) frm.getContentPane();//這裡真的玄學  完全看不懂就置底了 http://peimei0808.pixnet.net/blog/post/330127461-%5Bjava%5D-%E5%A6%82%E4%BD%95%E5%9C%A8jframe%E4%B8%AD%E5%8A%A0%E5%85%A5%E8%83%8C%E6%99%AF%E5%9C%96%28%E4%B8%80%29
		img_pnl.setOpaque(false);
		
		frm.addKeyListener(frm);//玄學按鈕
		frm.setFocusable(true);
		frm.requestFocus();
		
		start.addActionListener(frm);
		explain.addActionListener(frm);
		leave.addActionListener(frm);
		back.addActionListener(frm);
		save.addActionListener(frm);
		load.addActionListener(frm);
		
		frm.add(name_pnl);
		frm.add(M_pnl);//畫面加入主畫面
		frm.add(S_pnl);//畫面加入開始畫面
		frm.add(E_pnl);//畫面加入說明畫面
		frm.setVisible(true);
		
	}
	
	public static void start_page()//設定開始畫面
	{
		bg_flag = 0;
		
		M_pnl.setVisible(false);//主畫面下去
		
		S_pnl.setLayout(null);//設定start_panel
		//S_pnl.setBackground(null);	
		S_pnl.setOpaque(false);
		S_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());//設定大小跟圖片吻合
		
		start.setBounds(50, 300, 180, 60);//各種按鈕
		start.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,30) ) ;
		S_pnl.add(start);
		
		explain.setBounds(50, 400, 180, 60);
		explain.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,30) ) ;
		S_pnl.add(explain);
		
		load.setBounds(50, 500, 180, 60);
		load.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,30) ) ;
		S_pnl.add(load);
		
		leave.setBounds(50, 600, 180, 60);
		leave.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,30) ) ;
		S_pnl.add(leave);
		
		S_pnl.setVisible(true);
	}
	
	public static void main_page()//設定主畫面
	{
		S_pnl.setVisible(false);//開始畫面下去
		
		bg_flag=1; //頁面換了
		
		M_pnl.setLayout(null);//設定main_panel
		M_pnl.setOpaque(false);
		M_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		set_name();
		
		back.setBounds(50, 550, 100, 40);//上一頁按鈕
		back.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		M_pnl.add(back);
		
		save.setBounds(1070, 550, 80, 40);//存檔按鈕
		save.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		M_pnl.add(save);
		
		
		talk.setBounds(50, 600, 1100, 200);//設定對話框
		talk.setBackground(Color.BLACK);
		talk.setEditable(false);
		talk.setFont(new Font("@微軟正黑體",Font.PLAIN ,30) ) ;
		talk.setForeground(Color.WHITE);
		talk.setText(null);//每次點進去要先清空
		talk.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
				lineflag++;
				if(lineflag==1)
				{
					talk.setText(null);
					read_line2();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		M_pnl.add(talk);
		
		M_pnl.setVisible(true);//主畫面上來
		
		
	}
	
	public static void explain_page()//設定說明畫面\
	{
		S_pnl.setVisible(false);
		
		bg_flag=2; //頁面換了
		
		E_pnl.setLayout(null);//設定explain_panel
		E_pnl.setBackground(null);	
		E_pnl.setOpaque(false);
		E_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		show_bgi.setIcon(exp);//換個背景換個心情
		
		howtoplay.setBounds(50, 600, 1100, 200);//設定對話框
		howtoplay.setBackground(Color.BLACK);
		howtoplay.setFont(new Font("@微軟正黑體",Font.PLAIN ,30) ) ;
		howtoplay.setForeground(Color.WHITE);
		load_rule();
		E_pnl.add(howtoplay);
		
		back.setBounds(50, 550, 100, 40);//上一頁按鈕
		back.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		E_pnl.add(back);
		
		E_pnl.setVisible(true);//說明畫面上來
		

	}
	
	public static void load_rule()//讀取遊戲說明檔案
	{
		try //我懶得看13章
		{
			String rule_ad = "txt/rule.txt";//設定目標路徑
			File rule= new File(rule_ad);//讀取路徑的檔案
			FileInputStream fi=new FileInputStream(rule);//將檔案轉換成電腦看得懂的樣子
			byte ba[]=new byte[fi.available()];//這些看不懂
			fi.read(ba);
			howtoplay.setText(new String(ba));
			fi.close();
		}
		catch(IOException ioe) {};
	}
	
	public static void set_name()//設定名字
	{		
		name_pnl.setLayout(null);//設定name_panel
		name_pnl.setOpaque(false);
		name_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		find_name.setBounds(520, 200, 180, 60);//指示標
		find_name.setOpaque(false);
		find_name.setFont(new Font("@微軟正黑體",Font.PLAIN ,30) ) ;
		find_name.setForeground(Color.WHITE);
		name_pnl.add(find_name);
		
		set_name.setBounds(520, 250, 180, 40);//打名子區域
		set_name.setBackground(Color.white);
		set_name.setFont(new Font("@微軟正黑體",Font.PLAIN ,30) ) ;
		set_name.setForeground(Color.black);
		name_pnl.add(set_name);
		
		save_name.setBounds(570, 300, 80, 40);//儲存按鈕
		save_name.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		name_pnl.add(save_name);
		save_name.addActionListener(new ActionListener() //監聽
		{

			@Override
			public void actionPerformed(ActionEvent b) //儲存名子
			{
				// TODO Auto-generated method stub
				try
				{
				File writename = new File("txt/name.txt");
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				out.write(set_name.getText());
				out.flush();
				out.close();
				}catch (Exception a) {a.printStackTrace();}
				name_pnl.setVisible(false);//關掉name_panel
				bg_flag=3;
				talk.setText(null);//每次取完名子要先清空
				read_line();//讀取文本
			}
			
		});
		
		name_pnl.setVisible(true);
		
	}
	public static void read_line()
	{
		try
		{
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//取得角色名稱
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader(line_ad));//取得文本
			String line=txt.readLine();
			
			while(line!=null)//一條一條讀
			{
				line=line.replaceAll("主角", player_name);
				word=line.toCharArray();
				linelength=line.length();
				tm = new Timer(100, new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						// TODO Auto-generated method stub
						talk.append(String.valueOf(word[counter]));
						if(counter==linelength-1)
						{
							talk.append("\r\n");
							counter=-1;
							word=null;
							tm.stop();
						}
						counter++;
					}					
				});
				
				line=txt.readLine();
				
			}
			tm.start();
			txt.close();
			
		}catch (IOException e){};
	}
	public static void read_line2()
	{
		try
		{
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//取得角色名稱
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader("txt/line2.txt"));//取得文本
			String line=txt.readLine();
			
			while(line!=null)//一條一條讀
			{
				line=line.replaceAll("主角", player_name);
				char[]word=line.toCharArray();
				linelength=line.length();
				tm = new Timer(100, new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						// TODO Auto-generated method stub
						talk.append(String.valueOf(word[counter]));
						if(counter==linelength-1)
						{
							talk.append("\r\n");
							counter=-1;
							tm.stop();
						}
						counter++;
					}					
				});
				line=txt.readLine();	
			}
			tm.start();
			txt.close();
			
		}catch (IOException e){};
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		JButton btn=(JButton) e.getSource();
		p.button_sound();
		if(btn==leave)//關閉視窗
		{
			System.exit(0);
		}
		if(btn==start)//進入主畫面
		{
			main_page();
		}
		if(btn==back)//返回
		{
			start_page();
			E_pnl.setVisible(false);
			M_pnl.setVisible(false);
			name_pnl.setVisible(false);
			show_bgi.setIcon(bgi);//背景換回來
		}
		if(btn==explain)//說明畫面
		{
			explain_page();
		}
	
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_P://緊急迴避按鈕設置
			{
				if((bg_flag==0||bg_flag==1||bg_flag==2||bg_flag==3)&&dodge_bg_flag==0)//在任何一個頁面如果迴避頁面沒被啟動
				{
					S_pnl.setVisible(false);		//隱藏所有panel
					E_pnl.setVisible(false);
					M_pnl.setVisible(false);
					name_pnl.setVisible(false);
					show_bgi.setIcon(dodge);		//迴避
					dodge_bg_flag=1;				//緊急迴避啟動了
				}
				else if(bg_flag==3&&dodge_bg_flag==1)//判斷啟動前在哪個頁面
				{
					M_pnl.setVisible(true);			 //把那個頁面的panel調出來
					show_bgi.setIcon(bgi);			 //那個頁面的背景
					dodge_bg_flag=0;				 //緊急迴避關閉了
				}
				else if(bg_flag==0&&dodge_bg_flag==1)
				{
					S_pnl.setVisible(true);
					show_bgi.setIcon(bgi);
					dodge_bg_flag=0;
				}
				else if(bg_flag==2&&dodge_bg_flag==1)
				{
					E_pnl.setVisible(true);
					show_bgi.setIcon(exp);
					dodge_bg_flag=0;
				}
				else if(bg_flag==1&&dodge_bg_flag==1)
				{
					M_pnl.setVisible(true);
					name_pnl.setVisible(true);
					show_bgi.setIcon(bgi);
					dodge_bg_flag=0;
				}
				break;
			}
			case KeyEvent.VK_ESCAPE: //無聊做個關閉
			{
				System.exit(0);
				break;
			}
			default: 
			{
				break;
			}
		}
	}
	@Override
	
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
/*String a=new String(ba);
a=a.replaceAll("你好", "測試");//替換舊的字串
String data[] = a.split(";");
for(int i=0; i<data.length; i++) 
{
	//howtoplay.append(data[i]);//課本p.17-28 append 在目前文字區內的文字之後加上新的文字
	
}*/

/*char word[]=line.toCharArray();
linelength=line.length();
for(int i=0;i<linelength;i++)
{
	tm = new Timer(500, new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			// TODO Auto-generated method stub
			talk.append(String.valueOf(word[counter]));
			if(counter==linelength-1)
			{
				tm.stop();
				counter=0;
			}
			counter++;
		}					
	});
}
tm.start();*/

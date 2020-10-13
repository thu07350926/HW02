import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class start_view extends JFrame implements ActionListener , KeyListener
{
	private static final long serialVersionUID = 1L;//�ڬݤW������ĸ������� �����I�@�U�N�X�{�F

	public static int bg_flag = 0;//�P�_�ثe�b���ӭ���(�b�e���ŧi�᭱���i�H��  0=�}�l�e��  1=�D�e��  2=�����e��
	public static int dodge_bg_flag = 0; //�P�_�j�׭������S���Q�Ұ�
	
	static start_view frm = new start_view();//�]�mframe
	static JPanel S_pnl = new JPanel();//�]�mstart_panel
	static JPanel M_pnl = new JPanel();//�]�mmain_panel
	static JPanel E_pnl = new JPanel();//�]�mexplain_panel									
	static JPanel img_pnl = new JPanel();//�]�m�Ⱦ�panel
	
	static ImageIcon bgi=new ImageIcon("img/background.jpg");//�N�Ϥ��ɤJ
	static ImageIcon exp=new ImageIcon("img/explain.jpg");//�N�Ϥ��ɤJ
	static ImageIcon dodge=new ImageIcon("img/dodge.jpg");//�N�Ϥ��ɤJ
	static JLabel show_bgi=new JLabel(bgi);//���Ϥ��@�Ӯa
	
	static JButton start=new JButton("�}�l�^��");//�U�ض}�l�e�����s
	static JButton explain=new JButton("�ާ@��k");
	static JButton load=new JButton("�l�Ф���");
	static JButton leave=new JButton("���}�C��");
	static JButton back=new JButton("�^���D");//�D�e�����s
	static JButton save=new JButton("�O��");
	
	static JPanel name_pnl = new JPanel();//�]�mname_panel
	static JLabel find_name=new JLabel("�Х���J�W�l");//�]�w�W�l
	static JTextArea set_name=new JTextArea("");
	static JButton save_name=new JButton("�T�w");
	
	static talkarea talk=new talkarea();//���� ��ܮ�!!!
	static JTextArea howtoplay=new JTextArea("");//�����奻
	
	static backgroundsound p=new backgroundsound();
	
	static int linelength = 0;
	static Timer tm;
	static int counter = 0;
	static int line_flag = 0;
	static int lineflag = 0;
	static int line_temp = 0;
	static char word[];
	static String line_ad = "txt/line.txt";//�奻�a�}
	static String name_ad = "txt/name.txt";
	
	@SuppressWarnings("static-access")//�o�Ӥ]�O�ݤ��n����N�X�Ӫ�
	public static void main(String[] args) 
	{
		start_page();
		
		p.playmusic();

		frm.setLayout(null);
		frm.setTitle("��ܹC���M�D");
		frm.setSize(bgi.getIconWidth(), bgi.getIconHeight());
		
		frm.setDefaultCloseOperation(EXIT_ON_CLOSE);//�q�ߤO���g������۹L�Ӫ�
		frm.setResizable(false);
		frm.setLocationRelativeTo(null);
		
		show_bgi.setBounds(0, 0, bgi.getIconWidth(), bgi.getIconHeight());
		frm.add(show_bgi);//�[�J�Ϥ�
		frm.getLayeredPane().add(show_bgi, new Integer(Integer.MIN_VALUE));//�m��
		img_pnl = (JPanel) frm.getContentPane();//�o�̯u���Ⱦ�  �����ݤ����N�m���F http://peimei0808.pixnet.net/blog/post/330127461-%5Bjava%5D-%E5%A6%82%E4%BD%95%E5%9C%A8jframe%E4%B8%AD%E5%8A%A0%E5%85%A5%E8%83%8C%E6%99%AF%E5%9C%96%28%E4%B8%80%29
		img_pnl.setOpaque(false);
		
		frm.addKeyListener(frm);//�Ⱦǫ��s
		frm.setFocusable(true);
		frm.requestFocus();
		
		start.addActionListener(frm);
		explain.addActionListener(frm);
		leave.addActionListener(frm);
		back.addActionListener(frm);
		save.addActionListener(frm);
		load.addActionListener(frm);
		
		frm.add(name_pnl);
		frm.add(M_pnl);//�e���[�J�D�e��
		frm.add(S_pnl);//�e���[�J�}�l�e��
		frm.add(E_pnl);//�e���[�J�����e��
		frm.setVisible(true);
		
	}
	
	public static void start_page()//�]�w�}�l�e��
	{
		bg_flag = 0;
		
		M_pnl.setVisible(false);//�D�e���U�h
		
		S_pnl.setLayout(null);//�]�wstart_panel
		//S_pnl.setBackground(null);	
		S_pnl.setOpaque(false);
		S_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());//�]�w�j�p��Ϥ��k�X
		
		start.setBounds(50, 300, 180, 60);//�U�ث��s
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
	
	public static void main_page()//�]�w�D�e��
	{
		S_pnl.setVisible(false);//�}�l�e���U�h
		
		bg_flag=1; //�������F
		
		M_pnl.setLayout(null);//�]�wmain_panel
		M_pnl.setOpaque(false);
		M_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		set_name();
		
		back.setBounds(50, 550, 100, 40);//�W�@�����s
		back.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		M_pnl.add(back);
		
		save.setBounds(1070, 550, 80, 40);//�s�ɫ��s
		save.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		M_pnl.add(save);
		
		
		talk.setBounds(50, 600, 1100, 200);//�]�w��ܮ�
		talk.setBackground(Color.BLACK);
		talk.setEditable(false);
		talk.setFont(new Font("@�L�n������",Font.PLAIN ,30) ) ;
		talk.setForeground(Color.WHITE);
		talk.setText(null);//�C���I�i�h�n���M��
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
		
		M_pnl.setVisible(true);//�D�e���W��
		
		
	}
	
	public static void explain_page()//�]�w�����e��\
	{
		S_pnl.setVisible(false);
		
		bg_flag=2; //�������F
		
		E_pnl.setLayout(null);//�]�wexplain_panel
		E_pnl.setBackground(null);	
		E_pnl.setOpaque(false);
		E_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		show_bgi.setIcon(exp);//���ӭI�����Ӥ߱�
		
		howtoplay.setBounds(50, 600, 1100, 200);//�]�w��ܮ�
		howtoplay.setBackground(Color.BLACK);
		howtoplay.setFont(new Font("@�L�n������",Font.PLAIN ,30) ) ;
		howtoplay.setForeground(Color.WHITE);
		load_rule();
		E_pnl.add(howtoplay);
		
		back.setBounds(50, 550, 100, 40);//�W�@�����s
		back.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		E_pnl.add(back);
		
		E_pnl.setVisible(true);//�����e���W��
		

	}
	
	public static void load_rule()//Ū���C�������ɮ�
	{
		try //���i�o��13��
		{
			String rule_ad = "txt/rule.txt";//�]�w�ؼи��|
			File rule= new File(rule_ad);//Ū�����|���ɮ�
			FileInputStream fi=new FileInputStream(rule);//�N�ɮ��ഫ���q���ݱo�����ˤl
			byte ba[]=new byte[fi.available()];//�o�Ǭݤ���
			fi.read(ba);
			howtoplay.setText(new String(ba));
			fi.close();
		}
		catch(IOException ioe) {};
	}
	
	public static void set_name()//�]�w�W�r
	{		
		name_pnl.setLayout(null);//�]�wname_panel
		name_pnl.setOpaque(false);
		name_pnl.setSize(bgi.getIconWidth(),bgi.getIconHeight());
		
		find_name.setBounds(520, 200, 180, 60);//���ܼ�
		find_name.setOpaque(false);
		find_name.setFont(new Font("@�L�n������",Font.PLAIN ,30) ) ;
		find_name.setForeground(Color.WHITE);
		name_pnl.add(find_name);
		
		set_name.setBounds(520, 250, 180, 40);//���W�l�ϰ�
		set_name.setBackground(Color.white);
		set_name.setFont(new Font("@�L�n������",Font.PLAIN ,30) ) ;
		set_name.setForeground(Color.black);
		name_pnl.add(set_name);
		
		save_name.setBounds(570, 300, 80, 40);//�x�s���s
		save_name.setFont(new Font("UD Digi Kyokasho NK-R",Font.ITALIC ,18) ) ;
		name_pnl.add(save_name);
		save_name.addActionListener(new ActionListener() //��ť
		{

			@Override
			public void actionPerformed(ActionEvent b) //�x�s�W�l
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
				name_pnl.setVisible(false);//����name_panel
				bg_flag=3;
				talk.setText(null);//�C�������W�l�n���M��
				read_line();//Ū���奻
			}
			
		});
		
		name_pnl.setVisible(true);
		
	}
	public static void read_line()
	{
		try
		{
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//���o����W��
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader(line_ad));//���o�奻
			String line=txt.readLine();
			
			while(line!=null)//�@���@��Ū
			{
				line=line.replaceAll("�D��", player_name);
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
			BufferedReader name = new BufferedReader (new FileReader(name_ad));//���o����W��
			String player_name;
			player_name=name.readLine();						
			
			BufferedReader txt = new BufferedReader (new FileReader("txt/line2.txt"));//���o�奻
			String line=txt.readLine();
			
			while(line!=null)//�@���@��Ū
			{
				line=line.replaceAll("�D��", player_name);
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
		if(btn==leave)//��������
		{
			System.exit(0);
		}
		if(btn==start)//�i�J�D�e��
		{
			main_page();
		}
		if(btn==back)//��^
		{
			start_page();
			E_pnl.setVisible(false);
			M_pnl.setVisible(false);
			name_pnl.setVisible(false);
			show_bgi.setIcon(bgi);//�I�����^��
		}
		if(btn==explain)//�����e��
		{
			explain_page();
		}
	
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_P://���j�׫��s�]�m
			{
				if((bg_flag==0||bg_flag==1||bg_flag==2||bg_flag==3)&&dodge_bg_flag==0)//�b����@�ӭ����p�G�j�׭����S�Q�Ұ�
				{
					S_pnl.setVisible(false);		//���éҦ�panel
					E_pnl.setVisible(false);
					M_pnl.setVisible(false);
					name_pnl.setVisible(false);
					show_bgi.setIcon(dodge);		//�j��
					dodge_bg_flag=1;				//���j�ױҰʤF
				}
				else if(bg_flag==3&&dodge_bg_flag==1)//�P�_�Ұʫe�b���ӭ���
				{
					M_pnl.setVisible(true);			 //�⨺�ӭ�����panel�եX��
					show_bgi.setIcon(bgi);			 //���ӭ������I��
					dodge_bg_flag=0;				 //���j�������F
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
			case KeyEvent.VK_ESCAPE: //�L�ᰵ������
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
a=a.replaceAll("�A�n", "����");//�����ª��r��
String data[] = a.split(";");
for(int i=0; i<data.length; i++) 
{
	//howtoplay.append(data[i]);//�ҥ�p.17-28 append �b�ثe��r�Ϥ�����r����[�W�s����r
	
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

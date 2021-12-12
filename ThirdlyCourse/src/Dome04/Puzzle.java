package Dome04;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class Puzzle extends JFrame implements Runnable{
	MainPanel panel;
	String path;// 图片的路径
	int pattern;// 图片的底数
	JMenuBar jmBar;
	JMenu menu, menuSelect, menuChange, menuRank, menuHelp;
	JMenuItem itemStart, itemExit, itemView;
	JRadioButtonMenuItem[] pic_change = new JRadioButtonMenuItem[4];
	JRadioButtonMenuItem[] game_rank = new JRadioButtonMenuItem[3];
	JLabel total_time;
	JLabel total_count;
	long startTime;
	long endTime;

	public Puzzle() {
		// TODO Auto-generated constructor stub
		jmBar = new JMenuBar();
		menu = new JMenu("菜单");
		menuSelect = new JMenu("选择");
		menuHelp = new JMenu("帮助");
		menuChange = new JMenu("图片更换");
		menuRank = new JMenu("等级");

		itemStart = new JMenuItem("开始");
		itemExit = new JMenuItem("退出");
		itemView = new JMenuItem("查看背景");

		total_time = new JLabel("时间：");
		total_count = new JLabel("步数：");
		total_time.setForeground(Color.RED);
		total_count.setForeground(Color.RED);

		ButtonGroup groupChange = new ButtonGroup();
		for (int i = 0; i < pic_change.length; i++) {
			pic_change[i] = new JRadioButtonMenuItem("0" + (i + 1) + ".jpg");
			groupChange.add(pic_change[i]);
			menuChange.add(pic_change[i]);
		}
		pic_change[0].setSelected(true);

		ButtonGroup groupRank = new ButtonGroup();
		String content;
		for (int i = 0; i < game_rank.length; i++) {
			if (i == 0)
				content = "简单";
			else if (i == 1)
				content = "普通";
			else
				content = "复杂";
			game_rank[i] = new JRadioButtonMenuItem(content);
			groupRank.add(game_rank[i]);
			menuRank.add(game_rank[i]);
		}
		game_rank[0].setSelected(true);

		menu.add(itemStart);
		menu.add(itemView);
		menu.add(itemExit);
		menuSelect.add(menuChange);
		menuSelect.add(menuRank);
		jmBar.add(menu);
		jmBar.add(menuSelect);
		jmBar.add(menuHelp);
		jmBar.add(new JLabel("               "));
		jmBar.add(total_time);
		jmBar.add(new JLabel("       "));
		jmBar.add(total_count);

		this.setJMenuBar(jmBar);

		itemStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				breakState();
			}
		});

		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		itemView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton index = new JButton(new ImageIcon(path + "\\test.jpg"));
				JFrame model = new JFrame("拼图模板");
				model.setSize(370, 370);
				model.setResizable(false);
				model.add(index);
				model.setVisible(true);
			}
		});

		this.setTitle("拼图");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(380, 420);
		this.setResizable(false);
		this.setPath();
		this.setPattern();

		panel = new MainPanel(path, pattern);

		startTime = System.currentTimeMillis();
		this.add(panel);
		this.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			endTime = System.currentTimeMillis();
			int time = (int)((endTime-startTime)/1000);
			total_time.setText("时间:"+time);
			total_count.setText("步数:"+panel.getCount());
		}
	}

	public void breakState() {
		startTime = System.currentTimeMillis();
		setPattern();
		setPath();
		panel.breakRandom(path, pattern);
	}

	public void setPath() {
		// path = "img\\type1\\"
		for (int i = 0; i < pic_change.length; i++) {
			if (pic_change[i].isSelected()) {
				path = "E:\\HP\\Desktop\\JAVA\\type" + (i+1) +"\\";
			}
		}
	}

	public void setPattern() {
		// pattern = 3;
		for (int i = 0; i < game_rank.length; i++) {
			if (game_rank[i].isSelected()) {
				if (i == 0)
					pattern = 3;
				else if (i == 1)
					pattern = 4;
				else if (i == 2)
					pattern = 5;
			}

		}
	}

	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle();
		Thread th = new Thread(puzzle);
		th.start();
	}

}
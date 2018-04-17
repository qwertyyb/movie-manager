package example5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

class DetailPanel extends JPanel {
	private JLabel thumb = new JLabel(), 
			title = new JLabel(), 
			actors = new JLabel(), 
			director = new JLabel(), 
			showDate  = new JLabel(),
			time = new JLabel(),
			type = new JLabel(), 
			country = new JLabel();
	private JTextArea detail = new JTextArea();
	private HashMap<String, String> movie;
	
	private Font defaultFont = new Font("Microsoft YaHei", 0, 24);
	
	private void initLayout() {
		// 设置字体
		thumb.setFont(defaultFont);
		thumb.setSize(300, 400);
		title.setFont(defaultFont);
		actors.setFont(defaultFont);
		director.setFont(defaultFont);
		showDate.setFont(defaultFont);
		time.setFont(defaultFont);
		type.setFont(defaultFont);
		country.setFont(defaultFont);
		detail.setFont(defaultFont);
		
		// 设置剧情梗概自动换行并添加滚动条
		detail.setLineWrap(true);
		detail.setWrapStyleWord(true);
		detail.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(detail);
		scrollPanel.setPreferredSize(new Dimension(800, 250));
		
		Box boxV1 = Box.createVerticalBox();
		boxV1.add(title);
		boxV1.add(actors);
		boxV1.add(Box.createVerticalStrut(15));
		boxV1.add(director);
		boxV1.add(Box.createVerticalStrut(15));
		boxV1.add(showDate);
		boxV1.add(Box.createVerticalStrut(15));
		boxV1.add(time);
		boxV1.add(Box.createVerticalStrut(15));
		boxV1.add(type);
		boxV1.add(Box.createVerticalStrut(15));
		boxV1.add(country);
		boxV1.add(Box.createVerticalStrut(15));
		
		Box boxH1 = Box.createHorizontalBox();
		boxH1.add(thumb);
		boxH1.add(Box.createHorizontalStrut(10));
		boxH1.add(boxV1);
		
		Box baseBox = Box.createVerticalBox();
		baseBox.add(title);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxH1);
		baseBox.add(Box.createVerticalStrut(10));

		baseBox.add(scrollPanel);
		setBackground(Color.WHITE);
		add(baseBox);
	}
	
	DetailPanel(HashMap<String, String> movie){
		initLayout();
		// 初始化数据
		ImageIcon image = new ImageIcon(movie.get("thumb"));
		image.setImage(image.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
		thumb.setIcon(image);
		thumb.setSize(20, 20);
		title.setText("电影名：" + movie.get("title"));
		title.setFont(new Font("Microsoft YaHei", 0, 32));
		title.setForeground(Color.RED);
		actors.setText("演员：" + movie.get("actors"));
		director.setText("导演：" + movie.get("director"));
		showDate.setText("上映时间：" + movie.get("showDate"));
		time.setText("时长：" + movie.get("time"));
		type.setText("类型：" + movie.get("type"));
		country.setText("国家：" + movie.get("country"));
		detail.setText("剧情梗概：\n" + movie.get("detail"));
		this.movie = movie;
	}
	
	public HashMap<String, String> getMovie() {
		return movie;
	}
}

public class ManageFrm extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private Font defaultFont = new Font("Microsoft YaHei", 0, 24);
	private JButton btnAdd = new JButton("添加电影");
	private JButton btnEdit = new JButton("编辑电影");
	private JButton btnDel = new JButton("删除电影");
	private JLabel labelTitle = new JLabel("电影管理系统");
	private JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT);
	
	private ManageFrm that = this;
	private String user;
	private JLabel labelUser = new JLabel(user);
	
	ManageFrm(String user) {
		this.user = user;
		initLayout();
		addListener();
		setVisible(true);
	}
	
	private void addListener () {
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditFrm(that);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailPanel detailPanel = (DetailPanel)tabPane.getSelectedComponent();
				new EditFrm(that, detailPanel.getMovie());
			}
		});
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(that, "删除后将不可恢复，是否删除？", "确认", JOptionPane.WARNING_MESSAGE);
				if (result != JOptionPane.OK_OPTION) {
					return;
				}
				DetailPanel detailPanel = (DetailPanel)tabPane.getSelectedComponent();
				int id = Integer.parseInt(detailPanel.getMovie().get("id"));
				if (Util.deleteMovie(id)) {
					JOptionPane.showMessageDialog(that, "删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
					that.refresh();
				} else {
					JOptionPane.showMessageDialog(that, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	// 重新从数据库获取数据，并显示到页面上
	public void refresh() {
		tabPane.removeAll();
		LinkedList<HashMap<String, String>> movies = Util.getAllMovie();
		for(int i = 0; i < movies.size(); i++) {
			HashMap<String, String> movie = movies.get(i);
			tabPane.addTab(movie.get("title"), new DetailPanel(movie));
		}
	}
	
	private void initLayout() {
		setTitle("管理-电影管理系统 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1600, 920);
		
		// 菜单栏
		JMenu MovieMenu = new JMenu("电影管理");
		MovieMenu.setFont(defaultFont);
		JMenuItem addItem = new JMenuItem("添加");
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditFrm(that);
			}
		});
		addItem.setFont(defaultFont);
		MovieMenu.add(addItem);
		JMenu userMenu = new JMenu("账户管理");
		userMenu.setFont(defaultFont);
		JMenuItem resetPwdItem = new JMenuItem("重置密码");
		resetPwdItem.setFont(defaultFont);
		resetPwdItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResetPwdDlg(that);
			}
		});
		userMenu.add(resetPwdItem);
		menuBar.add(MovieMenu);
		menuBar.add(userMenu);
		
		// 状态栏
		JToolBar toolBar = new JToolBar();
		labelUser.setText("用户：" + user);
		toolBar.add(labelUser);
		toolBar.setFloatable(false);
		
		// 设置字体
		btnAdd.setFont(defaultFont);
		btnEdit.setFont(defaultFont);
		btnDel.setFont(defaultFont);
		tabPane.setFont(defaultFont);
		labelUser.setFont(defaultFont);
		labelTitle.setFont(new Font("Microsoft YaHei", 0, 36));
		// 设置标题居中
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		
		// 获取数据并显示到组件上
		refresh();

		// 添加按钮
		JPanel panel = new JPanel();
		panel.add(btnAdd);
		panel.add(btnEdit);
		panel.add(btnDel);
		((FlowLayout)panel.getLayout()).setVgap(100);
		panel.setPreferredSize(new Dimension(160, 500));
		panel.setBackground(Color.WHITE);

		add(labelTitle, BorderLayout.NORTH);
		add(tabPane, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		add(toolBar, BorderLayout.SOUTH);
		setJMenuBar(menuBar);
	}
	
	public String getUser() {
		return user;
	}
}

package example5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		// ��������
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
		
		// ���þ��鹣���Զ����в���ӹ�����
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
		// ��ʼ������
		ImageIcon image = new ImageIcon(movie.get("thumb"));
		image.setImage(image.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
		thumb.setIcon(image);
		thumb.setSize(20, 20);
		title.setText("��Ӱ����" + movie.get("title"));
		title.setFont(new Font("Microsoft YaHei", 0, 32));
		title.setForeground(Color.RED);
		actors.setText("��Ա��" + movie.get("actors"));
		director.setText("���ݣ�" + movie.get("director"));
		showDate.setText("��ӳʱ�䣺" + movie.get("showDate"));
		time.setText("ʱ����" + movie.get("time"));
		type.setText("���ͣ�" + movie.get("type"));
		country.setText("���ң�" + movie.get("country"));
		detail.setText("���鹣�ţ�\n" + movie.get("detail"));
		this.movie = movie;
	}
	
	public HashMap<String, String> getMovie() {
		return movie;
	}
}

public class ManageFrm extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private Font defaultFont = new Font("Microsoft YaHei", 0, 24);
	private JButton btnAdd = new JButton("��ӵ�Ӱ");
	private JButton btnEdit = new JButton("�༭��Ӱ");
	private JButton btnDel = new JButton("ɾ����Ӱ");
	private JLabel labelTitle = new JLabel("��Ӱ����ϵͳ");
	private JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT);
	
	private ManageFrm that = this;
	private String user;
	
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
				int result = JOptionPane.showConfirmDialog(that, "ɾ���󽫲��ɻָ����Ƿ�ɾ����", "ȷ��", JOptionPane.WARNING_MESSAGE);
				if (result != JOptionPane.OK_OPTION) {
					return;
				}
				DetailPanel detailPanel = (DetailPanel)tabPane.getSelectedComponent();
				int id = Integer.parseInt(detailPanel.getMovie().get("id"));
				if (Util.deleteMovie(id)) {
					JOptionPane.showMessageDialog(that, "ɾ���ɹ���", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
					that.refresh();
				} else {
					JOptionPane.showMessageDialog(that, "ɾ��ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	// ���´����ݿ��ȡ���ݣ�����ʾ��ҳ����
	public void refresh() {
		tabPane.removeAll();
		LinkedList<HashMap<String, String>> movies = Util.getAllMovie();
		for(int i = 0; i < movies.size(); i++) {
			HashMap<String, String> movie = movies.get(i);
			tabPane.addTab(movie.get("title"), new DetailPanel(movie));
		}
	}
	
	private void initLayout() {
		setTitle("����-��Ӱ����ϵͳ ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1600, 920);
		
		JMenu MovieMenu = new JMenu("��Ӱ����");
		MovieMenu.setFont(defaultFont);
		JMenuItem addItem = new JMenuItem("���");
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditFrm(that);
			}
		});
		addItem.setFont(defaultFont);
		MovieMenu.add(addItem);
		JMenu userMenu = new JMenu("�˻�����");
		userMenu.setFont(defaultFont);
		JMenuItem resetPwdItem = new JMenuItem("��������");
		resetPwdItem.setFont(defaultFont);
		resetPwdItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResetPwdDlg(that);
			}
		});
		userMenu.add(resetPwdItem);
		menuBar.add(MovieMenu);
		menuBar.add(userMenu);
		// ��������
		btnAdd.setFont(defaultFont);
		btnEdit.setFont(defaultFont);
		btnDel.setFont(defaultFont);
		tabPane.setFont(defaultFont);
		labelTitle.setFont(new Font("Microsoft YaHei", 0, 36));
		// ���ñ������
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		
		// ��ȡ���ݲ���ʾ�������
		refresh();

		// ��Ӱ�ť
		JPanel panel = new JPanel();
		panel.add(btnAdd);
		panel.add(btnEdit);
		panel.add(btnDel);
		
		add(labelTitle, BorderLayout.NORTH);
		add(tabPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		setJMenuBar(menuBar);
	}
	
	public String getUser() {
		return user;
	}
}

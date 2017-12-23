package example5;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrm extends JFrame implements ActionListener{

	private Font defaultFont = new Font("Microsoft YaHei", 0, 24);
	private JLabel headerLabel = new JLabel("电影管理系统");
	private JLabel labelUser = new JLabel("用户名");
	private JLabel labelPwd = new JLabel("密   码");
	private JTextField inputUser = new JTextField(16);
	private JPasswordField inputPwd = new JPasswordField(16);
	private JButton btnSubmit = new JButton("登录");
	private LoginFrm that = this;
	
	LoginFrm() {
		initLayout();
		addListener();
	}
	
	private void initLayout() {
		setTitle("登录-电影信息管理");
		setBounds(580, 300, 450, 400);
		getContentPane().setBackground(new Color(208, 233, 255));
		setLayout(null);
		setResizable(false);
		
		headerLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 48));
		headerLabel.setBounds(80, 50, 300, 50);
		headerLabel.setForeground(Color.RED);
		labelUser.setBounds(100, 150, 80, 30);
		labelPwd.setBounds(100, 200, 80, 30);
		labelUser.setFont(defaultFont);
		labelPwd.setFont(defaultFont);
		inputUser.setBounds(180, 150, 150, 30);
		inputUser.setFont(defaultFont);
		inputPwd.setBounds(180, 200, 150, 30);
		inputPwd.setFont(defaultFont);
		btnSubmit.setFont(defaultFont);
		btnSubmit.setBounds(150, 270, 120, 30);
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(29, 176, 184));
		
		
		add(headerLabel);
		add(labelUser);
		add(labelPwd);
		add(inputPwd);
		add(inputUser);
		add(btnSubmit);
		
		setVisible(true);
	}
	
	private void addListener() {
		// 给登录按钮绑定事件
		btnSubmit.addActionListener(this);
	}
	
	private boolean checkTextFields() {
		String user = inputUser.getText().trim(),
				pwd = new String(inputPwd.getPassword()).trim();
		if (user.length() <= 0 || pwd.length() <= 0) {
			return false;
		}
		return Util.validateAdmin(user, pwd);
	}

	public void actionPerformed(ActionEvent e) {
		// 验证用户名和密码
		if (!checkTextFields()) {
			JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码", "错误！", JOptionPane.ERROR_MESSAGE );
			return;
		}
		new ManageFrm(inputUser.getText().trim());
		that.dispose();
	}
	
}

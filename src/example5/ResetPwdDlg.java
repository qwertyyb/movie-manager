package example5;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ResetPwdDlg extends JDialog implements ActionListener{
	
	private Font defaultFont = new Font("Microsoft YaHei", Font.PLAIN, 24);
	
	private JPasswordField originInput = new JPasswordField(),
			newInput = new JPasswordField(),
			confirmInput = new JPasswordField();
	private JButton btnSubmit = new JButton("ȷ��");

	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String origin = new String(originInput.getPassword()).trim(),
				newPwd = new String(newInput.getPassword()).trim(),
				confirmPwd = new String(confirmInput.getPassword()).trim();
		if (origin.length() > 0 && newPwd.length() > 0 && newPwd.equals(confirmPwd)) {
			if(Util.resetPwd(((ManageFrm)getOwner()).getUser(), origin, newPwd)) {
				JOptionPane.showMessageDialog(null, "�������޸ģ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "ԭ���벻��ȷ", "����", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "��������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	ResetPwdDlg(JFrame owner) {
		super(owner, true);
		initLayout();
	}
	
	private void initLayout() {
		JLabel labelOrigin = new JLabel("ԭ����"),
				labelNew = new JLabel("������"),
				labelConfirm = new JLabel("ȷ������");
		
		labelOrigin.setFont(defaultFont);
		labelNew.setFont(defaultFont);
		labelConfirm.setFont(defaultFont);
		originInput.setFont(defaultFont);
		newInput.setFont(defaultFont);
		confirmInput.setFont(defaultFont);
		btnSubmit.setFont(defaultFont);
		
		labelOrigin.setBounds(20, 20, 120, 30);
		originInput.setBounds(160, 25, 180, 30);
		labelNew.setBounds(20, 80, 120, 30);
		newInput.setBounds(160, 85, 180, 30);
		labelConfirm.setBounds(20, 140, 120, 30);
		confirmInput.setBounds(160, 145, 180, 30);
		btnSubmit.setBounds(120, 200, 120, 40);
		
		labelOrigin.setHorizontalAlignment(JLabel.RIGHT);
		labelNew.setHorizontalAlignment(JLabel.RIGHT);
		labelConfirm.setHorizontalAlignment(JLabel.RIGHT);
		setLayout(null);
		add(labelOrigin);
		add(labelNew);
		add(labelConfirm);
		add(originInput);
		add(newInput);
		add(confirmInput);
		add(btnSubmit);
		
		btnSubmit.addActionListener(this);
		
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setBounds(550, 400, 400, 300);
		setTitle("��������");
		setVisible(true);
	}
	
}

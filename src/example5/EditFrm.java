package example5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditFrm extends JFrame{
	private boolean editing = false;
	private int id = -1;
	
	private Font defaultFont = new Font("Microsoft YaHei", 0, 20);
	
	private JTextField titleInput = new JTextField(),
			actorsInput = new JTextField(),
			directorInput = new JTextField(),
			showDateInput = new JTextField(),
			timeInput = new JTextField(),
			typeInput = new JTextField(),
			countryInput = new JTextField();
	private JTextArea detailInput = new JTextArea();
	private JButton btnChooseImage = new JButton("ѡ��ͼƬ"),
			btnSubmit = new JButton("����");
	private JLabel thumbView = new JLabel("����ͼƬ");
	private String thumbInput;
	private ManageFrm parent;
	private EditFrm that = this;
	
	EditFrm(ManageFrm parent) {
		this.parent = parent;
		initLayout();
	}
	EditFrm(ManageFrm parent, HashMap<String, String> movie) {
		titleInput.setText(movie.get("title"));
		actorsInput.setText(movie.get("actors"));
		directorInput.setText(movie.get("director"));
		showDateInput.setText(movie.get("showDate"));
		timeInput.setText(movie.get("time"));
		typeInput.setText(movie.get("type"));
		countryInput.setText(movie.get("country"));
		detailInput.setText(movie.get("detail"));
		
		ImageIcon image = new ImageIcon(movie.get("thumb"));
		image.setImage(image.getImage().getScaledInstance(200, 260, Image.SCALE_DEFAULT));
		thumbView.setIcon(image);
		thumbInput = movie.get("thumb");
		
		editing = true;
		id = Integer.parseInt(movie.get("id"));
		this.parent = parent;
		initLayout();
		addListener();
		setTitle("�༭-��Ӱ����ϵͳ");
	}
	private void initLayout() {
		JLabel thumb = new JLabel("����"), 
				title = new JLabel("ӰƬ��"), 
				actors = new JLabel("��Ա"), 
				director = new JLabel("����"), 
				showDate  = new JLabel("��ӳ����"),
				time = new JLabel("ʱ��"),
				type = new JLabel("����"), 
				country = new JLabel("����"),
				detail = new JLabel("���鹣��");
		
		// ���������λ��
		thumb.setBounds(60, 240, 80, 40);
		title.setBounds(60, 280, 80, 40);
		actors.setBounds(60, 320, 80, 40);
		director.setBounds(60, 360, 80, 40);
		showDate.setBounds(60, 400, 80, 40);
		time.setBounds(60, 440, 80, 40);
		type.setBounds(60, 480, 80, 40);
		country.setBounds(60, 520, 80, 40);
		detail.setBounds(60, 560, 80, 40);
		
		thumbView.setBounds(380, 10, 200, 260);
		thumbView.setBorder(new LineBorder(Color.BLACK));
		btnChooseImage.setBounds(160, 245, 160, 30);
		titleInput.setBounds(160, 285, 560, 30);
		directorInput.setBounds(160, 325, 560, 30);
		actorsInput.setBounds(160, 365, 560, 30);
		showDateInput.setBounds(160, 405, 560, 30);
		timeInput.setBounds(160, 445, 560, 30);
		typeInput.setBounds(160, 485, 560, 30);
		countryInput.setBounds(160, 525, 560, 30);
		
		// �����Զ����к͹�����
		detailInput.setLineWrap(true);
		detailInput.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(detailInput);
		scrollPane.setPreferredSize(new Dimension(560, 240));
		scrollPane.setBounds(160, 565, 560, 200);
		btnSubmit.setBounds(300, 830, 140, 30);
		
		// ��������
		thumbView.setFont(defaultFont);
		thumb.setFont(defaultFont);
		title.setFont(defaultFont);
		actors.setFont(defaultFont);
		director.setFont(defaultFont);
		showDate.setFont(defaultFont);
		time.setFont(defaultFont);
		type.setFont(defaultFont);
		country.setFont(defaultFont);
		detail.setFont(defaultFont);
		
		btnChooseImage.setFont(defaultFont);
		titleInput.setFont(defaultFont);
		directorInput.setFont(defaultFont);
		actorsInput.setFont(defaultFont);
		showDateInput.setFont(defaultFont);
		timeInput.setFont(defaultFont);
		typeInput.setFont(defaultFont);
		countryInput.setFont(defaultFont);
		detailInput.setFont(defaultFont);
		btnSubmit.setFont(defaultFont);
		
		// �ı���������
		thumbView.setHorizontalAlignment(JLabel.CENTER);
		thumb.setHorizontalAlignment(JLabel.RIGHT);
		title.setHorizontalAlignment(JLabel.RIGHT);
		director.setHorizontalAlignment(JLabel.RIGHT);
		actors.setHorizontalAlignment(JLabel.RIGHT);
		showDate.setHorizontalAlignment(JLabel.RIGHT);
		time.setHorizontalAlignment(JLabel.RIGHT);
		type.setHorizontalAlignment(JLabel.RIGHT);
		country.setHorizontalAlignment(JLabel.RIGHT);
		detail.setHorizontalAlignment(JLabel.RIGHT);
		
		thumbView.setForeground(Color.GRAY);
		// ��ӵ�������
		setLayout(null);
		add(thumb);
		add(title);
		add(actors);
		add(director);
		add(showDate);
		add(time);
		add(type);
		add(country);
		add(detail);
		add(thumbView);
		add(btnChooseImage);
		add(titleInput);
		add(directorInput);
		add(actorsInput);
		add(showDateInput);
		add(timeInput);
		add(typeInput);
		add(countryInput);
		add(scrollPane);
		add(btnSubmit);
		// ���ô��ڵ�����
		setTitle("��ӵ�Ӱ-��Ӱ����ϵͳ");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 60, 800, 940);
		setVisible(true);
	}
		
	private void addListener() {
		// ��ѡ��ͼƬ�İ�ť����¼�
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("ͼƬ�ļ�", "jpg", "JPG", "jpeg", "png", "PNG"));
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					ImageIcon image = new ImageIcon(f.getPath());
					thumbInput = f.getPath();
					Image pic = image.getImage().getScaledInstance(180, 240, Image.SCALE_DEFAULT);
					image.setImage(pic);
					thumbView.setIcon(image);
				}
			}
		});
		
		// �����水ť����¼�
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleInput.getText().trim(),
						actors = actorsInput.getText().trim(),
						director = directorInput.getText().trim(),
						showDate = showDateInput.getText().trim(),
						time = timeInput.getText().trim(),
						type = typeInput.getText().trim(),
						country = countryInput.getText().trim(),
						detail = detailInput.getText().trim(),
						// �����·������mySql���ݿ��У���б����ʧ������
						thumb = thumbInput.replace("\\", "\\\\");
				
				if (title.length() > 0 && actors.length() >0 && director.length() > 0
						&& showDate.length() > 0 && time.length() > 0 && type.length() > 0
						&&  thumb.length() > 0 && country.length() > 0 && detail.length() > 0) {
					HashMap<String, String> movie = new HashMap<String, String>();
					movie.put("title", title);
					movie.put("thumb", thumb);
					movie.put("actors", actors);
					movie.put("director", director);
					movie.put("showDate", showDate);
					movie.put("time", time);
					movie.put("type", type);
					movie.put("country", country);
					movie.put("detail", detail);
					if (editing) {
						if (Util.updateMovie(id, movie)) {
							that.dispose();
							JOptionPane.showMessageDialog(null, "���³ɹ���", "״̬��ʾ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if (Util.addMovie(movie)) {
							that.dispose();
							JOptionPane.showMessageDialog(null, "��ӳɹ���", "״̬��ʾ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
						}
					}
					parent.refresh();
				} else {
					JOptionPane.showMessageDialog(null, "��������д��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

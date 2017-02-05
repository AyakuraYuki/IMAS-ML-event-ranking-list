package namugo.mlEventRankingList.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import namugo.mlEventRankingList.entity.Ranking;
import namugo.mlEventRankingList.service.GetRanking;
import namugo.mlEventRankingList.utils.ExceptionUtils;
import namugo.mlEventRankingList.utils.PropertiesUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GUIMain extends JFrame
{
	private static final long serialVersionUID = 1L;

	private final String[] thead = { "Rank", "Producer", "Point" };
	private Object[][] tbody = { { "", "", "" } };
	private final Font font = new Font("微软雅黑", Font.PLAIN, 14);
	private final URL config = GUIMain.class.getResource("../../../config.properties");

	protected DefaultTableModel model = new DefaultTableModel(tbody, thead);
	protected JTable table = new JTable(model);
	protected JScrollPane tablePanel = new JScrollPane(table);
	private JPanel btnPanel = new JPanel();

	private JButton update = new JButton("コンフィグ");
	private JButton refresh = new JButton("データリフレッシュ");
	private JButton exit = new JButton("プログラム終了");

	public GUIMain()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setLayout(new BorderLayout(2, 2));

			update.addActionListener(ActionListener -> config());
			refresh.addActionListener(ActionListener -> refreshTable());
			exit.addActionListener(ActionListener -> System.exit(0));

			btnPanel.add(update);
			btnPanel.add(refresh);
			btnPanel.add(exit);
			add(btnPanel, BorderLayout.SOUTH);

			add(tablePanel, BorderLayout.CENTER);

			initialization();
		}
		catch (Exception e)
		{
			ExceptionUtils.Log(e);
		}
	}

	private void initialization()
	{
		update.setFont(font);
		refresh.setFont(font);
		exit.setFont(font);
		table.setFont(font);
		table.setEnabled(false);
		table.getColumn("Rank").setMaxWidth(140);
		table.getColumn("Producer").setMaxWidth(300);
		table.getColumn("Point").setMaxWidth(200);
		table.setBackground(new Color(Integer.parseInt(PropertiesUtils.R),
				Integer.parseInt(PropertiesUtils.G), Integer.parseInt(PropertiesUtils.B)));
		this.setFont(font);
		this.setIconImage(new ImageIcon("images/logo.jpg").getImage());
		this.setTitle("アイドルマスターミリオンライブ　イベントランキングボーダー");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 600);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void config()
	{
		Runtime runtime = Runtime.getRuntime();
		try
		{
			runtime.exec("cmd.exe /c start " + config);
		}
		catch (IOException e)
		{
			ExceptionUtils.Log(e);
		}
	}

	private void refreshTable()
	{
		PropertiesUtils.Reload();
		model.getDataVector().clear();
		tbody = GetTbody();
		model.setDataVector(tbody, thead);
		table.setModel(model);
		table.setFont(font);
		table.setEnabled(false);
		table.getColumn("Rank").setMaxWidth(140);
		table.getColumn("Producer").setMaxWidth(300);
		table.getColumn("Point").setMaxWidth(200);
		table.setBackground(new Color(Integer.parseInt(PropertiesUtils.R),
				Integer.parseInt(PropertiesUtils.G), Integer.parseInt(PropertiesUtils.B)));
		tablePanel.validate();
		tablePanel.repaint();
	}

	private static Object[][] GetTbody()
	{
		List<Ranking> list = GetRanking.GetRankingList();
		Object[][] objects = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++)
		{
			objects[i] = new Object[3];
			objects[i][0] = list.get(i).getRank();
			objects[i][1] = list.get(i).getProducer();
			objects[i][2] = list.get(i).getPt();
		}
		return objects;
	}
}

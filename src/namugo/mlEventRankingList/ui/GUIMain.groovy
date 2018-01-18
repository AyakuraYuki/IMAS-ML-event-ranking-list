package namugo.mlEventRankingList.ui

import namugo.mlEventRankingList.entity.Ranking
import namugo.mlEventRankingList.service.GetRanking
import namugo.mlEventRankingList.utils.ColorUtils
import namugo.mlEventRankingList.utils.PropertiesUtils

import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.*
import java.awt.event.ActionListener
import java.util.List

class GUIMain extends JFrame {

    private static final long serialVersionUID = 1L

    private final String[] thead = ["Rank", "Producer", "Point"]
    private final Font font = new Font("微软雅黑", Font.PLAIN, 14)
    private final URL config = GUIMain.class.getResource("../../../config.properties")
    private Object[][] tbody = [["", "", ""]]
    private DefaultTableModel model = new DefaultTableModel(tbody, thead)
    private JTable table = new JTable(model)
    private JScrollPane tablePanel = new JScrollPane(table)
    private JPanel btnPanel = new JPanel()

    private JButton update = new JButton("コンフィグ")
    private JButton refresh = new JButton("データリフレッシュ")
    private JButton exit = new JButton("プログラム終了")

    GUIMain() {
        try {
            UIManager.setLookAndFeel(UIManager.systemLookAndFeelClassName)
            setLayout(new BorderLayout(2, 2))

            update.addActionListener({
                Runtime runtime = Runtime.getRuntime()
                try {
                    runtime.exec("cmd.exe /c start " + config)
                } catch (IOException ignored) {
                }
            } as ActionListener)

            refresh.addActionListener({
                PropertiesUtils.Reload()
                model.getDataVector().clear()
                tbody = getTbody()
                model.setDataVector(tbody, thead)
                table.setModel(model)
                table.setFont(font)
                table.setEnabled(false)
                table.getColumn("Rank").setMaxWidth(140)
                table.getColumn("Producer").setMaxWidth(300)
                table.getColumn("Point").setMaxWidth(200)
                table.setBackground(new Color(ColorUtils.R, ColorUtils.G, ColorUtils.B))
                tablePanel.validate()
                tablePanel.repaint()
                JOptionPane.showMessageDialog(null, "リフレッシュ完了")
            } as ActionListener)

            exit.addActionListener({
                System.exit(0)
            } as ActionListener)

            btnPanel.add(update)
            btnPanel.add(refresh)
            btnPanel.add(exit)
            add(btnPanel, BorderLayout.SOUTH)

            add(tablePanel, BorderLayout.CENTER)

            initialization()
        } catch (Exception ignored) {
        }
    }

    private static Object[][] getTbody() {
        List<Ranking> list = GetRanking.rankingList
        Object[][] objects = new Object[list.size()][]
        for (int i = 0; i < list.size(); i++) {
            objects[i] = new Object[3]
            objects[i][0] = list.get(i).rank
            objects[i][1] = list.get(i).producer
            objects[i][2] = list.get(i).pt
        }
        return objects
    }

    private void initialization() {
        update.setFont(font)
        refresh.setFont(font)
        exit.setFont(font)
        table.setFont(font)
        table.setEnabled(false)
        table.getColumn("Rank").maxWidth = 140
        table.getColumn("Producer").maxWidth = 300
        table.getColumn("Point").maxWidth = 200
        table.setBackground(new Color(ColorUtils.R, ColorUtils.G, ColorUtils.B))
        this.setFont(font)
        this.setIconImage(new ImageIcon("images/logo.jpg").image)
        this.setTitle("アイドルマスターミリオンライブ　イベントランキングボーダー")
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        this.setSize(640, 600)
        this.setLocationRelativeTo(this)
        this.setVisible(true)
        this.setResizable(false)
    }

}

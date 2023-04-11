package wukongdev;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class scoreRating extends JTable {
    scoreRating() {
        String[] headDataTable = { "ID", "Names", "Scores", "Time" };
        Object[][] dataScoreTable = {
                { 1, "DefaultZ", 99, "20/07/200Z" },
                { 2, "DefaultX", 98, "20/07/200X" },
                { 3, "DefaultC", 97, "20/07/200C" },
                { 4, "DefaultV", 96, "20/07/200V" },
                { 5, "DefaultB", 95, "20/07/200B" } };
        this.setModel(new DefaultTableModel(dataScoreTable, headDataTable));

        TableColumn columnId = this.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(30);
        TableColumn columnNames = this.getColumnModel().getColumn(1);
        columnNames.setPreferredWidth(170);
        TableColumn columnScores = this.getColumnModel().getColumn(2);
        columnScores.setPreferredWidth(50);
        TableColumn columnTime = this.getColumnModel().getColumn(3);
        columnTime.setPreferredWidth(100);

        this.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
        this.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
        this.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());

        this.getTableHeader().setResizingAllowed(false);
        this.getTableHeader().setReorderingAllowed(false);

        this.setBorder(null);
        this.setShowGrid(false);
        this.setEnabled(false);
        this.setVisible(true);
    }
}

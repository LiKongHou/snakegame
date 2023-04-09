package wukongdev;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class scoreRating extends JTable {
    scoreRating() {
        String[] headDataTable = { "ID", "Names", "Scores", "Time" };
        Object[][] dataScoreTable = {
                { 1, "Default", 99, "20/07/2002" },
                { 2, "Default", 98, "20/07/2002" },
                { 3, "Default", 97, "20/07/2002" },
                { 4, "Default", 96, "20/07/2002" },
                { 5, "Default", 95, "20/07/2002" } };
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

        this.setShowGrid(false);
        this.setEnabled(false);
        this.setVisible(true);
    }
}

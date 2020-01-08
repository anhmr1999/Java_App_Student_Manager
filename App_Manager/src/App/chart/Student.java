/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.chart;

import App.controller.Course_Controller;
import App.controller.Student_Controller;
import App.model.tbl_Course;
import java.sql.Connection;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author PC Hoang Anh
 */
public class Student {

    private String title;
    private Connection conn;

    public Student(String title, Connection conn) {
        this.title = title;
        this.conn = conn;
    }

    public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                "Khóa Học", "Số học sinh",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        Course_Controller CC = new Course_Controller(conn);
        List<tbl_Course> LC = CC.select(" ORDER BY ID DESC");
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Student_Controller SC = new Student_Controller(conn);
        if (LC.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                tbl_Course c = LC.get(i);
                int CountSt = SC.select(" where Class_ID in (select ID from tbl_Class where Course_ID = "+c.getId()+")").size();
                dataset.addValue(CountSt, "Số học sinh", c.getName());
            }
        } else {
            for (tbl_Course c : LC) {
                int CountSt = SC.select(" where Class_ID in (select ID from tbl_Class where Course_ID = "+c.getId()+")").size();
                dataset.addValue(CountSt, "Số học sinh", c.getName());
            }
        }
        return dataset;
    }

//    public static void main(String[] args) {
//        ChartPanel chartPanel = new ChartPanel(createChart());
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
//        JFrame frame = new JFrame();
//        frame.add(chartPanel);
//        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
//        frame.setSize(900, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.chart;

import App.controller.Mark_Controller;
import App.model.tbl_Mark;
import java.sql.Connection;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author PC Hoang Anh
 */
public class Mark {

    String title;
    Connection conn;
    String check;

    public JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                title, dataset, true, true, true);
        return chart;
    }

    public PieDataset createDataset() {
        Mark_Controller MC = new Mark_Controller(conn);
        List<tbl_Mark> LM = MC.select(check);
        int num_CT = 0,num_5 = 0, num_6 = 0, num_8 = 0,num_max = 0;
        for (tbl_Mark m : LM) {
            if(m.getStatus() == 1){
                if(m.getMark() < 5){
                    num_5 +=1;
                } else if(m.getMark() >=5 && m.getMark() <6.5){
                    num_6 +=1;
                } else if(m.getMark() >=6.5 && m.getMark() <8){
                    num_8 +=1;
                } else {
                    num_max +=1;
                }
            } else {
                num_CT+=1;
            }
        }
        float CT = ((float)num_CT)/LM.size();
        float d5 = ((float)num_5)/LM.size();
        float d6 = ((float)num_6)/LM.size();
        float d8 = ((float)num_8)/LM.size();
        float max = ((float) 1) - CT - d5 - d6 -d8;
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Chưa Có Điểm ("+num_CT+" )", new Double(CT*100));
        dataset.setValue("Điểm Yếu ( < 5.0 có "+num_5+" )", new Double(d5*100));
        dataset.setValue("Điểm Trung Bình ( từ 5-6.5 có "+num_6+" )", new Double(d6*100));
        dataset.setValue("Điểm Khá ( từ 6.5-8 có "+num_8+" )", new Double(d8*100));
        dataset.setValue("Điểm Giỏi ( từ 8-10 có "+num_max+" )", new Double(max));
        return dataset;
    }

    public Mark(Connection conn, String check,String title) {
        this.conn = conn;
        this.check = check;
        this.title = title;
    }

}
 
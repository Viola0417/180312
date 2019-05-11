package service;



import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Administrator
 * 
 */
public class pie2 {
    // ����ͼ
    public static void generatePieChart(List<Integer> F_list,String path) throws IOException {
    	System.setProperty("java. awt.headless", "true");
    	DefaultPieDataset dpd = new DefaultPieDataset(); // ����һ��Ĭ�ϵı�ͼ
    	DefaultPieDataset dataType = new DefaultPieDataset();   
        // ���ݲ��� ���ݣ�����   
        int a = F_list.get(0);
        int b = F_list.get(1);
        int c = F_list.get(2);
        int d = F_list.get(3);
        int e = F_list.get(4);
        dataType.setValue("<0.1", a);   
        dataType.setValue("0.1-0.2", b);   
        dataType.setValue("0.2-0.3", c);   
        dataType.setValue("0.3-0.4", d);   
        dataType.setValue("0.4-0.5", e);   
         
        DefaultPieDataset data = dataType;   
             // ������ͨ��״ͼ���� 3D ����   
             // ����3D��״ͼ   
        PiePlot3D plot = new PiePlot3D(data);   
        JFreeChart chart = new JFreeChart(   
                     "F distribution",            // ͼ�α���   
                     JFreeChart.DEFAULT_TITLE_FONT, // ��������   
                     plot,                          // ͼ��������   
                     true                           // �Ƿ���ʾͼ��   
             );   
             // ��������ͼƬ�ı���ɫ   
       //chart.setBackgroundPaint(Color.PINK);   
             // ����ͼƬ�б߿�   
       chart.setBorderVisible(true);   
             // ��������   
       Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�   
       Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����   
             // ͼƬ����   
       chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));   
             // �ײ�   
        chart.getLegend().setItemFont(kfont);    
        OutputStream os = new FileOutputStream(path);//ͼƬ���ļ���ʽ�ģ���Ҫ�õ�FileOutputStream���������
        System.out.println(chart);
        ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
        System.out.println("ͼƬ���ɳɹ�");
         
    }
}


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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dao.Log_Dao;
import entity.Log;

/**
 * @author Administrator
 * 
 */
public class multicolumn {

    // ����״ͼ
    public static void generateColumnChart(int stu_id,String path) throws IOException, SQLException {
    	DefaultCategoryDataset dataTime = new DefaultCategoryDataset();   
    	Log_Dao l_dao = new Log_Dao();
		List<Log> log_list = new ArrayList<Log>();
		log_list = l_dao.QueryByStuNo(stu_id);
		int num=1;
		//�ж�ѧ��������Щ��
		List<Integer> task_list = new ArrayList<Integer>();
		List<Double> f_list = new ArrayList<Double>();
		for(Log l:log_list) {
			if(!task_list.contains(l.getTask_id())) {
				task_list.add(l.getTask_id());
			}
		}
		System.out.println(task_list);
		
		for(int t:task_list) {
			num=1;
			f_list = new ArrayList<Double>();
			System.out.println("-------------");
			System.out.println(t);
			for(Log l:log_list) {
				if(l.getTask_id()==t) {
					f_list.add(l.getF());
				}
			}
			String t_str = String.valueOf(t);
			System.out.println(f_list);
			for(double f:f_list) {
				String num_str = String.valueOf(num);
				dataTime.addValue(f,num_str,t_str);
				num++;
			}
			System.out.println("-------------");
		}
    	//DefaultCategoryDataset dataTime = new DefaultCategoryDataset();   
        // ����һ������   
  
        try {   
            DefaultCategoryDataset data = dataTime;   
            // ʹ��ChartFactory����3D��״ͼ������ʹ��3D��ֱ��ʹ��createBarChart   
            JFreeChart chart = ChartFactory.createBarChart3D(   
                    "��ѧ���������ͳ��",    
                    "���",    
                    "F",    
                    data,   
                    PlotOrientation.VERTICAL,    
                    true,    
                    false,    
                    false  
            );   
            // ��������ͼƬ�ı���ɫ   
            //chart.setBackgroundPaint(Color.PINK);   
            // ����ͼƬ�б߿�   
            chart.setBorderVisible(true);   
            Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�   
            Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����   
            // ͼƬ����   
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));   
            // �ײ�   
            chart.getLegend().setItemFont(kfont);   
            // �õ�������������������   
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();   
            categoryplot.setDomainGridlinesVisible(true);   
            categoryplot.setRangeCrosshairVisible(true);   
            categoryplot.setRangeCrosshairPaint(Color.blue);   
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();   
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();   
            barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));   
            barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));   
            CategoryAxis domainAxis = categoryplot.getDomainAxis();            
            /*------����X�������ϵ�����-----------*/  
            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));   
            /*------����X��ı�������------------*/  
            domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));   
            /*------����Y�������ϵ�����-----------*/  
            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));   
            /*------����Y��ı�������------------*/  
            numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));   
            /*------���������˵ײ��������������-----------*/  
            chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));     
            //ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,   
            //        chart, 500, 300, null);   
            OutputStream os = new FileOutputStream(path);//ͼƬ���ļ���ʽ�ģ���Ҫ�õ�FileOutputStream���������
            System.out.println(chart);
            ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
            System.out.println("ͼƬ���ɳɹ�");
        } catch (Exception es) {   
            es.printStackTrace();   
        }   
    }   
}

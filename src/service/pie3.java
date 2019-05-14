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

import dao.Log_Dao;
import dao.Student_Dao;
import dao.Task_Dao;
import entity.Log;

/**
 * @author Administrator
 * 
 */
public class pie3 {
    // ����ͼ
    public static void generatePieChart(String task_kind,String path) throws IOException, SQLException {
    	System.setProperty("java. awt.headless", "true");
    	DefaultPieDataset dpd = new DefaultPieDataset(); // ����һ��Ĭ�ϵı�ͼ
    	DefaultPieDataset dataType = new DefaultPieDataset();   
        // ���ݲ��� ���ݣ�����   
		Log_Dao l_dao = new Log_Dao();
		List<Integer> task_id_list = new ArrayList<Integer>();
		List<Integer> res_list = new ArrayList<Integer>();
		List<String> algo_list = new ArrayList<String>();
		Task_Dao t_dao = new Task_Dao();
		task_id_list = t_dao.queryIDbyKind(task_kind);
		//System.out.println(task_id_list);
		int a_num = 0;//��¼SVM
		int b_num = 0;//��¼������
		int c_num = 0;//��¼KNN
		int d_num = 0;//��¼���ɭ��
		int e_num = 0;//��¼���ر�Ҷ˹
		int f_num = 0;//��¼�߼��ع�
		int g_num = 0;//��¼����
		for(int task_id:task_id_list) {
			//System.out.println("++++++++++++++++++++++++++++++++");
			//System.out.println(task_id);
			algo_list = l_dao.QueryAlgobyTask(task_id);
			if(!algo_list.isEmpty()) {
				for(String s:algo_list) {
					//System.out.println(s);
					if(s.equals("SVM")) {
						a_num++;
					}else if(s.equals("������")) {
						b_num++;
					}else if(s.equals("KNN�����")) {
						c_num++;
					}else if(s.equals("���ɭ��")) {
						d_num++;
					}else if(s.equals("���ر�Ҷ˹")) {
						e_num++;
					}else if(s.equals("�߼��ع�")) {
						f_num++;
					}else if(s.equals("����")) {
						g_num++;
					}
				}
	
			}
			
			//System.out.println(algo_list);
			//System.out.println("++++++++++++++++++++++++++++++++");
		}
		//System.out.println(a_num);

		res_list.add(a_num);
		res_list.add(b_num);
		res_list.add(c_num);
		res_list.add(d_num);
		res_list.add(e_num);
		res_list.add(f_num);
		res_list.add(g_num);
		
        int a = res_list.get(0);
        int b = res_list.get(1);
        int c = res_list.get(2);
        int d = res_list.get(3);
        int e = res_list.get(4);
        int f = res_list.get(5);
        int g = res_list.get(6);
        
        dataType.setValue("SVM", a);   
        dataType.setValue("Decision Tree", b);   
        dataType.setValue("KNN", c);   
        dataType.setValue("Random Forest", d);   
        dataType.setValue("Bayesian", e); 
        dataType.setValue("Logistic", f);
        dataType.setValue("Others", g);
        String title = "key algorithm distribution";
        DefaultPieDataset data = dataType;   
             // ������ͨ��״ͼ���� 3D ����   
             // ����3D��״ͼ   
        PiePlot3D plot = new PiePlot3D(data);   
        JFreeChart chart = new JFreeChart(   
                     title,            // ͼ�α���   
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


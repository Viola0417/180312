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

    // 画柱状图
    public static void generateColumnChart(int stu_id,String path) throws IOException, SQLException {
    	System.setProperty("java. awt.headless", "true");
    	DefaultCategoryDataset dataTime = new DefaultCategoryDataset();   
    	Log_Dao l_dao = new Log_Dao();
		List<Log> log_list = new ArrayList<Log>();
		log_list = l_dao.QueryByStuNo(stu_id);
		int num=1;
		//判断学生做过哪些题
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
        // 这是一组数据   
  
        try {   
            DefaultCategoryDataset data = dataTime;   
            // 使用ChartFactory创建3D柱状图，不想使用3D，直接使用createBarChart   
            JFreeChart chart = ChartFactory.createBarChart3D(   
                    "F statistics",    
                    "task_id",    
                    "F",    
                    data,   
                    PlotOrientation.VERTICAL,    
                    true,    
                    false,    
                    false  
            );   
            // 设置整个图片的背景色   
            //chart.setBackgroundPaint(Color.PINK);   
            // 设置图片有边框   
            chart.setBorderVisible(true);   
            Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部   
            Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题   
            // 图片标题   
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));   
            // 底部   
            chart.getLegend().setItemFont(kfont);   
            // 得到坐标设置字体解决乱码   
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();   
            categoryplot.setDomainGridlinesVisible(true);   
            categoryplot.setRangeCrosshairVisible(true);   
            categoryplot.setRangeCrosshairPaint(Color.blue);   
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();   
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();   
            barrenderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 12));   
            barrenderer.setSeriesItemLabelFont(1, new Font("宋体", Font.PLAIN, 12));   
            CategoryAxis domainAxis = categoryplot.getDomainAxis();            
            /*------设置X轴坐标上的文字-----------*/  
            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));   
            /*------设置X轴的标题文字------------*/  
            domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));   
            /*------设置Y轴坐标上的文字-----------*/  
            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));   
            /*------设置Y轴的标题文字------------*/  
            numberaxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));   
            /*------这句代码解决了底部汉字乱码的问题-----------*/  
            chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));     
            //ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,   
            //        chart, 500, 300, null);   
            OutputStream os = new FileOutputStream(path);//图片是文件格式的，故要用到FileOutputStream用来输出。
            System.out.println(chart);
            ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
            System.out.println("图片生成成功");
        } catch (Exception es) {   
            es.printStackTrace();   
        }   
    }   
}

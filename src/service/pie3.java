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
    // 画饼图
    public static void generatePieChart(String task_kind,String path) throws IOException, SQLException {
    	System.setProperty("java. awt.headless", "true");
    	//System.out.println("进入Pie3函数，题目类别是"+task_kind);
    	DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
    	DefaultPieDataset dataType = new DefaultPieDataset();   
        // 数据参数 内容，数量   
		Log_Dao l_dao = new Log_Dao();
		List<Integer> task_id_list = new ArrayList<Integer>();
		List<Integer> res_list = new ArrayList<Integer>();
		List<String> algo_list = new ArrayList<String>();
		Task_Dao t_dao = new Task_Dao();
		task_id_list = t_dao.queryIDbyKind(task_kind);
		//System.out.println("回归的题目有："+task_id_list);
		//System.out.println(task_id_list);
		int a_num = 0;//记录SVM
		int b_num = 0;//记录决策树
		int c_num = 0;//记录KNN
		int d_num = 0;//记录随机森林
		int e_num = 0;//记录朴素贝叶斯
		int f_num = 0;//记录逻辑回归
		int g_num = 0;//记录其他
		for(int task_id:task_id_list) {
			//System.out.println("++++++++++++++++++++++++++++++++");
			//System.out.println(task_id);
			algo_list = l_dao.QueryAlgobyTask(task_id);
			if(!algo_list.isEmpty()) {
				for(String s:algo_list) {
				//	System.out.println(s);
					if(s.equals("SVM")) {
						a_num++;
					}else if(s.equals("决策树")) {
						b_num++;
					}else if(s.equals("KNN最近邻")) {
						c_num++;
					}else if(s.equals("随机森林")) {
						d_num++;
					}else if(s.equals("朴素贝叶斯")) {
						e_num++;
					}else if(s.equals("逻辑回归")) {
						f_num++;
					}else if(s.equals("其他")) {
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
             // 生成普通饼状图除掉 3D 即可   
             // 生产3D饼状图   
        PiePlot3D plot = new PiePlot3D(data);   
        JFreeChart chart = new JFreeChart(   
                     title,            // 图形标题   
                     JFreeChart.DEFAULT_TITLE_FONT, // 标题字体   
                     plot,                          // 图标标题对象   
                     true                           // 是否显示图例   
             );   
             // 设置整个图片的背景色   
       //chart.setBackgroundPaint(Color.PINK);   
             // 设置图片有边框   
       chart.setBorderVisible(true);   
             // 配置字体   
       Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部   
       Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题   
             // 图片标题   
       chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));   
             // 底部   
        chart.getLegend().setItemFont(kfont);    
        OutputStream os = new FileOutputStream(path);//图片是文件格式的，故要用到FileOutputStream用来输出。
        //System.out.println(chart);
        ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
        System.out.println("图片生成成功");
         
    }
}


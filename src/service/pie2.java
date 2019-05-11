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
    // 画饼图
    public static void generatePieChart(List<Integer> F_list,String path) throws IOException {
    	System.setProperty("java. awt.headless", "true");
    	DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
    	DefaultPieDataset dataType = new DefaultPieDataset();   
        // 数据参数 内容，数量   
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
             // 生成普通饼状图除掉 3D 即可   
             // 生产3D饼状图   
        PiePlot3D plot = new PiePlot3D(data);   
        JFreeChart chart = new JFreeChart(   
                     "F distribution",            // 图形标题   
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
        System.out.println(chart);
        ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
        System.out.println("图片生成成功");
         
    }
}


package service;


import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Administrator
 * 
 */
public class pie {
    // 画饼图
    public static void generatePieChart(List<Integer> F_list,String path) throws IOException {
        DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
        int a = F_list.get(0);
        int b = F_list.get(1);
        int c = F_list.get(2);
        int d = F_list.get(3);
        int e = F_list.get(4);
        dpd.setValue("<0.1", a); // 输入数据
        dpd.setValue("0.1-0.2", b);
        dpd.setValue("0.2-0.3", c);
        dpd.setValue("0.3-0.4", d);
        dpd.setValue("0.4-0.5", e);

        // 创建主题样式  (可以解决中文乱码问题)
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        
        JFreeChart chart = ChartFactory.createPieChart("F区间统计图", dpd, true,
                true, false);
        // 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        ChartFrame chartFrame = new ChartFrame("F区间统计图", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        //chartFrame.pack(); // 以合适的大小展现图形
        //chartFrame.setVisible(true);// 图形是否可见
        OutputStream os = new FileOutputStream(path);//图片是文件格式的，故要用到FileOutputStream用来输出。
        System.out.println(chart);
        ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
        System.out.println("图片生成成功");
    }
}

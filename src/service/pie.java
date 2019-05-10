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
    // ����ͼ
    public static void generatePieChart(List<Integer> F_list,String path) throws IOException {
        DefaultPieDataset dpd = new DefaultPieDataset(); // ����һ��Ĭ�ϵı�ͼ
        int a = F_list.get(0);
        int b = F_list.get(1);
        int c = F_list.get(2);
        int d = F_list.get(3);
        int e = F_list.get(4);
        dpd.setValue("<0.1", a); // ��������
        dpd.setValue("0.1-0.2", b);
        dpd.setValue("0.2-0.3", c);
        dpd.setValue("0.3-0.4", d);
        dpd.setValue("0.4-0.5", e);

        // ����������ʽ  (���Խ��������������)
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // ���ñ�������
        standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
        // ����ͼ��������
        standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
        // �������������
        standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
        // Ӧ��������ʽ
        ChartFactory.setChartTheme(standardChartTheme);
        
        JFreeChart chart = ChartFactory.createPieChart("F����ͳ��ͼ", dpd, true,
                true, false);
        // ���Բ�����API�ĵ�,��һ�������Ǳ��⣬�ڶ���������һ�����ݼ���������������ʾ�Ƿ���ʾLegend�����ĸ�������ʾ�Ƿ���ʾ��ʾ�������������ʾͼ���Ƿ����URL
        ChartFrame chartFrame = new ChartFrame("F����ͳ��ͼ", chart);
        // chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
        //chartFrame.pack(); // �Ժ��ʵĴ�Сչ��ͼ��
        //chartFrame.setVisible(true);// ͼ���Ƿ�ɼ�
        OutputStream os = new FileOutputStream(path);//ͼƬ���ļ���ʽ�ģ���Ҫ�õ�FileOutputStream���������
        System.out.println(chart);
        ChartUtilities.writeChartAsJPEG(os, chart, 500, 400);
        System.out.println("ͼƬ���ɳɹ�");
    }
}

package plane;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

public class PlaneAccident {
    //数据获存取链表
    private static List<String> allData = new ArrayList<>();
    public static void main(String[] args){

    }

    /**
     * 从源文件中爬取数据
     */
    public static void getData(String filepath){
        File f = new File(filepath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;
            while ( (line = br.readLine()) != null){
                allData.add(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void maxDeadYear(){
        //记录年份对应的死亡人数
        Map<Integer,String> map = new HashMap<>();
        //时间用date显示
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        for (String data:allData){
            String[] strs = data.split(",");
            if (strs[0] != null){
                try {
                    Date date = sdf.parse(strs[0]);
                    int year = date.getYear();
                }catch (Exception e){

                }
            }
        }
    }
}

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
        Map<Integer,Integer> map = new HashMap<>();
        //时间用date显示
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        for (String data:allData){
            String[] strs = data.split(",");
            if (strs[0] != null){
                try {
                    Date date = sdf.parse(strs[0]);
                    int year = date.getYear();
                    //判断map是否记录过
                    if (map.containsKey(year)){
                        map.put(year,map.get(year)+Integer.parseInt(strs[10]));
                    }else {
                        map.put(year,Integer.parseInt(strs[10]));
                    }
                }catch (Exception e){

                }
            }
        }
        int max_year = -1;
        int dead_count = 0;
        Set<Integer> keyset = map.keySet();
        for (int year:keyset){
            if (map.get(year)>dead_count&&map.get(year)<10000){
                max_year = year;
                dead_count = map.get(year);
            }
        }
        System.out.println("死亡人数最多的年份:"+(max_year+1901)+"死亡人数"+dead_count);
    }

    public static void maxAccidentYear(){
        Map<Integer,Integer> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        for (String data:allData){
            String[] strs= data.split(",");
            if (strs[0] != null){
                try {
                    Date date = sdf.parse(strs[0]);
                    int year = date.getYear();
                    if (map.containsKey(year)){
                        map.put(year,map.get(year)+1);
                    }else {
                        map.put(year,1);
                    }
                }catch (Exception e){

                }
            }
        }
        int max_year = 0;
        int acc_count = 0;

        Set<Integer> keySet = map.keySet();
        for (int year:keySet){
            if (map.get(year)>acc_count){
                max_year = year;
                acc_count = map.get(year);
            }
        }
        System.out.println("事故最多的年份"+(max_year+1901)+" 改年事故发生的数目"+acc_count);
    }

    public static void FrequencyPeriod(){
        Map<String,Integer> map = new HashMap<>();

        String[] strsTime = {"上午(6:00~12:00)","下午(12:00~18:00)","晚上(18:00~24:00)","深夜(0:00~6:00)"};

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (String data:allData){
            String[] strs = data.split(",");
            if (strs[1] != null){
                try{
                    Date date = sdf.parse(strs[1]);
                    int hour = date.getHours();
                    int index = 0;
                    if (hour>=12&&hour<18){
                        index = 1;
                    }else if (hour >= 18){
                        index = 2;
                    }else if (hour <6){
                        index = 6;
                    }

                    if (map.containsKey(strsTime[index])){
                        map.put(strsTime[index],map.get(strsTime[index])+1);
                    }else{
                        map.put(strsTime[index],1);
                    }
                }catch (Exception e){

                }
            }
            String maxTime = null;
            int maxCount = 0;
            Set<String> keySet = map.keySet();
            for (String timeScope:keySet){
                if (map.get(timeScope) >maxCount) {
                    maxTime = timeScope;
                    maxCount = map.get(maxTime);
                }
            }
            System.out.println("事故发生最多的时间段: ");
            System.out.println(maxTime+" : "+maxCount);
        }
    }
}

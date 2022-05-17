
import java.util.HashMap;
import java.util.Map;

public class TestThread {
    public String d ;
    public Map<String,String> stringMap;

    public static void main(String[] args) throws InterruptedException {
        TestThread thread = new TestThread();
        thread.stringMap = new HashMap<>();
        thread.stringMap.put("13412","Миша");
        thread.stringMap.put("7335","Саша");
        thread.stringMap.put("12","Коля");
        thread.getStringMap().put("231","Даша");
        thread.getStringMap().put("21","Марина");
        thread.getStringMap().put("18","Оля");
        for (Map.Entry<String,String> s:thread.getStringMap().entrySet()){
            if (s.getKey().equals("231")){
                System.out.println(s.getValue());
            }
        }
        System.out.println(thread.getStringMap().size());
    }

    public Map<String,String> getStringMap() {
        return stringMap;
    }

    public  String getD() {
        return d;
    }

    public  void setD(String d) {
        this.d = d;
    }
}

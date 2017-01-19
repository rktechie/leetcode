import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        
        t1();
        String s = "2016-07-18 10:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date date = simpleDateFormat.parse(s);

            System.out.println("date : "+ date.getTime());
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
    }

    static void t1() {
        
        String s = "2016-07-18 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = simpleDateFormat.parse(s);

            System.out.println("date : "+simpleDateFormat.format(date));
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
    }
}

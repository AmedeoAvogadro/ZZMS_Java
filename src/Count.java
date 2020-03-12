import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Count {
    static int max = 20;
    static int[] l = new int[max];

    public static void main(String[] args) throws FileNotFoundException{

        File in_f = new File("C:\\Users\\11096\\IdeaProjects\\ZZMS_Java\\src\\ori.txt");
        InputStream in_s = new FileInputStream(in_f);
        Scanner scanner = new Scanner(in_s);

        File out_f = new File("C:\\Users\\11096\\IdeaProjects\\ZZMS_Java\\src\\new.txt");
        OutputStream out_s = new FileOutputStream(out_f);
        PrintWriter printWriter = new PrintWriter(out_s);

        Pattern pattern = Pattern.compile("[qwertyuiopasdfghjklzxcvbnm]$");
        Pattern pat = Pattern.compile("[']");
        Matcher matcher;
        Matcher mat;

        int x;
        String s;
        do{
            try {
                s = scanner.next();
                matcher = pattern.matcher(s);
                mat = pat.matcher(s);
                if (mat.find())
                    x = s.length()-1;
                else if (matcher.find())
                    x = s.length();
                else
                    x = s.length()-1;
                l[x-1] += 1;
            }catch (Exception e){
                x = 0;
            }
        }while (x != 0);

        for (int i=0; i<l.length; i+=1)
            printWriter.write(i+1+": "+l[i]+"\n");
        printWriter.flush();
    }
}

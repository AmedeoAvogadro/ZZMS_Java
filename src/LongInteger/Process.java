package LongInteger;

import java.util.*;

import java.io.*;

public class Process {
    public static void main(String[] args) throws IOException {
        File in_f = new File("C:\\Users\\11096\\IdeaProjects\\ZZMS_Java\\src\\LongInteger\\Long_Input.txt");
        File out_f = new File("C:\\Users\\11096\\IdeaProjects\\ZZMS_Java\\src\\LongInteger\\Long_Output.txt");
        InputStream in_s = new FileInputStream(in_f);
        OutputStream out_s = new FileOutputStream(out_f);
        Scanner scnr = new Scanner(in_s);
        PrintWriter prtwtr = new PrintWriter(out_s);

        LongInteger.input_file(scnr);
        LongInteger.output_file(prtwtr);
        scnr.close();
        prtwtr.flush();
        prtwtr.close();
    }
}

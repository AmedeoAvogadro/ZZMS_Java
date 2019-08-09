package LongInteger;

import java.util.*;

import java.io.*;

public class LongInteger {
    public static List<LongInteger> all = new ArrayList<LongInteger>();
    private List<Integer> ints;
    final private Integer[] save;
    public LongInteger(List<Integer> ints) {
        this.ints = ints;
        this.save = this.ints.toArray(Integer[]::new);
    }

    public Integer[] getSave() {
        return save;
    }

    public List<Integer> getInts() {
        return ints;
    }

    public void setInts(List<Integer> ints) {
        this.ints = ints;
    }

    @Override
    public String toString() {
        return format(this.ints);
    }

    private static String format(List<Integer> ll){
        List<String> x = new ArrayList<>();
        for (Integer i : ll)
            x.add(String.valueOf(i));
        return String.join("",x);
    }

    private static List<Integer> negate(List<Integer> x) {
        x.set(0, -x.get(0));
        return x;
    }

    private void reSet() {
        this.ints.clear();
        this.ints.addAll(Arrays.asList(this.save));
    }

    private static void modify_length_list(List<Integer> lst1, List<Integer> lst2) {
        if (lst1.size() > lst2.size()) {
            Collections.reverse(lst2);
             while (lst1.size() > lst2.size())
                lst2.add(0);
            Collections.reverse(lst2);
        }

        if (lst1.size() < lst2.size()) {
            Collections.reverse(lst1);
            while (lst2.size() > lst1.size())
                lst1.add(0);
            Collections.reverse(lst1);
        }
    }

    public List<Integer> plus(LongInteger o) {
        if (this.ints.get(0) < 0 && o.ints.get(0) < 0) {
            this.ints.set(0,Math.abs( this.ints.get(0)));
            o.ints.set(0,Math.abs(o.ints.get(0)));
            return negate(this.plus(o));
        }
        if (this.ints.get(0) < 0 && o.ints.get(0) >= 0) {
            this.ints.set(0,Math.abs( this.ints.get(0)));
            o.ints.set(0,Math.abs(o.ints.get(0)));
            return negate(this.minus(o));
        }
        if (this.ints.get(0) >= 0 && o.ints.get(0) < 0) {
            this.ints.set(0,Math.abs( this.ints.get(0)));
            o.ints.set(0,Math.abs(o.ints.get(0)));
            return this.minus(o);
        }

        List<Integer> re = new ArrayList<>();
        re.add(0);
        modify_length_list(this.ints, o.ints);

        for (int i = 0; i < this.ints.size(); i++)
            re.add(this.ints.get(i) + o.ints.get(i));

        Collections.reverse(re);
        for (int i = 1; i < re.size(); i++) {
            if (re.get(i - 1) > 9) {
                re.set(i, re.get(i) + 1);
                re.set(i - 1, re.get(i - 1) - 10);
            }
        }
        Collections.reverse(re);

        this.reSet();
        o.reSet();

        while (re.get(0) == 0)
            re.remove(0);

        return re;
    }

    public List<Integer> minus(LongInteger o) {
        if (this.ints.get(0) < 0 && o.ints.get(0) < 0) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return negate(this.minus(o));
        }
        if (this.ints.get(0) < 0 && o.ints.get(0) >= 0) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return negate(this.plus(o));
        }
        if (this.ints.get(0) >= 0 && o.ints.get(0) < 0) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return this.plus(o);
        }
        if(this.ints.get(0) > 0 && o.ints.get(0) > 0 && this.ints.size() < o.ints.size()) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return negate(o.minus(this));
        }

        List<Integer> re = new ArrayList<>();
        re.add(0);
        modify_length_list(this.ints, o.ints);

        for (int i = 0; i < this.ints.size(); i++)
            re.add(this.ints.get(i) - o.ints.get(i));

        Collections.reverse(re);
        for (int i = 1; i < re.size(); i++) {
            if (re.get(i - 1) < 0) {
                re.set(i, re.get(i) - 1);
                re.set(i - 1, re.get(i - 1) + 10);
            }
        }
        Collections.reverse(re);

        this.reSet();
        o.reSet();

        while (re.get(0) == 0)
            re.remove(0);

        return re;
    }

    public List<Integer> multiply(LongInteger o) {
        if (this.ints.get(0) < 0 && o.ints.get(0) < 0) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return this.multiply(o);
        }
        else if (this.ints.get(0) < 0 || o.ints.get(0) < 0) {
            this.ints.set(0, Math.abs(this.ints.get(0)));
            o.ints.set(0, Math.abs(o.ints.get(0)));
            return negate(this.multiply(o));
        }

        List<Integer> re = new ArrayList<>();
        for (int i = this.ints.size()-1; i >= 0; i--) {
            List<Integer> temp = new ArrayList<>();
            temp.add(0);

            for (int j = 0; j < o.ints.size(); j++)
                temp.add(o.ints.get(j) * this.ints.get(i));

            for (int k = temp.size()-1; k > 0 ; k--) {
                if (temp.get(k) > 9) {
                    int con = Integer.parseInt(String.valueOf(temp.get(k).toString().charAt(0)));
                    temp.set(k-1, temp.get(k-1) + con);
                    temp.set(k, temp.get(k) - 10*con);
                }
            }

            int m = 1;
            while (m < this.ints.size()-i) {
                temp.add(0);
                m++;
            }

            modify_length_list(re, temp);
            LongInteger x = new LongInteger(re);
            LongInteger y = new LongInteger(temp);
            re = x.plus(y);
        }
        this.reSet();
        o.reSet();

        while (re.get(0) == 0)
            re.remove(0);

        return re;
    }

    public static void input_file(Scanner s) {
        while (s.hasNextLine()) {
            String ss = String.valueOf(s.nextInt());
            List<Integer> lst = new ArrayList<>();
            boolean flag = false;
            for (int i = 0; i < ss.length(); i++) {
                if (ss.charAt(i) == '-') {
                    lst.add(Integer.parseInt(String.format("%c%c", ss.charAt(0), ss.charAt(1))));
                    flag = true;
                    continue;
                }
                if (flag) {
                    flag = false;
                    continue;
                }
                lst.add(Integer.parseInt(String.valueOf(ss.charAt(i))));
            }
            LongInteger x = new LongInteger(lst);
            all.add(x);
        }
    }

    public static void output_file(PrintWriter p) {
        for (int i = 0; i < all.size(); i+=2){
            p.write(format(all.get(i).plus( all.get(i+1)))+"\n");
            p.flush();
            p.write(format(all.get(i).minus(all.get(i+1)))+"\n");
            p.flush();
            p.write(format(all.get(i).multiply(all.get(i+1)))+"\n");
            p.flush();
        }
    }
}
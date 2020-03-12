public class Slot {
    public static void main(String[] args) {
        for (int s = 0; s < 8; s++) {
            int num = 10000000;
            double m = 0;
            int j, k;
            for (int i = 0; i < num; i++) {
                j = (int) (Math.random() * 6 + 1);
                k = (int) (Math.random() * 6 + 1);
                m -= 2.99;
                switch (j) {
                    case 1:
                        m += 0.88;
                        break;
                    case 2:
                        if (k != 6)
                            m += 1.88;
                        break;
                    case 3:
                        if (k < 5)
                            m += 3.88;
                        break;
                    case 4:
                        if (k < 4)
                            m += 8.88;
                        break;
                    case 5:
                        if (k < 3)
                            m += 12.88;
                        break;
                    case 6:
                        if (k == 1)
                            m += 28.88;
                        break;
                }
            }
            System.out.println(m);
        }
    }
}

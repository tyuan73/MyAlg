package AmazonTest2015;

/**
 * Created by yuantian on 5/15/15.
 */

/*

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class A2 {

    static class Student implements Comparable<Student> {
        String fName, lName;
        int avg;
        int[] scores;
        String grade;

        Student(String fn, String ln, ArrayList<Integer> s) {
            this.fName = fn;
            this.lName = ln;
            Collections.sort(s);
            double t = 0;
            scores = new int[5];
            for (int i = s.size() - 1, j = 0; i >= 0; i--, j++) {
                scores[j] = s.get(i);
                t += scores[j];
            }
            t /= 5.0;
            this.avg = (int) (t + 1 - .05);
            System.out.println(this.avg);

            if (this.avg >= 93) {
                this.grade = "A";
            } else if (this.avg >= 90) {
                this.grade = "A-";
            } else if (this.avg >= 87) {
                this.grade = "B+";
            } else if (this.avg >= 83) {
                this.grade = "B";
            } else if (this.avg >= 80) {
                this.grade = "B-";
            } else if (this.avg >= 77) {
                this.grade = "C+";
            } else if (this.avg >= 73) {
                this.grade = "C";
            } else if (this.avg >= 70) {
                this.grade = "C-";
            } else if (this.avg >= 67) {
                this.grade = "D+";
            } else if (this.avg >= 63) {
                this.grade = "D";
            } else if (this.avg >= 60) {
                this.grade = "D-";
            } else {
                this.grade = "F";
            }
        }

        public int compareTo(Student st) {
            return this.avg - st.avg;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(lName).append(", ").append(fName).append(", ");
            sb.append(avg).append(", ").append(grade).append(": ");
            for (int s : scores) {
                sb.append(s).append(", ");
            }
            return sb.substring(0, sb.length() - 2).toString();
        }
    }

    static String[] grade(String[] grades) {
        int n = grades.length;
        Student[] all = new Student[n];
        for (int j = 0; j < grades.length; j++) {
            String one = grades[j];
            String[] ele = one.split(",");
            String fn = ele[0].trim();
            String ln = ele[1].trim();
            ArrayList<Integer> scores = new ArrayList<>();
            for (int i = 2; i < ele.length; i++) {
                String e = ele[i].trim();
                if (e.length() > 0) {
                    scores.add(Integer.parseInt(e));
                }
            }
            all[j] = new Student(fn, ln, scores);
        }

        Arrays.sort(all);

        for (int i = 0; i < n; i++) {
            grades[i] = all[i].toString();
        }
        return grades;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = "/tmp/Aoutput";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;

        int _grades_size = Integer.parseInt(in.nextLine());
        String[] _grades = new String[_grades_size];
        String _grades_item;
        for (int _grades_i = 0; _grades_i < _grades_size; _grades_i++) {
            try {
                _grades_item = in.nextLine();
            } catch (Exception e) {
                _grades_item = null;
            }
            _grades[_grades_i] = _grades_item;
        }

        res = grade(_grades);
        for (int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
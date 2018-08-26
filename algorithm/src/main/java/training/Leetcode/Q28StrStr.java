package training.Leetcode;

public class Q28StrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null) {
            return -1;
        }
        if (needle == null) {
            return 0;
        }
        return haystack.indexOf(needle);
    }
    public static void main(String[] args) {
        
    }
}

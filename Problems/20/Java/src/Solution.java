import java.util.ArrayList;
import java.util.List;

class Solution {
    public String removeRepeatation(String s){
        if(s == null || s.length() < 2)
            return s;

        int j = 0;
        String newS = null;
        List<Integer> indexToRemove = new ArrayList<>();
        while(j < s.length() - 1){
            if(String.valueOf(s.charAt(j)).equals("(") && String.valueOf(s.charAt(j + 1)).equals(")") )
                indexToRemove.add(j);
            else if(String.valueOf(s.charAt(j)).equals("{") && String.valueOf(s.charAt(j + 1)).equals("}") )
                indexToRemove.add(j);
            else if(String.valueOf(s.charAt(j)).equals("[") && String.valueOf(s.charAt(j + 1)).equals("]") )
                indexToRemove.add(j);
            j++;
        }
        indexToRemove = indexToRemove.reversed();
        for(Integer index : indexToRemove){
            newS = s.substring(0, index) + s.substring(index + 2);
        }
        if(newS != null && newS.length() != s.length())
            return removeRepeatation(newS);

        return newS;
    }

    public boolean isValid(String s) {
        s = removeRepeatation(s);
        if(s == null || s.length() % 2 != 0) {
            return false;
        }

        String[] parts = { s.substring(0, s.length() / 2), s.substring(s.length() / 2) };
        String reverse = "";
        for(int i = s.length() / 2 - 1; i >= 0; i--){
            if(String.valueOf(parts[1].charAt(i)).equals(")"))
                reverse += "(";
            else if(String.valueOf(parts[1].charAt(i)).equals("}"))
                reverse += "{";
            else if(String.valueOf(parts[1].charAt(i)).equals("]"))
                reverse += "[";
        }
        return reverse.equals(parts[0]);
    }
}

class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("()", "()[]{}", "(]", "([])", "([)]"));
        // List<String> list = new ArrayList<>(List.of("()[]{}"));

        Solution solution = new Solution();

        for(String s : list){
            boolean result = solution.isValid(s);
            String[] parts = { s.substring(0, s.length() / 2), s.substring(s.length() / 2) };
            System.out.print("Input string: " + s);
            System.out.print(" 1st Half: " + parts[0]);
            System.out.println(" 2nd Half: " + parts[1]);
            System.out.println("Is the string valid? " + result);
            System.out.println("-----");
        }

    }
}

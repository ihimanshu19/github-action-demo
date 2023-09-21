import java.util.HashMap;

public class Bitwise {

    public static void main(String[] args) {
        minWindow("rGusddQS6UvK9GzxPSJDMSyoTOpkLK18ZfxKF64HwZ0","o8athbAkGyGg7B79xJzPZAXmnqw1dWlUMmA3LehdRaXl2S7HVrgmpUvj9m2RtnZggXG9B");
    }
    public static String minWindow(String A, String B) {
        StringBuilder ansString = new StringBuilder();
        HashMap<String,Integer> freqA = new HashMap<>();
        HashMap<String,Integer> freqB = new HashMap<>();
        String []strA = A.split("");
        String []strB = B.split("");
        for(int i = 0 ; i < strB.length ; i++){
            if(freqB.containsKey(strB[i])){
                int j = freqB.get(strB[i]);
                freqB.put(strB[i],j+1);
            }else{
                freqB.put(strB[i], 1);
            }
        }
        int l = 0, r = strB.length - 1;
        for(int i = l ; i <= r ; i++){
            if(freqA.containsKey(strA[i])){
                int j = freqA.get(strA[i]);
                freqA.put(strA[i],j+1);
            }else{
                freqA.put(strA[i], 1);
            }
        }
        int ansLength = Integer.MAX_VALUE;
        int startIndx = 0, endIndx = 0;
        while( r < strA.length){
            if(compare(freqA, freqB)){
                if(ansLength > (r - l + 1)){
                    ansLength = r - l + 1 ;
                    startIndx = l;
                    endIndx = r;
                    // System.out.println("L="+l+"R="+r);
                }
                int j = freqA.get(strA[l]);
                freqA.put(strA[l] , j - 1);
                l++;
            }else{
                r++;
                if(r == strA.length){
                    break;
                }
                int j = freqA.get(strA[r]) == null ? 0 : freqA.get(strA[r]);
                freqA.put(strA[r] , j + 1);
            }
        }

        return ansLength == Integer.MAX_VALUE ? "" : A.substring(startIndx,endIndx+1);
    }

    private static boolean compare(HashMap<String,Integer> freqA, HashMap<String,Integer> freqB){
        for(String key : freqB.keySet()){
            if( freqA.get(key) == null || freqA.get(key) < freqB.get(key)){
                return false;
            }
        }
        return true;
    }
}

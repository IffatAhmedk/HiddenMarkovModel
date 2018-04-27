package hmm;

import java.util.Scanner;

public class HMM {
    public static void main(String[] args) {
            
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your sequence:");
        String sequence = sc.nextLine();
        
        double[][] a= {{0,0.8,0.2,0},{0,0.6,0.3,0.1},{0,0.4,0.5,0.1},{0,0,0,0}};
        double[][] b= {{0.2,0.4,0.4},{0.5,0.4,0.1}};
        
        Model modelObject = new Model(4, 8, a, b);
        
        double probability = Computation(sequence);
        
        System.out.println(""+probability);
    }
    

    private static double Computation(String sequence){
        int n = sequence.length();
        char[] iceCreamNumber = sequence.toCharArray();
        int currentState = 0;
        double[][] a= { {0,0.8,0.2,0},
                        {0,0.6,0.3,0.1},
                        {0,0.4,0.5,0.1},
                        {0,0,0,0}};
        double[][] b= { {0.2,0.4,0.4},
                        {0.5,0.4,0.1}};
        
        double probabilityH= 0;
        double probabilityC= 0;
        double probability1C =0;
        double probability2C =0;
        double probability1H =0;
        double probability2H =0;
        
        if(iceCreamNumber[0] == '1'){
            probabilityH= b[0][0]*a[0][1];
            probabilityC= b[1][0]*a[0][2];
        }
        
        else if(iceCreamNumber[0] == '2'){
            probabilityH= b[0][1]*a[0][1];
            probabilityC= b[1][1]*a[0][2];
        }
        
        else if(iceCreamNumber[0] == '3'){
            probabilityH= b[0][2]*a[0][1];
            probabilityC= b[1][2]*a[0][2];
        }
            
        probability1C = probabilityC;
        probability1H = probabilityH;

        //System.out.println(""+probabilityH +" "+ probabilityC);
        
        int value;
        double probabilityHH; 
        
        for(int i=0; i<n ; i++){
            value = Character.getNumericValue(iceCreamNumber[i]);
            
            System.out.println(""+probabilityH);
            System.out.println(""+probabilityC);
            probabilityHH = probabilityH;
            
            probabilityH=(a[1][1] * b[0][value-1]) *probabilityH + (a[2][1] *b[0][value-1])* probabilityC;
            probabilityC= probability1C*a[2][2] +probability1H *a[2][1] + probability2C *a[2][2] +probability2H *a[1][2] ;
            probability1C = probability1C*a[2][2];
            probability1H = probability1H *a[2][1];
            probability2C = probability2C *a[2][2];
            probability2H = probability2H *a[1][2];
        
        }
        
        System.out.println("ANSWER:         "+probabilityC);
        System.out.println("ANSWER:         "+probabilityH);
        return probability;
    }
}

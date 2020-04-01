import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Math;
public class Ortogonalization {
    //here we calculate alpha, dividing scalar(x(i),b) and scalar(A*x(i), x(i))
    static double alpha(double[][] matr,double[] x, double[] b){ 
        return scalar(x,b)/scalar(mult(matr,x),x);
    }
    //this function returns array multiplied by current number a
    static  double[] mult_a(double a, double[] mas){
        for(int i=0; i<mas.length; i++){
            mas[i]*=a;
        }
        return mas;
    }
    static double[] sum(double[] x1, double[] x2){ //return sum of 2 arrays
        for(int i=0; i<x1.length; i++){
            x1[i]+=x2[i];
        }
        return x1;
    }
    //Array (vector) From Matrix
    static double[] AFM(double[][] matr, int index){
        double[] x=new double[matr.length];
        for(int i=0; i<matr.length; i++){
            x[i]=matr[i][index];
        }
        return x;
    }

    static double[] mult(double[][] matr, double[] mas){
        double[] x=new double[mas.length];
        for(int i=0; i<mas.length; i++){
            for (int j=0; j<mas.length; j++){
            x[i]+=matr[i][j]*mas[j];

            }
        }
        return x;}
    static double scalar(double[] x1, double[] x2){
        double sum=0;

            for(int i=0; i<x1.length; i++){
                sum+=x1[i]*x2[i];
        }
        return sum;
    }
    public static void main(String args[]) throws Exception{
        //------------------------------------------------------------------------------------------------//
        //File reading block
        Scanner sc = new Scanner(new BufferedReader(new FileReader("src/text.txt")));
        int rows, columns;
        rows=Integer.parseInt(sc.nextLine().trim());
        columns=rows+1;
        double[][] myArray = new double[rows][columns];

        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = Double.parseDouble(line[j]);
                }
            }
        }
        //------------------------------------------------------------------------------------------------//
        //Algorythm block

        double[][] y=new double[rows][columns]; //y(1)=(1,0,0...0), y(2)=(0,1,0,...0), y(3),...,y(rows)=(0,0,...,1)
        for (int i=0; i<y.length;i++){
            y[i][i]=1;
        }
        //lambda
        double[] lam=new double[rows-1];
        //x(1), x(2),x(3)....
        double[][] x=new double[rows][columns];
        //final solution
        double[] FinalX=new double[rows];

        //x(1)=y(1)=(1,0,0,...0)T
        for(int k=0; k<rows; k++){
            x[k][0]=y[k][0];
        }
        double[] temp;
        //here we find x(2), x(3), ... x(n)
        for(int m=1; m<rows; m++){
            temp=AFM(y,m); //here we get array (vector) y(m)
            //according to formula we see that x(m)=y(m)+a(1)x(1)+...;
            //so here we just init our x(m) as y(m)
            for(int cx=0; cx<rows; cx++){
                x[cx][m]=temp[cx];
            }
            //and then in this block we just add a(i)x(i)
            for(int i=0;i<m;i++){
                lam[i]=-scalar(AFM(x,i),mult(myArray,AFM(y,m)))/scalar(AFM(x,i),mult(myArray,AFM(x,i)));
                temp=mult_a(lam[i],AFM(x,i));
                for(int h=0; h<rows; h++){
                    x[h][m]+=temp[h];
                }

            }
            //after this two cycles we get x(m)
        }
        //--Final result (FinalX)
        //here we see linear combination - sum of x(i) multiplied by alpha, where alpha calculates in function alpha()
        for(int f=0; f<rows;f++){
            FinalX=sum(FinalX,mult_a(alpha(myArray,AFM(x,f),AFM(myArray,rows)),AFM(x,f)));

        }
        //------------------------------------------------------------------------------------------------//
        //--Matrix Output Block
        /*for(int i=0; i<rows; i++){
            System.out.println();
            for(int j=0; j<columns; j++){
                System.out.print(myArray[i][j]);
                System.out.print(" ");
            }
        }*/

        //--input final result
        //Shows our results
        for(Double gh:FinalX){ //for each Double gh in array Finalx shows gh
            System.out.print(Math.round(1000.0 * gh) / 1000.0); //here i just rounded results
            System.out.print(" ");

        }

    }
}

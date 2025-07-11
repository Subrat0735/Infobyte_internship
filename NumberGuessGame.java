
import java.util.*;
public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println("WELCOME TO GUESSING GAME!!!\nRules:\n  1)You have to choose a Number between 1 to 100\n  2)Total number of level 6 \n  3)You have only 5 chances in each Level");
         int score=0;
         int level=1;
         while(level<=6){
             System.out.println("LEVEL : "+level+"\n\n" );
             int point=numberguess(level*2,sc);
             score=point>0?score+point :score;
             if (point==-1) {
                 System.out.println("Score: "+score);
                 break;
             }
             level++;
         }
         if(level==6) System.out.println("\t\tCongratulation U WON!!\n\n\n");
        System.out.println("Game completed");
        sc.close();
    }

    public static int numberguess(int point,Scanner sc){

        Random rand=new Random();
        int random_number= rand.nextInt(100)+1;
        // int rand_no=(int)(Math.random()*(100-1+1)+1);
        int counter=0;

        int guess;
        do{
            System.out.print("Guess a number: ");
            guess=sc.nextInt();
            if (guess==random_number) {
                System.out.println("Gotcha!!! You got the Right guess");

                return (5-counter)*point;
            }
            else {
                int difference = Math.abs(guess - random_number);

                if (difference >= 30) {
                    System.out.println("You're way off! Try guessing much " + (guess > random_number ? "lower!" : "higher!"));
                } else if (difference >= 15) {
                    System.out.println("Getting warmer, but still off. Try guessing " + (guess > random_number ? "lower." : "higher."));
                } else if (difference >= 5) {
                    System.out.println("You're really close! Just a bit " + (guess > random_number ? "lower!" : "higher!"));
                } else {
                    System.out.println("Super close! You're almost there!");
                }
            }

            counter++;
            System.out.println("You have "+(5-counter)+" chances.");
        }while(counter<5);
        System.out.println("Generated number was :"+random_number+"\nBETTER LUCK NEXT TIME");

    return -1;
    }
}

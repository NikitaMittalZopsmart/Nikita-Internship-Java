import java.util.*;
class Pallimdrome
{
    int number,copy,result=0,rem;
    Scanner scan=new Scanner(System.in);
    public
    void check()
    {
        number=scan.nextInt();
        copy=number;
        while(number>0)
        {
            rem=number%10;
            result=result*10+rem;
            number=number/10;
        }
        number=copy;
        if(number==result)
            System.out.println("The given number is pallimdrome");
        else
            System.out.println("The given number is not pallimdrome");
    }
    public static void main(String)
    {
        Pallimdrome pal=new Pallimdrome();
        pal.check();
    }
}
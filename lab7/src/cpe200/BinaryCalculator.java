package cpe200;

import java.math.BigDecimal;

/**
 * Created by ather on 25/10/2559.
 */
public class BinaryCalculator {
    private IOperand firstOperand;
    private IOperand secondOperand;

    private String x;
    private String y;
    private String carry;

    private int xpointLength = 0;
    private int ypointLength = 0;

    public BinaryCalculator() {
        x = "0";
        y = "0";
    }

    public void setFirstOperand(IOperand operand) {
        System.out.println("x was to Setting is : "+operand.getOperand());
        x = getFloat(operand.getOperand());
        //System.out.println(getFloat("3"));
        //System.out.println(rePointting("1000"));
        System.out.println("Now x is :  "+x);

        firstOperand = operand;
    }

    public void setSecondOperand(IOperand operand) {
        System.out.println("y was to Setting is : "+operand.getOperand());
        y = getFloat(operand.getOperand());
        secondOperand = operand;
    }

    public String add() throws RuntimeException {
        //Full Adder
        System.out.println("---------ADD MODULE-------");
        chkBalance();
        String sum = new String();
        String carry = new String("0");
        for (int i=this.x.length()-1;i>=0;i--){
            sum += Integer.parseInt(Character.toString(y.charAt(i)))
                    ^(Integer.parseInt(Character.toString(x.charAt(i)))
                    ^Integer.parseInt(carry));
            carry = Integer.toString(
                        (Integer.parseInt(Character.toString(y.charAt(i)))
                        &Integer.parseInt(Character.toString(x.charAt(i))))
                        |Integer.parseInt(carry)&
                        (Integer.parseInt(Character.toString(y.charAt(i)))
                        ^Integer.parseInt(Character.toString(x.charAt(i))))
                    );
        }
        this.carry = carry;
        String ress = reFormmating(rePointting(carry+sum)).stripTrailingZeros().toString();
        System.out.println("\taddition result : "+ress);
        return ress;
    }

    public String subtract() throws RuntimeException {
        //one_complement
        if(reFormmating(firstOperand.getOperand()).floatValue() >= 0){
            System.out.println("---------Subst MODULE-------");
            chkBalance();
            
        }else {
            throw new RuntimeException();
        }

    }

    public String multiply() throws RuntimeException {
        return "";
    }

    /* This method should throw an exception when divide by zero*/
    public String division() throws RuntimeException {
        return "";
    }

    public String power() throws RuntimeException {
        return "";
    }

    public void chkBalance() throws RuntimeException{
        this.x = String.format("%0"+this.y.length()+"d", Integer.parseInt(this.x));
        this.y = String.format("%0"+this.x.length()+"d", Integer.parseInt(this.y));
        System.out.println("Check Balance");
    }

    public String one_complement(String base) throws RuntimeException{
        String res = new String();
        for (int i=0;i<base.length();i++){
            res += (base.charAt(i) == '0')?"1":"0";
        }
        return res;
    }
    private String use_adder(){
        return "";
    }

    private String getFloat(String x){
        //Covert String of Double to String of Binary Double without point
        if (x.contains(".")){
            double f = Double.parseDouble("0."+x.split("[.]")[1]);

            //Find float Length
            String fText = floatToBinaryString(f);
            xpointLength = fText.length();
            String s = x.split("[.]")[0];

            String re = Integer.toBinaryString(Integer.parseInt(s))+fText;
            System.out.println("\tYes has point : "+re);
            return re;
        }else {
            String re = Integer.toBinaryString(Integer.parseInt(x));
            System.out.println("\tNo hasnt point : "+re);
            return re;
        }
    }

    private String binaryToIntergerString(String binary){
        return rePointting(binary);
    }

    private String floatToBinaryString( double n ) {
        String val = "";
        int i = 0;
        while ( n > 0 && i < 10) {
            double r = n * 2;
            if( r >= 1 ) {
                val += "1";
                n = r - 1;
            }else{
                val += "0";
                n = r;
            }
            i++;
        }
        return (val.equals(""))?"0":val;
    }

    private String rePointting(String numbr){
        if(xpointLength > 0){
            String sNum = numbr.substring(0, numbr.length()-xpointLength);
            String sPoint = numbr.substring(numbr.length()-xpointLength, numbr.length());
            String n = Integer.toString(Integer.parseInt(sNum,2));
            String p = Integer.toString(Integer.parseInt(sPoint,2));
            //Result of p.n is Double
            System.out.println("\t\tRepoint : "+n+"."+p);
            return n+"."+p;
        }else {
            String n = Integer.toString(Integer.parseInt(numbr,2));
            System.out.println("\t\tNot repoint : "+n);
            return n;
        }
    }

    private BigDecimal reFormmating(String result){
        System.out.println("\t\tReformmat input : "+result);
        BigDecimal res = new BigDecimal(result);
        return res;
    }

    public String getCarry(){
        return this.carry;
    }


}

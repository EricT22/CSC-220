package RationalNumbers;

public class RationalNumber {
    protected int n, d;

    public RationalNumber(){
        this.n = 0;
        this.d = 1;
    }

    public RationalNumber(int n, int d) throws IllegalArgumentException{
        if (d == 0){
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        
        this.n = n;
        this.d = d;
    }

    public RationalNumber(RationalNumber r){
        this.n = r.n;
        this.d = r.d;
    }

    @Override
    public String toString(){
        RationalNumber[] prop = properFraction(this);

        String overallNeg = (prop[2].n < 0 || prop[2].d < 0) ? "-" : "";

        if (prop[0].n != 0){
            if (prop[1].n != 0){
                return overallNeg + prop[0].n + " " + prop[1].n + "/" + prop[1].d;
            } else {
                return overallNeg + prop[0].n;
            }
        } else {
            if (prop[1].n != 0){
                return overallNeg + prop[1].n + "/" + prop[1].d;
            }
        }

        return "0";
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof RationalNumber){
            RationalNumber r  = (RationalNumber) o;

            return this.n * r.d == this.d * r.n;
        }

        return false;
    }
    
    public RationalNumber add(RationalNumber r){
        return new RationalNumber(((this.n * r.d) + (r.n * this.d)), this.d * r.d);
    }

    public RationalNumber sub(RationalNumber r){
        return new RationalNumber(((this.n * r.d) - (r.n * this.d)), this.d * r.d);
    }

    public RationalNumber mult(RationalNumber r){
        return new RationalNumber(this.n * r.n, this.d * r.d);
    }

    public RationalNumber div(RationalNumber r) throws ArithmeticException{
        if (r.n == 0){
            throw new ArithmeticException("Can't divide by zero");
        }
        
        return mult(inverse(r));
    }



    //getters and setters
    public void setNumerator(int n){
        this.n = n;
    }

    public void setDenominator(int d) throws IllegalArgumentException{
        if (d == 0){
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        this.d = d;
    }

    public int getNumerator(){
        return this.n;
    }

    public int getDenominator(){
        return this.d;
    }


    
    //helpers
    private RationalNumber inverse(RationalNumber r){
        return new RationalNumber(r.d, r.n);
    }

    // whole num first, then remaining fraction, followed by a number which keeps track of the negatives
    private RationalNumber[] properFraction(RationalNumber r){
        RationalNumber[] arr = new RationalNumber[3];

        if (r.n < 0 && r.d > 0){
            arr[2] = new RationalNumber(-1, 1);
        } else if (r.d < 0 && r.n > 0){
            arr[2] = new RationalNumber(1, -1);
        } else{
            arr[2] = new RationalNumber(1, 1);
        }

        RationalNumber workingR = new RationalNumber(Math.abs(r.n), Math.abs(r.d));
        int count = 0;

        while (true){
            if (workingR.n < workingR.d){
                arr[0] = new RationalNumber(count, 1);
                arr[1] = simplifyFract(workingR);
                return arr;
            }

            workingR.n -= workingR.d;
            count++;
        }
    }

    private RationalNumber simplifyFract(RationalNumber r){
        if (r.n == 0){
            return r;
        }

        int[] modResult = {r.n, r.d % r.n};
        int temp;

        while (modResult[1] != 0){
            temp = modResult[0];
            modResult[0] = modResult[1];
            modResult[1] = temp % modResult[0];
        }

        return new RationalNumber(r.n / modResult[0], r.d / modResult[0]);

        // RationalNumber workingR = new RationalNumber(r);

        // int modResult = workingR.d % workingR.n;
        // if (modResult == 0){
        //     workingR.d /= workingR.n;
        //     workingR.n /= workingR.n;

        //     return workingR;
        // }
        
        // while (true){
        //     if (workingR.n % modResult == workingR.d % modResult){
        //         workingR.d /= modResult;
        //         workingR.n /= modResult;

        //         modResult = workingR.d % workingR.n; // update modResult

        //         if (modResult == 1){
        //             return workingR;
        //         }
        //     } else {
        //         return workingR;
        //     }
        // }
    }
}

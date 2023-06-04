package ComplexNumbers;

public class ComplexNumber {
    private double r, i;

    public ComplexNumber(){
        this.r = 0;
        this.i = 0;
    }

    public ComplexNumber(double r, double i){
        this.r = r;
        this.i = i;
    }

    public ComplexNumber(ComplexNumber rhs){
        this.r = rhs.r;
        this.i = rhs.i;
    }

    public void setReal(double r){
        this.r = r;
    }

    public void setImag(double i){
        this.i = i;
    }

    public double getReal(){
        return this.r;
    }

    public double getImag(){
        return this.i;
    }

    public String toString(){
        if (i == 0){
            return this.r + "";
        } else if (r == 0){
            return this.i + "i";
        }

        return (i < 0) ? (this.r + " - " + Math.abs(this.i) + "i") : (this.r + " + " + this.i + "i");
    }

    public ComplexNumber add(ComplexNumber rhs){
        return new ComplexNumber(this.r + rhs.r, this.i + rhs.i);
    }

    public ComplexNumber sub(ComplexNumber rhs){
        return new ComplexNumber(this.r - rhs.r, this.i - rhs.i);
    }

    public ComplexNumber mult(ComplexNumber rhs){
        return new ComplexNumber(this.r * rhs.r - this.i * rhs.i, this.r * rhs.i + this.i * rhs.r);
    }

    public ComplexNumber div(ComplexNumber rhs) throws IllegalArgumentException{
        if (rhs.r == 0 && rhs.i == 0)
            throw new IllegalArgumentException("Divides by 0 + 0i");
        
        double denom = Math.pow(rhs.r, 2) + Math.pow(rhs.i, 2);

        return new ComplexNumber((this.r * rhs.r + this.i * rhs.i) / denom, (this.i * rhs.r - this.r * rhs.i) / denom);
    }

    public double mag(){
        return Math.sqrt(Math.pow(this.r, 2) + Math.pow(this.i, 2));
    }

    public ComplexNumber conj(){
        return new ComplexNumber(this.r, -this.i);
    }

    public ComplexNumber sqrt(){
        if (this.i != 0){
            return new ComplexNumber(Math.sqrt((this.r + mag()) / 2), Math.sqrt((-this.r + mag()) / 2));
        } else if (this.r >= 0){
            return new ComplexNumber(Math.sqrt(this.r), 0);
        }
        return new ComplexNumber(0, Math.sqrt(-this.r));
    }

    public boolean equals(ComplexNumber rhs){
        return this.r == rhs.r && this.i == rhs.i;
    }
}

public class Mapper {

    private Matrix input;
    private Matrix ih; //weights from input to hidden
    private Matrix hidden;
    private Matrix ho; //weights from hidden to output
    private Matrix output;

    public Mapper(int i, int h, int o) {
        input = new Matrix(i, 1);
        hidden = new Matrix(h, 1);
        output = new Matrix(o, 1);
    }



    //sigmoid function
    private double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

}

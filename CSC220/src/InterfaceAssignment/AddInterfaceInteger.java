package InterfaceAssignment;

public class AddInterfaceInteger implements AddInterface{

    private int i;

    public AddInterfaceInteger(){
        this.i = 0;
    }

    public AddInterfaceInteger(int i) {
        this.i = i;
    }

    public AddInterfaceInteger(AddInterface ai){
        this();

        if (ai instanceof AddInterfaceInteger){
            AddInterfaceInteger aii = (AddInterfaceInteger) ai;
            this.i = aii.i;
        }
    }

    @Override
    public String toString(){
        return i + "";
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof AddInterfaceInteger){
            AddInterfaceInteger aii = (AddInterfaceInteger) o;
            return this.i == aii.i;
        }
        return false;
    }

    @Override
    public AddInterface add(AddInterface ai) {
        if (ai instanceof AddInterfaceInteger){
            AddInterfaceInteger aii = (AddInterfaceInteger) ai;

            return new AddInterfaceInteger(this.i + aii.i);
        }
        return null;
    }
}

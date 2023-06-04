package InterfaceAssignment;

public class AddInterfaceString implements AddInterface{

    private String s;

    public AddInterfaceString(){
        this.s = "";
    }

    public AddInterfaceString(String s) {
        this.s = s;
    }

    public AddInterfaceString(AddInterface ai){
        this();

        if (ai instanceof AddInterfaceString){
            AddInterfaceString ais = (AddInterfaceString) ai;
            this.s = ais.s;
        }
    }

    @Override
    public String toString(){
        return s;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof AddInterfaceString){
            AddInterfaceString ais = (AddInterfaceString) o;
            return this.s.equals(ais.s);
        }
        return false;
    }

    @Override
    public AddInterface add(AddInterface ai) {
        if (ai instanceof AddInterfaceString){
            AddInterfaceString ais = (AddInterfaceString) ai;

            return new AddInterfaceString(this.s + ais.s);
        }
        return null;
    }
    
}

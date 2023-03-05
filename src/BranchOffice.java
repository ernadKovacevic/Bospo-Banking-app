
public class BranchOffice {
    private String name;
    private String[] offices = new String[10];
    private int numOfOffices = 0;

    public BranchOffice() {
    }

    public BranchOffice(String branchOfficeName, String[] offices_) {
        for(int i = 0; i < offices_.length; i++){
            this.offices[numOfOffices++] = offices_[i];
        }
        this.name = branchOfficeName;
    }

    public void add(String name, String office){
        this.offices[numOfOffices++] = office;
    }

    public void printOffices(){
        for(int i = 0; i < numOfOffices; i++){
            System.out.println(this.offices[i]);
        }
    }

    public String[] getOffices(){
        String[] temp = new String[numOfOffices];

        for(int i = 0; i < numOfOffices; i++){
            temp[i] = this.offices[i];
        }
        
        return temp;
    }

    public String[] getAllOffices(){
        return this.offices;
    }


    public String getName(){
        return this.name;
    }
}

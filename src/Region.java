public class Region {
    private String name;
    private BranchOffice[] branchOffices = new BranchOffice[10];
    private int numOfBranchOffices = 0;

    public Region() {
    
    }

    public Region(String regionName, BranchOffice[] branchOfficesInit) {
        for(int i = 0; i < branchOfficesInit.length; i++){
            this.branchOffices[numOfBranchOffices++] = branchOfficesInit[i];
        }
        this.name = regionName;
    }

    public void add(BranchOffice branchOffice){
        this.branchOffices[numOfBranchOffices++] = branchOffice;
    }

    public void printBranchOffices(){
        for(int i = 0; i < numOfBranchOffices; i++){
            System.out.println((this.branchOffices[i]).getName());
        }
    }

    public String[] getBranchOffices(){
        String[] temp = new String [numOfBranchOffices];
        for(int i = 0; i < numOfBranchOffices; i++){
            temp[i] = this.branchOffices[i].getName();
        }
        return temp;
    }

    public BranchOffice findBranchOffice(String branchOfficeName){
        BranchOffice temp = new BranchOffice();

        for(int i = 0; i < numOfBranchOffices; i++){
            if(branchOfficeName == branchOffices[i].getName()){
                temp = branchOffices[i];
                break;
            }
        }
        return temp;
    }




    public String getName(){
        return this.name;
    }
}

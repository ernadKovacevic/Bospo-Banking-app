
public class MI_BOSPO {
    private Region[] regions = new Region[10];
    private int numOfRegions = 0;

    public MI_BOSPO() {
    }

    public MI_BOSPO(Region[] regionsInit){
        for(int i = 0; i < regionsInit.length; i++){
            this.regions[numOfRegions++] = regionsInit[i];
        }
    }

    public MI_BOSPO(Region region) {
        this.regions[numOfRegions] = region;
    }

    public void  printRegions(){
        for(int i = 0; i < numOfRegions - 1; i++){
            System.out.println(this.regions[i].getName());
        }
    }

    public String[] getRegions(){
        String[] temp = new String [numOfRegions];
        for(int i = 0; i < numOfRegions; i++){
            temp[i] = this.regions[i].getName();
        }
        return temp;
    }

    public Region findRegion(String regionName){
        Region temp = new Region();

        for(int i = 0; i < numOfRegions; i++){
            if(regionName == regions[i].getName()){
                temp = regions[i];
                break;
            }
        }
        return temp;
    }


    
}

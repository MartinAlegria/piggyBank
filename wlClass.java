
public class wlClass {

    //Instance Variables

    private String wlName;
    private int wlMoney;

    // Constructor **********************************************************************

    wlClass(){
        this("---",0);
    }

    wlClass(String wlName, int wlMoney){

        setWLName(wlName);
        setWLMoney(wlMoney);
    }

    //SETERS **********************************************************************


    private void setWLName(String wlName) {
        this.wlName = wlName;
    }

    private void setWLMoney(int wlMoney) {
        this.wlMoney = wlMoney;
    }

    //GETERS **********************************************************************


    public String getWLName() {
        return wlName;
    }

    public int getWLMoney() {
        return wlMoney;
    }

    //METHODS **********************************************************************

    public String editWLName(String name){

        String temp = name;
        setWLName(temp);
        return(temp);
    }

    public int editWLMoney(int money){

        int temp = money;
        setWLMoney(money);
        return(temp);
    }

    public String showWLName(){
        return(getWLName());
    }//showwlName

    public int showWLMoney(){
        return (getWLMoney());
    }//showwlMoney

}//CLASS **********************************************************************

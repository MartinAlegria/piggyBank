/**
 * Created by Martin88 on 6/6/17.
 * PiggyBank Class
 * #1
 */
public class piggyBank {

    //Instance Variables **********************************************************************

    private int money;
    private String nameUser;

    //Constructor **********************************************************************

    piggyBank(){
        this(0,"");
    }

    piggyBank(int money,String nameUser){

        setMoney(money);
        setNameUser(nameUser);

    }

    //SETERS **********************************************************************

    private void setMoney(int money) {
        this.money = money;
    }

    private void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    //GETERS **********************************************************************


    public int getMoney() {
        return money;
    }

    public String getNameUser() {
        return nameUser;
    }

    // METHODS **********************************************************************

    public int editMoney(int amount){

        int temp = amount;
        setMoney(temp);
        return (temp);

    }//editMoney

    public int subsMoney(int amount){

        int temp = amount;
        int moneyTemp = getMoney();
        int result = moneyTemp - temp;
        setMoney(result);
        return(result);

    }//subsMoney

    public int addMoney(int amount){

        int temp = amount;
        int moneyTemp = getMoney();
        int result = moneyTemp + temp;
        setMoney(result);
        return(result);

    }//addMoney

    public String editName(String name){

        String temp = name;
        setNameUser(temp);
        return(temp);

    }

    public String showName(){
        return(getNameUser());
    }//showName

    public int showMoney(){
        return(getMoney());
    }//showMoney

}//CLASS **********************************************************************

package JMM.Lock;

public enum CountryEnum {
    ONE(1,"燕"),TWO(2,"齐"),Three(3,"楚"),Four(4,"赵"),Five(5,"韩"),SIX(6,"魏");

    private int retCode;
    private String Country;

    public int getRetCode() {
        return retCode;
    }


    CountryEnum(int retCode, String country) {
        this.retCode = retCode;
        Country = country;
    }

    public String getCountry() {
        return Country;
    }
    public static CountryEnum Foreach(int index){

        for (CountryEnum i :  CountryEnum.values()){
            if(i.retCode==index)
                return i;
        }
        return null;
    }
}

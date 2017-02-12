package webconsole.co.in.cardless;

/**
 * Created by Harsh Mathur on 02-02-2017.
 */

public class UserInformation {

    public String mobNum;
    public String AccNum;
    public String IfscCode;

    public UserInformation(){

    }
    public UserInformation(String mobNum, String AccN, String ifsc){
        this.mobNum = mobNum;
        this.AccNum = AccN;
        this.IfscCode = ifsc;
    }

}

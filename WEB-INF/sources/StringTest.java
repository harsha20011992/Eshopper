class StringTest{
public static void main(String[] args){
String test = "{'Email':'2'}";
String[] arrtest = test.split(":");
for(int i=0; i<arrtest.length; i++ ){
System.out.println(arrtest[i]);
}

}
}
import java.util.*;

public class Main {
    static Random random = new Random();
    static Scanner input = new Scanner(System.in);
    static boolean isRussia = false;
    static Country russia = new Country();
    static Country ukraine = new Country();
    public static void main(String[] args) {
        russia.population = 145;
        russia.food = 100;
        russia.factory =2;
        russia.land = 1710;
        ukraine.population = 48;
        ukraine.food = 100;
        ukraine.factory =1;
        ukraine.land = 60;
        countrySelection();
        signIn();
        for(int i=1;i<4;i++){
            System.out.println("This is the " + i + " rounds!");
            roundStart();
            russia.population = russia.population* russia.food/100;
            ukraine.population = ukraine.population* russia.food/100;
        }
        int i =4;
        System.out.println("A war is started!!!!");
        while (true){
            System.out.println("This is the " + i + " rounds!");
            roundStart();
            i++;
        }



    }
    static void signIn(){
        System.out.println("Please insert your name(better be real name)");
        String name = input.next();
        if(name.equals("May") || name.equals("may") || name.equals("may k") || name.equals("May K.")){
            System.out.println("Welcome May, ur country is buffed cuz im not sure u gonna win without the buff lol");
            if(isRussia==true)
                russia.militaryPoints +=100000;
            else {
                russia.land-=1000;
                ukraine.militaryPoints+=100000;
            }
        }
        else if(name.equals("Elvin") || name.equals("elvin") || name.equals("Elvin Xu")){
            System.out.println("Ok elvin u finally joined my game lmao");

        }
        else{
            System.out.println("Undocumented player, welcome to contact to the author for documenting the name in code");
        }
    }
    static void fightOrNo(){
        System.out.println("Do you want to fight?");
        String output = input.next();
        if(output.equals("yes")){
            roundEnd();
        }
        else if(output.equals("no")){
            System.out.println("You didn't fight");
            russia.soldiers += russia.population/10;
            ukraine.soldiers += ukraine.population/10;
            russia.militaryPoints= russia.soldiers + russia.ships*10 + russia.tanks*12 + russia.planes*10;
            ukraine.militaryPoints= ukraine.soldiers + ukraine.ships*7 + ukraine.tanks*8 + ukraine.planes*7;
            russia.population = russia.population* russia.food/100;
            ukraine.population = ukraine.population* russia.food/100;
        }
        else{
            System.out.println("sorry,invalid input");
            fightOrNo();
        }
    }
    static void countrySelection(){
        System.out.println("Please pick your country: Ukraine Or Russia");
        String country = input.next();
        if(country.equals("Ukraine")){
            isRussia = false;
            System.out.println("You pick Ukraine");
        }
        else if(country.equals("Russia")) {
            isRussia = true;
            System.out.println("You pick Russia");
        }
        else{
            System.out.println("Sorry,invalid input. Please insert the correct name");
            countrySelection();
        }
    }
    static void printData(Country country,String countryName){

        System.out.println("Country: " + countryName);

        System.out.println("Population: "+country.population);
        System.out.println("food: "+country.food);
        System.out.println("tanks: "+country.tanks);
        System.out.println("planes: "+country.planes);
        System.out.println("ships: "+country.ships);
        System.out.println("militaryPower: "+country.militaryPoints);
        System.out.println("factory: "+country.factory);
        System.out.println("land: "+country.land);
    }
    static void roundStart(){
        int goFirst = random.nextInt(1);
        ukraine.tanks++;
        ukraine.food+=10;
        ukraine.ships++;
        ukraine.planes++;
        ukraine.soldiers+=1;
        printData(russia,"russia");
        printData(ukraine,"ukraine");
        if(goFirst ==0){
            if(isRussia==true){
                UkraineBot();

                playersControl();
            }
            else{
                playersControl();
                RussiaBot();;
            }
        }
        else{
            if(isRussia==false){
                UkraineBot();
                playersControl();
            }
            else{
                playersControl();
                RussiaBot();;
            }
        }
    }
    static void roundEnd(){
        russia.soldiers += russia.population/10;
        ukraine.soldiers += ukraine.population/10;
        russia.militaryPoints= russia.soldiers + russia.ships*10 + russia.tanks*12 + russia.planes*10;
        ukraine.militaryPoints= ukraine.soldiers + ukraine.ships*7 + ukraine.tanks*8 + ukraine.planes*7;
        russia.population = russia.population* russia.food/100;
        ukraine.population = ukraine.population* russia.food/100;
        if (ukraine.land<=0){
            System.out.println("Ukraine has lost!");
            System.exit(0);
        }
        else if(russia.land<=0){
            System.out.println("Russia has lost!");
            System.exit(0);
        }
        if(russia.militaryPoints> ukraine.militaryPoints){
            System.out.println("Ukraine was weaker and lost some land!");
            if(ukraine.land<=10){
                ukraine.land-=5;
                russia.land+=5;
            }
            else{
                int randLand =  random.nextInt(ukraine.land/4);
                ukraine.land -= randLand;
                russia.land +=randLand;
            }

        }
        else if(russia.militaryPoints< ukraine.militaryPoints){
            System.out.println("Russia was weaker and lost some land!");
            if(russia.land<=10){
                russia.land-=5;
                ukraine.land+=5;
            }
            else {
                int randLand = random.nextInt(ukraine.land / 4);
                russia.land -= randLand;
                ukraine.land += randLand;

            }

        }
        System.out.println("Ukraine has " + ukraine.land + " lands and Russia has " + russia.land +" lands");
        if (ukraine.land<=0){
            System.out.println("Ukraine has lost!");
            System.exit(0);
        }
        else if(russia.land<=0){
            System.out.println("Russia has lost!");
            System.exit(0);
        }
    }
    static void playersControl(){
        System.out.print("It's your turn! You are " );

        if(isRussia==true){
            System.out.println("Russia!");
            int factory = russia.factory;
            for(int i=1;i<= factory;i++){
                makeSelection(i);
            }
        }
        else{
            System.out.println("Ukraine!");
            int factory = ukraine.factory;
            for(int i=1;i<= factory;i++){
                makeSelection(i);
            }
        }
        fightOrNo();
    }
    static void makeSelection(int i){
        if(isRussia == true) {
            System.out.println("this is your factory number" + i + " please choose what you want to make:food,tanks,planes,ships,factory");
            String selection = input.next();
            if (selection.equals("food")) {
                russia.food += 10;
            }
            else if (selection.equals("tanks")) {
                russia.tanks += 1;
            }
            else if (selection.equals("planes")) {
                russia.planes += 1;
            }
            else if (selection.equals("ships")) {
                russia.ships+= 1;
            }
            else if (selection.equals("factory")) {
                russia.factory += 1;
            }
            else{
                System.out.println("sorry,invalid input");
                makeSelection(i);
            }
        }
        else{
            System.out.println("this is your factory number" + i + " please choose what you want to make:food,tanks,planes,ships,factory");
            String selection = input.next();
            if (selection.equals("food")) {
                ukraine.food += 10;
            }
            else if (selection.equals("tanks")) {
                ukraine.tanks += 1;
            }
            else if (selection.equals("planes")) {
                ukraine.planes += 1;
            }
            else if (selection.equals("ships")) {
                ukraine.ships+= 1;
            }
            else if (selection.equals("factory")) {
                ukraine.factory += 1;
            }
            else{
                System.out.println("sorry,invalid input");
                makeSelection(i);
            }
        }

    }
    static void UkraineBot(){
        int randMove;
        for(int i=0;i< ukraine.factory;i++){
            randMove=random.nextInt(4);
            if(randMove==0){
                ukraine.food += 10;
            }
            else if(randMove==1){
                ukraine.tanks += 1;
            }
            else if(randMove==2){
                ukraine.factory += 1;
            }
            else if(randMove==3){
                ukraine.ships += 1;
            }
            else if(randMove==4){
                ukraine.planes += 1;
            }
        }

    }
    static void RussiaBot(){
        int randMove;
        for(int i=0;i< russia.factory;i++){
            randMove=random.nextInt(4);
            if(randMove==0){
                russia.food += 10;
            }
            else if(randMove==1){
                russia.tanks += 1;
            }
            else if(randMove==2){
                russia.factory += 1;
            }
            else if(randMove==3){
                russia.ships += 1;
            }
            else if(randMove==4){
                russia.planes += 1;
            }
        }
    }
}
package projet.trashyv15;

/**
 * Created by vincent on 17-04-23.
 */

public class sqlComm {
    public static void fillSchedule(String input) {
        //System.out.println(input);
        String[] line =input.split(" ");
        sqlParse(line,"","","","","","",1);

    }

    public static void sqlParse(String[] line,String dateIn,String dateOut,
                                String hourIn,String hourOut,String type,
                                String weekday, int offset) {
        int id;
        boolean once;
        String[] hours;
        String[] dates={""};
        String weekdays="";
        type = line[0];
        boolean fixe=false;
        for (; offset < line.length; offset++) {
            if (!line[offset].equals("")&&line!=null) {
                weekdays = weekday(line[offset]);
                hours = hours(line[offset]).split("-");

                if(weekdays.length()>0){// && !weekdayAlreadyIn(weekday,weekdays)) {
                    if (weekday.length() >= 2&&!fixe){
                        sqlParse(line, "", "", hourIn, hourOut, type, weekdays, offset + 1);
                        fixe = true;
                    }
                    else if(fixe){
                        if(weekday.length()>=2)
                            weekday=weekdays;
                    }
                    else weekday += weekdays;
                }
                else if(hours.length<3&&hours.length>1||(hours.length==1&&!hours[0].equals(""))) {
                    if (!(fixe && !hourOut.equals(""))) {
                        if (hours.length > 0)
                            if (hourIn.equals("")) {
                                hourIn = hours[0];
                            } else if (hours.length == 1)
                                hourOut = hours[0];
                        if (hours.length == 2)
                            hourOut = hours[1];
                    } else if (!fixe) {
                        if (hours.length == 2)
                            sqlParse(line, dateIn, dateOut, hours[0], hours[1], type, weekday, offset + 1);
                        else
                            sqlParse(line, dateIn, dateOut, hours[0], "", type, weekday, offset + 1);
                        fixe = true;
                    }
                }

                else if(!(fixe && !dateOut.equals(""))){
                    dates = day(line[offset]);
                    boolean ip1;
                    if(dates!=null)
                    for(int i=0;i<dates.length;i++){
                        if(i+1< dates.length)ip1=true;
                        if(!dates[i].equals("")) {
                            if(dates.length==1) {
                                if (dateIn.equals(""))
                                    dateIn = remove_(dates[i]);
                                else if(dateOut.equals("")&&dates[i].charAt(0)=='_')
                                    dateOut=remove_(dates[i]);
                                else {sqlParse(line, remove_(dates[i]), "", hourIn, hourOut, type, weekday, offset + 1);
                                fixe =true;}
                            }
                            else if(dateIn.equals(""))
                                dateIn = remove_(dates[i]);
                            else if(dateOut.equals(""))
                                if(dates[i].charAt(0)=='_')
                                    dateOut=remove_(dates[i]);
                            else if(!fixe){
                                    String tmp="";
                                    for(int j =1;j<dates.length;j++)
                                        tmp+=dates[j]+"-";
                                    line[offset]=tmp;
                                    sqlParse(line, "", "", hourIn, hourOut, type, weekday, offset);
                                    fixe=true;
                                }


                        }
                    }
                }



            }

        }
        //System.out.println(splitdate(dateIn)+" / "+splidate(dateOut)+" "+ hourIn+"-"+ hourOut+" "+ type+" "+weekday);
        //requete SQL ici
        //
        //
        //
        //
        //
    }
    public static String splitdate(String date){
        return date.substring(0,2)+"/"+date.substring(2,4);}

    public static String remove_(String date){
        if((date.charAt(0)+"").equals("_"))
            return(date.substring(1,date.length()));
        return date;
    }
    public static boolean weekdayAlreadyIn(String word, String day){
        for(int i = 0;i<word.length();i++){
            if((""+word.charAt(i)).equals(day)) return true;
        }
        return false;
    }
//fillwithZero(day.substring(0,day.length()-2));
    public static String[] day(String input) {//considerant que ce n'est pas une heure
        String[] days = input.split("-");

        if(days.length==1&&days[0].length()>0){
            if(!MonthToNumber(days[0].substring(days[0].length()-2,days[0].length())).equals("")){
                if(AnalyseLine.tryParse(days[0].substring(0,days[0].length()-2))!=null){
                    String[] returnvalue={"_"+fillwithZero(days[0])};
                    return returnvalue;
                }
                }else if(!MonthToNumber(days[0].substring(days[0].length()-3,days[0].length()-1)).equals("")){
                if(AnalyseLine.tryParse(days[0].substring(0,days[0].length()-3))!=null){
                    String[] returnvalue={"_"+days[0].substring(0,days[0].length()-1)};
                    return returnvalue;}}
        }
        else {
            boolean isDate = true;
            boolean gotmonth = true;
            for (int i = 0; i < days.length; i++) {
                if (days[i].length() > 0) {
                    if (days[i].length() > 2&&!MonthToNumber(days[i].substring(days[i].length() - 2, days[i].length())).equals("")) {
                        if (AnalyseLine.tryParse(days[i].substring(0, days[i].length() - 2)) != null)
                            ;
                        else if (AnalyseLine.tryParse(days[i].substring(1, days[i].length() - 2)) != null)
                            ;
                        else isDate = false;
                    } else if (AnalyseLine.tryParse(days[i]) != null) {
                        for (int j = i; j < days.length && gotmonth; j++) {
                            if (days[j].length()>2 && !MonthToNumber(days[j].substring(days[j].length() - 2, days[j].length())).equals("")) {
                                days[i] += days[j].substring(days[j].length() - 2, days[j].length());
                                gotmonth=false;
                            }
                        }
                    } else isDate = false;


                } else isDate = false;

            }
            if (isDate) return days;
        }
        return null;
    }


    public static String fillwithZero(String input) {
        if(input.length()<4)return "0"+input;
        return input;
    }
    public static String MonthToNumber(String input) {
        switch(input){
            case "JA" : return "01";
            case "FE" : return "02";
            case "MR" : return "03";
            case "AV" : return "04";
            case "MA" : return "05";
            case "JU" : return "06";
            case "JL" : return "07";
            case "AO" : return "08";
            case "SE" : return "09";
            case "OC" : return "10";
            case "NO" : return "11";
            case "DE" : return "12";
        }
        return "";
    }

    public static String weekday(String input) {
        if (input.equals("L")) return "0";
        else if (input.equals("M")) return "1";
        else if (input.equals("m")) return "2";
        else if (input.equals("J")) return "3";
        else if (input.equals("V")) return "4";
        else if (input.equals("S")) return "5";
        else if (input.equals("D")) return "6";
        else return "";
    }

    public static String hours(String input) {
        String[] hours=input.split("-");
        String[] halfHour;
        boolean bool=false;
        if (hours.length<3){
        for(int i = 0;i<hours.length;i++)
            if(!hours[i].equals("")){
                if(AnalyseLine.tryParse(hours[i])!=null)
                    bool=true;
                else{
                    halfHour=hours[i].split(",");
                    if(halfHour.length==2){
                        if(AnalyseLine.tryParse(halfHour[0])!=null&&
                                AnalyseLine.tryParse(halfHour[1])!=null)
                                bool=true;}
                    else bool = false;

                }
            }
        }
        if(bool)return input;
        return "";
    }

}
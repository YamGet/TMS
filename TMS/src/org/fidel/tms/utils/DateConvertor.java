package org.fidel.tms.utils;

public class DateConvertor {
	
//	public static void main(String[] args) {
//        //String date1 = "11-january-2013";
//        //String date2 = "10-December-2014";
//
//        //System.out.println(dateDifference(date1, date2));
//        System.out.println("-------------------");
//        String date3 = "1-Tir-2005";
//        String date4 = "21-yekatit-2003";
//        System.out.println(date4);
//        System.out.println(dateConvertor_ethtogrig(date4));
//    }

    ///////////////////////a method to convert gregorian date to Ethiopian calendar
    public static String dateConvertor(String x) {
        ///////////////////////extracting day, month and year from the string given
        int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < x.length(); i++, j++) {
            if (x.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        int g_day = Integer.parseInt(x.substring(0, separator[0]));
        String g_month = x.substring(separator[0] + 1, separator[1]);
        int g_year = Integer.parseInt(x.substring(separator[1] + 1));
        
        ///////////////////////returning the month in number
        int month_num = 0;
        String[] g_months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
        for (int i = 0; i < g_months.length; i++) {
            if (g_month.equalsIgnoreCase(g_months[i])) {
                month_num = i + 1;
            }
        }
        //////// if month parameter is in number
        if(month_num == 0){
        	month_num = Integer.parseInt(g_month)+1;
        }
        if(month_num == 13){
        	month_num = month_num - 1;
        }
        
        ///////////////////////converting the gregorian year to Ethiopian year
        int eth_year = 0;
        if ((month_num < 9 || (month_num == 9 && g_day < 11)) && g_year % 4 != 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 11)) && g_year % 4 != 0) {
            eth_year = g_year - 7;
        } else if ((month_num < 9 || (month_num == 9 && g_day < 12)) && g_year % 4 == 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 12)) && g_year % 4 == 0) {
            eth_year = g_year - 7;
        }
        //System.out.println(g_year % 4 + " test");

        ///////////////////////converting the month to Ethiopian calendar
        String eth_month = "";
        int eth_month_num = 0;
        int eth_day = 0;
        if (g_year % 4 == 0) {
            if ((month_num == 9 && g_day >= 12) || (month_num == 10 && g_day <= 11)) {
                eth_month = eth_months[0];
                if (month_num == 9) {
                    eth_day = g_day - 11;
                } else if (month_num == 10) {
                    eth_day = g_day + 19;
                }
            } else if ((month_num == 10 && g_day >= 12) || (month_num == 11 && g_day <= 10)) {
                eth_month = eth_months[1];
                if (month_num == 10) {
                    eth_day = g_day - 11;
                } else if (month_num == 11) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 11 && g_day >= 11) || (month_num == 12 && g_day <= 10)) {
                eth_month = eth_months[2];
                if (month_num == 11) {
                    eth_day = g_day - 10;
                } else if (month_num == 12) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 12 && g_day >= 11) || (month_num == 1 && g_day <= 9)) {
                eth_month = eth_months[3];
                if (month_num == 12) {
                    eth_day = g_day - 10;
                } else if (month_num == 1) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 1 && g_day >= 10) || (month_num == 2 && g_day <= 8)) {
                eth_month = eth_months[4];
                if (month_num == 1) {
                    eth_day = g_day - 9;
                } else if (month_num == 2) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 2 && g_day >= 9) || (month_num == 3 && g_day <= 9)) {
                eth_month = eth_months[5];
                if (month_num == 2) {
                    eth_day = g_day - 8;
                } else if (month_num == 3) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 3 && g_day >= 10) || (month_num == 4 && g_day <= 8)) {
                eth_month = eth_months[6];
                if (month_num == 3) {
                    eth_day = g_day - 9;
                } else if (month_num == 4) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 4 && g_day >= 9) || (month_num == 5 && g_day <= 8)) {
                eth_month = eth_months[7];
                if (month_num == 4) {
                    eth_day = g_day - 8;
                } else if (month_num == 5) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 5 && g_day >= 9) || (month_num == 6 && g_day <= 7)) {
                eth_month = eth_months[8];
                if (month_num == 5) {
                    eth_day = g_day - 8;
                } else if (month_num == 6) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 6 && g_day >= 8) || (month_num == 7 && g_day <= 7)) {
                eth_month = eth_months[9];
                if (month_num == 6) {
                    eth_day = g_day - 7;
                } else if (month_num == 7) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 7 && g_day >= 8) || (month_num == 8 && g_day <= 6)) {
                eth_month = eth_months[10];
                if (month_num == 7) {
                    eth_day = g_day - 7;
                } else if (month_num == 8) {
                    eth_day = g_day + 24;
                }
            } else if ((month_num == 8 && g_day >= 7) || (month_num == 9 && g_day <= 5)) {
                eth_month = eth_months[11];
                if (month_num == 8) {
                    eth_day = g_day - 6;
                } else if (month_num == 9) {
                    eth_day = g_day + 25;
                }
            } else if ((month_num == 9 && g_day >= 6) || (month_num == 9 && g_day <= 11)) {
                eth_month = eth_months[12];
                eth_day = g_day - 5;
            }
        } else {
            if ((month_num == 9 && g_day >= 11) || (month_num == 10 && g_day <= 10)) {
                eth_month = eth_months[0];
                if (month_num == 9) {
                    eth_day = g_day - 10;
                } else if (month_num == 10) {
                    eth_day = g_day + 19;
                }
            } else if ((month_num == 10 && g_day >= 11) || (month_num == 11 && g_day <= 9)) {
                eth_month = eth_months[1];
                if (month_num == 10) {
                    eth_day = g_day - 10;
                } else if (month_num == 11) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 11 && g_day >= 10) || (month_num == 12 && g_day <= 9)) {
                eth_month = eth_months[2];
                if (month_num == 11) {
                    eth_day = g_day - 9;
                } else if (month_num == 12) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 12 && g_day >= 10) || (month_num == 1 && g_day <= 8)) {
                eth_month = eth_months[3];
                if (month_num == 12) {
                    eth_day = g_day - 9;
                } else if (month_num == 1) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 1 && g_day >= 9) || (month_num == 2 && g_day <= 7)) {
                eth_month = eth_months[4];
                if (month_num == 1) {
                    eth_day = g_day - 8;
                } else if (month_num == 2) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 2 && g_day >= 8) || (month_num == 3 && g_day <= 9)) {
                eth_month = eth_months[5];
                if (month_num == 2) {
                    eth_day = g_day - 7;
                } else if (month_num == 3) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 3 && g_day >= 10) || (month_num == 4 && g_day <= 8)) {
                eth_month = eth_months[6];
                if (month_num == 3) {
                    eth_day = g_day - 9;
                } else if (month_num == 4) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 4 && g_day >= 9) || (month_num == 5 && g_day <= 8)) {
                eth_month = eth_months[7];
                if (month_num == 4) {
                    eth_day = g_day - 8;
                } else if (month_num == 5) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 5 && g_day >= 9) || (month_num == 6 && g_day <= 7)) {
                eth_month = eth_months[8];
                if (month_num == 5) {
                    eth_day = g_day - 8;
                } else if (month_num == 6) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 6 && g_day >= 8) || (month_num == 7 && g_day <= 7)) {
                eth_month = eth_months[9];
                if (month_num == 6) {
                    eth_day = g_day - 7;
                } else if (month_num == 7) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 7 && g_day >= 8) || (month_num == 8 && g_day <= 6)) {
                eth_month = eth_months[10];
                if (month_num == 7) {
                    eth_day = g_day - 7;
                } else if (month_num == 8) {
                    eth_day = g_day + 24;
                }
            } else if ((month_num == 8 && g_day >= 7) || (month_num == 9 && g_day <= 5)) {
                eth_month = eth_months[11];
                if (month_num == 8) {
                    eth_day = g_day - 6;
                } else if (month_num == 9) {
                    eth_day = g_day + 25;
                }
            } else if ((month_num == 9 && g_day >= 6) || (month_num == 9 && g_day <= 11)) {
                eth_month = eth_months[12];
                eth_day = g_day - 5;
            }
        }

        //String converted_eth_date = eth_day + "-" + eth_month + "-" + eth_year;
        String converted_eth_date = String.valueOf(eth_year);
        return converted_eth_date;
    }
    
    ///////////////////////a method to convert gregorian date to Ethiopian calendar
    public static String gregToEthioDateConvertor(String x) {
    	
        ///////////////////////extracting day, month and year from the string given
        int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < x.length(); i++, j++) {
            if (x.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        int g_day = Integer.parseInt(x.substring(0, separator[0]));
        String g_month = x.substring(separator[0] + 1, separator[1]);
        int g_year = Integer.parseInt(x.substring(separator[1] + 1));
        
        ///////////////////////returning the month in number
        int month_num = 0;
        String[] g_months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
        for (int i = 0; i < g_months.length; i++) {
            if (g_month.equalsIgnoreCase(g_months[i])) {
                month_num = i + 1;
            }
        }
        
        ////////if month parameter is in number
	    if(month_num == 0){
	    	month_num = Integer.parseInt(g_month);
	    }
	    if(month_num == 13){
	    	month_num = month_num-1;
	    }

        ///////////////////////converting the gregorian year to Ethiopian year
        int eth_year = 0;
        if ((month_num < 9 || (month_num == 9 && g_day < 11)) && g_year % 4 != 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 11)) && g_year % 4 != 0) {
            eth_year = g_year - 7;
        } else if ((month_num < 9 || (month_num == 9 && g_day < 12)) && g_year % 4 == 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 12)) && g_year % 4 == 0) {
            eth_year = g_year - 7;
        }
        
        ///////////////////////converting the month to Ethiopian calendar
        String eth_month = "";
        int eth_month_num = 0;
        int eth_day = 0;
        if (g_year % 4 == 0) {
            if ((month_num == 9 && g_day >= 12) || (month_num == 10 && g_day <= 11)) {
                eth_month = eth_months[0];
                if (month_num == 9) {
                    eth_day = g_day - 11;
                } else if (month_num == 10) {
                    eth_day = g_day + 19;
                }
            } else if ((month_num == 10 && g_day >= 12) || (month_num == 11 && g_day <= 10)) {
                eth_month = eth_months[1];
                if (month_num == 10) {
                    eth_day = g_day - 11;
                } else if (month_num == 11) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 11 && g_day >= 11) || (month_num == 12 && g_day <= 10)) {
                eth_month = eth_months[2];
                if (month_num == 11) {
                    eth_day = g_day - 10;
                } else if (month_num == 12) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 12 && g_day >= 11) || (month_num == 1 && g_day <= 9)) {
                eth_month = eth_months[3];
                if (month_num == 12) {
                    eth_day = g_day - 10;
                } else if (month_num == 1) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 1 && g_day >= 10) || (month_num == 2 && g_day <= 8)) {
                eth_month = eth_months[4];
                if (month_num == 1) {
                    eth_day = g_day - 9;
                } else if (month_num == 2) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 2 && g_day >= 9) || (month_num == 3 && g_day <= 9)) {
                eth_month = eth_months[5];
                if (month_num == 2) {
                    eth_day = g_day - 8;
                } else if (month_num == 3) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 3 && g_day >= 10) || (month_num == 4 && g_day <= 8)) {
                eth_month = eth_months[6];
                if (month_num == 3) {
                    eth_day = g_day - 9;
                } else if (month_num == 4) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 4 && g_day >= 9) || (month_num == 5 && g_day <= 8)) {
                eth_month = eth_months[7];
                if (month_num == 4) {
                    eth_day = g_day - 8;
                } else if (month_num == 5) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 5 && g_day >= 9) || (month_num == 6 && g_day <= 7)) {
                eth_month = eth_months[8];
                if (month_num == 5) {
                    eth_day = g_day - 8;
                } else if (month_num == 6) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 6 && g_day >= 8) || (month_num == 7 && g_day <= 7)) {
                eth_month = eth_months[9];
                if (month_num == 6) {
                    eth_day = g_day - 7;
                } else if (month_num == 7) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 7 && g_day >= 8) || (month_num == 8 && g_day <= 6)) {
                eth_month = eth_months[10];
                if (month_num == 7) {
                    eth_day = g_day - 7;
                } else if (month_num == 8) {
                    eth_day = g_day + 24;
                }
            } else if ((month_num == 8 && g_day >= 7) || (month_num == 9 && g_day <= 5)) {
                eth_month = eth_months[11];
                if (month_num == 8) {
                    eth_day = g_day - 6;
                } else if (month_num == 9) {
                    eth_day = g_day + 25;
                }
            } else if ((month_num == 9 && g_day >= 6) || (month_num == 9 && g_day <= 11)) {
                eth_month = eth_months[12];
                eth_day = g_day - 5;
            }
        } else {
            if ((month_num == 9 && g_day >= 11) || (month_num == 10 && g_day <= 10)) {
                eth_month = eth_months[0];
                if (month_num == 9) {
                    eth_day = g_day - 10;
                } else if (month_num == 10) {
                    eth_day = g_day + 19;
                }
            } else if ((month_num == 10 && g_day >= 11) || (month_num == 11 && g_day <= 9)) {
                eth_month = eth_months[1];
                if (month_num == 10) {
                    eth_day = g_day - 10;
                } else if (month_num == 11) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 11 && g_day >= 10) || (month_num == 12 && g_day <= 9)) {
                eth_month = eth_months[2];
                if (month_num == 11) {
                    eth_day = g_day - 9;
                } else if (month_num == 12) {
                    eth_day = g_day + 20;
                }
            } else if ((month_num == 12 && g_day >= 10) || (month_num == 1 && g_day <= 8)) {
                eth_month = eth_months[3];
                if (month_num == 12) {
                    eth_day = g_day - 9;
                } else if (month_num == 1) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 1 && g_day >= 9) || (month_num == 2 && g_day <= 7)) {
                eth_month = eth_months[4];
                if (month_num == 1) {
                    eth_day = g_day - 8;
                } else if (month_num == 2) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 2 && g_day >= 8) || (month_num == 3 && g_day <= 9)) {
                eth_month = eth_months[5];
                if (month_num == 2) {
                    eth_day = g_day - 7;
                } else if (month_num == 3) {
                    eth_day = g_day + 21;
                }
            } else if ((month_num == 3 && g_day >= 10) || (month_num == 4 && g_day <= 8)) {
                eth_month = eth_months[6];
                if (month_num == 3) {
                    eth_day = g_day - 9;
                } else if (month_num == 4) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 4 && g_day >= 9) || (month_num == 5 && g_day <= 8)) {
                eth_month = eth_months[7];
                if (month_num == 4) {
                    eth_day = g_day - 8;
                } else if (month_num == 5) {
                    eth_day = g_day + 22;
                }
            } else if ((month_num == 5 && g_day >= 9) || (month_num == 6 && g_day <= 7)) {
                eth_month = eth_months[8];
                if (month_num == 5) {
                    eth_day = g_day - 8;
                } else if (month_num == 6) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 6 && g_day >= 8) || (month_num == 7 && g_day <= 7)) {
                eth_month = eth_months[9];
                if (month_num == 6) {
                    eth_day = g_day - 7;
                } else if (month_num == 7) {
                    eth_day = g_day + 23;
                }
            } else if ((month_num == 7 && g_day >= 8) || (month_num == 8 && g_day <= 6)) {
                eth_month = eth_months[10];
                if (month_num == 7) {
                    eth_day = g_day - 7;
                } else if (month_num == 8) {
                    eth_day = g_day + 24;
                }
            } else if ((month_num == 8 && g_day >= 7) || (month_num == 9 && g_day <= 5)) {
                eth_month = eth_months[11];
                if (month_num == 8) {
                    eth_day = g_day - 6;
                } else if (month_num == 9) {
                    eth_day = g_day + 25;
                }
            } else if ((month_num == 9 && g_day >= 6) || (month_num == 9 && g_day <= 11)) {
                eth_month = eth_months[12];
                eth_day = g_day - 5;
            }
        }

        String converted_eth_date = eth_day + "-" + eth_month + "-" + eth_year;
        return converted_eth_date;
    }

    ///////////////////////a method to calculate the difference b/n two ethiopian dates
    public static int dateDifference(String date1, String date2) {
        ///////////////////////to calculate the differnce b/n two gregorian calendar after converting to ethiopian calendar dates
//        String eth_date1 = dateConvertor(date1);
//        String eth_date2 = dateConvertor(date2);
        ///////////////////////to calculate the differnce b/n two ethiopian calendar dates directly
        String eth_date1 = date1;
        String eth_date2 = date2;
        String[] eth_date1_comp = dateMonthYear(eth_date1);
        String[] eth_date2_comp = dateMonthYear(eth_date2);

        int numberOfRemiaingDays = 0;
        int numberOfDaysInBetween = 0;
        int totalMonthsInBetween = 0;
        int totalDaysInBetween = 0;
        int totalNoOfDays = 0;
        
        ///////////////////////the total number of days when the two interval falls in the same year and in different years
        if (eth_date2_comp[3].equals(eth_date1_comp[3])) {

            totalMonthsInBetween = (Integer.parseInt(eth_date2_comp[2]) - Integer.parseInt(eth_date1_comp[2]) + 1);

            ///////////////////////total number of days b/n the two months of the given dates
            if (totalMonthsInBetween < 13) {
                totalDaysInBetween = totalMonthsInBetween * 30;
            } else if (totalMonthsInBetween == 13 && Integer.parseInt(eth_date2_comp[3]) % 4 != 0) {
                totalDaysInBetween = ((totalMonthsInBetween - 1) * 30) + 5;
            } else if (totalMonthsInBetween == 13 && Integer.parseInt(eth_date2_comp[3]) % 4 == 0) {
                totalDaysInBetween = ((totalMonthsInBetween - 1) * 30) + 6;
            }

            ///////////////////////total number of days exactly b/n the given dates
            if (Integer.parseInt(eth_date1_comp[2]) > Integer.parseInt(eth_date2_comp[2])) {
                if (Integer.parseInt(eth_date1_comp[2]) < 13) {
                    numberOfRemiaingDays = 30 - Integer.parseInt(eth_date1_comp[0]);
                } else if (Integer.parseInt(eth_date1_comp[2]) == 13 && Integer.parseInt(eth_date1_comp[2]) % 4 == 0) {
                    numberOfRemiaingDays = 6 - Integer.parseInt(eth_date1_comp[0]);
                } else if (Integer.parseInt(eth_date1_comp[2]) == 13 && Integer.parseInt(eth_date1_comp[2]) % 4 != 0) {
                    numberOfRemiaingDays = 5 - Integer.parseInt(eth_date1_comp[0]);
                }
                totalNoOfDays = totalDaysInBetween - Integer.parseInt(eth_date2_comp[0]) - numberOfRemiaingDays;

            } else {
                if (Integer.parseInt(eth_date2_comp[2]) < 13) {
                    numberOfRemiaingDays = 30 - Integer.parseInt(eth_date2_comp[0]);
                } else if (Integer.parseInt(eth_date2_comp[2]) == 13 && Integer.parseInt(eth_date2_comp[2]) % 4 == 0) {
                    numberOfRemiaingDays = 6 - Integer.parseInt(eth_date2_comp[0]);
                } else if (Integer.parseInt(eth_date2_comp[2]) == 13 && Integer.parseInt(eth_date2_comp[2]) % 4 != 0) {
                    numberOfRemiaingDays = 5 - Integer.parseInt(eth_date2_comp[0]);
                }
                totalNoOfDays = totalDaysInBetween - Integer.parseInt(eth_date1_comp[0]) - numberOfRemiaingDays;
            }
        } else {
            int totalNoOfDaysOfFullYears = 0;
            int yearGap = (Integer.parseInt(eth_date2_comp[3]) - Integer.parseInt(eth_date1_comp[3])) + 1;
            int[] totalFullYears = new int[yearGap - 2];
            for (int i = Integer.parseInt(eth_date1_comp[3]) + 1, j = 0; i < Integer.parseInt(eth_date2_comp[3]); i++, j++) {
                totalFullYears[j] = i;
            }
            for (int j = 0; j < totalFullYears.length; j++) {
                if (totalFullYears[j] % 4 == 0) {
                    totalNoOfDaysOfFullYears += 366;
                } else if (totalFullYears[j] % 4 != 0) {
                    totalNoOfDaysOfFullYears += 365;
                }
            }
            ///////////////////////number of days on the first date given
            int monthOfDate1 = Integer.parseInt(eth_date1_comp[2]);
            int numberOfMonthsOffirstDateGiven = 12 - monthOfDate1;
            int numberOfDaysOffirstDateGiven = 0;
            if (Integer.parseInt(eth_date1_comp[3]) % 4 == 0) {
                numberOfDaysOffirstDateGiven = numberOfMonthsOffirstDateGiven * 30 + 6 + (30 - Integer.parseInt(eth_date1_comp[0]) + 1);
            } else if (Integer.parseInt(eth_date1_comp[3]) % 4 != 0) {
                numberOfDaysOffirstDateGiven = numberOfMonthsOffirstDateGiven * 30 + 5 + (30 - Integer.parseInt(eth_date1_comp[0]) + 1);
            }

            ///////////////////////number of days on the second date given
            int monthOfDate2 = Integer.parseInt(eth_date2_comp[2]);
            int numberOfDaysOfSecondDateGiven = 0;
            if (Integer.parseInt(eth_date2_comp[3]) % 4 == 0) {
                numberOfDaysOfSecondDateGiven = ((monthOfDate2 - 1) * 30) + Integer.parseInt(eth_date2_comp[0]);
            } else if (Integer.parseInt(eth_date2_comp[3]) % 4 != 0) {
                numberOfDaysOfSecondDateGiven = ((monthOfDate2 - 1) * 30) + Integer.parseInt(eth_date2_comp[0]);
            }

            totalNoOfDays = totalNoOfDaysOfFullYears + numberOfDaysOffirstDateGiven + numberOfDaysOfSecondDateGiven;
            //return totalNoOfDays;
        }

//        System.out.println("year beginning - " + eth_date1);
//        System.out.println("year ending - " + eth_date2);
//        System.out.println("-------------------");
        return totalNoOfDays;
    }

    public static String[] dateMonthYear(String date) {
        ///////////////////////extracting day, month and year from the string given
        int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < date.length(); i++, j++) {
            if (date.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        String day = date.substring(0, separator[0]);
        String month = date.substring(separator[0] + 1, separator[1]);
        String year = date.substring(separator[1] + 1);
        String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
        String month_number = "";
        for (int j = 0; j < eth_months.length; j++) {
            if (month.equalsIgnoreCase(eth_months[j])) {
                month_number = String.valueOf(j + 1);
            }
        }
        String[] date_components = new String[]{day, month, month_number, year};
        return date_components;
    }

    public static String dateConvertor_ethtogrig(String x) {

        ///////////////////////extracting day, month and year from the string given

        int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < x.length(); i++, j++) {
            if (x.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        int eth_day = Integer.parseInt(x.substring(0, separator[0]));
        String eth_month = x.substring(separator[0] + 1, separator[1]);
        int eth_year = Integer.parseInt(x.substring(separator[1] + 1));

        ///////////////////////returning the month in number

        int month_num = 0;
        String[] g_months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
        for (int i = 0; i < eth_months.length; i++) {
            if (eth_month.equalsIgnoreCase(eth_months[i])) {
                month_num = i + 1;
            }
        }

        ///////////////////////converting the Ethiopian year to Grigorian year

        int g_year = 0;
        if ((month_num > 4 || (month_num == 4 && eth_day >= 23)) && eth_year % 4 != 0) {
            g_year = eth_year + 8;
        } else if ((month_num < 4 || (month_num == 4 && eth_day < 23)) && eth_year % 4 != 0) {
            g_year = eth_year + 7;
        } else if ((month_num > 4 || (month_num == 4 && eth_day >= 22)) && eth_year % 4 == 0) {
            g_year = eth_year + 8;
        } else if ((month_num < 4 || (month_num == 4 && eth_day < 22)) && eth_year % 4 == 0) {
            g_year = eth_year + 7;
        }
        //System.out.println(g_year % 4 + " test");

        ///////////////////////converting the month to Ethiopian calendar
        String g_month = "";
        int g_month_num = 0;
        int g_day = 0;

        if (eth_year % 4 == 0) {
            if ((month_num == 4 && eth_day >= 22) || (month_num == 5 && eth_day <= 22)) {//January
                g_month = g_months[0];
                if (month_num == 4) {
                    g_day = eth_day - 21;
                } else if (month_num == 5) {
                    g_day = eth_day + 9;
                }
            } else if ((month_num == 5 && eth_day >= 23) || (month_num == 6 && eth_day <= 21)) {//February
                g_month = g_months[1];
                if (month_num == 5) {
                    g_day = eth_day - 22;
                } else if (month_num == 6) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 6 && eth_day >= 22) || (month_num == 7 && eth_day <= 22)) {//March
                g_month = g_months[2];
                if (month_num == 6) {
                    g_day = eth_day - 21;
                } else if (month_num == 7) {
                    g_day = eth_day + 9;
                }
            } else if ((month_num == 7 && eth_day >= 23) || (month_num == 8 && eth_day <= 22)) {//April
                g_month = g_months[3];
                if (month_num == 7) {
                    g_day = eth_day - 22;
                } else if (month_num == 8) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 8 && eth_day >= 23) || (month_num == 9 && eth_day <= 23)) {//May
                g_month = g_months[4];
                if (month_num == 8) {
                    g_day = eth_day - 22;
                } else if (month_num == 9) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 9 && eth_day >= 24) || (month_num == 10 && eth_day <= 23)) {//June
                g_month = g_months[5];
                if (month_num == 9) {
                    g_day = eth_day - 23;
                } else if (month_num == 10) {
                    g_day = eth_day + 7;
                }
            } else if ((month_num == 10 && eth_day >= 24) || (month_num == 11 && eth_day <= 24)) {//July
                g_month = g_months[6];
                if (month_num == 10) {
                    g_day = eth_day - 23;
                } else if (month_num == 11) {
                    g_day = eth_day + 7;
                }
            } else if ((month_num == 11 && eth_day >= 25) || (month_num == 12 && eth_day <= 25)) {//Auguest
                g_month = g_months[7];
                if (month_num == 11) {
                    g_day = eth_day - 24;
                } else if (month_num == 12) {
                    g_day = eth_day + 6;
                }
            } else if ((month_num == 12 && eth_day >= 26) || month_num == 13 || (month_num == 1 && eth_day <= 19)) {//September
                g_month = g_months[8];
                if (month_num == 12) {
                    g_day = eth_day - 25;
                } else if (month_num == 13) {
                    g_day = eth_day + 5;
                } else if (month_num == 1) {
                    g_day = eth_day + 11;
                }
            } else if ((month_num == 1 && eth_day >= 20) || (month_num == 2 && eth_day <= 20)) {//October
                g_month = g_months[9];
                if (month_num == 1) {
                    g_day = eth_day - 19;
                } else if (month_num == 2) {
                    g_day = eth_day + 11;
                }
            } else if ((month_num == 2 && eth_day >= 21) || (month_num == 3 && eth_day <= 20)) {//November
                g_month = g_months[10];
                if (month_num == 2) {
                    g_day = eth_day - 20;
                } else if (month_num == 3) {
                    g_day = eth_day + 10;
                }
            } else if ((month_num == 3 && eth_day >= 21) || (month_num == 4 && eth_day <= 21)) {//December
                g_month = g_months[11];
                if (month_num == 3) {
                    g_day = eth_day - 20;
                } else if (month_num == 4) {
                    g_day = eth_day + 10;
                }
            }
        } else {
            if ((month_num == 4 && eth_day >= 23) || (month_num == 5 && eth_day <= 23)) {//January
                g_month = g_months[0];
                if (month_num == 4) {
                    g_day = eth_day - 21;
                } else if (month_num == 5) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 5 && eth_day >= 24) || (month_num == 6 && eth_day <= 21)) {//February
                g_month = g_months[1];
                if (month_num == 5) {
                    g_day = eth_day - 22;
                } else if (month_num == 6) {
                    g_day = eth_day + 7;
                }
            } else if ((month_num == 6 && eth_day >= 22) || (month_num == 7 && eth_day <= 22)) {//March
                g_month = g_months[2];
                if (month_num == 6) {
                    g_day = eth_day - 21;
                } else if (month_num == 7) {
                    g_day = eth_day + 9;
                }
            } else if ((month_num == 7 && eth_day >= 23) || (month_num == 8 && eth_day <= 22)) {//April
                g_month = g_months[3];
                if (month_num == 7) {
                    g_day = eth_day - 22;
                } else if (month_num == 8) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 8 && eth_day >= 23) || (month_num == 9 && eth_day <= 23)) {//May
                g_month = g_months[4];
                if (month_num == 8) {
                    g_day = eth_day - 22;
                } else if (month_num == 9) {
                    g_day = eth_day + 8;
                }
            } else if ((month_num == 9 && eth_day >= 24) || (month_num == 10 && eth_day <= 23)) {//June
                g_month = g_months[5];
                if (month_num == 9) {
                    g_day = eth_day - 23;
                } else if (month_num == 10) {
                    g_day = eth_day + 7;
                }
            } else if ((month_num == 10 && eth_day >= 24) || (month_num == 11 && eth_day <= 24)) {//July
                g_month = g_months[6];
                if (month_num == 10) {
                    g_day = eth_day - 23;
                } else if (month_num == 11) {
                    g_day = eth_day + 7;
                }
            } else if ((month_num == 11 && eth_day >= 25) || (month_num == 12 && eth_day <= 25)) {//Auguest
                g_month = g_months[7];
                if (month_num == 11) {
                    g_day = eth_day - 24;
                } else if (month_num == 12) {
                    g_day = eth_day + 6;
                }
            } else if ((month_num == 12 && eth_day >= 26) || month_num == 13 || (month_num == 1 && eth_day <= 20)) {//September
                g_month = g_months[8];
                if (month_num == 12) {
                    g_day = eth_day - 25;
                } else if (month_num == 13) {
                    g_day = eth_day + 5;
                } else if (month_num == 1) {
                    g_day = eth_day + 11;
                }
            } else if ((month_num == 1 && eth_day >= 21) || (month_num == 2 && eth_day <= 21)) {//October
                g_month = g_months[9];
                if (month_num == 1) {
                    g_day = eth_day - 20;
                } else if (month_num == 2) {
                    g_day = eth_day + 10;
                }
            } else if ((month_num == 2 && eth_day >= 22) || (month_num == 3 && eth_day <= 21)) {//November
                g_month = g_months[10];
                if (month_num == 2) {
                    g_day = eth_day - 21;
                } else if (month_num == 3) {
                    g_day = eth_day + 9;
                }
            } else if ((month_num == 3 && eth_day >= 22) || (month_num == 4 && eth_day <= 22)) {//December
                g_month = g_months[11];
                if (month_num == 3) {
                    g_day = eth_day - 21;
                } else if (month_num == 4) {
                    g_day = eth_day + 9;
                }
            }
        }

        String converted_g_date = g_day + "-" + g_month + "-" + g_year;
        return converted_g_date;
    }
    
    public static String returnConvertedEthiopianYear(String gregDate){
    	
    	int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < gregDate.length(); i++, j++) {
            if (gregDate.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        int g_year = Integer.parseInt(gregDate.substring(0, separator[0]));
        String g_month = gregDate.substring(separator[0] + 1, separator[1]);
        int g_day  = Integer.parseInt(gregDate.substring(separator[1] + 1));
        
        ///////////////////////returning the month in number
        int month_num = 0;
        String[] g_months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
        for (int i = 0; i < g_months.length; i++) {
            if (g_month.equalsIgnoreCase(g_months[i])) {
                month_num = i + 1;
            }
        }
        //////// if month parameter is in number
        if(month_num == 0){
        	month_num = Integer.parseInt(g_month)+1;
        }
        if(month_num == 13){
        	month_num = month_num - 1;
        }
        
        ///////////////////////converting the gregorian year to Ethiopian year
        int eth_year = 0;
        if ((month_num < 9 || (month_num == 9 && g_day < 11)) && g_year % 4 != 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 11)) && g_year % 4 != 0) {
            eth_year = g_year - 7;
        } else if ((month_num < 9 || (month_num == 9 && g_day < 12)) && g_year % 4 == 0) {
            eth_year = g_year - 8;
        } else if ((month_num >= 9 || (month_num == 9 && g_day >= 12)) && g_year % 4 == 0) {
            eth_year = g_year - 7;
        }
        
        return String.valueOf(eth_year);
    }

}

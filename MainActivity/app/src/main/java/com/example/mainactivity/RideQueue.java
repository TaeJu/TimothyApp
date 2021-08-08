package com.example.mainactivity;

import java.util.*;

public class RideQueue {
    public static int count = 0;
    public static boolean found = false;
    private String[] array1 = new String[4];
    private String[] array2 = new String[4];
    public ArrayList<String[]> al = new ArrayList<String[]>();

    public ArrayList<String[]> getAl() {
        return al;
    }

    public void setAl(ArrayList<String[]> al) {
        this.al = al;
    }

    public void updateOG() {
        al.add(array1);
        al.add(array2);
    }

    public void update() {
        if(al.size() >= 2) {
        } else {
            al.add(array1);
            al.add(array2);
            count = 0;
        }
    }

    public void printAll() {
        for(int i=0; i< al.size(); i++){
            for(int j=0; j < al.get(i).length; j++) {
                System.out.println(al.get(i)[j]);
            }
        }
    }

    public void fill() {
        for(int i=0; i < al.size(); i++) {
            for(int j=0; j < al.get(i).length; j++) {
                al.get(i)[j] = "";
            }
        }
    }

    public void fill2() {
        for(int i=0; i < al.size(); i++) {
            for(int j=0; j < al.get(i).length; j++) {
                if(al.get(i)[j] == null) {
                    al.get(i)[j] = "";
                }
            }
        }
    }
    public boolean checkNull() {
        for(int i=0; i< al.size(); i++){
            for(int j=0; j < al.get(i).length; j++) {
                if(al.get(i)[j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clear() {
        for(int i=0; i < al.size(); i++) {
            for(int j=0; j < al.get(i).length; j++) {
                al.get(i)[j] = null;
            }
        }
    }

    public void clearAL() {
        for(int i=0; i < al.size(); i++) {
            al.remove(0);
        }
    }

    public void cancelRide(String userName) {
        for(int i=0; i < al.size(); i++) {
            for(int j=0; j < al.get(i).length; j++) {
                if(al.get(i)[j].equals(userName)) {
                    al.get(i)[j] = "";
                }
            }
        }
    }

    public String[] request() {
        String n1 = al.get(count)[0];
        String n2 = al.get(count)[1];
        String n3 = al.get(count)[2];
        String n4 = al.get(count)[3];
        count = count + 1;
        String[] a1 = new String[4];
        a1[0] = n1;
        a1[1] = n2;
        a1[2] = n3;
        a1[3] = n4;
        return a1;
    }

    public String[] request2(int requestNum) {
        String n1 = al.get(requestNum)[0];
        String n2 = al.get(requestNum)[1];
        String n3 = al.get(requestNum)[2];
        String n4 = al.get(requestNum)[3];
        String[] a1 = new String[4];
        a1[0] = n1;
        a1[1] = n2;
        a1[2] = n3;
        a1[3] = n4;
        return a1;
    }

    public void addIn(String userName) {
        for(int i=0; i < al.size() && found == false; i++) {
            for(int j=0; j < al.get(i).length; j++) {
                // int k=0;
                if (al.get(i)[j] == "") {
                    al.get(i)[j] = userName;
                    found = true;
                    break;
                }
            }
        }
    }
}
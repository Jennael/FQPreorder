package com.example.meimei.fqpreorder;

public class CountCheck {

    private int preorder_limit;
    private int database_count;

    public CountCheck(String count){
        preorder_limit = Integer.parseInt(count);
    }

    private void retrieval(){
        //TODO retrieve database_count from database
        database_count = 0;
    }

    public Boolean stopPreorder() {
        retrieval();
        if (database_count >= preorder_limit){
            return true;
        }
        return false;
    }

    public int getCurrent(){
        retrieval();
        return database_count;
    }

}
